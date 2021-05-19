package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.databinding.FragmentSendingMenuBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SendingMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentSendingMenuBinding? = null
    private val binding get() = _binding!!


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSendingMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.sendingMenuButtonSend.setOnClickListener {

            val tokenGroup = "88a1d21f807fe5a534ebd62721612411fe5e2fcdd6d15a65fb879b84754f91d60d25f68b0cc116b9c3f78"

            GlobalScope.launch {

                val sendMessageToConversations = (requireActivity().application as ProgApplication).dependencyFactory.provideSendMessageToConversationsUseCase()

                sendMessageToConversations.sendMessageToConversations(
                    binding.sendingMenuSendingTextEditTextTextMultiLine.text.toString(),
                    tokenGroup)
            }

            Toast.makeText(requireContext(), "Сообщения отправлены пользователям!", Toast.LENGTH_SHORT).show()
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}