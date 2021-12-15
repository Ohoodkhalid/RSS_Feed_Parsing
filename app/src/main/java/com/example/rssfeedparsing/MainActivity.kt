package com.example.rssfeedparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RecyclerViewAdapter
    private lateinit var details: List<Details>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        details = listOf()

        rvMain=findViewById(R.id.rvMain)
        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RecyclerViewAdapter(details)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        parseRSS()

    }
    private fun parseRSS(){
        CoroutineScope(IO).launch {
            val data = async {
                val parser = XMLParser()
                parser.parse()
            }.await()
            try{
                withContext(Main){
                    rvAdapter.update(data)
                }
            }catch(e: java.lang.Exception){
                Log.d("MAIN", "Unable to get data")
            }
        }
    }
}