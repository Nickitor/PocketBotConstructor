package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.BotMenuViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentBotMenuBinding

class BotMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentBotMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BotMenuViewModel by viewModels()

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

        binding.botMenuButtonStart.setOnClickListener {
            if (viewModel.switchLongPollServer((requireActivity().application as ProgApplication).dependencyFactory))
            {
                binding.botMenuButtonStart.text = resources.getString(R.string.button_stop)
                Toast.makeText(requireContext(), "Чат-бот включен!", Toast.LENGTH_SHORT)
                    .show()
            }
            else
            {
                binding.botMenuButtonStart.text = resources.getString(R.string.button_start)
                Toast.makeText(requireContext(), "Чат-бот выключен!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}