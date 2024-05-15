package com.example.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskapp.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding //accessing views in activity_add_task.xml file
    private lateinit var db: TasksDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= TasksDatabaseHelper(this)

        //When the save button is clicked, it retrieves the text entered in the title
        // and content EditText fields.
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            // Validate input data

            //if title and content is empty toast msj will appear
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Please enter both title and content", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //if title length is greater than 50 toast msj will appear
            if (title.length > 50) { // Adjust the maximum length as needed
                Toast.makeText(this, "Title is too long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //if content is greater
            if (content.length > 500) { // Adjust the maximum length as needed
                Toast.makeText(this, "Content is too long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val task = Task(0, title, content) //auto generate the id
            db.insertTask(task) // insert data into the database
            finish()
            Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show() // toast msg
        }

    }
}