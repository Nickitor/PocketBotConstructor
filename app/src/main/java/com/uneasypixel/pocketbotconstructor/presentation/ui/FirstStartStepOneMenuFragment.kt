/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ КЛАСС ФРАГМЕНТА. ДАННЫЙ ФРАГМЕНТ ЗАПУСКАЕТСЯ ОДИН РАЗ
// ПРИ ПЕРВОМ ЗАПУСКЕ ПРИЛОЖЕНИЯ И СОДЕРЖИТ ПРИВЕТСВИЕ И ОПИСАНИЕ ПРИЛОЖЕНИЯ.
// *********************************************************************
package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentFirstStartStepOneMenuBinding

class FirstStartStepOneMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentFirstStartStepOneMenuBinding? = null
    private val binding get() = _binding!!

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true ) {
                override fun handleOnBackPressed() {

                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstStartStepOneMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Действие при нажатии кнопки перехода к следующему фрагменту
        binding.firstStartStepOneButtonNext.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepOneFragment_to_firstStartStepTwoFragment)
        }

        // Действие при нажатии второй RadioButton
        binding.firstStartStepOneRadioButtonStepTwo.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepOneFragment_to_firstStartStepTwoFragment)
        }
    }

    override fun onResume() {
        super.onResume()

        binding.firstStartStepOneRadioButtonStepTwo.isChecked = false
        binding.firstStartStepOneRadioButtonStepOne.isChecked = true
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}