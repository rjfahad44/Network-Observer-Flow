package com.example.network_observer_flow

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.network_observer_flow.databinding.SnackbarBinding
import com.google.android.material.snackbar.Snackbar

class CustomSnackbar(private val view: View) {

    fun showSnackBar(title: String, cancelFun: () -> Unit = {}) {
        val snackView = View.inflate(view.context, R.layout.snackbar, null)
        val binding = SnackbarBinding.bind(snackView)
        val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE)
        (snackbar.view as ViewGroup).removeAllViews()
        (snackbar.view as ViewGroup).addView(binding.root)
        snackbar.view.setPadding(0, 0, 0, 0)
        snackbar.view.elevation = 0f
        snackbar.setBackgroundTint(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        binding.iconImgV.setImageResource(when(title){
           ConnectivityObserver.Status.Available.name -> { R.drawable.network_availale }
           ConnectivityObserver.Status.Unavailable.name -> { R.drawable.network_unavailable }
            else ->{ R.drawable.network_unavailable }
        })
        binding.titleTextV.text = title
        binding.root.setOnClickListener {
            cancelFun()
            snackbar.dismiss()
        }
        snackbar.show()
    }
}