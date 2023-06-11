package ru.kapuchinka.kinosklad

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.kapuchinka.kinosklad.api.service.CategoryApi
import ru.kapuchinka.kinosklad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://193.233.49.143/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val categoryApi = retrofit.create(CategoryApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val category = categoryApi.getCategoryById(0)
            runOnUiThread {
                print(category.category_id)
                print(category.category_name)
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView = binding.navView

        navView.setupWithNavController(navController)
        visibilityNavElements()
    }

    private fun visibilityNavElements() {
        findNavController(R.id.nav_host_fragment_activity_main).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    navView.visibility = View.GONE
                }

                else -> {
                    navView.visibility = View.VISIBLE
                }
            }
        }
    }
}