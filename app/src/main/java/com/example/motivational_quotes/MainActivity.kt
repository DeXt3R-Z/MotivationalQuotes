package com.example.motivational_quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var quote: TextView
    private lateinit var author: TextView
    private lateinit var generate: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quote = findViewById(R.id.tvQuote)
        author = findViewById(R.id.tv_author)
        generate = findViewById(R.id.btnGenerate)
        val quotesApi = RetrofitInstance.getInstance().create(ApiInterface::class.java)

        getQuotes(quotesApi)

        generate.setOnClickListener {
            getQuotes(quotesApi)
        }

    }

    private fun getQuotes(quotesApi: ApiInterface)
    {
        CoroutineScope(Dispatchers.IO).launch {
            val result = quotesApi.getQuotes()
            if (result!=null)
            {
                val body = result.body()
                if(body!=null)
                {
                    withContext(Dispatchers.Main)
                    {
                        quote.text = body[0].content
                        author.text = body[0].author
                    }
                }
            }
        }
    }
}