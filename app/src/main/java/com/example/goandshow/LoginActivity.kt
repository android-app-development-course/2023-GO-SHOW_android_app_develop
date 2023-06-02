package com.example.goandshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class LoginActivity : AppCompatActivity() {

    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var toSignUpButton: Button

    val dbHelper = MyDatabaseHelper(this, "userInfo.db", 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)
        loginButton = findViewById(R.id.loginButton)
        toSignUpButton = findViewById(R.id.toSignUpButton)

        // val dbHelper = MyDatabaseHelper(this, "userInfo.db", 2)

        // loginEmail.setOnClickListener {
            // val cursorOffset = 2
            // loginEmail.setSelection(cursorOffset)
        // }

        loginButton.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            if (isValidCredentials(email, password)) {
                // 登录成功，进行相应的操作
                showToast("Login successful")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showToast("Invalid account or password")
            }
        }

        toSignUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isValidCredentials(email: String, password: String): Boolean {
        // 在这里添加你的验证逻辑，例如检查账号和密码是否匹配
        // return email == "admin" && password == "123456"
        val db = dbHelper.readableDatabase
        val projection = arrayOf("user_email")
        val selection = "user_email = ? AND user_password = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query("user_account", projection, selection, selectionArgs, null, null, null)

        val isValid = cursor.count > 0
        cursor.close()
        return isValid
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}