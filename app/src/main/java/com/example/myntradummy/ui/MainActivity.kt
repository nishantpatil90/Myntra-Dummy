package com.example.myntradummy.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myntradummy.R
import com.example.myntradummy.data.model.MyntraSearchResponse
import com.example.myntradummy.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val myntraAdapter by lazy { MyntraAdapter() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()
        initObserver()
    }

    private fun initRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = myntraAdapter
        }
    }

    private fun initObserver() {
//        viewModel.productsList.observe(this) {
//            myntraAdapter.submitList(it)
//        }
        lifecycleScope.launch(Dispatchers.IO) {
            val a = getJsonFromAssets(this@MainActivity, "myntra_response.json")
            val gson = Gson()
            val listUserType = object : TypeToken<MyntraSearchResponse>() {}.type
            val searchResponse: MyntraSearchResponse = gson.fromJson(a, listUserType)
            withContext(Dispatchers.Main) {
                myntraAdapter.submitList(
                    searchResponse.products ?: listOf()
                )
            }
        }
    }

    private suspend fun getJsonFromAssets(context: Context, fileName: String?): String? {
        return withContext(Dispatchers.IO) {
            val jsonString: String = try {
                val `is` = context.assets.open(fileName!!)
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, charset("UTF-8"))
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext null
            }
            return@withContext jsonString
        }
    }

}
