package br.com.cassianojunior.passwordmanager.ui.vault

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.cassianojunior.passwordmanager.R

class VaultFragment : Fragment() {

    private lateinit var toolsviewModel: VaultViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsviewModel =
            ViewModelProvider(this).get(VaultViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_vault, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        toolsviewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}