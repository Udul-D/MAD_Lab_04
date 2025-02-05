package com.example.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LaunchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)

        var button = findViewById<Button>(R.id.button)

        button.setOnClickListener() {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}