package com.connect.androidinternassignment3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_offer")
data class TopOffer(
    @PrimaryKey
    val imageId:Int,
    val top_offer:String)
