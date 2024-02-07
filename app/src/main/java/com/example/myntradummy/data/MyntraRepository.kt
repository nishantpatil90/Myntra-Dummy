package com.example.myntradummy.data

import com.example.myntradummy.data.model.Product
import com.example.myntradummy.di.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyntraRepository {

    suspend fun fetchProductsList(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(ServiceLocator.myntraApiClient.getProductsList().products ?: listOf())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
