package br.com.cassianojunior.passwordmanager.ui.vault

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cassianojunior.passwordmanager.R
import br.com.cassianojunior.passwordmanager.data.model.Password
import br.com.cassianojunior.passwordmanager.databinding.FragmentVaultBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class VaultFragment : Fragment() {

    private lateinit var toolsviewModel: VaultViewModel
    private var _binding: FragmentVaultBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVaultBinding.inflate(inflater, container, false)


//        val root = inflater.inflate(R.layout.fragment_vault, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolsviewModel = ViewModelProvider(this).get(VaultViewModel::class.java)

        bindUI()
    }

    private fun bindUI() {

        //Mocked passwords
        //TODO change this to data source
        val passwords = ArrayList<Password>()

        val p1 = Password(1, "Google", "cassianojr", "1234")
        val p2 = Password(2, "Facebook", "cassianojr123", "1234")
        val p3 = Password(3, "Twitter", "kyro23", "1234")
        val p4 = Password(4, "Github", "cassianojr_", "1234")
        val p5 = Password(5, "Spotify", "kyro23", "1234")
        val p6 = Password(6, "Valorant", "PaulaTejano", "1234")
        val p7 = Password(7, "Origin", "JucaBala", "1234")
        val p8 = Password(8, "COD", "SeLeuMorreu", "1234")

        passwords.add(p1)
        passwords.add(p2)
        passwords.add(p3)
        passwords.add(p4)
        passwords.add(p5)
        passwords.add(p6)
        passwords.add(p7)
        passwords.add(p8)

        initRecyclerView(passwords.toPasswordItems())
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