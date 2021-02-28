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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dogsViewModel by viewModels<DogsViewModel>()
        setContent {
            MyTheme(darkTheme = true) {
                MyApp(dogsViewModel)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(dogsViewModel: DogsViewModel) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "home") {
            composable("home") { Home(navController, dogsViewModel) }
            composable(
                "detail/{dogId}",
                arguments = listOf(navArgument("dogId") { type = NavType.IntType })
            ) { Detail(checkNotNull(it.arguments?.getInt("dogId")), dogsViewModel) }
        }
    }
}
