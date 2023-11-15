package uz.mbr.novustest.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mbr.novustest.R
import uz.mbr.novustest.SharedPref
import uz.mbr.novustest.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var mySharedPref: SharedPref

    private val handler = Handler(Looper.getMainLooper())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashScreenBinding.bind(view)
        mySharedPref = SharedPref(requireContext())

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_animation)
        binding.logo.startAnimation(scaleAnimation)

        handler.postDelayed({
            if (mySharedPref.isRegistered()) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_navigation_home)
            } else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_signUpFragment)
            }
        }, 2000)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        handler.removeCallbacksAndMessages(null)
    }
}