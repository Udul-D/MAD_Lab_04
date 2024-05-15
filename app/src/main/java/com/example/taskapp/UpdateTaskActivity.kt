package com.example.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskapp.databinding.ActivityUpdateTaskBinding

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateTaskBinding
    private lateinit var db:TasksDatabaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= TasksDatabaseHelper(this)

        taskId=intent.getIntExtra("task_id",-1)
        if (taskId==-1){
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.updateContentEditText.setText(task.content)

        //when the updateSaveButton is clicked it retrieves the text entered in the title
        // and content EditText fields.
        binding.updateSaveButton.setOnClickListener{
            val newTitle=binding.updateTitleEditText.text.toString()
            val newContent=binding.updateContentEditText.text.toString()

            // Validate input data

            //if title and content is empty toast msj will appear
            if (newTitle.isEmpty() || newContent.isEmpty()) {
                Toast.makeText(this, "Please enter both title and content", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //if title length is greater than 50 toast msj will appear
            if (newTitle.length > 50) { // Adjust the maximum length as needed
                Toast.makeText(this, "Title is too long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //if content is greater
            if (newContent.length > 500) { // Adjust the maximum length as needed
                Toast.makeText(this, "Content is too long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedTask = Task(taskId, newTitle, newContent)
            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
