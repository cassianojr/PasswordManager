package br.com.cassianojunior.passwordmanager.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import br.com.cassianojunior.passwordmanager.R
import java.util.concurrent.Executor

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var executor:Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo:BiometricPrompt.PromptInfo
    private val REQUEST_SETTINGS_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = createBiometricPrompt()

        checkBiometricAvailable()
        buildPromptInfo()

        biometricPrompt.authenticate(promptInfo)
        setButtonsListeners()
    }

    private fun buildPromptInfo() {
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setDeviceCredentialAllowed(true)
            .build()
    }

    private fun setButtonsListeners() {
        val biometricLoginButton = findViewById<Button>(R.id.biometric_login_button)
        val exitButton = findViewById<Button>(R.id.exit_button)

        biometricLoginButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
        exitButton.setOnClickListener {
            finish()
        }
    }

    private fun createBiometricPrompt(): BiometricPrompt {
       return BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
           override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
               super.onAuthenticationError(errorCode, errString)
               if (errorCode == BiometricPrompt.ERROR_NO_DEVICE_CREDENTIAL) {
                   enrollBiometricSettings()
               }
               Toast.makeText(
                   applicationContext,
                   "Authentication Error: $errString +$errorCode",
                   Toast.LENGTH_SHORT
               ).show()
           }

           override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
               super.onAuthenticationSucceeded(result)
               startActivity(Intent(this@AuthenticationActivity, NavigationActivity::class.java))
               finish()
           }

           override fun onAuthenticationFailed() {
               super.onAuthenticationFailed()
               Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT)
                   .show()
           }
       })
    }

    private fun checkBiometricAvailable() {
        val biometricManager = BiometricManager.from(this)
        when(biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)){
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(applicationContext, "No hardware available to authenticate! Sorry :(", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(applicationContext, "Biometric features are currently unavailable.", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->{
                enrollBiometricSettings()
            }
            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED->{
                enrollBiometricSettings()
            }
        }
    }

    @SuppressLint("InlinedApi")
    private fun enrollBiometricSettings() {
        val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
            putExtra(
                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )
        }
        startActivityForResult(enrollIntent, REQUEST_SETTINGS_CODE)
    }
}