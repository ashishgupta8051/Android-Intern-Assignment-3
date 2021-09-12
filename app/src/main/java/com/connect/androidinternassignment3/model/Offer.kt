package com.connect.androidinternassignment3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "offer")
data class Offer(
    @PrimaryKey
    val imageId:Int)
