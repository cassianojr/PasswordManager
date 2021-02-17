package br.com.cassianojunior.passwordmanager.ui.create_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import br.com.cassianojunior.passwordmanager.data.db.AppDatabase
import br.com.cassianojunior.passwordmanager.data.repository.PasswordManagerRepositoryImpl
import br.com.cassianojunior.passwordmanager.databinding.CreatePasswordFragmentBinding

class CreatePasswordFragment : Fragment() {
    private var _binding: CreatePasswordFragmentBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CreatePasswordFragment()
    }

    private val viewModel: CreatePasswordViewModel by activityViewModels(
            factoryProducer = {
                val database = AppDatabase.getDatabase(requireContext())

                CreatePasswordViewModelFactory(passwordManagerRepository = PasswordManagerRepositoryImpl(database.passwordDao()))
            }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreatePasswordFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        handleCreateButton()
    }

    private fun handleCreateButton() {
        binding.btnNewPassword.setOnClickListener {
            val website = binding.textWebsite.text.toString()
            val username = binding.textUsername.text.toString()
            val password = binding.textPassword.text.toString()

            viewModel.createPassword(website, username, password)
            cleanFields()

            NavHostFragment.findNavController(this).popBackStack()
        }
    }

    private fun cleanFields() {
        binding.textWebsite.setText("")
        binding.textUsername.setText("")
        binding.textPassword.setText("")
    }

}