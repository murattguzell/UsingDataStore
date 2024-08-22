package com.muratguzel.usingdatastore

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.muratguzel.usingdatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ap = AppPref(this)
        CoroutineScope(Dispatchers.Main).launch {
            // save operations
            ap.saveName("Murat")
            ap.saveAge(22)
            ap.saveHeight(1.78)
            ap.saveSingle(true)
            val list = HashSet<String>()
            list.add("Ali")
            list.add("Veli")
            ap.saveFriendList(list)


            //delete operations -> ap.deleteName()

            //get operations

            val getName = ap.getName()
            Log.d("DataStore", getName)
            val getAge = ap.getAge()
            Log.d("DataStore", getAge.toString())
            val getHeight = ap.getHeight()
            Log.d("DataStore", getHeight.toString())
            val getSingle = ap.getSingle()
            Log.d("DataStore", getSingle.toString())
            val getFriendList = ap.getFriendList()
            if (getFriendList != null) {
                for (friend in getFriendList)
                    Log.d("DataStore", friend)
            }

            //counter operations
            var getCounter = ap.getCounter()
            if (getCounter == null) {
                getCounter = 1
            ap.saveCounter(getCounter)
            } else {
                getCounter ++
                ap.saveCounter(getCounter)
            }
            binding.textViewSayac.text = "Açılış sayısı $getCounter"
        }
    }
}