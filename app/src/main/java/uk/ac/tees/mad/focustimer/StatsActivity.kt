package uk.ac.tees.mad.focustimer

import android.os.Bundle
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
<<<<<<< HEAD
<<<<<<< HEAD
import android.widget.Toast
=======
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.Locale
import uk.ac.tees.mad.focustimer.databinding.ActivityStatsBinding

data class Session(
    val type: String = "",
    val timestamp: com.google.firebase.Timestamp? = null,
<<<<<<< HEAD
<<<<<<< HEAD
    val duration: Long = 0 // in seconds
=======
    val duration: Long = 0
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
)

=======
    val duration: Long = 0
)

<<<<<<< HEAD
=======
=======
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.focustimer.databinding.ActivityStatsBinding

>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
class StatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

<<<<<<< HEAD
<<<<<<< HEAD
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
=======
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd

        loadStats()
    }

    private fun loadStats() {
<<<<<<< HEAD
<<<<<<< HEAD
        val user = auth.currentUser ?: return
        val userDocRef = db.collection("users").document(user.uid)

        userDocRef.collection("sessions").orderBy("timestamp", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { documents ->
                val sessions = documents.toObjects(Session::class.java)
                
                if (sessions.isEmpty()) {
                    binding.emptyStateView.visibility = View.VISIBLE
                    binding.recentActivityRecyclerView.visibility = View.GONE
                    updateSummary(0, 0)
                } else {
                    binding.emptyStateView.visibility = View.GONE
                    binding.recentActivityRecyclerView.visibility = View.VISIBLE
                    
                    val totalDurationSeconds = sessions.sumOf { it.duration }
                    updateSummary(totalDurationSeconds, sessions.size)
                    
=======
        val user = auth.currentUser
        if (user != null) {
=======
        val user = auth.currentUser
        if (user != null) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
            val userDocRef = db.collection("users").document(user.uid)

            userDocRef.collection("sessions").orderBy("timestamp", Query.Direction.DESCENDING).limit(10).get()
                .addOnSuccessListener { documents ->
                    val sessions = documents.toObjects(Session::class.java)
<<<<<<< HEAD
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
                    val adapter = SessionAdapter(sessions)
                    binding.recentActivityRecyclerView.adapter = adapter
                    binding.recentActivityRecyclerView.layoutManager = LinearLayoutManager(this)
                }
<<<<<<< HEAD
<<<<<<< HEAD
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to load stats: ${e.message}", Toast.LENGTH_SHORT).show()
                binding.emptyStateView.visibility = View.VISIBLE
            }
    }

    private fun updateSummary(totalSeconds: Long, count: Int) {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        binding.totalTimeText.text = if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
        binding.sessionCountText.text = count.toString()
        val avgMinutes = if (count > 0) (totalSeconds / count) / 60 else 0
        binding.avgSessionText.text = "${avgMinutes}m"
=======
        } else {
            // Handle the case where there is no user logged in
=======
        } else {
            // Handle the case where there is no user logged in
<<<<<<< HEAD
=======
=======
            db.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val totalFocusTime = document.getLong("totalFocusTime") ?: 0
                        val sessionsCompleted = document.getLong("sessionsCompleted") ?: 0

                        val hours = totalFocusTime / 3600
                        val minutes = (totalFocusTime % 3600) / 60

                        binding.totalFocusTimeValue.text = "${hours}h ${minutes}m"
                        binding.sessionsCompletedValue.text = sessionsCompleted.toString()
                    }
                }
>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
<<<<<<< HEAD
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
    }
=======
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
}

class SessionAdapter(private val sessions: List<Session>) : RecyclerView.Adapter<SessionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sessionType: TextView = view.findViewById(R.id.sessionType)
        val sessionDate: TextView = view.findViewById(R.id.sessionDate)
        val sessionDuration: TextView = view.findViewById(R.id.sessionDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_session, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = sessions[position]
<<<<<<< HEAD
<<<<<<< HEAD
        holder.sessionType.text = session.type.replaceFirstChar { it.uppercase() }
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
=======
        holder.sessionType.text = session.type
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
>>>>>>> 3498cc690c20aecf062c4d01b52f8c8a4195ec91
=======
        holder.sessionType.text = session.type
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
        holder.sessionDate.text = if (session.timestamp != null) sdf.format(session.timestamp.toDate()) else ""
        holder.sessionDuration.text = "${session.duration / 60} min"
    }

    override fun getItemCount() = sessions.size
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
>>>>>>> c4144caddc92ab5f19b818255ab88e93af1a979a
>>>>>>> 9a6205be34ae7be24b1d4534b310b2f34d77ddbd
}