package com.example.goandshow
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class PlaceDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        // 获取传递的景点信息
        val intent = intent
        val name = intent.getStringExtra("name")
        val imageResId = intent.getIntExtra("imageResId", 0)
        val description = intent.getStringExtra("description")

        // 显示景点信息
        val placeImage = findViewById<ImageView>(R.id.place_image)
        val placeName = findViewById<TextView>(R.id.place_name)
        val placeDescription = findViewById<TextView>(R.id.place_description)
        placeImage.setImageResource(imageResId)
        placeName.text = name
        placeDescription.text = description
    }}