package bans.qaadhar.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bans.qaadhar.adapters.CardsAdapter
import bans.qaadhar.databinding.ActivityDatabaseBinding
import bans.qaadhar.log.Log
import bans.qaadhar.models.AadharCard
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class DatabaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatabaseBinding
    private val dataSet: ArrayList<AadharCard> = ArrayList()
    private lateinit var mAdapter: CardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupDatabase()

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = CardsAdapter(this, dataSet) {
            val intent = Intent(this, AadharViewActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("aadhar", it)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        binding.recyclerView.adapter = mAdapter

    }


    private fun setupDatabase() {
        // Read from the database
        val dbRef: DatabaseReference = Firebase.database.getReference("ber")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value: AadharCard? = dataSnapshot.getValue<AadharCard>()
                if (value != null) {
                    mAdapter.addItem(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("setupDatabase()", "Failed to read value." + error.toException())
            }
        })
    }

}