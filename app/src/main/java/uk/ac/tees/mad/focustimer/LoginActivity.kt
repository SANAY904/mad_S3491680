package uk.ac.tees.mad.focustimer

<<<<<<< HEAD
<<<<<<< HEAD
=======
import android.content.Context
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
import android.content.Context
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.focustimer.databinding.ActivityLoginBinding
=======
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.focustimer.databinding.ActivityLoginBinding
import java.util.concurrent.Executor
<<<<<<< HEAD
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        // If user is already logged in, skip this activity entirely
        if (auth.currentUser != null) {
            startActivity(Intent(this, TimerActivity::class.java))
            finish()
            return
        }

        setTheme(R.style.Theme_FocusTimer) // Switch back to the main theme
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
<<<<<<< HEAD
        // Clear errors when user starts typing
        binding.emailEditText.doOnTextChanged { _, _, _, _ ->
            binding.emailInputLayout.error = null
        }
        binding.passwordEditText.doOnTextChanged { _, _, _, _ ->
            binding.passwordInputLayout.error = null
        }
=======
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext, "Authentication error: $errString", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, TimerActivity::class.java))
                    finish()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for FocusTimer")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        val sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
        binding.fingerprintCheckBox.isChecked = sharedPreferences.getBoolean("useFingerprint", false)
<<<<<<< HEAD
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

<<<<<<< HEAD
<<<<<<< HEAD
            // Reset errors
            binding.emailInputLayout.error = null
            binding.passwordInputLayout.error = null

=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
                            // Correctly save the fingerprint preference
                            sharedPreferences.edit().putBoolean("useFingerprint", binding.fingerprintCheckBox.isChecked).apply()

>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
                            // Correctly save the fingerprint preference
                            sharedPreferences.edit().putBoolean("useFingerprint", binding.fingerprintCheckBox.isChecked).apply()

>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                            Toast.makeText(baseContext, "Login successful.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, TimerActivity::class.java))
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            binding.emailInputLayout.error = "Invalid email or password"
                            binding.passwordInputLayout.error = "Invalid email or password"
                        }
                    }
            } else {
<<<<<<< HEAD
<<<<<<< HEAD
                if (email.isEmpty()) binding.emailInputLayout.error = "Email is required"
                if (password.isEmpty()) binding.passwordInputLayout.error = "Password is required"
=======
                binding.emailInputLayout.error = "Email and password cannot be empty"
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
                binding.emailInputLayout.error = "Email and password cannot be empty"
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
            }
        }

        binding.signupTextView.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd

        binding.fingerprintButton.setOnClickListener {
            if (binding.fingerprintCheckBox.isChecked) {
                val biometricManager = BiometricManager.from(this)
                if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS) {
                    biometricPrompt.authenticate(promptInfo)
                } else {
                    Toast.makeText(this, "Biometric authentication is not available.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enable fingerprint login in the settings.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Session check is now handled in onCreate to prevent re-triggering on every onStart
<<<<<<< HEAD
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
    }
}