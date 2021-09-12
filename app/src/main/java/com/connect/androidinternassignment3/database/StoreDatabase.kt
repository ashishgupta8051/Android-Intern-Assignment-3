package com.connect.androidinternassignment3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.connect.androidinternassignment3.model.*
import com.connect.androidinternassignment3.utils.Credentials

@Database(entities = [ViewPagerImage::class,Offer::class,BestSellers::class,TopOffer::class,MoreProduct::class]
    , version = 1, exportSchema = false)
abstract class StoreDatabase : RoomDatabase(){

    abstract fun getDao(): StoreDao

    companion object{
        private var INSTANCE : StoreDatabase? = null

        fun getInstance(context: Context) : StoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreDatabase::class.java,
                    Credentials().DATABASE
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}