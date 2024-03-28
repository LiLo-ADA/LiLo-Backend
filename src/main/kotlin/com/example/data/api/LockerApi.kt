package com.example.data.api

import com.example.data.table.LockerTable
import com.example.data.table.LockerTable.toLocker
import com.example.model.base.BaseResponse
import com.example.model.base.MetaResponse
import com.example.model.request.locker.AreaRequest
import com.example.model.request.locker.LockInRequest
import com.example.model.request.locker.LockerIdRequest
import com.example.utils.query.dbQuery
import com.example.utils.response.respondGeneral
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

object LockerApi: KoinComponent {
    private val lockerTable by inject<LockerTable>()

    fun Route.getLockersByArea(path: String) {
        post(path) {
            val request = call.receive<AreaRequest>()
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@post call.respondText(
                "Unknown Error",
                status = HttpStatusCode.InternalServerError
            )

            dbQuery {
                lockerTable
                    .select {
                        lockerTable.area eq request.area
                    }
                    .map {
                        it.toLocker()
                    }
            }.let {
                call.respond(
                    HttpStatusCode.OK,
                    BaseResponse(
                        meta = MetaResponse(
                            success = true,
                            message = "Get Lockers Success"
                        ),
                        data = it
                    )
                )
            }
        }
    }

    fun Route.lockIn(path: String) {
        post(path) {
            val request = call.receive<LockInRequest>()
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@post call.respondText(
                "Unknown Error",
                status = HttpStatusCode.InternalServerError
            )

            // check if email already using locker
            dbQuery {
                lockerTable
                    .select {
                        lockerTable.email eq email
                    }
                    .count()
            }.let { count ->
                if (count > 0) {
                    respondGeneral(
                        success = false,
                        message = "You already using a locker",
                        code = HttpStatusCode.Conflict
                    )

                    return@post
                }
            }

            // save locker
            dbQuery {
                lockerTable
                    .update(
                        { lockerTable.id eq UUID.fromString(request.id) }
                    ) {
                        it[status] = 2
                        it[password] = request.password
                        it[lockerTable.email] = email
                    }
            }.let {
                call.respond(
                    HttpStatusCode.OK,
                    BaseResponse(
                        meta = MetaResponse(
                            success = true,
                            message = "Lock In Success"
                        ),
                        data = null
                    )
                )
            }
        }
    }

    fun Route.lockOut(path: String) {
        post(path) {
            val request = call.receive<LockerIdRequest>()
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@post call.respondText(
                "Unknown Error",
                status = HttpStatusCode.InternalServerError
            )

            // check if email already using locker
            dbQuery {
                lockerTable
                    .select {
                        lockerTable.email eq email
                    }
                    .count()
            }.let { count ->
                if (count <= 0) {
                    respondGeneral(
                        success = false,
                        message = "You don't use any locker locker",
                        code = HttpStatusCode.Conflict
                    )

                    return@post
                }
            }

            // save locker
            dbQuery {
                lockerTable
                    .update(
                        { lockerTable.id eq UUID.fromString(request.id) }
                    ) {
                        it[status] = 1
                        it[password] = null
                        it[lockerTable.email] = null
                    }
            }.let {
                call.respond(
                    HttpStatusCode.OK,
                    BaseResponse(
                        meta = MetaResponse(
                            success = true,
                            message = "Lock Out Success"
                        ),
                        data = null
                    )
                )
            }
        }
    }

    fun Route.getLockerByEmail(path: String) {
        get(path) {
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@get call.respondText(
                "Unknown Error",
                status = HttpStatusCode.InternalServerError
            )

            // save locker
            dbQuery {
                lockerTable
                    .select  {
                        lockerTable.email eq email
                    }
                    .singleOrNull()
            }.let {
                call.respond(
                    HttpStatusCode.OK,
                    BaseResponse(
                        meta = MetaResponse(
                            success = it != null,
                            message = if (it != null) "Get Locker Success"
                            else "You don't have any locker used"
                        ),
                        data = it?.toLocker()
                    )
                )
            }
        }
    }

    fun Route.reportFilled(path: String) {
        post(path) {
            val request = call.receive<LockerIdRequest>()
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@post call.respondText(
                "Unknown Error",
                status = HttpStatusCode.InternalServerError
            )

            // save locker
            dbQuery {
                lockerTable.select {
                    lockerTable.id eq UUID.fromString(request.id)
                }.singleOrNull()
                    ?.toLocker()
            }.let { locker ->
                if (locker == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        BaseResponse(
                            meta = MetaResponse(
                                success = false,
                                message = "Locker not found"
                            ),
                            data = null
                        )
                    )

                    return@post
                }

                dbQuery {
                    lockerTable
                        .update(
                            { lockerTable.id eq UUID.fromString(request.id) }
                        ) {
                            it[reportCount] = locker.reportCount + 1

                            if (locker.reportCount + 1 == 3) {
                                it[status] = 2
                            }
                        }
                }.let {
                    call.respond(
                        HttpStatusCode.OK,
                        BaseResponse(
                            meta = MetaResponse(
                                success = true,
                                message = "Report Success"
                            ),
                            data = null
                        )
                    )
                }
            }
        }
    }

    fun Route.reportEmpty(path: String) {
        post(path) {
            val request = call.receive<LockerIdRequest>()
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@post call.respondText(
                "Unknown Error",
                status = HttpStatusCode.InternalServerError
            )

            // save locker
            dbQuery {
                lockerTable.select {
                    lockerTable.id eq UUID.fromString(request.id)
                }.singleOrNull()
                    ?.toLocker()
            }.let { locker ->
                if (locker == null) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        BaseResponse(
                            meta = MetaResponse(
                                success = false,
                                message = "Locker not found"
                            ),
                            data = null
                        )
                    )

                    return@post
                }

                dbQuery {
                    lockerTable
                        .update(
                            { lockerTable.id eq UUID.fromString(request.id) }
                        ) {
                            it[reportCount] = locker.reportCount - 1

                            if (locker.reportCount - 1 == -3) {
                                it[status] = 1
                            }
                        }
                }.let {
                    call.respond(
                        HttpStatusCode.OK,
                        BaseResponse(
                            meta = MetaResponse(
                                success = true,
                                message = "Report Success"
                            ),
                            data = null
                        )
                    )
                }
            }
        }
    }
}