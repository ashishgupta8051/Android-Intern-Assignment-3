package com.connect.androidinternassignment3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "best_sellers")
data class BestSellers(
    @PrimaryKey
    val imageId:Int,
    val best_offer:String)
