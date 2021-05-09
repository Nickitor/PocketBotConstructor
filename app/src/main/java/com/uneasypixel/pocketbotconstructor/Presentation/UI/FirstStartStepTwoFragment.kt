/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ КЛАСС ФРАГМЕНТА. ДАННЫЙ ФРАГМЕНТ ЗАПУСКАЕТСЯ ОДИН РАЗ
// ПРИ ПЕРВОМ ЗАПУСКЕ ПРИЛОЖЕНИЯ И СОДЕРЖИТ ПРИВЕТСВИЕ И ОПИСАНИЕ ПРИЛОЖЕНИЯ.
// *********************************************************************
package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentFirstStartStepTwoBinding

class FirstStartStepTwoFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentFirstStartStepTwoBinding? = null
    private val binding get() = _binding!!

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstStartStepTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Действие при нажатии первой RadioButton
        binding.radioButtonStepOne.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepTwoFragment_to_firstStartStepOneFragment)
        }

        // Действие при нажатии кнопки входа ВКонтакте
        binding.buttonEnterVk.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepTwoFragment_to_authorisationErrorFragment)
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}