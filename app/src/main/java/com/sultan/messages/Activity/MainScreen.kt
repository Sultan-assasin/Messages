package com.sultan.messages.Activity

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sultan.messages.R
import com.sultan.messages.navigation.Screens
import com.sultan.messages.ui.theme.MainViewModel
import com.sultan.messages.ui.theme.MessagesTheme

@Composable
fun MainScreen(navController: NavController)  {
    MessagesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(navController: NavController) {

    val viewModel = viewModel<MainViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val messages by viewModel.messages.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            },
            placeholder = { Text(text = "Search Links") },
            shape = RoundedCornerShape(40.dp),
            maxLines = 1,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .padding(end = 10.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )



        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(messages) { message ->
                showMessage(
                    message,
                    navController
                )

            }
        }


    }

}

@Composable
fun showMessage(message: com.sultan.messages.data.Message, navController: NavController) {

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .height(50.dp)
        .clickable {
            navController.navigate(Screens.MessageScreen.name)
        }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_account_circle_24),
            contentDescription = null,
            tint = Color.Green,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .align(Alignment.Top)
        )
        Column {
            Text(message.title, fontSize = 18.sp)
            Text(message.messageInfo, color = Color.Gray)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MessagesTheme {
        Greeting(rememberNavController())
    }
}