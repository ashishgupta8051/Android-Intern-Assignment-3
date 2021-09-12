package com.connect.androidinternassignment3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.androidinternassignment3.databinding.BestSellersListBinding
import com.connect.androidinternassignment3.model.BestSellers

class BestSellersAdapter : RecyclerView.Adapter<BestSellersAdapter.BestSellersHolder>() {
    private val bestSellersList:ArrayList<BestSellers> = arrayListOf()


    inner class BestSellersHolder(binding:BestSellersListBinding) : RecyclerView.ViewHolder(binding.root){
        val binding:BestSellersListBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellersHolder {
        val binding = BestSellersListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BestSellersHolder(binding)
    }

    override fun onBindViewHolder(holder: BestSellersHolder, position: Int) {
        val bestSellers = bestSellersList[position]
        holder.binding.bestSellersImage.setImageResource(bestSellers.imageId)
        holder.binding.bestSellersText.text = bestSellers.best_offer
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getBestSellers(list:List<BestSellers>){
        bestSellersList.clear()
        bestSellersList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return bestSellersList.size
    }
}