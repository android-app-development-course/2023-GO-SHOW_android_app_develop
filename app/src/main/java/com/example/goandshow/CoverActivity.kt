package com.example.goandshow

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CoverActivity : AppCompatActivity() {

    private lateinit var coverImage: ImageButton
    private val countdownInterval: Long = 1000 // 时间间隔1000毫秒
    private val totalCountdownTime: Long = 1000 // 倒计时5000毫秒

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cover)

        coverImage = findViewById(R.id.coverImage)

        object : CountDownTimer(totalCountdownTime, countdownInterval) {
            // 倒计时时可触发的事件：点击图片跳转页面
            override fun onTick(millisUntilFinished: Long) {
                coverImage.setOnClickListener {
                    val intent = Intent(this@CoverActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            // 倒计时结束时触发的事件：自动跳转页面
            override fun onFinish() {
                val intent = Intent(this@CoverActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}