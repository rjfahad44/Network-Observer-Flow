package com.example.network_observer_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.network_observer_flow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var customSnackbar: CustomSnackbar

    private val networkObserverViewModel by viewModels<NetworkObserverViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customSnackbar = CustomSnackbar(binding.root)

        lifecycleScope.launch {
            networkObserverViewModel.networkObserve().collectLatest {
                customSnackbar.showSnackBar(it.name)
            }
        }
    }
}