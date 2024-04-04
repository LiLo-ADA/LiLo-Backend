package com.example.data.table

import com.example.model.response.locker.Locker
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object LockerTable: Table("locker") {
    val id = uuid("id").autoGenerate()
    val area = varchar("area", length = 20)
    val number = integer("number")
    val status = integer("status")
    val reported = bool("reported")
    val password = varchar("password", length = 128).nullable()
    val email = varchar("email", length = 64).references(UserTable.email).nullable()

    override val primaryKey = PrimaryKey(id)

    fun ResultRow.toLocker(): Locker {
        return Locker(
            id = this[id].toString(),
            area = this[area],
            number = this[number],
            status = this[status],
            reported = this[reported],
            password = this[password]
        )
    }
}