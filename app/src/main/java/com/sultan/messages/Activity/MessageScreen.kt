package com.sultan.messages.Activity

import android.annotation.SuppressLint
import android.os.Build
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
import com.sultan.messages.recyclerview.BusListItem
import com.sultan.messages.viewModel.TicketsViewModel


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageScreen(viewModel: TicketsViewModel) {

    val itemList = viewModel.itemList.collectAsState(initial = emptyList())
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

    TopAppBar(
        title = { Text(text = "9909") },
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
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)

    )


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
                value = viewModel.text.value,
                onValueChange = {
                    viewModel.text.value = it
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
                                    if (viewModel.text.value.isNotEmpty()) {
                                        viewModel.insertItem()
                                    }

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
        LazyColumn {
            items(itemList.value) { item ->
                BusListItem(bus = item, {
                    viewModel.ticket = it
                    viewModel.text.value = it.number
                },
                    {
                        viewModel.deleteItem(tickets = it)
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PrevMessageScreen() {
    //MessageScreen()
}