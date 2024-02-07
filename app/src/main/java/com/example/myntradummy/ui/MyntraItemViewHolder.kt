package com.example.myntradummy.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myntradummy.data.model.Product
import com.example.myntradummy.databinding.ProductItemLayoutBinding

class MyntraItemViewHolder(private val binding: ProductItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Product) {
        Glide.with(binding.root).load("https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/461701/item/goods_65_461701.jpg?width=750").into(binding.productImage)
        binding.productName.text = model.productName
        binding.productPrice.text = model.price?.toString()
    }
}
