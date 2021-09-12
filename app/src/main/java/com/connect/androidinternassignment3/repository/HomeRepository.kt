package com.connect.androidinternassignment3.repository

import androidx.lifecycle.LiveData
import com.connect.androidinternassignment3.database.StoreDao
import com.connect.androidinternassignment3.model.*

class HomeRepository(private val storeDao: StoreDao) {

    suspend fun addViewPagerImage(viewPagerImage: ViewPagerImage){
        storeDao.addViewPagerImage(viewPagerImage)
    }

    fun getViewPagerImage() : LiveData<List<ViewPagerImage>>{
        return storeDao.getViewPagerImage()
    }

    suspend fun addOffer(offer: Offer){
        storeDao.addOffer(offer)
    }

    fun getOffer() : LiveData<List<Offer>>{
        return storeDao.getOffer()
    }

    suspend fun addBestSellers(bestSellers: BestSellers){
        storeDao.addBestSellers(bestSellers)
    }

    fun getBestSellers() : LiveData<List<BestSellers>>{
        return storeDao.getBestSellers()
    }

    suspend fun addTopOffer(topOffer: TopOffer){
        storeDao.addTopOffer(topOffer)
    }

    fun getTopOffer() : LiveData<List<TopOffer>>{
        return storeDao.getTopOffer()
    }

    suspend fun addMoreProduct(moreProduct: MoreProduct){
        storeDao.addMOreProduct(moreProduct)
    }

    fun getMoreProduct() : LiveData<List<MoreProduct>>{
        return storeDao.getMoreProduct()
    }


}