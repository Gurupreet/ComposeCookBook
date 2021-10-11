package com.adwi.betty.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.round


val <T> T.exhaustive: T
    get() = this

fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

fun AppCompatActivity.launchCoroutine(body: suspend () -> Unit): Job {
    return lifecycleScope.launchWhenStarted {
        body()
    }
}

fun ViewModel.onDispatcher(dispatcher: CoroutineDispatcher, body: suspend () -> Unit): Job {
    return viewModelScope.launch(dispatcher) {
        body()
    }
}