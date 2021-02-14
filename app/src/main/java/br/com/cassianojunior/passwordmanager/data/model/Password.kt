package br.com.cassianojunior.passwordmanager.data.model

data class Password (
    private val id:Long,
    private val website:String,
    private val username:String,
    private val password:String
)