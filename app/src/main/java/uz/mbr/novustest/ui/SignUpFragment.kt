package uz.mbr.novustest.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mbr.novustest.R
import uz.mbr.novustest.SharedPref
import uz.mbr.novustest.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var mySharedPref: SharedPref

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignUpBinding.bind(view)
        mySharedPref = SharedPref(requireContext())

        with(binding) {
            filledTextField.errorIconDrawable = null
            editText.addTextChangedListener {
                filledTextField.error = null
            }

            btnContinue.setOnClickListener {
                val phoneNumber = editText.text.toString()
                val action =
                    SignUpFragmentDirections.actionSignUpFragmentToVerificationFragment(phoneNumber)

                if (editText.length() == 9) {
                    mySharedPref.setPhoneNumber(phoneNumber)
                    mySharedPref.setRegistered(true)
                    findNavController().navigate(action)
                } else filledTextField.error = "Enter correct phone number"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}