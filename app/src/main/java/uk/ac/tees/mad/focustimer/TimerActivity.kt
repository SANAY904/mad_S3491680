package uk.ac.tees.mad.focustimer
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
<<<<<<< HEAD
<<<<<<< HEAD
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
=======
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
<<<<<<< HEAD
import androidx.core.content.ContextCompat
<<<<<<< HEAD
import com.google.android.material.dialog.MaterialAlertDialogBuilder
=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
<<<<<<< HEAD
import androidx.core.content.ContextCompat
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp
<<<<<<< HEAD
=======
=======
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
<<<<<<< HEAD
import com.google.firebase.Timestamp
=======
>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
import org.json.JSONArray
import uk.ac.tees.mad.focustimer.databinding.ActivityTimerBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Calendar
=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private var timer: CountDownTimer? = null
    private var timerRunning = false
    private var timeLeftInMillis: Long = 0
    private var focusLengthInMillis: Long = 0

    private var isFocusSession = true
<<<<<<< HEAD
<<<<<<< HEAD
    private var focusSessionsCompletedToday = 0
    private var currentQuoteText = "Stay focused and keep moving forward!"
    private var pendingCompletionMessage: String? = null
    private var isGoalAchievementShown = false
=======
    private var focusSessionsCompleted = 0
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
    private var focusSessionsCompleted = 0
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd

    private val notificationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted
