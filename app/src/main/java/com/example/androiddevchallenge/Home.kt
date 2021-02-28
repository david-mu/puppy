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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import dev.chrisbanes.accompanist.picasso.PicassoImage

@Composable
fun Home(navController: NavController, dogsViewModel: DogsViewModel) {
    val dogs: List<Dog> by dogsViewModel.dogs.observeAsState(listOf())
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(Data.dogs.size) {
            DogCard(
                dog = dogs[it],
                onClickCard = { navController.navigate("detail/$it") }
            )
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
                if (dog.requested) {
                    Text(
                        text = "Requested for adoption",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
