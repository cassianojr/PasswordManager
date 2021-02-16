package br.com.cassianojunior.passwordmanager.ui.create_password

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cassianojunior.passwordmanager.R

class CreatePasswordFragment : Fragment() {

    companion object {
        fun newInstance() = CreatePasswordFragment()
    }

    private lateinit var viewModel: CreatePasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreatePasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}