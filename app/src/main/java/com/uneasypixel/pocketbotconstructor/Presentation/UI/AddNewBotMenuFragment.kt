package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAddNewBotMenuBinding

class AddNewBotMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentAddNewBotMenuBinding? = null
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
        _binding = FragmentAddNewBotMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addNewBotButtonAdd.setOnClickListener {
            val name = binding.addNewBotName.text.toString()
            val image = R.drawable.ic_android_robot_mobile_mood_emoji
            val bundle = bundleOf("NEW_BOT_KEY" to Bot(name, image))
            findNavController().navigate(R.id.action_addNewBotMenuFragment_to_listOfBotsFragment, bundle)
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}