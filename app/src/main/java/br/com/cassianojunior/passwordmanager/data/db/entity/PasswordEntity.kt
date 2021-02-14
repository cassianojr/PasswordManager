package br.com.cassianojunior.passwordmanager.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "password")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    private val website:String,
    private val username:String,
    private val password:String

)
