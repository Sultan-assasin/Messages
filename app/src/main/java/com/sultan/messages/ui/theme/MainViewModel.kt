package com.sultan.messages.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.graphics.toColor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultan.messages.R
import com.sultan.messages.data.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _messages = MutableStateFlow(allMessages)
    val messages = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_messages) { text, messages ->
            if (text.isBlank()) {
                messages
            } else {
                messages.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _messages.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }


}


private val allMessages = listOf(
    Message(
        title = "My Activ",
        messageInfo = "ВНИМАНИЕ! Вы исчерпали бонусный объем звонков на Beeline, включенный в Ваш тарифный план Базовый Kazakhtel. FMS Закр. Вы не можете совершать звонки на Beeline до 00:00 1-го числа следующего месяца. Вы можете возобновить доступ к звонкам, подключив дополнительный пакет, набрав *938#. Срок действия дополнительного пакета – до конца текущего месяца.  Проверка остатка пакета услуг: *111*3#.",
        date = "Mon",
        picture = R.drawable.baseline_account_circle_24
    ),
    Message(
        title = "7852",
        messageInfo = "У вас закончились минуты или ба ...",
        date = "Mon",
        picture = R.drawable.baseline_account_circle_24
    ),
    Message(
        title = "Beeline",
        messageInfo = "Баланс ниже 100 тг. Пополните без к ...",
        date = "Mon",
        picture = R.drawable.baseline_account_circle_24
    ),
    Message(
        title = "Balance",
        messageInfo = "Ваш тариф: Базовый Rfzakhtel. FMS ... ",
        date = "Mon",
        picture = R.drawable.baseline_account_circle_24
    ),
    Message(
        title = "Nursain",
        messageInfo = "Абонент +7775241252 звонил вам ...",
        date = "Mon",
        picture = R.drawable.baseline_account_circle_24
    ),
    Message(
        title = "Timur",
        messageInfo = "Абонент +7775241252 звонил вам ...",
        date = "Mon",
        picture = R.drawable.baseline_account_circle_24
    ),Message(
        title = "9909",
        messageInfo = "Абонент +7775241252 звонил вам ...",
        date = "Mon",
        picture = R.drawable.baseline_account_circle_24
    ),

    )


