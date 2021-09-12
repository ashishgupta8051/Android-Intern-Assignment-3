package com.connect.androidinternassignment3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.androidinternassignment3.databinding.ImageSliderListBinding
import com.connect.androidinternassignment3.model.ViewPagerImage

class ViewPagerImageAdapter : RecyclerView.Adapter<ViewPagerImageAdapter.ImageHolder>() {
    private val listImage:ArrayList<ViewPagerImage> = arrayListOf()

    inner class ImageHolder(binding: ImageSliderListBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ImageSliderListBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = ImageSliderListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val imageSlider = listImage[position]
        holder.binding.tvShowPictures.setImageResource(imageSlider.imageId)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getImageId(list:List<ViewPagerImage>){
        listImage.clear()
        listImage.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listImage.size
    }
}