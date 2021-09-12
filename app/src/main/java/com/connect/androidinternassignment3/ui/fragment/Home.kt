package com.connect.androidinternassignment3.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.connect.androidinternassignment3.R
import com.connect.androidinternassignment3.adapter.*
import com.connect.androidinternassignment3.databinding.FragmentHomeBinding
import com.connect.androidinternassignment3.model.*
import com.connect.androidinternassignment3.viewmodel.HomeViewModel
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager




class Home : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPagerImageAdapter: ViewPagerImageAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var offerAdapter: OfferAdapter
    private lateinit var bestSellersAdapter: BestSellersAdapter
    private lateinit var topOfferAdapter: TopOfferAdapter
    private lateinit var moreProductAdapter: MoreProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        //Change StatusBar color and background
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(),R.color.white)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        setUP()

        return binding.root
    }

    private fun setUP() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //get Viewpager image
        viewPagerImageAdapter = ViewPagerImageAdapter()
        homeViewModel.getViewPagerImage().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                getViewPagerImages()
            }else{
                viewPagerImageAdapter.getImageId(it)
                loadViewPagerImage(it)
            }
        }

        //get category
        categoryAdapter = CategoryAdapter()
        categoryAdapter.getCategory(getCategory())
        binding.categoryRecycler.adapter = categoryAdapter

        //get offer
        offerAdapter = OfferAdapter()
        homeViewModel.getOffer().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                getOffer()
            }else{
                offerAdapter.getOffer(it)
                binding.offerRecycler.adapter = offerAdapter
            }
        }

        //get Best Sellers
        bestSellersAdapter = BestSellersAdapter()
        homeViewModel.getBestSellers().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                getBestSellers()
            }else{
                bestSellersAdapter.getBestSellers(it)
                binding.bestSellersRecycler.adapter = bestSellersAdapter
            }
        }

        //get Top Offer
        topOfferAdapter = TopOfferAdapter()
        homeViewModel.getTopOffer().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                getTopOffer()
            }else{
                topOfferAdapter.getTopOffer(it)
                binding.topOffersRecycler.adapter = topOfferAdapter
            }
        }

        //get moreProduct
        moreProductAdapter = MoreProductAdapter()
        val linearLayoutManager: LinearLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.moreProductRecycler.layoutManager = linearLayoutManager
        homeViewModel.getMoreProduct().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                getMoreProduct()
            }else{
                moreProductAdapter.getMoreProduct(it)
                binding.moreProductRecycler.adapter = moreProductAdapter
            }
        }
    }

    private fun loadViewPagerImage(it: List<ViewPagerImage>) {
        binding.homeViewPager.offscreenPageLimit = 1
        setupSliderIndicator(it.size)
        binding.homeViewPager.adapter = viewPagerImageAdapter
        binding.homeViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentSliderIndicator(position)
            }
        })
    }

    private fun setupSliderIndicator(size: Int) {
        val indicator = arrayOfNulls<ImageView>(size)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(8, 0, 8, 0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(requireContext())
            indicator[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),R.drawable.image_selector_inactive
                )
            )
            indicator[i]!!.layoutParams = params
            binding.indicatorLayoutManager.addView(indicator[i])
        }
    }

    private fun setCurrentSliderIndicator(position :Int){
        val count = binding.indicatorLayoutManager.childCount
        for (item in 0 until count){
            val imageView:ImageView = binding.indicatorLayoutManager.getChildAt(item) as ImageView
            if (item == position){
                imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.image_selector_active))
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.image_selector_inactive))
            }
        }
    }

    private fun getViewPagerImages(){
        //listOf (Value is fix we can not extend array size)
        val imageId: List<Int> = listOf(
            R.drawable.view_pager2,
            R.drawable.viewpager3,
            R.drawable.view_pager1)
        for (item in imageId){
            val viewPager = ViewPagerImage(item)
            homeViewModel.addViewPagerImage(viewPager)
        }

    }

    private fun getCategory() : List<Category>{
        val categoryName: List<String> = listOf("Phone","Laptop","Camera","Video Game","AC")
        val imageId: List<Int> = listOf(
            R.drawable.phone_icon,
            R.drawable.laptop_icon,
            R.drawable.camera_icon,
            R.drawable.videogame_icon,
            R.drawable.ac_icon)

        val categoryList:ArrayList<Category> = arrayListOf()
        var count = 0
        for (id in imageId){
            categoryList.add(Category(id, categoryName[count]))
            count += 1
        }
        return categoryList
    }

    private fun getOffer(){
        val imageId: List<Int> = listOf(
            R.drawable.offer_1,
            R.drawable.offer_2,
            R.drawable.offer_3,
            R.drawable.offer_4)
        for (item in imageId){
            val offer = Offer(item)
            homeViewModel.addOffer(offer)
        }
    }

    private fun getBestSellers(){
        val offer: List<String> = listOf("Up to 20% Off","Up to 30% Off","Up to 50% Off","Up to 10% Off")
        val imageId: List<Int> = listOf(
            R.drawable.best_s_1,
            R.drawable.best_s_2,
            R.drawable.best_s_3,
            R.drawable.best_s_4)
        var count = 0
        for (item in imageId){
            val bestSellers = BestSellers(item,offer[count])
            homeViewModel.addBestSellers(bestSellers)
            count += 1
        }

    }

    private fun getTopOffer(){
        val offer: List<String> = listOf("Up to 20% Off","Up to 30% Off","Up to 50% Off","Up to 10% Off")
        val imageId: List<Int> = listOf(
            R.drawable.top_o_1,
            R.drawable.top_o_2,
            R.drawable.top_o_3,
            R.drawable.top_o_4)
        var count = 0
        for (item in imageId){
            val topOffer = TopOffer(item,offer[count])
            homeViewModel.addTopOffer(topOffer)
            count += 1
        }

    }

    private fun getMoreProduct(){
        val productDetails: List<String> = listOf(
            "Redux Analog Linear Designer Dial Men’s &amp; Boy's Watch",
            "EOS Camera Support Download drivers, software, firmware and manuals and get access to online technical support resources and troubleshooting",
            "Festive Wear Printed Khadi silk sharee, With blouse piece, 5.5 m (separate blouse piece)",
            "Redmi 9 (Sky Blue, 4GB RAM, 64GB Storage) | 2.3GHz Mediatek Helio G35 Octa core Processor")
        val productPrice: List<String> = listOf(
            "US $1.23","US $3.03","US $2.23","US $1.43")
        val productSold: List<String> = listOf(
            "1234 Sold","2342 Sold","2342 Sold","9867 Sold")
        val imageId: List<Int> = listOf(
            R.drawable.m_1,
            R.drawable.m_2,
            R.drawable.m_3,
            R.drawable.m_4)

        var count = 0
        for (id in imageId){
            val moreProduct = MoreProduct(id,productDetails[count],productPrice[count],productSold[count])
            homeViewModel.addMoreProduct(moreProduct)
            count += 1
        }
    }
}