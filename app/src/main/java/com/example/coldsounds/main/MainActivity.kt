package com.example.coldsounds.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.coldsounds.R
import com.example.coldsounds.databinding.ActivityMainBinding
import com.example.coldsounds.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ProfileItem -> {
                    viewModel.signInScreen(savedInstanceState)
                    true
                } else -> false
            }
        }
        viewModel.signInScreen(savedInstanceState)

        viewModel.navigationLiveData().observe(this) {
            it.show(binding.container.id, supportFragmentManager)
        }
    }
}