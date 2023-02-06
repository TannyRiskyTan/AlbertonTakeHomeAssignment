package com.example.albertontakehomeassignment.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.albertontakehomeassignment.R
import com.example.albertontakehomeassignment.domain.api.RetrofitClient
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            println("----------Test Log------------")
            println(RetrofitClient.getAcromineEndpoint().getAcromine("FBI").first())
            println("----------------------")
        }
    }
}