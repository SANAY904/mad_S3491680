package uk.ac.tees.mad.focustimer

import android.content.Context
import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
=======
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
import uk.ac.tees.mad.focustimer.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var auth: FirebaseAuth
<<<<<<< HEAD
=======
    private lateinit var db: FirebaseFirestore
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
<<<<<<< HEAD

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
=======
        db = FirebaseFirestore.getInstance()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        loadUserProfile()
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a

        val sharedPreferences = getSharedPreferences("TimerSettings", Context.MODE_PRIVATE)

        // Load saved settings
<<<<<<< HEAD
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
=======
        binding.focusLengthEditText.setText(sharedPreferences.getLong("focusLength", 25).toString())
        binding.shortBreakEditText.setText(sharedPreferences.getLong("shortBreakLength", 5).toString())
        binding.longBreakEditText.setText(sharedPreferences.getLong("longBreakLength", 15).toString())

        val currentNightMode = AppCompatDelegate.getDefaultNightMode()
        when (currentNightMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> binding.lightThemeRadioButton.isChecked = true
            AppCompatDelegate.MODE_NIGHT_YES -> binding.darkThemeRadioButton.isChecked = true
            else -> binding.systemThemeRadioButton.isChecked = true
        }

        binding.themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.lightThemeRadioButton -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.darkThemeRadioButton -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                R.id.systemThemeRadioButton -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        binding.versionValue.text = packageManager.getPackageInfo(packageName, 0).versionName

        binding.aboutLabel.setOnClickListener {
            showAboutDialog()
        }

        binding.saveButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putLong("focusLength", binding.focusLengthEditText.text.toString().toLong())
            editor.putLong("shortBreakLength", binding.shortBreakEditText.text.toString().toLong())
            editor.putLong("longBreakLength", binding.longBreakEditText.text.toString().toLong())
            editor.apply()

            Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                auth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadUserProfile() {
        val user = auth.currentUser
        if (user != null) {
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        binding.profileName.text = document.getString("name")
                        binding.profileEmail.text = document.getString("email")
                    }
                }
        }
    }

    private fun showAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle("About FocusTimer")
            .setMessage("FocusTimer is a simple and effective Pomodoro timer designed to help you stay focused and productive.")
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
}