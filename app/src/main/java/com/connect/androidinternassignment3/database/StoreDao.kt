package com.connect.androidinternassignment3.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.connect.androidinternassignment3.model.*

@Dao
interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addViewPagerImage(viewPagerImage: ViewPagerImage)

    @Query("select * from image_slider")
    fun getViewPagerImage(): LiveData<List<ViewPagerImage>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOffer(offer: Offer)

    @Query("select * from offer")
    fun getOffer(): LiveData<List<Offer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBestSellers(bestSellers: BestSellers)

    @Query("select * from best_sellers")
    fun getBestSellers(): LiveData<List<BestSellers>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTopOffer(topOffer: TopOffer)

    @Query("select * from top_offer")
    fun getTopOffer(): LiveData<List<TopOffer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMOreProduct(moreProduct: MoreProduct)

    @Query("select * from more_product")
    fun getMoreProduct(): LiveData<List<MoreProduct>>


}