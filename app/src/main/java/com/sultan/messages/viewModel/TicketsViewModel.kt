package com.sultan.messages.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.sultan.messages.App
import com.sultan.messages.data.Tickets
import com.sultan.messages.data.db.TicketsDatabase
import kotlinx.coroutines.launch

class TicketsViewModel(
    val database: TicketsDatabase
) : ViewModel() {

    val itemList = database.ticketDao.getAllTickets()
    val text = mutableStateOf("")
    var ticket: Tickets? = null

    fun  insertItem() = viewModelScope.launch {
        val item = ticket?.copy(number = text.value) // старый элемент с новой записью
            ?: Tickets(number = text.value) // создается новый элемент с без идентификатора
        database.ticketDao.insertTicket(item)

        ticket = null
        text.value = ""
    }

    fun deleteItem(tickets: Tickets) = viewModelScope.launch {
        database.ticketDao.deleteTicket(tickets)
    }

    companion object{
        @Suppress("UNCHECKED_CAST")
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = checkNotNull(extras[APPLICATION_KEY] as App).database
                return TicketsViewModel(database) as T
            }
        }
    }
}