package com.example.data.table

import com.example.model.response.locker.Locker
import com.example.model.response.user.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object LockerTable: Table("locker") {
    val id = uuid("id").autoGenerate()
    val area = varchar("area", length = 20)
    val number = integer("number")
    val status = integer("status")
    val reportCount = integer("reportCount")
    val email = varchar("email", length = 64).references(UserTable.email).nullable()

    override val primaryKey = PrimaryKey(id)

    fun ResultRow.toLocker(
        user: User
    ): Locker {
        return Locker(
            id = this[id].toString(),
            area = this[area],
            number = this[number],
            status = this[status],
            reportCount = this[reportCount],
            user = user
        )
    }
}