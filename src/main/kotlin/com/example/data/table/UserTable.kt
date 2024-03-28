package com.example.data.table

import com.example.model.response.user.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object UserTable: Table("user") {
    val email = varchar("email", length = 64)
    val session = integer("session")

    override val primaryKey = PrimaryKey(email)

    fun ResultRow.toUser(): User {
        return User(
            email = this[email],
            session = this[session]
        )
    }
}