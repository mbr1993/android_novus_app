package uz.mbr.novustest.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.mbr.novustest.R
import uz.mbr.novustest.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignUpBinding.bind(view)

        with(binding) {
            filledTextField.errorIconDrawable = null
            editText.addTextChangedListener {
                filledTextField.error = null
            }

            btnContinue.setOnClickListener {
                val action =
                    SignUpFragmentDirections.actionSignUpFragmentToVerificationFragment(
                        phoneNumber = binding.editText.text.toString()
                    )

                if (editText.length() == 9) findNavController().navigate(action)
                else filledTextField.error = "Enter correct phone number"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}