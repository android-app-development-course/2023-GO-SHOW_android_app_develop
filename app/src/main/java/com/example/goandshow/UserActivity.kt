package com.example.goandshow
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

@Suppress("DEPRECATION")
class UserActivity : AppCompatActivity() {

    private lateinit var logoutButton: Button
    private val REQUEST_IMAGE_PICK = 1

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
        back.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
            finish()
        }

        val imageView2 = findViewById<ImageButton>(R.id.imageView2)
        imageView2.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            val imageView2 = findViewById<ImageButton>(R.id.imageView2)

            imageView2.setImageURI(selectedImage)
        }
    }
}