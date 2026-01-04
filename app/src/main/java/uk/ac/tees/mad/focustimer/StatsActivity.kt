package uk.ac.tees.mad.focustimer

import android.os.Bundle
<<<<<<< HEAD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    val duration: Long = 0
)

=======
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.focustimer.databinding.ActivityStatsBinding

>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        loadStats()
    }

    private fun loadStats() {
        val user = auth.currentUser
        if (user != null) {
<<<<<<< HEAD
            val userDocRef = db.collection("users").document(user.uid)

            userDocRef.collection("sessions").orderBy("timestamp", Query.Direction.DESCENDING).limit(10).get()
                .addOnSuccessListener { documents ->
                    val sessions = documents.toObjects(Session::class.java)
                    val adapter = SessionAdapter(sessions)
                    binding.recentActivityRecyclerView.adapter = adapter
                    binding.recentActivityRecyclerView.layoutManager = LinearLayoutManager(this)
                }
        } else {
            // Handle the case where there is no user logged in
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
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
<<<<<<< HEAD
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
        holder.sessionType.text = session.type
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        holder.sessionDate.text = if (session.timestamp != null) sdf.format(session.timestamp.toDate()) else ""
        holder.sessionDuration.text = "${session.duration / 60} min"
    }

    override fun getItemCount() = sessions.size
=======
>>>>>>> 82d5e1e42278d6baf72332826bb700cc5cb22bf1
}