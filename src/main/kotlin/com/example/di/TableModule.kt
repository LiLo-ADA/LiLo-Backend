package com.example.di

import com.example.data.table.LockerTable
import com.example.data.table.UserTable
import org.koin.dsl.module

object TableModule {
    val provide = module {
        single { UserTable }
        single { LockerTable }
    }
}