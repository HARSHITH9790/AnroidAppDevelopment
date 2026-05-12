package com.example.nammarailubuddy

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class TrainListActivity : AppCompatActivity() {

    lateinit var listView: ListView
    val filteredTrains = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_list)

        listView = findViewById(R.id.trainListView)

        val from = intent.getStringExtra("from")
        val to = intent.getStringExtra("to")

        loadTrains(from, to)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            filteredTrains
        )

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->

            val selectedTrain = filteredTrains[position]

            val intent = Intent(this, TrainDetailActivity::class.java)

            intent.putExtra("from", from)
            intent.putExtra("to", to)
            intent.putExtra("train", selectedTrain)
            intent.putExtra("platform", selectedTrain)

            startActivity(intent)
        }
    }

    fun loadTrains(from: String?, to: String?) {

        val json = assets.open("trains.json")
            .bufferedReader()
            .use { it.readText() }

        val array = JSONArray(json)

        for (i in 0 until array.length()) {

            val obj = array.getJSONObject(i)

            val trainFrom = obj.getString("from")
            val trainTo = obj.getString("to")
            val name = obj.getString("name")
            val platform = obj.getString("platform")
            val time = obj.getString("time")

            if (trainFrom == from && trainTo == to) {

                val trainInfo =
                    "$name\nTime: $time\nPlatform: $platform"

                filteredTrains.add(trainInfo)
            }
        }
    }
}