package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAddNewBotMenuBinding

class AddNewBotMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentAddNewBotMenuBinding? = null
    private val binding get() = _binding!!

    private var listOfBots: MutableList<BotDTO> = mutableListOf()

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listOfBots = (requireActivity().application as ProgApplication).dependencyFactory.provideGetBotsUseCase().getBots(this.requireContext())

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
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

            if (name == "") {

                Toast.makeText(requireContext(), "Имя не должно быть пустым!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            for (bot in listOfBots) {
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

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}