package br.com.cassianojunior.passwordmanager.ui.vault

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cassianojunior.passwordmanager.R
import br.com.cassianojunior.passwordmanager.data.db.AppDatabase
import br.com.cassianojunior.passwordmanager.data.db.entity.toPassword
import br.com.cassianojunior.passwordmanager.data.model.Password
import br.com.cassianojunior.passwordmanager.data.repository.PasswordManagerRepositoryImpl
import br.com.cassianojunior.passwordmanager.databinding.FragmentVaultBinding
import br.com.cassianojunior.passwordmanager.ui.base.ScopedFragment
import br.com.cassianojunior.passwordmanager.ui.create_password.CreatePasswordViewModel
import br.com.cassianojunior.passwordmanager.ui.create_password.CreatePasswordViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VaultFragment : ScopedFragment() {

    private var _binding: FragmentVaultBinding? = null

    private val viewModel: VaultViewModel by activityViewModels(
            factoryProducer = {
                val database = AppDatabase.getDatabase(requireContext())

                VaultViewModelFactory(passwordManagerRepository = PasswordManagerRepositoryImpl(database.passwordDao()))
            }
    )

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVaultBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main){

        val passwords = viewModel.listPasswords.await()

        passwords.observe(viewLifecycleOwner, Observer { passwordEntityes->
            if(passwordEntityes == null) return@Observer

            val passwordEntries = passwordEntityes.map { passwordEntity ->
                passwordEntity.toPassword()
            }

            initRecyclerView(passwordEntries.toPasswordItems())
        })

        handleAddButtonClick()
    }

    private fun handleAddButtonClick() {
        binding.btnNewPassword.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_navigation_vault_to_createPasswordFragment)
        }
    }

    private fun List<Password>.toPasswordItems():List<PasswordItem>{
        return this.map{
            PasswordItem(it)
        }
    }

    private fun initRecyclerView(items:List<PasswordItem>){
        val groupAdapter =  GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@VaultFragment.context)
            adapter = groupAdapter
        }
    }
}