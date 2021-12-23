package com.guru.pinlock

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@OptIn(ExperimentalFoundationApi::class)
class BiometricActivity : FragmentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biometricPromt = createBiometricPrompt(this)
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verify Identity to login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use pin password")
            .build()
        biometricPromt.authenticate(promptInfo)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, BiometricActivity::class.java)
    }
}

private fun createBiometricPrompt(activity: FragmentActivity): BiometricPrompt {
    val executor = ContextCompat.getMainExecutor(activity)

    val callback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
//            if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
//                activity.finish()
//            }
            activity.finish()
        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            activity.setResult(Activity.RESULT_OK)
            activity.finish()
        }
    }
    val biometricPrompt = BiometricPrompt(activity, executor, callback)

    return biometricPrompt
}


