package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAddNewBotMenuBinding
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel

class AddNewBotMenuFragment : Fragment() {
    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentAddNewBotMenuBinding

    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewBotMenuBinding.inflate(inflater, container, false)
        setOnBackPressedCallback()
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addNewBotButtonAdd.setOnClickListener {

            val name = binding.addNewBotName.text.toString()
            val image = R.drawable.ic_android_robot_mobile_mood_emoji

            if (name == "") {

                Toast.makeText(requireContext(), "Имя не должно быть пустым!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            for (bot in listOfBotsViewModel.listOfBotsDTO) {
                if (name == bot.name) {
                    Toast.makeText(requireContext(), "Бот с таким именем уже создан!", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }

            val bundle = bundleOf(
                "NEW_BOT_NAME_KEY" to name,
                "NEW_BOT_IMAGE_KEY" to image
            )
            findNavController().navigate(
                R.id.action_addNewBotMenuFragment_to_listOfBotsFragment,
                bundle
            )
        }
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