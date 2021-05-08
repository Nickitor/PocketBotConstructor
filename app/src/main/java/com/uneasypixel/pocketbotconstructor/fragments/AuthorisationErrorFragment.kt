/**
 * Автор: Никита Юрьевич Замыслов
 * Дата создания: 08.05.2021
 */
// *********************************************************************
// ФАЙЛ СОДЕРЖИТ КЛАСС ФРАГМЕНТА ОШИБКИ АВТОРИЗАЦИИ ВКОНТАКТЕ. ДАННЫЙ
// ФРАГМЕНТ ОТОБРАЖАЕТСЯ, КОГДА В ПРОЦЕССЕ ПОЛУЧЕНИЯ ДОСТУПА К ФУНКЦИЯМ
// VP API ПРОИЗОШЛА ОШИБКА.
// *********************************************************************
package com.uneasypixel.pocketbotconstructor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAuthorisationErrorBinding

class AuthorisationErrorFragment : Fragment() {

    // Объект навигационного контроллера для перехода между фрагментами
    private var _binding: FragmentAuthorisationErrorBinding? = null
    private val binding get() = _binding!!

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorisationErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Действие при нажатии кнопки возврата к начальному фрагменту
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_authorisationErrorFragment_to_firstStartStepOneFragment)
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}