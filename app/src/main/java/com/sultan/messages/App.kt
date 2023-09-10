package com.sultan.messages

import android.app.Application
import com.sultan.messages.data.db.TicketsDatabase

class App : Application() {
    val database by lazy { TicketsDatabase.getDatabaseInstance(this) }
    // if a database created we a get access or not we create a new database
}