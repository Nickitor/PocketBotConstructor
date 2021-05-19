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
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.BotMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.BotMenuViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentBotMenuBinding


class BotMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentBotMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BotMenuViewModel by viewModels()
    private lateinit var dependencyFactory: DependencyFactory

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory

        val listOfBots = arguments?.getParcelableArrayList<Bot>("BOTS_KEY") as MutableList<Bot>
        val position = arguments?.getInt("POS_KEY")

        viewModel.initial(
            dependencyFactory,
            this,
            listOfBots,
            position!!
        )
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.saveBots()
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

        binding.botMenuTitle.text = viewModel.bot.name

        updateStartServerButton()

        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.botMenuButtonStart.setOnClickListener {
            switchStartServerButton()
        }
    }


    private fun switchStartServerButton() {
        if (viewModel.bot.isEnabled) {

            viewModel.stopLongPollServer()

            updateStartServerButton()
            Toast.makeText(requireContext(), "Чат-бот выключен!", Toast.LENGTH_SHORT)
                .show()
        } else {

            viewModel.startLongPollServer()

            updateStartServerButton()
            Toast.makeText(requireContext(), "Чат-бот включен!", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun updateStartServerButton() {
        if (viewModel.bot.isEnabled)
            binding.botMenuButtonStart.text = resources.getString(R.string.button_stop)
        else
            binding.botMenuButtonStart.text = resources.getString(R.string.button_start)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.saveBots()
    }

    // Переходы к фрагментам по кнопка меню
    override fun recyclerViewListClicked(position: Int) {

        val bundle = bundleOf(
            "BOTS_KEY" to viewModel.listOfBots,
            "POS_KEY" to position
        )
        when (position) {
            0 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_createMenuMenuFragment,
                bundle
            )
            1 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_setOfPhrasesMenuFragment,
                bundle
            )
            2 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_reactionsToPhrasesMenuFragment,
                bundle
            )
            3 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_reactionsToEventsMenuFragment,
                bundle
            )
            4 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_dialoguesMenuFragment,
                bundle
            )
            5 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_statisticsMenuFragment,
                bundle
            )
            6 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_sendingMenuFragment,
                bundle
            )
            7 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_variablesMenuFragment,
                bundle
            )
        }
    }
}