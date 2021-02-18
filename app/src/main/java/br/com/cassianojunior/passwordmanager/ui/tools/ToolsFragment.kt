package br.com.cassianojunior.passwordmanager.ui.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.cassianojunior.passwordmanager.databinding.FragmentToolsBinding

class ToolsFragment : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel

    private var _binding: FragmentToolsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        toolsViewModel =
            ViewModelProvider(this).get(ToolsViewModel::class.java)
        _binding = FragmentToolsBinding.inflate(inflater, container, false)

        toolsViewModel.genRandomPassword()
        setObservers()

        return binding.root
    }

    private fun setObservers() {
        toolsViewModel.length.observe(viewLifecycleOwner, {
            binding.labelLengthNumb.text = it.toString()
        })

        toolsViewModel.passwordGenerated.observe(viewLifecycleOwner, {
            binding.txtPasswordGenerated.text = it
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setSeekBarListener()
        setSwitchesListeners()
        setCreateButtonListener()
    }

    private fun setCreateButtonListener() {
        binding.btnGeneratePassword.setOnClickListener{
            val passwordGenerated = toolsViewModel.passwordGenerated.value
            val action = ToolsFragmentDirections.actionNavigationToolsToCreatePasswordFragment(passwordGenerated.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun setSeekBarListener() {
        binding.seekPasswordLength.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                toolsViewModel.length.value = i
                toolsViewModel.genRandomPassword()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    private fun setSwitchesListeners() {
        binding.switchNumbers.setOnClickListener {
            toolsViewModel.includeNumbers.value = binding.switchNumbers.isChecked
            toolsViewModel.genRandomPassword()
        }

        binding.switchLetters.setOnClickListener {
            toolsViewModel.includeLetters.value = binding.switchLetters.isChecked
            toolsViewModel.genRandomPassword()
        }

        binding.switchSymbols.setOnClickListener {
            toolsViewModel.includeSymbols.value = binding.switchSymbols.isChecked
            toolsViewModel.genRandomPassword()
        }
    }
}

