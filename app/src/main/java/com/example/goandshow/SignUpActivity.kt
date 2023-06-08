package com.example.goandshow

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpUserName: EditText
    private lateinit var signUpEmailAddress: EditText
    private lateinit var signUpPassword: EditText
    private lateinit var confirmSignUpPassword: EditText
    private lateinit var signUpButton: Button
    private lateinit var toLoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val dbHelper = MyDatabaseHelper(this, "userInfo.db", 2)

        signUpUserName = findViewById(R.id.signUpUserName)
        signUpEmailAddress = findViewById(R.id.signUpEmailAddress)
        signUpPassword = findViewById(R.id.signUpPassword)
        confirmSignUpPassword = findViewById(R.id.confirmSignUpPassword)
        signUpButton = findViewById(R.id.signUpButton)
        toLoginButton = findViewById(R.id.toLoginButton)

        signUpButton.setOnClickListener {
            if (signUpPassword.text.toString() != confirmSignUpPassword.text.toString()) {
                // 两次输入密码不一致，提示用户重新输入
                //confirmSignUpPassword.error = "Different Password Entered!"
                //confirmSignUpPassword.requestFocus()
                showToast("Different Password Entered!")
                return@setOnClickListener
            }

            // 创建SharedPreferences对象
            val sharedPreferences = getPreferences(Context.MODE_PRIVATE)

            // 将用户信息写入SharedPreferences文件
            val editor = sharedPreferences.edit()
            editor.putString("user_name", signUpUserName.text.toString())
            editor.putString("user_email", signUpEmailAddress.text.toString())
            editor.putString("user_password", signUpPassword.text.toString())
            editor.apply()

            val db = dbHelper.writableDatabase

            val value = ContentValues().apply {
                put("user_name", signUpUserName.text.toString())
                put("user_email", signUpEmailAddress.text.toString())
                put("user_password", signUpPassword.text.toString())
            }
            db.insert("user_account", null, value)
            showToast("Account Created!")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        toLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}