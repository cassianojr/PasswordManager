package br.com.cassianojunior.passwordmanager.data.repository

import androidx.lifecycle.LiveData
import br.com.cassianojunior.passwordmanager.data.db.dao.PasswordDao
import br.com.cassianojunior.passwordmanager.data.db.entity.PasswordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PasswordManagerRepositoryImpl(
        private val passwordDao: PasswordDao
):PasswordManagerRepository {

    override suspend fun getPasswordList(): LiveData<out List<PasswordEntity>> {
        return withContext(Dispatchers.IO){
            return@withContext passwordDao.getPasswordList()
        }
    }

    override suspend fun createPassword(passwordEntity: PasswordEntity) {
        return withContext(Dispatchers.IO){
            return@withContext passwordDao.save(passwordEntity)
        }
    }
}