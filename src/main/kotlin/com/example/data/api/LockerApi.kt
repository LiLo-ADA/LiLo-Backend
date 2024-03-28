package com.example.data.api

import com.example.data.table.LockerTable
import com.example.data.table.UserTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object LockerApi: KoinComponent {
    private val userTable by inject<UserTable>()
    private val lockerTable by inject<LockerTable>()

    fun Route.getLockers(path: String) {
        get(path) {
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@get call.respondText(
                "Unknown Error",
                status = HttpStatusCode.InternalServerError
            )


        }
    }
}