package br.com.cassianojunior.passwordmanager.data.model

data class Password (
    val id: Long,
    val website: String,
    val username: String,
    val password: String
)