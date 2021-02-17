package br.com.cassianojunior.passwordmanager.data.model

import br.com.cassianojunior.passwordmanager.data.db.entity.PasswordEntity

data class Password (
    val id: Long,
    val website: String,
    val username: String,
    val password: String
)

fun Password.toPasswordEntity():PasswordEntity{
    return PasswordEntity(
        id = this.id,
        website = this.website,
        username = this.username,
        password = this.password
    )
}