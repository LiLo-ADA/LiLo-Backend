package com.example.data.api

import com.example.data.table.UserTable
import com.example.model.base.BaseResponse
import com.example.model.base.MetaResponse
import com.example.model.request.authentication.AuthRequest
import com.example.model.response.authentication.AuthResponse
import com.example.utils.manager.TokenManager
import com.example.utils.query.dbQuery
import com.example.utils.response.respondGeneral
import com.example.utils.validation.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object AuthApi: KoinComponent {
    private val userTable by inject<UserTable>()

    fun Route.authenticate(path: String) {
        post(path) {
            val request = call.receive<AuthRequest>()

            if (!isValidEmail(request.email)) {
                respondGeneral(
                    success = false,
                    message = "Invalid email address",
                    code = HttpStatusCode.BadRequest
                )

                return@post
            }

            // check email availability
            dbQuery {
                userTable
                    .select {
                        userTable.email eq request.email
                    }
                    .count()
            }.let { count ->
                if (count > 0) {
                    dbQuery {
                        userTable.update(
                            { userTable.email eq request.email }
                        ) {
                            it[session] = request.session
                        }
                    }.let {
                        val token = TokenManager
                            .generateToken(
                                email = request.email
                            )

                        call.respond(
                            HttpStatusCode.OK,
                            BaseResponse(
                                meta = MetaResponse(
                                    success = true,
                                    message = "Authentication Success"
                                ),
                                data = AuthResponse(
                                    token = token
                                )
                            )
                        )
                    }

                    return@post
                }
            }

            dbQuery {
                userTable.insert {
                    it[email] = request.email
                    it[session] = request.session
                }[UserTable.email]
            }.let { email ->
                val token = TokenManager
                    .generateToken(
                        email = email
                    )

                call.respond(
                    HttpStatusCode.OK,
                    BaseResponse(
                        meta = MetaResponse(
                            success = true,
                            message = "Authentication Success"
                        ),
                        data = AuthResponse(
                            token = token
                        )
                    )
                )
            }
        }
    }
}