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
            val id = binding.addNewBotId.text.toString()
            val token = binding.addNewBotToken.text.toString()
            val serviceToken = binding.addNewBotServiceToken.text.toString()
            val ic = listOf(
                R.drawable.ic_android_robot_mobile_mood_emoji,
                R.drawable.ic_android_robot_mobile_mood_emoji_angry_upset,
                R.drawable.ic_android_robot_mobile_mood_emoji_crash_bug_dead,
                R.drawable.ic_android_robot_mobile_mood_emoji_happy_joke_tounge,
                R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile,
                R.drawable.ic_android_robot_mobile_mood_emoji_happy_smile_successful,
                R.drawable.ic_android_robot_mobile_mood_emoji_love_lovely,
                R.drawable.ic_android_robot_mobile_mood_emoji_sad,
                R.drawable.ic_android_robot_mobile_mood_emoji_sad_crying,
                R.drawable.ic_android_robot_mobile_mood_emoji_sad_tear,
                R.drawable.ic_android_robot_mobile_mood_emoji_sick_ill_trouble,
                R.drawable.ic_android_robot_mobile_mood_emoji_smile_happy_successful,
                R.drawable.ic_android_robot_mobile_sleeping_tired
            )

            val image = ic.random()

            if (name == "") {

                Toast.makeText(requireContext(), "Имя не должно быть пустым!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (id == "") {

                Toast.makeText(
                    requireContext(),
                    "Поле id не должно быть пустым!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }

            if (token == "") {

                Toast.makeText(
                    requireContext(),
                    "Поле token не должно быть пустым!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }

            if (serviceToken == "") {

                Toast.makeText(
                    requireContext(),
                    "Поле сервисного ключа не должно быть пустым!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }

            for (bot in listOfBotsViewModel.listOfBotsDTO) {
                if (name == bot.name) {
                    Toast.makeText(
                        requireContext(),
                        "Бот с таким именем уже создан!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    return@setOnClickListener
                }
            }

            val bundle = bundleOf(
                "NEW_BOT_NAME_KEY" to name,
                "NEW_BOT_ID_KEY" to id,
                "NEW_BOT_TOKEN_KEY" to token,
                "NEW_BOT_SERVICE_TOKEN" to serviceToken,
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
                    findNavController().navigate(R.id.action_addNewBotMenuFragment_to_listOfBotsFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}