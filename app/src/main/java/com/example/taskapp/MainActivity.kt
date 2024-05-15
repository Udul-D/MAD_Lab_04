package com.example.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //to access the layout file
    private lateinit var db: TasksDatabaseHelper
    private lateinit var tasksAdapter: TasksAdapter //store data into a recycle view

    override fun onCreate(savedInstanceState: Bundle?) { // when activity is create onCreate method called
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= TasksDatabaseHelper(this)
        tasksAdapter= TasksAdapter(db.getAllTasks(), this)

        binding.tasksRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.tasksRecyclerView.adapter=tasksAdapter

        binding.addButton.setOnClickListener { //add button intent
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        tasksAdapter.refreshData(db.getAllTasks()) // retrieve updated data from the database
    }
}