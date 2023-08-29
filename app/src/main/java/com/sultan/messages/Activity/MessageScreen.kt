package com.sultan.messages.Activity

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sultan.messages.R
import com.sultan.messages.data.BusNumber
import com.sultan.messages.data.DataProvider
import com.sultan.messages.data.Message
import com.sultan.messages.recyclerview.BusListItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageScreen() {
    val systemUiController = rememberSystemUiController()

    if (isSystemInDarkTheme()) {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }

    TopAppBar(title = { Text(text = "9909") },
        navigationIcon = {
            Row {
                Icon(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Icon(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    text = "9909",
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(Modifier.weight(1f))

                Icon(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp),
                    imageVector = Icons.Filled.Call,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White )

    )
    writeMessage()
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun writeMessage() {
    var isClicked by remember { mutableStateOf(false) }
    val message = remember { mutableStateOf("") }

    val currentDateTime = remember { LocalDateTime.now() }
    val monthDay = currentDateTime.dayOfMonth
    val monthValue = currentDateTime.monthValue
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val time = currentDateTime.format(timeFormatter).toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp, top = 60.dp),
        verticalArrangement = Arrangement.Bottom

    ) {
        Row {
            Image(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .padding(top = 10.dp),
                painter = painterResource(id = R.drawable.point),
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .padding(top = 10.dp),
                painter = painterResource(id = R.drawable.baseline_image_search_24),
                contentDescription = null
            )

            TextField(
                value = message.value,
                onValueChange = {
                    message.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                placeholder = { Text(text = "Message text") },
                shape = RoundedCornerShape(40.dp),
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.emoji),
                            contentDescription = null,
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .padding(end = 10.dp),
                            tint = Color(0xFF555555)
                        )
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = null,
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .padding(end = 10.dp)
                                .clickable {
                                    isClicked = true
                                    Log.d("yyyyy", isClicked.toString())
                                },
                            tint = Color(0xFF555555)
                        )
                    }

                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )

            )

        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp, top = 60.dp)
    ) {
        if (isClicked) {
           // val mes = BusNumber(message.value, time,"63HH78","http://qr.tha.kz/135C1")
            val mes = DataProvider.busList
            LazyColumn {
                items(mes) {
                    BusListItem(bus = it)
                }
            }

        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PrevMessageScreen() {
    MessageScreen()
}