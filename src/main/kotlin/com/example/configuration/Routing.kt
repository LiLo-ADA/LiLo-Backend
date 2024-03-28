package com.example.configuration

import com.example.data.api.AuthApi.authenticate
import com.example.data.api.LockerApi.getLockerByEmail
import com.example.data.api.LockerApi.getLockersByArea
import com.example.data.api.LockerApi.lockIn
import com.example.data.api.LockerApi.lockOut
import com.example.data.api.LockerApi.reportEmpty
import com.example.data.api.LockerApi.reportFilled
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/user") {
            authenticate("/authenticate")
        }

        route("/locker") {
            authenticate {
                getLockersByArea("/all")
                lockIn("/in")
                lockOut("/out")
                getLockerByEmail("/email")
                reportFilled("/filled")
                reportEmpty("/empty")
            }
        }
    }
}
