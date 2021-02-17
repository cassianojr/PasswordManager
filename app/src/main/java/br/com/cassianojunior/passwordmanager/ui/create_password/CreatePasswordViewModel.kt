package br.com.cassianojunior.passwordmanager.ui.create_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.cassianojunior.passwordmanager.data.model.Password
import br.com.cassianojunior.passwordmanager.data.model.toPasswordEntity
import br.com.cassianojunior.passwordmanager.data.repository.PasswordManagerRepository
import kotlinx.coroutines.launch

class CreatePasswordViewModel(
       private val passwordManagerRepository: PasswordManagerRepository
) : ViewModel() {

    fun createPassword(website:String, username:String, password:String){
        viewModelScope.launch {
            val passwordObj = Password(0, website, username, password)
            passwordManagerRepository.createPassword(passwordObj.toPasswordEntity())
        }
    }

}