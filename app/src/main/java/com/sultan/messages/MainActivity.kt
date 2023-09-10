package com.sultan.messages

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.sultan.messages.data.db.TicketsDatabase
import com.sultan.messages.navigation.AppNavigation
import com.sultan.messages.viewModel.TicketsViewModel

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}


