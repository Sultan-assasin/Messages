package com.sultan.messages.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.painter.Painter

data class Message(
    val title: String,
    val messageInfo: String,
    val date: String,
    @DrawableRes val picture: Int
) {
    fun doesMatchSearchQuery(query: String): Boolean {


        val matchingCombinations = listOf(
            "$title $messageInfo",
            "$title$messageInfo",
            "${title.first()} ${messageInfo.first()} ",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}