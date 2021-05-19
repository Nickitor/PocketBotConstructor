package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.BotMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.BotMenuViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentBotMenuBinding


class BotMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentBotMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val viewModel: BotMenuViewModel by activityViewModels()

    private lateinit var adapter: BotMenuItemAdapter

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory
        getBotName()
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
        binding.botMenuTitle.text = viewModel.botName

        setOnBackPressedCallback()

        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


    fun getBotName() {
        val botName = arguments?.getString("BOT_NAME_KEY")
        if (botName != null)
            viewModel.botName = botName
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_botMenuFragment_to_listOfBotsFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    // Переходы к фрагментам по кнопкам меню
    override fun recyclerViewListClicked(position: Int) {

        val bundle = bundleOf(
            "BOT_NAME_KEY" to viewModel.botName
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


    override fun recyclerViewListChanged() {}


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}