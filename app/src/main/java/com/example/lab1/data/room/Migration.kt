package com.example.lab1.data.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration1 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("create table PaymentEntity(id text not null, total real not null, primary key(id))")
    }
}