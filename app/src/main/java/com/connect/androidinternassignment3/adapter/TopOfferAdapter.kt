package com.connect.androidinternassignment3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.androidinternassignment3.databinding.BestSellersListBinding
import com.connect.androidinternassignment3.databinding.TopOffersListBinding
import com.connect.androidinternassignment3.model.BestSellers
import com.connect.androidinternassignment3.model.TopOffer

class TopOfferAdapter : RecyclerView.Adapter<TopOfferAdapter.TopOfferHolder>() {
    private val top_offer_list:ArrayList<TopOffer> = arrayListOf()

    inner class TopOfferHolder(binding:TopOffersListBinding) : RecyclerView.ViewHolder(binding.root){
        val binding:TopOffersListBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopOfferHolder {
        val binding = TopOffersListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopOfferHolder(binding)
    }

    override fun onBindViewHolder(holder: TopOfferHolder, position: Int) {
        val top_offer = top_offer_list[position]
        holder.binding.topOffersImage.setImageResource(top_offer.imageId)
        holder.binding.topOffers.text = top_offer.top_offer
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getTopOffer(list:List<TopOffer>){
        top_offer_list.clear()
        top_offer_list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return top_offer_list.size
    }
}