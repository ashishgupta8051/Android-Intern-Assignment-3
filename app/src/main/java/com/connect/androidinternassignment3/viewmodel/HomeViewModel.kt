package com.connect.androidinternassignment3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.connect.androidinternassignment3.database.StoreDatabase
import com.connect.androidinternassignment3.model.*
import com.connect.androidinternassignment3.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var homeRepository: HomeRepository
    init {
        val dao = StoreDatabase.getInstance(application).getDao()
        homeRepository = HomeRepository(dao)
    }

    fun addViewPagerImage(viewPagerImage: ViewPagerImage) = viewModelScope.launch(Dispatchers.IO) {
            homeRepository.addViewPagerImage(viewPagerImage)
    }

    fun getViewPagerImage() : LiveData<List<ViewPagerImage>> {
        return homeRepository.getViewPagerImage()
    }


    fun addOffer(offer: Offer) = viewModelScope.launch(Dispatchers.IO) {
        homeRepository.addOffer(offer)
    }

    fun getOffer() : LiveData<List<Offer>> {
        return homeRepository.getOffer()
    }

    fun addBestSellers(bestSellers: BestSellers) = viewModelScope.launch(Dispatchers.IO) {
        homeRepository.addBestSellers(bestSellers)
    }

    fun getBestSellers() : LiveData<List<BestSellers>> {
        return homeRepository.getBestSellers()
    }

    fun addTopOffer(topOffer: TopOffer) = viewModelScope.launch(Dispatchers.IO) {
        homeRepository.addTopOffer(topOffer)
    }

    fun getTopOffer() : LiveData<List<TopOffer>> {
        return homeRepository.getTopOffer()
    }

    fun addMoreProduct(moreProduct: MoreProduct) = viewModelScope.launch(Dispatchers.IO) {
        homeRepository.addMoreProduct(moreProduct)
    }

    fun getMoreProduct() : LiveData<List<MoreProduct>> {
        return homeRepository.getMoreProduct()
    }
}