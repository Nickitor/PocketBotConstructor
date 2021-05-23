package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentSendingMenuBinding
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SendingMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentSendingMenuBinding

    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()
    var bot: Bot? = null

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBotToViewModel(arguments?.getString("BOT_NAME_KEY"))
    }


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendingMenuBinding.inflate(inflater, container, false)

        setOnBackPressedCallback()
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.sendingMenuButtonSend.setOnClickListener {

            GlobalScope.launch {

                val sendMessageToConversations = (requireActivity().application as ProgApplication).dependencyFactory.provideSendMessageToConversationsUseCase()

                sendMessageToConversations.sendMessageToConversations(
                    binding.sendingMenuSendingTextEditTextTextMultiLine.text.toString(),
                    bot!!.token)
            }

            Toast.makeText(requireContext(), "Сообщения отправлены пользователям!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_sendingMenuFragment_to_botMenuFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    fun setBotToViewModel(botName : String?) {
        if (botName != null) {
            for (bot in listOfBotsViewModel.listOfBots) {
                if (bot.name == botName) {
                    this.bot = bot
                    return
                }
            }
        }
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}