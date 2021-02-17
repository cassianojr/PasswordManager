package br.com.cassianojunior.passwordmanager.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.cassianojunior.passwordmanager.data.model.Password
import br.com.cassianojunior.passwordmanager.data.model.toPasswordEntity

@Entity(tableName = "password")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    val website:String,
    val username:String,
    val password:String
)

fun PasswordEntity.toPassword():Password{
    return Password(
            id = this.id,
            website = this.website,
            username = this.username,
            password = this.password
    )
}



