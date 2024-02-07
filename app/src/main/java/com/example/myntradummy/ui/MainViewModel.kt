package com.example.myntradummy.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myntradummy.data.MyntraRepository
import com.example.myntradummy.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository by lazy { MyntraRepository() }

    private val _productsList = MutableStateFlow(listOf<Product>())
    val productsList = _productsList.asStateFlow().asLiveData()

    fun fetchList() {
        viewModelScope.launch {
            val response = repository.fetchProductsList()
            Log.d("####### ", "MainViewModel#fetchList: $response")
            response.onSuccess {
                _productsList.value = it
            }

            response.onFailure {
                Log.d("####### ", "MainViewModel#fetchList error: $it")
            }
        }
    }
}
