package com.example.flowersmarket

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowersmarket.databinding.ActivityMainBinding
import com.example.flowersmarket.ui.FlowersAdapter
import com.example.flowersmarket.viewmodel.FlowersViewModel

/*
1. Initialize the VM Object
2. Observer the changes in live data
3. Assign the value to recyclerview adapter
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FlowersViewModel

    private lateinit var adapter: FlowersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

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
        binding.rvFlowers.layoutManager = LinearLayoutManager(this@MainActivity)
        //adapter  = FlowersAdapter(viewModel.flowers.observe(t))

    }

    private fun observeData() {
        //View live data: error, loading, success
        //viewModel observes the changes in the data to update the UI

        viewModel.flowers.observe(this) {flowers ->
            if (flowers.isNotEmpty()) {
                Log.i("Data", "First flower: ${flowers[0].name}")

                // Assign the adapter
                binding.rvFlowers.adapter = FlowersAdapter(flowers)
            } else {
                Log.i("Data", "No flowers found")
                // Optional: You could show a "No items" text view here
            }
            binding.rvFlowers.adapter= FlowersAdapter(flowers)

        }

        viewModel.loading.observe(this){isLoading ->
            Log.i("Loading", isLoading.toString())

        }

        viewModel.error.observe(this){ errorMessage ->
            Log.i("Loading", errorMessage.toString())

            Toast.makeText(this, errorMessage,Toast.LENGTH_LONG).show()
        }

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[FlowersViewModel::class.java]
    }
}