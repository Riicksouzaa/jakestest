package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupNavigation()
        setToolbarBackButton()
    }

    // SCREEN SETUP section start

    private fun setToolbarBackButton() = with(binding) {
        setSupportActionBar(mainActivityToolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mainActivityToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupNavigation() = with(binding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            updateScreenForFragment(destination.id)
        }
    }

    private fun updateScreenForFragment(fragmentId: Int?) = with(binding) {
        // Update toolbar
        when (fragmentId) {
            R.id.loginFragment -> {
                // showing toolbar for screens with titles
                mainActivityToolbar.visibility = View.GONE
            }
            R.id.photoListFragment -> {
                mainActivityToolbar.visibility = View.VISIBLE
            }
            else -> {
                mainActivityToolbar.visibility = View.GONE
            }
        }
    }
}