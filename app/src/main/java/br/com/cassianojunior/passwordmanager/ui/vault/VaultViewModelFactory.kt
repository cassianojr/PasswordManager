package br.com.cassianojunior.passwordmanager.ui.vault

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.cassianojunior.passwordmanager.data.repository.PasswordManagerRepository

class VaultViewModelFactory(
        private val passwordManagerRepository: PasswordManagerRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VaultViewModel(passwordManagerRepository) as T
    }

}