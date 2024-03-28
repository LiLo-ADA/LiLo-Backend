package com.example.utils.manager

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.example.utils.config.Config
import io.ktor.server.auth.jwt.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object TokenManager: KoinComponent {
    private val config by inject<Config>()

    private val verifier: JWTVerifier = JWT
        .require(Algorithm.HMAC256(config.jwtSecret))
        .withAudience(config.jwtAudience)
        .withIssuer(config.jwtIssuer)
        .build()

    fun generateToken(email: String): String {
        return JWT.create()
            .withAudience(config.jwtAudience)
            .withIssuer(config.jwtIssuer)
            .withClaim("email", email)
            .sign(Algorithm.HMAC256(config.jwtSecret))
    }

    fun configureJwt(conf: JWTAuthenticationProvider.Config) = with(conf) {
        realm = config.jwtRealm
        verifier(verifier)
        validate { credential ->
            if (credential.payload.audience.contains(config.jwtAudience)) JWTPrincipal(credential.payload) else null
        }
    }
}