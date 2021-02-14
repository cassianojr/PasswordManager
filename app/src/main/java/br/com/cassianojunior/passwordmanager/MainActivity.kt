package br.com.cassianojunior.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.cassianojunior.passwordmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

    }
}