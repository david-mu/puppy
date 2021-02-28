/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.picasso.PicassoImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme(darkTheme = true) {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "home") {
            composable("home") { Home(navController) }
            composable(
                "detail/{dogId}",
                arguments = listOf(navArgument("dogId") { type = NavType.IntType })
            ) { Detail(checkNotNull(it.arguments?.getInt("dogId"))) }
        }
    }
}

@Composable
fun Home(navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(Data.dogs.size) {
            DogCard(
                dog = Data.dogs[it],
                onClickCard = { navController.navigate("detail/$it") }
            )
        }
    }
}

@Composable
fun Detail(dogId: Int) {
    val dog = Data.dogs[dogId]
    Column {
        PicassoImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            data = dog.thumbnailUri,
            contentDescription = dog.name,
            contentScale = ContentScale.FillWidth
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = dog.name, style = MaterialTheme.typography.h3)
            Text(text = "Description: ${dog.description}", style = MaterialTheme.typography.body1)
            Text(text = "Age: ${dog.ageMonths} months", style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun DogCard(dog: Dog, onClickCard: () -> Unit) {
    Card(modifier = Modifier.clickable(onClick = onClickCard)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) {
            PicassoImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.surface),
                data = dog.thumbnailUri,
                contentDescription = dog.name,
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = dog.name,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = dog.description,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