<<<<<<< HEAD
<<<<<<< HEAD
=======
        } else {
            // Permission denied
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
        } else {
            // Permission denied
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        createNotificationChannel()
        requestNotificationPermission()
<<<<<<< HEAD
        loadStats()
<<<<<<< HEAD
        
        binding.quoteCard.visibility = View.GONE
=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
<<<<<<< HEAD
        loadStats()
=======
<<<<<<< HEAD
        loadStats()
=======
>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.statsButton.setOnClickListener {
            startActivity(Intent(this, StatsActivity::class.java))
        }

        binding.startPauseButton.setOnClickListener {
            if (timerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        binding.resetButton.setOnClickListener {
            resetTimer()
        }
    }

    override fun onResume() {
        super.onResume()
        handleIntent(intent)
<<<<<<< HEAD
<<<<<<< HEAD
        loadStats()
=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (intent.hasExtra("startBreak")) {
            isFocusSession = false
            loadTimerSettings()
            resetTimer()
            startTimer()
<<<<<<< HEAD
<<<<<<< HEAD
            intent.removeExtra("startBreak") 
=======
            intent.removeExtra("startBreak") // Clear the extra
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
            intent.removeExtra("startBreak") // Clear the extra
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
        } else if (intent.hasExtra("startFocus")) {
            isFocusSession = true
            loadTimerSettings()
            resetTimer()
            startTimer()
<<<<<<< HEAD
<<<<<<< HEAD
            intent.removeExtra("startFocus") 
=======
            intent.removeExtra("startFocus") // Clear the extra
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
            intent.removeExtra("startFocus") // Clear the extra
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
        } else {
            loadTimerSettings()
            updateTimerText()
        }
    }

    private fun loadTimerSettings() {
        val sharedPreferences = getSharedPreferences("TimerSettings", Context.MODE_PRIVATE)
        val focusLength = sharedPreferences.getLong("focusLength", 25)
        focusLengthInMillis = focusLength * 60 * 1000
        val shortBreakLength = sharedPreferences.getLong("shortBreakLength", 5)
        val longBreakLength = sharedPreferences.getLong("longBreakLength", 15)

        if (!timerRunning) {
            timeLeftInMillis = if (isFocusSession) {
                focusLengthInMillis
            } else {
<<<<<<< HEAD
<<<<<<< HEAD
                if ((focusSessionsCompletedToday % 4) == 0 && focusSessionsCompletedToday != 0) {
=======
                if ((focusSessionsCompleted % 4) == 0 && focusSessionsCompleted != 0) {
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
                if ((focusSessionsCompleted % 4) == 0 && focusSessionsCompleted != 0) {
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                    longBreakLength * 60 * 1000
                } else {
                    shortBreakLength * 60 * 1000
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD
        
        binding.sessionStatusChip.text = if (isFocusSession) "FOCUS" else "BREAK"
=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                timerRunning = false
<<<<<<< HEAD
                updateButtonUI()
<<<<<<< HEAD
                
                if (isFocusSession) {
                    updateStats(focusLengthInMillis)
                    isFocusSession = false
                    showBreakNotification()
                    pendingCompletionMessage = "Great job! Focus session completed."
                } else {
                    isFocusSession = true
                    showFocusNotification()
                    pendingCompletionMessage = "Break ended. Time to focus!"
                }
                
                FetchQuoteTask().execute()
=======
=======
<<<<<<< HEAD
                updateButtonUI()
=======
                binding.startPauseButton.text = "Start"
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                if (isFocusSession) {
                    focusSessionsCompleted++
                    updateStats(focusLengthInMillis)
                    isFocusSession = false
                    showBreakNotification()
                    showQuote()
                } else {
                    isFocusSession = true
                    showFocusNotification()
                }
<<<<<<< HEAD
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
                loadTimerSettings()
                updateTimerText()
=======
                loadTimerSettings()
                updateTimerText()
<<<<<<< HEAD
=======
                binding.startPauseButton.text = if(isFocusSession) "Start Focus" else "Start Break"
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
            }
        }.start()

        timerRunning = true
<<<<<<< HEAD
        updateButtonUI()
    }

<<<<<<< HEAD
    private fun updateGoalProgress() {
        val sharedPreferences = getSharedPreferences("TimerSettings", Context.MODE_PRIVATE)
        val dailyGoal = sharedPreferences.getInt("dailyGoal", 4)
        
        binding.goalProgressText.text = "$focusSessionsCompletedToday/$dailyGoal"
        binding.goalProgressBar.max = dailyGoal
        binding.goalProgressBar.progress = focusSessionsCompletedToday

        // Check if goal is reached and cheer the user
        if (focusSessionsCompletedToday >= dailyGoal && !isGoalAchievementShown) {
            isGoalAchievementShown = true
            showGoalReachedDialog()
        } else if (focusSessionsCompletedToday < dailyGoal) {
            isGoalAchievementShown = false // Reset if goal is increased
        }
    }

    private fun showGoalReachedDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Daily Goal Achieved! 🎉")
            .setMessage("Fantastic work! You've reached your focus goal for today. Consistency is key to success—keep it up!")
            .setPositiveButton("Awesome!") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    private fun showCompletionPopup(message: String, quote: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(message)
            .setMessage(quote)
            .setPositiveButton("Continue") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
    private fun pauseTimer() {
        timer?.cancel()
        timerRunning = false
        updateButtonUI()
=======
<<<<<<< HEAD
        updateButtonUI()
=======
        binding.startPauseButton.text = "Pause"
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
    }

    private fun pauseTimer() {
        timer?.cancel()
        timerRunning = false
<<<<<<< HEAD
        updateButtonUI()
=======
        binding.startPauseButton.text = "Start"
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
    }

    private fun resetTimer() {
        timer?.cancel()
        timerRunning = false
        loadTimerSettings()
        updateTimerText()
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
        updateButtonUI()
        binding.progressIndicator.progress = 100
    }

    private fun updateButtonUI() {
        if (timerRunning) {
            binding.startPauseButton.text = "Pause"
            binding.startPauseButton.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause)
        } else {
            binding.startPauseButton.text = if (isFocusSession) "Start Focus" else "Start Break"
            binding.startPauseButton.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_media_play)
        }
    }

<<<<<<< HEAD
=======
=======
        binding.startPauseButton.text = if (isFocusSession) "Start Focus" else "Start Break"
        binding.progressIndicator.progress = 100
    }

>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
    private fun updateTimerText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        binding.timerTextView.text = timeFormatted

        val sharedPreferences = getSharedPreferences("TimerSettings", Context.MODE_PRIVATE)
        val totalTime = if (isFocusSession) {
            sharedPreferences.getLong("focusLength", 25) * 60 * 1000
        } else {
<<<<<<< HEAD
<<<<<<< HEAD
            if ((focusSessionsCompletedToday % 4) == 0 && focusSessionsCompletedToday != 0) {
=======
            if ((focusSessionsCompleted % 4) == 0 && focusSessionsCompleted != 0) {
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
            if ((focusSessionsCompleted % 4) == 0 && focusSessionsCompleted != 0) {
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                sharedPreferences.getLong("longBreakLength", 15) * 60 * 1000
            } else {
                sharedPreferences.getLong("shortBreakLength", 5) * 60 * 1000
            }
        }
        if (totalTime > 0) {
            binding.progressIndicator.progress = (timeLeftInMillis * 100 / totalTime).toInt()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "FocusTimer Channel"
            val descriptionText = "Channel for FocusTimer notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("FOCUS_TIMER_CHANNEL", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showBreakNotification() {
        val intent = Intent(this, TimerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("startBreak", true)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val sharedPreferences = getSharedPreferences("TimerSettings", Context.MODE_PRIVATE)
<<<<<<< HEAD
<<<<<<< HEAD
        val breakLength = if ((focusSessionsCompletedToday % 4) == 0 && focusSessionsCompletedToday != 0) {
=======
        val breakLength = if ((focusSessionsCompleted % 4) == 0 && focusSessionsCompleted != 0) {
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
        val breakLength = if ((focusSessionsCompleted % 4) == 0 && focusSessionsCompleted != 0) {
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
            sharedPreferences.getLong("longBreakLength", 15)
        } else {
            sharedPreferences.getLong("shortBreakLength", 5)
        }

        val builder = NotificationCompat.Builder(this, "FOCUS_TIMER_CHANNEL")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Focus Session Complete!")
            .setContentText("Time for a ${breakLength}-minute break.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            with(NotificationManagerCompat.from(this)) {
                notify(1, builder.build())
            }
        }
    }

    private fun showFocusNotification() {
        val intent = Intent(this, TimerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("startFocus", true)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, "FOCUS_TIMER_CHANNEL")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Break's Over!")
            .setContentText("Time to get back to focus.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            with(NotificationManagerCompat.from(this)) {
                notify(2, builder.build())
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
    private fun updateStats(focusTime: Long) {
        val user = auth.currentUser ?: return
        val userDocRef = db.collection("users").document(user.uid)
        
        userDocRef.update("totalFocusTime", FieldValue.increment(focusTime / 1000))
        userDocRef.update("sessionsCompleted", FieldValue.increment(1))
            .addOnSuccessListener {
                loadStats()
            }

        val session = hashMapOf(
            "type" to "Focus",
            "duration" to focusTime / 1000,
            "timestamp" to Timestamp.now()
        )
        userDocRef.collection("sessions").add(session)
    }

    private fun loadStats() {
        val user = auth.currentUser ?: return
        val userDocRef = db.collection("users").document(user.uid)
        
        // Load today's stats specifically
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfToday = Timestamp(calendar.time)

        userDocRef.collection("sessions")
            .whereGreaterThanOrEqualTo("timestamp", startOfToday)
            .get()
            .addOnSuccessListener { documents ->
                focusSessionsCompletedToday = documents.size()
                binding.sessionsCompletedValue.text = focusSessionsCompletedToday.toString()
                
                // Calculate today's focus time
                var todaySeconds: Long = 0
                for (doc in documents) {
                    todaySeconds += doc.getLong("duration") ?: 0
                }
                val hours = todaySeconds / 3600
                val minutes = (todaySeconds % 3600) / 60
                binding.totalFocusTimeValue.text = if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
                
                updateGoalProgress()
            }
=======
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
    private fun showQuote() {
        FetchQuoteTask().execute()
    }

    private fun showQuoteDialog(quote: String, author: String) {
        AlertDialog.Builder(this)
            .setTitle("Quote of the Session")
            .setMessage("\"$quote\" - $author")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun updateStats(focusTime: Long) {
        val user = auth.currentUser
        if (user != null) {
            val userDocRef = db.collection("users").document(user.uid)
            userDocRef.update("totalFocusTime", FieldValue.increment(focusTime / 1000))
            userDocRef.update("sessionsCompleted", FieldValue.increment(1))
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                .addOnSuccessListener {
                    Log.d("TimerActivity", "Stats updated successfully!")
                    loadStats()
                }
                .addOnFailureListener { e ->
                    Log.w("TimerActivity", "Error updating stats", e)
                }
<<<<<<< HEAD
=======
=======
                .addOnSuccessListener { 
<<<<<<< HEAD
                    Log.d("TimerActivity", "Stats updated successfully!")
                    loadStats() 
=======
                    Log.d("TimerActivity", "Stats updated successfully!") 
>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
                }
                .addOnFailureListener { e -> 
                    Log.w("TimerActivity", "Error updating stats", e) 
                }
<<<<<<< HEAD
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd

            val session = hashMapOf(
                "type" to "Focus",
                "duration" to focusTime / 1000,
                "timestamp" to Timestamp.now()
            )
            userDocRef.collection("sessions").add(session)
        }
    }

    private fun loadStats() {
        val user = auth.currentUser
        if (user != null) {
            val userDocRef = db.collection("users").document(user.uid)
            userDocRef.get().addOnSuccessListener {
                if (it.exists()) {
<<<<<<< HEAD
                    val name = it.getString("name")
                    binding.welcomeText.text = "Welcome, $name"

=======
<<<<<<< HEAD
                    val name = it.getString("name")
                    binding.welcomeText.text = "Welcome, $name"

=======
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                    val totalFocusTime = it.getLong("totalFocusTime") ?: 0
                    val sessionsCompleted = it.getLong("sessionsCompleted") ?: 0
                    val hours = totalFocusTime / 3600
                    val minutes = (totalFocusTime % 3600) / 60
                    binding.totalFocusTimeValue.text = "${hours}h ${minutes}m"
                    binding.sessionsCompletedValue.text = sessionsCompleted.toString()
                }
            }
<<<<<<< HEAD
        }
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
<<<<<<< HEAD
=======
=======
>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
        }
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
    }

    private inner class FetchQuoteTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            var urlConnection: HttpURLConnection? = null
            try {
                val url = URL("https://zenquotes.io/api/random")
                urlConnection = url.openConnection() as HttpURLConnection
<<<<<<< HEAD
<<<<<<< HEAD
                urlConnection.connectTimeout = 5000
                urlConnection.readTimeout = 5000
=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val stringBuilder = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    stringBuilder.append(line)
                }
                return stringBuilder.toString()
            } catch (e: Exception) {
<<<<<<< HEAD
<<<<<<< HEAD
                Log.e("TimerActivity", "Error fetching quote: ${e.message}")
=======
                e.printStackTrace()
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
                e.printStackTrace()
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
            } finally {
                urlConnection?.disconnect()
            }
            return null
        }

        override fun onPostExecute(result: String?) {
<<<<<<< HEAD
<<<<<<< HEAD
            var finalQuote = currentQuoteText
=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
            if (result != null) {
                try {
                    val jsonArray = JSONArray(result)
                    val jsonObject = jsonArray.getJSONObject(0)
                    val quote = jsonObject.getString("q")
                    val author = jsonObject.getString("a")
<<<<<<< HEAD
<<<<<<< HEAD
                    finalQuote = "\"$quote\" — $author"
                } catch (e: Exception) {
                    Log.e("TimerActivity", "Error parsing quote: ${e.message}")
                }
            }
            
            pendingCompletionMessage?.let {
                showCompletionPopup(it, finalQuote)
                pendingCompletionMessage = null
            }
=======
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                    showQuoteDialog(quote, author)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
<<<<<<< HEAD
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
        }
    }
}