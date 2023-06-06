package com.example.goandshow
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.baidu.mapapi.map.BitmapDescriptor
import com.baidu.mapapi.map.BitmapDescriptorFactory


class PlaceDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)
        val intent = intent
        val name = intent.getStringExtra("title")
        val imageName = intent.getStringExtra("imgid")
        val description = intent.getStringExtra("description")

        // 显示景点信息
        val placeImage = findViewById<ImageView>(R.id.place_image)
        val placeName = findViewById<TextView>(R.id.place_name)
        val placeDescription = findViewById<TextView>(R.id.place_description)

        // 获取图片资源ID
        val resId = resources.getIdentifier(imageName, "drawable", "com.example.goandshow")

        // 设置图片资源
        placeImage.setImageResource(resId)

        placeName.text = name
        placeDescription.text = description
    }}