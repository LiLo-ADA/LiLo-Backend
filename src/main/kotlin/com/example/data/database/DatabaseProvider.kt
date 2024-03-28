package com.example.data.database

import com.example.data.table.LockerTable
import com.example.data.table.UserTable
import com.example.utils.config.Config
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DatabaseProvider: KoinComponent {
    private val config by inject<Config>()
    private val userTable by inject<UserTable>()
    private val lockerTable by inject<LockerTable>()

    fun init(){
        val database = Database.connect(
            url = config.jdbcUrl,
            user = config.dbUsername,
            driver = "org.postgresql.Driver",
            password = config.dbPassword
        )

        transaction(database) {
            SchemaUtils.create(userTable)
            SchemaUtils.create(lockerTable)
        }
    }
}