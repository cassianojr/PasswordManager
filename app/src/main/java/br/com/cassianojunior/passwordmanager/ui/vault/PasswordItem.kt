package br.com.cassianojunior.passwordmanager.ui.vault

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.Toast
import br.com.cassianojunior.passwordmanager.R
import br.com.cassianojunior.passwordmanager.data.model.Password
import br.com.cassianojunior.passwordmanager.databinding.ItemPasswordBinding
import com.xwray.groupie.viewbinding.BindableItem

class PasswordItem(private val password:Password):BindableItem<ItemPasswordBinding>() {

    override fun bind(viewBinding: ItemPasswordBinding, position: Int) {
        viewBinding.apply {
            viewBinding.textViewWebsite.text = password.website
            viewBinding.textViewUsername.text = password.username
        }
        setClipboard(viewBinding)
    }

    private fun setClipboard(viewBinding: ItemPasswordBinding) {
        viewBinding.imageViewCopy.setOnClickListener { view ->
            val clipManager: ClipboardManager =
                view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(password.website, password.password)
            clipManager.setPrimaryClip(clip)

            Toast.makeText(view.context, "Copyed to Clipboard!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getLayout() = R.layout.item_password

    override fun initializeViewBinding(view: View): ItemPasswordBinding {
        return ItemPasswordBinding.bind(view)
    }
}