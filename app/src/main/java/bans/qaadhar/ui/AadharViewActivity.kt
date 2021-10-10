package bans.qaadhar.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bans.qaadhar.databinding.ActivityAadharViewBinding
import bans.qaadhar.models.AadharCard
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AadharViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAadharViewBinding
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAadharViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val aadhar: AadharCard? = intent.getParcelableExtra("aadhar")

        if (aadhar != null) {
            binding.card.aadhar = aadhar
        } else {
            Toast.makeText(this, "Couldn't get the data", Toast.LENGTH_SHORT).show()
            finish()
        }

        dbRef = Firebase.database.getReference("ber")
        binding.save.setOnClickListener {
            save(aadhar)
        }

        // Auto save on startup
        save(aadhar)
    }

    private fun save(aadhar: AadharCard?) {
        if (aadhar != null) {
            dbRef.child(aadhar.uuid).setValue(aadhar)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "Error- " + it.message, Toast.LENGTH_SHORT)
                        .show()
                }
        } else {
            Toast.makeText(applicationContext, "Null values", Toast.LENGTH_SHORT)
                .show()
        }
    }

}