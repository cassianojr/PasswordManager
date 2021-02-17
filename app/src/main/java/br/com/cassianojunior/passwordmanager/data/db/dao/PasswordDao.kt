package br.com.cassianojunior.passwordmanager.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.cassianojunior.passwordmanager.data.db.entity.PasswordEntity

@Dao
interface PasswordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(password:PasswordEntity)

    @Query("SELECT * FROM password")
    fun getPasswordList():LiveData<List<PasswordEntity>>

    @Query("SELECT * FROM password WHERE id = :id")
    fun getPassword(id:Long):PasswordEntity
}