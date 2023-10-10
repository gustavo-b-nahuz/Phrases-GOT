package com.example.phrasesgot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.phrasesgot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaoGuardar.setOnClickListener(this)
        mainVM = ViewModelProvider(this)[MainViewModel::class.java]
        setObserver()
    }

    private fun setObserver() {
        mainVM.getName().observe(this,Observer{
            val intent = Intent(this, PhraseActivity::class.java)
            intent.putExtra("name", mainVM.getName().value.toString())
            startActivity(intent)
        })
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.botao_guardar){
            val nome = binding.inputNome.text.toString()
            mainVM.changeName(nome)
        }
    }

}