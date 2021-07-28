package com.example.authentication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao{
    @Query("SELECT * FROM user WHERE username ==:usr")
    fun getUser(usr: String): User

    @Insert
    fun insertuser(user: User)
}