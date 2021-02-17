package br.com.cassianojunior.passwordmanager.ui.vault

import androidx.lifecycle.*
import br.com.cassianojunior.passwordmanager.data.db.entity.PasswordEntity
import br.com.cassianojunior.passwordmanager.data.model.Password
import br.com.cassianojunior.passwordmanager.data.repository.PasswordManagerRepository
import br.com.cassianojunior.passwordmanager.internal.lazyDeferred
import kotlinx.coroutines.launch

class VaultViewModel(
        private val passwordManagerRepository: PasswordManagerRepository
) : ViewModel() {

    val listPasswords by lazyDeferred {
        passwordManagerRepository.getPasswordList()
    }

//    fun listPasswords():LiveData<List<PasswordEntity>>{
//        viewModelScope.launch {
//             passwordManagerRepository.getPasswordList()
//
//        }
//    }

}