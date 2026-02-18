package com.example.flowersmarket

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.flowersmarket.viewmodel.FlowersViewModel


/*
1. Initialize the VM Object
2. Observer the changes in live data
3. Assign the value to recyclerview adapter
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FlowersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Set up recyclerview
        setUpRecyclerView()
        setUpViewModel()
        observeData()
        viewModel.fetchFlowers()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUpRecyclerView() {
        TODO("Not yet implemented")
    }

    private fun observeData() {
        //View live data: error, loading, success

        viewModel.flowers.observe(this) {flowers ->
            Log.i("Data", flowers[0].name)
        }

        viewModel.loading.observe(this){isLoading ->

        }

        viewModel.error.observe(this){ errorMessage ->
            Toast.makeText(this, errorMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpViewModel() {
        //TODO: HILT DI

        viewModel = ViewModelProvider(this)[FlowersViewModel::class.java]
    }
}