package com.anesabml.hospital

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.anesabml.hospital.auth.ui.LoginActivity
import com.anesabml.hospital.core.extensions.hide
import com.anesabml.hospital.core.extensions.show
import com.anesabml.hospital.core.model.Patient
import com.anesabml.hospital.core.utils.Result
import com.anesabml.hospital.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val LOGIN_REQUEST_CODE = 563
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appComponent = (application as MyApplication).appComponent

        appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewModel.patient.observe(this) {
            when (it) {
                Result.Loading -> {
                    binding.progressBar.show()
                }

                is Result.Success -> {
                    binding.progressBar.hide()
                    setupNavigation(it.data)
                }

                is Result.Error -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.root, R.string.error_login, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupNavigation(patient: Patient? = null) {

        // Change the start destination depending on whether the user has logged in or not
        if (patient == null) {

            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, LOGIN_REQUEST_CODE)

        } else {

            // Get the navigation controller
            navController = findNavController(R.id.main_nav_host)

            val graph = navController.navInflater.inflate(R.navigation.main_graph)

            val bundle = Bundle().apply {
                putSerializable("patient", patient)
            }
            navController.setGraph(graph, bundle)

            with(binding.bottomNavView) {
                isVisible = true
                setupWithNavController(navController)
                setOnNavigationItemSelectedListener {
                    when (it.itemId) {
                        R.id.appointments_fragment -> {
                            navController.navigate(R.id.appointments_fragment, bundle)
                        }
                        R.id.prescription_fragment -> {
                            navController.navigate(R.id.prescription_fragment, bundle)
                        }
                        R.id.profile_fragment -> {
                            navController.navigate(R.id.profile_fragment, bundle)
                        }
                        else -> {
                            navController.navigate(it.itemId)
                        }
                    }
                    true
                }
            }

            // Create the appBarConfiguration
            val appBarConfiguration =
                AppBarConfiguration(
                    setOf(
                        R.id.appointments_fragment,
                        R.id.prescription_fragment,
                        R.id.profile_fragment
                    )
                )

            // Setup the toolbar
            setupActionBarWithNavController(navController, appBarConfiguration)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LOGIN_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val patient = data.getSerializableExtra("patient") as Patient
                    setupNavigation(patient)
                } else {
                    finish()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onNavigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onNavigateUp()
    }
}
