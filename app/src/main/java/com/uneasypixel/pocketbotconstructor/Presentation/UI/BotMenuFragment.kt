package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.BotMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.BotMenuViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentBotMenuBinding


class BotMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentBotMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BotMenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBotMenuBinding.inflate(inflater, container, false)

        viewModel.setDependencyFactory((requireActivity().application as ProgApplication).dependencyFactory)

        val recyclerView = binding.botMenuRecyclerView
        recyclerView.adapter = BotMenuItemAdapter(viewModel.buttons)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        val Bot = arguments?.getParcelable<Bot>("SOME_BUNDLE_KEY")

        binding.botMenuTitle.text = Bot?.name

        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.botMenuButtonStart.setOnClickListener {
            if (viewModel.switchLongPollServer())
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