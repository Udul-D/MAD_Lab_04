package com.example.taskapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

//manage tasks in the recycler view
class TasksAdapter(private var tasks: List<Task>, context: Context) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>(){

    //communicate with the database
    private val db: TasksDatabaseHelper = TasksDatabaseHelper(context)

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView:TextView = itemView.findViewById(R.id.titleTextview)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)


    }

    //this method called when recycler view needs view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size //return no of tasks

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {//bind data to each view holder
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.contentTextView.text=task.content

        // set click listener for update button
        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateTaskActivity::class.java).apply {
                putExtra("task_id", task.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        // set click listener for delete button
        holder.deleteButton.setOnClickListener {
            db.deleteTask(task.id)
            refreshData(db.getAllTasks())
            Toast.makeText(holder.itemView.context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newTasks:List<Task>){// refresh new data set
        tasks = newTasks
        notifyDataSetChanged()//notifies adapter that data set has changed
    }


}