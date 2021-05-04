package com.uneasypixel.pocketbotconstructor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.databinding.FragmentFirstStartStepOneBinding

class FirstStartStepOneFragment : Fragment() {

    private var _binding: FragmentFirstStartStepOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstStartStepOneBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepOneFragment_to_firstStartStepTwoFragment)
        }

        binding.radioButtonStepTwo.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepOneFragment_to_firstStartStepTwoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}