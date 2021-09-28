package com.adwi.betty.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    val TAG: String = javaClass.simpleName

    val snackBarMessage = MutableStateFlow("")
    val toastMessage = MutableStateFlow("")
}