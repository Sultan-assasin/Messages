package com.sultan.messages.recyclerview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sultan.messages.data.BusNumber
import com.sultan.messages.ui.theme.Pink80
import com.sultan.messages.ui.theme.Purple80
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BusListItem(bus: BusNumber) {
    val currentDateTime = remember { LocalDateTime.now() }
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val time = currentDateTime.format(timeFormatter).toString()
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = time,
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally),
            color = Color.Gray
        )
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(end = 10.dp, top = 10.dp),
        horizontalArrangement = Arrangement.End, // Align elements to the end of the Row
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(40.dp)
                .width(100.dp)
                .background(Purple80),
            contentAlignment = Alignment.Center

        ) {
            Text(text = bus.number.toString(), fontSize = 16.sp, textDecoration = TextDecoration.Underline)
        }
    }
    showPaidMessage(number = bus)

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun showPaidMessage(number: BusNumber) {

    val currentDateTime = remember { LocalDateTime.now() }
    val monthDay = currentDateTime.dayOfMonth
    val monthValue = currentDateTime.monthValue
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val time = currentDateTime.format(timeFormatter).toString()


    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 30.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Pink80)
    ) {

        Text(text = "ONAY! ALA", Modifier.padding(top = 10.dp, start = 10.dp), fontSize = 16.sp)
        Row {
            Text(
                text = "KL ${monthDay}/0${monthValue} ",
                Modifier.padding(top = 5.dp, start = 10.dp),
                fontSize = 16.sp
            )
            Text(
                text = number.time,
                Modifier.padding(top = 5.dp),
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            )
        }

        Text(
            text = "${number.number},483LY02,100₸",
            Modifier.padding(top = 5.dp, start = 10.dp),
            fontSize = 16.sp
        )
        Text(
            text = "http://qr.tha.kz/135C1",
            Modifier.padding(top = 5.dp, start = 10.dp, bottom = 5.dp),
            textDecoration = TextDecoration.Underline,
            fontSize = 16.sp
        )
        Column(
            modifier = Modifier
                .background(Color.White)
                .height(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = "Нажмите, что бы просмотреть", color = Color.Gray)

        }
    }

}

