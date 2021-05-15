package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentBotMenuBinding

class BotMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentBotMenuBinding? = null
    private val binding get() = _binding!!


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBotMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.botMenuItemSendingLenearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_botMenuFragment_to_sendingMenuFragment)
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}