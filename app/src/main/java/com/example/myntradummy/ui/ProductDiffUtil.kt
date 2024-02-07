package com.example.myntradummy.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.myntradummy.data.model.Product

class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}