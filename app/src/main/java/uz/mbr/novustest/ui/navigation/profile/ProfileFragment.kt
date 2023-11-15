package uz.mbr.novustest.ui.navigation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.mbr.novustest.R
import uz.mbr.novustest.SharedPref
import uz.mbr.novustest.databinding.BottomSheetDialogLanguageBinding
import uz.mbr.novustest.databinding.FragmentProfileBinding
import kotlin.system.exitProcess

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var mySharedPref: SharedPref

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        mySharedPref = SharedPref(requireContext())

        with(binding) {
            tvPhoneNumber.text = getString(R.string.phone_number, mySharedPref.getPhoneNumber())

            exitBtn.setOnClickListener {
                activity?.finish()
                exitProcess(0)

            }
            cvOrderHistory.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_orderHistoryFragment)
            }
            cvPprofile.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    "You can not edit this time",
                    Toast.LENGTH_SHORT
                ).show()
            }
            editBtn.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    "You can not edit this time",
                    Toast.LENGTH_SHORT
                ).show()
            }
            tvLanguage.setOnClickListener { chooseLanguageDialog() }
            cvLanguage.setOnClickListener { chooseLanguageDialog() }
        }
    }

    private fun chooseLanguageDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val dialogBinding =
            BottomSheetDialogLanguageBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        with(dialogBinding) {
            tvEnglish.setOnClickListener { dialog.dismiss() }
            tvUzbek.setOnClickListener { dialog.dismiss() }
        }
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}