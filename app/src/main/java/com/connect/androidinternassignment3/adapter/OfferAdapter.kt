package com.connect.androidinternassignment3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.androidinternassignment3.databinding.OfferListBinding
import com.connect.androidinternassignment3.model.Offer
import com.connect.androidinternassignment3.model.ViewPagerImage
import java.util.ArrayList

class OfferAdapter : RecyclerView.Adapter<OfferAdapter.OfferHolder>() {
    private var offerList:ArrayList<Offer> = arrayListOf()

    inner class OfferHolder(binding:OfferListBinding) : RecyclerView.ViewHolder(binding.root){
        var binding:OfferListBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
        val binding = OfferListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OfferHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferHolder, position: Int) {
        val offer = offerList[position]

        holder.binding.offerImage.setImageResource(offer.imageId)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getOffer(list:List<Offer>){
        offerList.clear()
        offerList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return offerList.size
    }
}