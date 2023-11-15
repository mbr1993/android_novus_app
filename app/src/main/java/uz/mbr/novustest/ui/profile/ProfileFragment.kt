package uz.mbr.novustest.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mbr.novustest.R
import uz.mbr.novustest.databinding.FragmentProfileBinding
import kotlin.system.exitProcess

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        with(binding) {
            exitBtn.setOnClickListener {
                activity?.finish()
                exitProcess(0)

            }
            cvOrderHistory.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_orderHistoryFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}