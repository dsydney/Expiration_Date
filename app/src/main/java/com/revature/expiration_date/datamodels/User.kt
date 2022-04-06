package com.revature.expiration_date.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User_list")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name = "email")
    val email:String,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "password")
    val password:String

)
