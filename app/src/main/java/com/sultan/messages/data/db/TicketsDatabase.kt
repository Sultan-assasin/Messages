package com.sultan.messages.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sultan.messages.data.Tickets

@Database(entities = [Tickets::class], version = 2 , exportSchema = true)
abstract class TicketsDatabase : RoomDatabase() {
    abstract val ticketDao: TicketDao
    companion object {
        fun getDatabaseInstance(context: Context) : TicketsDatabase{
            return Room.databaseBuilder(
                context,
                TicketsDatabase::class.java,
                "tickets_db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }

}