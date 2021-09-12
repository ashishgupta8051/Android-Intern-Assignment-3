package com.connect.androidinternassignment3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_slider")
data class ViewPagerImage(
    @PrimaryKey
    val imageId: Int)
