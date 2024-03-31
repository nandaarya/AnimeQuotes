package com.example.animequotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.animequotes.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(binding?.root)

        val navView: BottomNavigationView? = binding?.bottomNavigation
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment -> {
                    navView?.visibility = View.GONE
                    supportActionBar?.apply {
                        title = getString(R.string.quote_details)
                        setDisplayHomeAsUpEnabled(true)
                    }
                }
                R.id.favoriteFragment -> {
                    navView?.visibility = View.VISIBLE
                    supportActionBar?.apply {
                        title = getString(R.string.favorite_quote)
                        setDisplayHomeAsUpEnabled(false)
                    }
                }
                else -> {
                    navView?.visibility = View.VISIBLE
                    supportActionBar?.apply {
                        title = getString(R.string.app_name)
                        setDisplayHomeAsUpEnabled(false)
                    }
                }
            }
        }

        binding?.bottomNavigation?.setupWithNavController(navController)
    }

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}