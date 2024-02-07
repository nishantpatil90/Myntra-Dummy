package com.example.myntradummy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myntradummy.data.model.Product
import com.example.myntradummy.databinding.ProductItemLayoutBinding

class MyntraAdapter : ListAdapter<Product, MyntraItemViewHolder>(ProductDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyntraItemViewHolder {
        val binding =
            ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyntraItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyntraItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
