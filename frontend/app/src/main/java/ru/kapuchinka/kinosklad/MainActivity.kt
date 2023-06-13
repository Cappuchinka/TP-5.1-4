package ru.kapuchinka.kinosklad

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kapuchinka.kinosklad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController
    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView = binding.navView

        navView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }
                R.id.navigation_favorites -> {
                    navController.navigate(R.id.navigation_favorites)
                    true
                }
                R.id.navigation_search -> {
                    navController.navigate(R.id.navigation_search)
                    true
                }
                R.id.navigation_profile -> {
                    if (pref?.getString("token", "").equals("")) {
                        navController.navigate(R.id.authFragment)
                    } else {
                        navController.navigate(R.id.navigation_profile)
                    }
                    true
                }
                else -> false
            }
        }
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