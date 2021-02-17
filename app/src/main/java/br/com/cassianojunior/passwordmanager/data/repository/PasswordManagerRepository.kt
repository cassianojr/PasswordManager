package br.com.cassianojunior.passwordmanager.data.repository

import androidx.lifecycle.LiveData
import br.com.cassianojunior.passwordmanager.data.db.entity.PasswordEntity

interface PasswordManagerRepository {

    suspend fun getPasswordList():LiveData<out List<PasswordEntity>>

    suspend fun createPassword(passwordEntity: PasswordEntity)
}