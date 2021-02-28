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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.picasso.PicassoImage

@Composable
fun Detail(dogId: Int, dogsViewModel: DogsViewModel) {
    val dogs: List<Dog> by dogsViewModel.dogs.observeAsState(listOf())
    if (dogs.isEmpty()) {
        return
    }
    val dog = dogs[dogId]
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
            Button(
                modifier = Modifier.padding(top = 8.dp),
                onClick = {
                    if (dog.requested) {
                        dogsViewModel.cancelRequestDog(dogId)
                    } else {
                        dogsViewModel.requestDog(dogId)
                    }
                }
            ) {
                Text(
                    text = if (dog.requested) {
                        "Adoption requested"
                    } else {
                        "Request adoption"
                    }
                )
            }
        }
    }
}
