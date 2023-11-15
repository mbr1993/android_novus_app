package uz.mbr.novustest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import uz.mbr.novustest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigate()
    }

    private fun setupNavigate() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.bottomNavView.setupWithNavController(navController)

        // Animation
        val navOptions: NavOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_right)
            .setPopExitAnim(R.anim.slide_out_left)
            .setPopUpTo(navController.graph.startDestinationId, false)
            .build()

        // Control Navigation
        binding.bottomNavView.setOnItemSelectedListener { item ->
            var handled = false
            if (navController.graph.findNode(item.itemId) != null) {

                if (item.itemId != binding.bottomNavView.selectedItemId)
                    navController.navigate(item.itemId, null, navOptions)
                handled = true
            }
            handled
        }

        // Controlling navigation in other screens
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_order, R.id.navigation_statistics, R.id.navigation_profile -> {
                    binding.bottomNavView.visibility = View.VISIBLE
                }

                else -> binding.bottomNavView.visibility = View.GONE
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        binding.bottomNavView.menu.getItem(0).isChecked = true
    }
}