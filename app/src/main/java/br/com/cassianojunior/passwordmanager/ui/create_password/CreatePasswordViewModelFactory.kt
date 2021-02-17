package br.com.cassianojunior.passwordmanager.ui.create_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.cassianojunior.passwordmanager.data.repository.PasswordManagerRepository

class CreatePasswordViewModelFactory(
        private val passwordManagerRepository: PasswordManagerRepository
):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreatePasswordViewModel(passwordManagerRepository) as T
    }

}