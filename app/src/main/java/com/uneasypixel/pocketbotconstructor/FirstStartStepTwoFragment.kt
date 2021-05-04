package com.uneasypixel.pocketbotconstructor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.databinding.FragmentFirstStartStepTwoBinding

class FirstStartStepTwoFragment : Fragment() {

    private var _binding: FragmentFirstStartStepTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstStartStepTwoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.radioButtonStepOne.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepTwoFragment_to_firstStartStepOneFragment)
        }

        binding.buttonEnterVk.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepTwoFragment_to_authorisationErrorFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}