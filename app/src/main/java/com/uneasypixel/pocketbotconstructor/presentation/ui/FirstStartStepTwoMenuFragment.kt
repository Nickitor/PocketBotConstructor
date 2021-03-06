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
import com.uneasypixel.pocketbotconstructor.databinding.FragmentFirstStartStepTwoMenuBinding


class FirstStartStepTwoMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentFirstStartStepTwoMenuBinding

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstStartStepTwoMenuBinding.inflate(inflater, container, false)

        setOnBackPressedCallback()
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Действие при нажатии кнопки перехода к следующему фрагменту
        binding.firstStartStepTwoButtonNext.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepTwoFragment_to_listOfBotsFragment)
        }

        // Действие при нажатии первой RadioButton
        binding.firstStartStepTwoRadioButtonStepOne.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepTwoFragment_to_firstStartStepOneFragment)
        }
    }

    // Инициализация компнентов макета при возврате на экран
    override fun onResume() {
        super.onResume()

        binding.firstStartStepTwoRadioButtonStepOne.isChecked = false
        binding.firstStartStepTwoRadioButtonStepTwo.isChecked = true
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}