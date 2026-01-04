package uk.ac.tees.mad.focustimer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.focustimer.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val sharedPreferences = getSharedPreferences("TimerSettings", Context.MODE_PRIVATE)

        // Load saved settings
        val savedFocusLength = sharedPreferences.getLong("focusLength", 25)
        binding.focusLengthSlider.value = savedFocusLength.toFloat().coerceIn(
            binding.focusLengthSlider.valueFrom,
            binding.focusLengthSlider.valueTo
        )
        binding.focusLengthText.text = "Focus Length: $savedFocusLength min"

        val savedShortBreak = sharedPreferences.getLong("shortBreakLength", 5)
        binding.shortBreakSlider.value = savedShortBreak.toFloat().coerceIn(
            binding.shortBreakSlider.valueFrom,
            binding.shortBreakSlider.valueTo
        )
        binding.shortBreakText.text = "Short Break Length: $savedShortBreak min"

        val savedLongBreak = sharedPreferences.getLong("longBreakLength", 15)
        binding.longBreakSlider.value = savedLongBreak.toFloat().coerceIn(
            binding.longBreakSlider.valueFrom,
            binding.longBreakSlider.valueTo
        )
        binding.longBreakText.text = "Long Break Length: $savedLongBreak min"

        binding.darkModeSwitch.isChecked = (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.focusLengthSlider.addOnChangeListener { _, value, _ ->
            val length = value.toLong()
            binding.focusLengthText.text = "Focus Length: $length min"
            sharedPreferences.edit().putLong("focusLength", length).apply()
        }

        binding.shortBreakSlider.addOnChangeListener { _, value, _ ->
            val length = value.toLong()
            binding.shortBreakText.text = "Short Break Length: $length min"
            sharedPreferences.edit().putLong("shortBreakLength", length).apply()
        }

        binding.longBreakSlider.addOnChangeListener { _, value, _ ->
            val length = value.toLong()
            binding.longBreakText.text = "Long Break Length: $length min"
            sharedPreferences.edit().putLong("longBreakLength", length).apply()
        }

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // About, Version, Privacy and Security
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            binding.versionText.text = packageInfo.versionName
        } catch (e: Exception) {
            binding.versionText.text = "1.0.0"
        }

        binding.privacyButton.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Privacy Note")
                .setMessage("Your data stays on your device. We do not collect, store, or share any personal information. All timer activity and usage statistics are processed locally to protect your privacy.")
                .setPositiveButton("OK", null)
                .show()
        }
        
        binding.aboutButton.setOnClickListener {
            Toast.makeText(this, "Focus Timer - Help you stay focused!", Toast.LENGTH_LONG).show()
        }
    }
}