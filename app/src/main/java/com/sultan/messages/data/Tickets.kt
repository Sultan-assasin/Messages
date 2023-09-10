package com.sultan.messages.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickets_table")
data class Tickets(
    @PrimaryKey(autoGenerate = true)
    val numberId : Int? = null,
    val number: String,
    val time: String = "10:00",
    val randomSymbols: String = "randomSymbols",
    val randomQr: String = "randomQr"
)