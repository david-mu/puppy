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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object Data {
    val dogs = listOf(
        Dog(
            name = "Henri",
            ageMonths = 3,
            description = "Friendly",
            thumbnailUri = "https://placedog.net/500/300?id=1"
        ),
        Dog(
            name = "David",
            ageMonths = 8,
            description = "Aggressive",
            thumbnailUri = "https://placedog.net/500/300?id=2"
        ),
        Dog(
            name = "Olivia",
            ageMonths = 6,
            description = "Friendly",
            thumbnailUri = "https://placedog.net/500/300?id=3"
        ),
        Dog(
            name = "Joris",
            ageMonths = 3,
            description = "Friendly",
            thumbnailUri = "https://placedog.net/500/300?id=4"
        ),
        Dog(
            name = "Gabriel",
            ageMonths = 3,
            description = "Friendly",
            thumbnailUri = "https://placedog.net/500/300?id=5"
        )
    )
}

class DogsViewModel : ViewModel() {
    private val _dogs = MutableLiveData(listOf<Dog>())
    val dogs: LiveData<List<Dog>>
        get() = _dogs

    init {
        _dogs.value = Data.dogs
    }

    fun requestDog(dogId: Int) {
        val mutableDogList = _dogs.value!!.toMutableList()
        mutableDogList[dogId] = _dogs.value!![dogId].copy(requested = true)
        _dogs.value = mutableDogList
    }

    fun cancelRequestDog(dogId: Int) {
        val mutableDogList = _dogs.value!!.toMutableList()
        mutableDogList[dogId] = _dogs.value!![dogId].copy(requested = false)
        _dogs.value = mutableDogList
    }
}

data class Dog(
    val name: String,
    val thumbnailUri: String,
    val ageMonths: Int,
    val description: String,
    val requested: Boolean = false
)
