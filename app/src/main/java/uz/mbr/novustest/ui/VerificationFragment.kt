package uz.mbr.novustest.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.mbr.novustest.R
import uz.mbr.novustest.databinding.FragmentVerificationBinding
import kotlin.random.Random

class VerificationFragment : Fragment(R.layout.fragment_verification) {
    private var _binding: FragmentVerificationBinding? = null
    private val binding get() = _binding!!
    private val handler = Handler(Looper.getMainLooper())

    private var list: MutableList<Int> = ArrayList()
    private var listBoxes: MutableList<EditText> = ArrayList()

    private val args: VerificationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVerificationBinding.bind(view)

        //adding all EditTexts to list
        fillListBoxes()
        //Generating face codes to verification boxes
        fillVerificationWithNumbers()

        //Getting phone number data from safeArgs
        val phoneNumber = args.phoneNumber


        with(binding) {
            textSendingInform.text = getString(R.string.text_code_sent, phoneNumber)

            iconBack.setOnClickListener { findNavController().navigateUp() }

            btnNext.setOnClickListener {
                if (isCorrect()) {
                    findNavController().navigate(R.id.action_verificationFragment_to_navigation_home)
                } else {
                    errorText.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun fillVerificationWithNumbers() {
        handler.postDelayed({
            for (i in 0..4) {
                val num = Random.nextInt(1, 10)
                list.add(num)
                listBoxes[i].setText(num.toString())
            }
            binding.btnNext.visibility = View.VISIBLE
        }, 1000)
    }

    private fun isCorrect(): Boolean {
        var isCorrect = false
        for (i in 0..4) {
            isCorrect = listBoxes[i].text.toString() == list[i].toString()
        }
        return isCorrect
    }

    private fun fillListBoxes() {
        listBoxes.add(binding.tv1)
        listBoxes.add(binding.tv2)
        listBoxes.add(binding.tv3)
        listBoxes.add(binding.tv4)
        listBoxes.add(binding.tv5)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        handler.removeCallbacksAndMessages(null)
    }
}