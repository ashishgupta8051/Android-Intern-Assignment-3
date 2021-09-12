package com.connect.androidinternassignment3.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.connect.androidinternassignment3.databinding.CategoryListBinding
import com.connect.androidinternassignment3.model.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    private var categoryList:ArrayList<Category> = arrayListOf()

    inner class CategoryHolder(binding: CategoryListBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: CategoryListBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding = CategoryListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = categoryList[position]
        holder.binding.categoryImage.setImageResource(category.imageId)
        holder.binding.categoryName.text = category.categoryName
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getCategory(list:List<Category>){
        categoryList.clear()
        categoryList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

}

