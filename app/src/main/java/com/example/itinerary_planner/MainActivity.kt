package com.example.itinerary_planner

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDuration: EditText
    private lateinit var etDay: EditText
    private lateinit var etTime: EditText
    private lateinit var etActivityDescription: EditText
    private lateinit var btnAddActivity: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var activityAdapter: ActivityAdapter
    private val activities = mutableListOf<Activity>()

    @SuppressLint("NotifyDataSetChanged", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTitle = findViewById(R.id.etTitle)
        etDuration = findViewById(R.id.etDuration)
        etDay = findViewById(R.id.etDay)
        etTime = findViewById(R.id.etTime)
        etActivityDescription = findViewById(R.id.etActivityDescription)
        btnAddActivity = findViewById(R.id.btnAddActivity)
        recyclerView = findViewById(R.id.recyclerView)

        // Set up the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        activityAdapter = ActivityAdapter(activities) { activity ->
            // Remove the selected activity
            activities.remove(activity)
            activityAdapter.notifyDataSetChanged()
        }
        recyclerView.adapter = activityAdapter

        // Add new activity on button click
        btnAddActivity.setOnClickListener {
            addActivity()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addActivity() {
        // Get input values and validate
        val day = etDay.text.toString().toIntOrNull()
        val time = etTime.text.toString()
        val description = etActivityDescription.text.toString()

        if (day != null && time.isNotBlank() && description.isNotBlank()) {
            // Create a new Activity instance and add it to the list
            val activity = Activity(day, time, description)
            activities.add(activity)
            activityAdapter.notifyDataSetChanged()  // Refresh the RecyclerView

            // Clear inputs for the next entry
            etDay.text.clear()
            etTime.text.clear()
            etActivityDescription.text.clear()
        }
    }
}