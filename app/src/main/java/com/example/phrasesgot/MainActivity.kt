package com.example.phrasesgot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.phrasesgot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaoGuardar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.botao_guardar){
            val nome = binding.inputNome.text.toString()
            val intent = Intent(this, PhraseActivity::class.java)
            intent.putExtra("name", nome)
            startActivity(intent)

        }
    }
}