package com.example.goandshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class UserActivity : AppCompatActivity() {

    private lateinit var logoutButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        logoutButton = findViewById(R.id.logoutButton)

        logoutButton.setOnClickListener {
            val intent = Intent(this, CoverActivity::class.java)
            startActivity(intent)
            finish()
        }
        val back = findViewById<ImageButton>(R.id.imageButton1)

        back.setOnClickListener{
            val intent1 = Intent(this,MainActivity::class.java)
            startActivity(intent1)
            finish()
        }

    }
}