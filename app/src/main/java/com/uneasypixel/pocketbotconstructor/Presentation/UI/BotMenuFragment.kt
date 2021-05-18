package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.BotMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.BotMenuViewModel
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentBotMenuBinding


class BotMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentBotMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BotMenuViewModel by viewModels()
    private val viewModel2: ListOfBotsViewModel by viewModels()

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setDependencyFactory((requireActivity().application as ProgApplication).dependencyFactory)
        viewModel.setBot(arguments?.getParcelable<Bot>("BOT_KEY"))

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true ) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_botMenuFragment_to_listOfBotsFragment)
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBotMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.botMenuRecyclerView
        recyclerView.adapter = BotMenuItemAdapter(viewModel.buttons, this)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        binding.botMenuTitle.text = viewModel.curBot?.name

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

    // Переходы к фрагментам по кнопка меню
    override fun recyclerViewListClicked(position: Int) {

        val bundle = bundleOf("BOT_KEY" to viewModel.curBot)
        when (position) {
            0 -> findNavController().navigate(R.id.action_botMenuFragment_to_createMenuMenuFragment, bundle)
            1 -> findNavController().navigate(R.id.action_botMenuFragment_to_setOfPhrasesMenuFragment, bundle)
            2 -> findNavController().navigate(R.id.action_botMenuFragment_to_reactionsToPhrasesMenuFragment, bundle)
            3 -> findNavController().navigate(R.id.action_botMenuFragment_to_reactionsToEventsMenuFragment, bundle)
            4 -> findNavController().navigate(R.id.action_botMenuFragment_to_dialoguesMenuFragment, bundle)
            5 -> findNavController().navigate(R.id.action_botMenuFragment_to_statisticsMenuFragment, bundle)
            6 -> findNavController().navigate(R.id.action_botMenuFragment_to_sendingMenuFragment, bundle)
            7 -> findNavController().navigate(R.id.action_botMenuFragment_to_variablesMenuFragment, bundle)
        }
    }
}