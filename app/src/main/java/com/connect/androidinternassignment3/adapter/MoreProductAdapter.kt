package com.connect.androidinternassignment3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.androidinternassignment3.databinding.BestSellersListBinding
import com.connect.androidinternassignment3.databinding.MoreProductListBinding
import com.connect.androidinternassignment3.databinding.TopOffersListBinding
import com.connect.androidinternassignment3.model.BestSellers
import com.connect.androidinternassignment3.model.MoreProduct
import com.connect.androidinternassignment3.model.TopOffer

class MoreProductAdapter : RecyclerView.Adapter<MoreProductAdapter.MoreProductHolder>() {
    private val more_product_list:ArrayList<MoreProduct> = arrayListOf()

    inner class MoreProductHolder(binding:MoreProductListBinding) : RecyclerView.ViewHolder(binding.root){
        val binding:MoreProductListBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreProductHolder {
        val binding = MoreProductListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MoreProductHolder(binding)
    }

    override fun onBindViewHolder(holder: MoreProductHolder, position: Int) {
        val moreProduct = more_product_list[position]
        holder.binding.moreProductImage.setImageResource(moreProduct.imageId)
        holder.binding.moreProductDetails.text = moreProduct.product_details
        holder.binding.moreProductPrice.text = moreProduct.product_price
        holder.binding.moreProductSoldDetails.text = moreProduct.productSold
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getMoreProduct(list:List<MoreProduct>){
        more_product_list.clear()
        more_product_list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return more_product_list.size
    }
}