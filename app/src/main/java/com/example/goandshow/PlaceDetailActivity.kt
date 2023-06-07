package com.example.goandshow

import android.content.Context
import android.os.Bundle
import android.widget.*

import androidx.appcompat.app.AppCompatActivity


class PlaceDetailActivity : AppCompatActivity() {

    private var mUserName: String? = null
    private lateinit var commentAdapter: ArrayAdapter<String>

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

        // 获取当前用户的用户名
        val preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        mUserName = preferences.getString("user_name", null)

        // 获取评论输入框和提交按钮
        val commentText = findViewById<EditText>(R.id.comment_text)
        val submitButton = findViewById<Button>(R.id.submit_button)

        // 获取评论列表和适配器
        val commentList = findViewById<ListView>(R.id.comment_list)
        commentAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        commentList.adapter = commentAdapter

        // 注册提交按钮的点击事件
        submitButton.setOnClickListener {
            // 获取用户输入的评论内容
            val comment = commentText.text.toString().trim()

            if (!comment.isEmpty()) {
                // 添加用户名和评论内容到评论列表中
                val commentWithUserName = "$mUserName: $comment"
                commentAdapter.add(commentWithUserName)

                // 清空评论输入框
                commentText.setText("")

                // 更新评论列表视图
                commentAdapter.notifyDataSetChanged()

                // 保存评论数据到Shared Preferences
                saveCommentsToSharedPreferences(commentAdapter)
            }
        }

        // 从Shared Preferences中获取之前保存的评论数据
        val comments = preferences.getStringSet("comments", emptySet())

        // 将评论数据添加到评论适配器中
        if (comments != null) {
            commentAdapter.addAll(comments)
        }
    }

    private fun saveCommentsToSharedPreferences(commentAdapter: ArrayAdapter<String>) {
        val comments = mutableListOf<String>()
        for (i in 0 until commentAdapter.count) {
            comments.add(commentAdapter.getItem(i) ?: "")
        }

        val preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putStringSet("comments", comments.toSet())
        editor.apply()
    }
}
