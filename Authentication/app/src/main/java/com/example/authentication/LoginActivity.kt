package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.authentication.data.AppDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginBtn: Button
    private lateinit var LogToSign: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginUsername=findViewById(R.id.login_username)
        loginPassword=findViewById(R.id.login_password)
        loginBtn=findViewById(R.id.login_button)
        LogToSign=findViewById(R.id.login_to_signup)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "User").allowMainThreadQueries().build()
        val userDao= db.userDao()

        loginBtn.setOnClickListener {
            val tempUser= userDao.getUser(loginUsername.text.toString())
            if(tempUser.password.equals(loginPassword.text.toString())){
                Toast.makeText(baseContext,"Login Successful",Toast.LENGTH_SHORT).show()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(baseContext,"Invalid Username OR Password",Toast.LENGTH_SHORT).show()
            }
        }

        LogToSign.setOnClickListener {
            val intent= Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
    }
}