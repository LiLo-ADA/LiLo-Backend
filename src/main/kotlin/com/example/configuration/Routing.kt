package com.example.configuration

import com.example.data.api.AuthApi.authenticate
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/user") {
            authenticate("/authenticate")
        }
    }
}
