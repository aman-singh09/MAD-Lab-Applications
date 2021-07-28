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
import com.example.authentication.data.User
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

        private lateinit var signupUsername: EditText
        private lateinit var signupPassword: EditText
        private lateinit var signupBtn: Button
        private lateinit var SignToLog: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signupUsername=findViewById(R.id.signup_username)
        signupPassword=findViewById(R.id.signup_password)
        signupBtn=findViewById(R.id.login_button)
        SignToLog=findViewById(R.id.login_to_signup)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "User").allowMainThreadQueries().build()
        val urDao= db.userDao()

        signupBtn.setOnClickListener {
            if(signupPassword.text.toString().length < 8 && !isvalidpassword(signupPassword.text.toString())){
                Toast.makeText(baseContext, "Invalid Password", Toast.LENGTH_SHORT).show()
            }
            else{
                val tempuser = User(signupUsername.text.toString(),signupPassword.text.toString())
                urDao.insertuser(tempuser)
                Toast.makeText(baseContext, "Sign Up Successful", Toast.LENGTH_SHORT).show()

            }
        }

        SignToLog.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isvalidpassword(pwd: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN= "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern= Pattern.compile(PASSWORD_PATTERN)
        matcher= pattern.matcher(pwd)
        return matcher.matches()
    }
}