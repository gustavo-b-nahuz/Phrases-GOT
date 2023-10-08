package com.example.phrasesgot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.phrasesgot.databinding.ActivityPhraseBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class PhraseActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding : ActivityPhraseBinding
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhraseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("name")
        binding.fraseOla.text = getString(R.string.greetings, nome)
        binding.botaoGerar.setOnClickListener(this)
        run("https://api.gameofthronesquotes.xyz/v1/random")
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.botao_gerar){
            run("https://api.gameofthronesquotes.xyz/v1/random")
        }
    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    try {
                        val responseBody = response.body()?.string()
                        val responseJson = JSONObject(responseBody.toString())
                        val sentence = responseJson.getString("sentence")

                        // Update UI with the extracted sentence (runOnUiThread is used to update UI from a background thread)
                        runOnUiThread {
                            binding.fraseGerada.text = sentence
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    // Handle non-successful response
                    println("Request failed with status code: ${response.code()}")
                }
            }
        })
    }
}