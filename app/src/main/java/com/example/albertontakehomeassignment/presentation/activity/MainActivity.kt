package com.example.albertontakehomeassignment.presentation.activity

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albertontakehomeassignment.R
import com.example.albertontakehomeassignment.domain.api.RetrofitClient
import com.example.albertontakehomeassignment.domain.repository.AcromineRepository
import com.example.albertontakehomeassignment.presentation.adapter.AcromineAdapter
import com.example.albertontakehomeassignment.presentation.viewmodel.AcromineViewModel
import com.example.albertontakehomeassignment.presentation.viewmodel.AcromineViewModelFactory

class MainActivity : AppCompatActivity() {

    private val acromineAdapter = AcromineAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val repository = AcromineRepository(
            RetrofitClient.getAcromineEndpoint()
        )

        val viewModel = ViewModelProvider(this, AcromineViewModelFactory(repository))
            .get(AcromineViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_acromine)
        val button = findViewById<Button>(R.id.bt_search)
        val editText = findViewById<EditText>(R.id.et_input)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = acromineAdapter
        }

        viewModel.observeLongFormList().observe(this, Observer { result ->
            result?.let {
                if (it.isSuccess) acromineAdapter.updateList(it.getOrDefault(listOf()))
                else Toast.makeText(this, it.exceptionOrNull()?.message, Toast.LENGTH_SHORT).show()
            }
        })

        button.setOnClickListener {
            val newQuery = editText.text.toString()
            when {
                !isNetWorkAvailable(this) ->
                    Toast.makeText(this, "Network not available", Toast.LENGTH_SHORT)
                        .show()
                newQuery.isBlank() ->
                    Toast.makeText(this, "Please don't leave input empty", Toast.LENGTH_SHORT)
                        .show()
                else ->
                    viewModel.getLongFormWithQuery(newQuery)

            }
        }

    }

    private fun isNetWorkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}