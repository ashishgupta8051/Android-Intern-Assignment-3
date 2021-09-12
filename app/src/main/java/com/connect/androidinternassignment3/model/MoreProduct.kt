package com.connect.androidinternassignment3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "more_product")
data class MoreProduct(
    @PrimaryKey
    val imageId:Int,
    val product_details:String,
    val product_price:String,
    val productSold:String)
