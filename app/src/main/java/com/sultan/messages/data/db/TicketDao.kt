package com.sultan.messages.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sultan.messages.data.Tickets
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Query("SELECT * FROM tickets_table")
    fun getAllTickets(): Flow<List<Tickets>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: Tickets)

    @Delete
    suspend fun deleteTicket(ticket: Tickets)

}