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
import com.uneasypixel.pocketbotconstructor.Data.Gateway.Server
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentFirstStartStepOneBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirstStartStepOneFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentFirstStartStepOneBinding? = null
    private val binding get() = _binding!!

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstStartStepOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Действие при нажатии кнопки перехода к следующему фрагменту
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepOneFragment_to_firstStartStepTwoFragment)

            //https://oauth.vk.com/authorize?client_id=7793844&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.130&state=123456

            GlobalScope.launch {

                val groupID = "193525063"
                val tokenUser = "e8fda682b660d9f970df4d960fe68099ee385b6c55cdae63cf03181793090a5a5f2ffac02acb22f38c237"
                val tokenGroup = "88a1d21f807fe5a534ebd62721612411fe5e2fcdd6d15a65fb879b84754f91d60d25f68b0cc116b9c3f78"

                val server = Server(groupID, tokenUser, tokenGroup, "90")

                val test = (requireActivity().application as ProgApplication).dependencyFactory.provideSendMessageToUserUseCase()
                val response = test.sendMessageToUser("Hello", "122781192", tokenGroup)

                server.start()
            }
        }

        // Действие при нажатии второй RadioButton
        binding.radioButtonStepTwo.setOnClickListener {
            findNavController().navigate(R.id.action_firstStartStepOneFragment_to_firstStartStepTwoFragment)
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}