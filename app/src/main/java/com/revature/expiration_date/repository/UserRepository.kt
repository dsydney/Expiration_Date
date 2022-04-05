package com.revature.expiration_date.repository

import androidx.lifecycle.LiveData
import com.revature.expiration_date.dao.UserDao
import com.revature.expiration_date.datamodels.User

class UserRepository( private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }
}