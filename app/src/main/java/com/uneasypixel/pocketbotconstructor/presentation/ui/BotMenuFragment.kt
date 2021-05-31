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
import androidx.recyclerview.widget.GridLayoutManager
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentBotMenuBinding
import com.uneasypixel.pocketbotconstructor.presentation.adapters.BotMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.presentation.adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.BotMenuViewModel
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ServerViewModel


class BotMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentBotMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val viewModel: BotMenuViewModel by activityViewModels()
    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()
    private val server: ServerViewModel by activityViewModels()

    private lateinit var adapter: BotMenuItemAdapter

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory
        setBotToViewModel()
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBotMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.botMenuRecyclerView
        adapter = BotMenuItemAdapter(
            viewModel.buttons,
            this
        )
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.botMenuTitle.text = viewModel.bot!!.name
        setOnBackPressedCallback()
        updateAddButton()
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.botMenuButtonStart.setOnClickListener {
            if (!viewModel.bot!!.isEnabled) {
                server.start(viewModel.bot!!)
                binding.botMenuButtonStart.text = "Выключить"
                Toast.makeText(requireContext(), "Чат-бот включен!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.botMenuButtonStart.text = "Включить"
                server.stop(viewModel.bot!!)
                Toast.makeText(requireContext(), "Чат-бот выключен!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    override fun onStop() {
        super.onStop()
        listOfBotsViewModel.saveBots(dependencyFactory, this.requireContext())
    }


    fun setBotToViewModel() {
        val botName = arguments?.getString("BOT_NAME_KEY")
        if (botName != null)
            for (bot in listOfBotsViewModel.listOfBots)
                if (bot.name == botName)
                    viewModel.bot = bot
    }


    fun updateAddButton() {
        if (viewModel.bot!!.isEnabled)
            binding.botMenuButtonStart.text = "Выключить"
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_botMenuFragment_to_listOfBotsFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    // Переходы к фрагментам по кнопкам меню
    override fun recyclerViewListClicked(position: Int) {

        val bundle = bundleOf(
            "BOT_NAME_KEY" to viewModel.bot!!.name
        )

        when (position) {
            0 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_setOfPhrasesMenuFragment,
                bundle
            )
            1 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_reactionsToPhrasesMenuFragment,
                bundle
            )
            2 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_reactionsToEventsMenuFragment,
                bundle
            )
            3 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_dialoguesMenuFragment,
                bundle
            )
            4 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_statisticsMenuFragment,
                bundle
            )
            5 -> findNavController().navigate(
                R.id.action_botMenuFragment_to_sendingMenuFragment,
                bundle
            )
        }
    }

    override fun recyclerViewListAdd(position: Int) {}

    override fun recyclerViewListDelete(position: Int) {}

    override fun recyclerViewListMove(fromPosition: Int, toPosition: Int) {}

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}