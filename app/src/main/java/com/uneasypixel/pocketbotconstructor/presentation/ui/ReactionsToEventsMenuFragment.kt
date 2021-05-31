package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentReactionsToEventsMenuBinding
import com.uneasypixel.pocketbotconstructor.domain.entities.DialogScript
import com.uneasypixel.pocketbotconstructor.presentation.adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.presentation.adapters.ReactionsToEventsMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ReactionsToEventsViewModel


class ReactionsToEventsMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentReactionsToEventsMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val viewModel: ReactionsToEventsViewModel by activityViewModels()
    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()

    private lateinit var adapter: ReactionsToEventsMenuItemAdapter

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory
        setBotToViewModel(arguments?.getString("BOT_NAME_KEY"))
    }


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReactionsToEventsMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.reactionsToEventsMenuRecyclerView
        adapter = ReactionsToEventsMenuItemAdapter(
            viewModel.bot!!.reactionsToEvents,
            this
        )
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        checkEvent()
        setOnBackPressedCallback()
        return binding.root
    }


    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }


    override fun onStop() {
        super.onStop()
        listOfBotsViewModel.saveBots(dependencyFactory, this.requireContext())
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_reactionsToEventsMenuFragment_to_botMenuFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    private fun setBotToViewModel(botName: String?) {
        if (botName != null) {
            for (bot in listOfBotsViewModel.listOfBots) {
                if (bot.name == botName) {
                    viewModel.bot = bot
                    return
                }
            }
        }
    }


    private fun checkEvent() {
        val event = arguments?.getParcelable<DialogScript>("EVENT_KEY")
        val eventPos = arguments?.getInt("EVENT_POS_KEY")
        if (event != null)
            viewModel.bot!!.reactionsToEvents[eventPos!!] = event
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }


    override fun recyclerViewListClicked(position: Int) {
        val bundle = bundleOf(
            "EVENT_KEY" to viewModel.bot!!.reactionsToEvents[position],
            "EVENT_POS_KEY" to position
        )
        findNavController().navigate(
            R.id.action_reactionsToEventsMenuFragment_to_addNewReactionToEventMenuFragment,
            bundle
        )
    }

    override fun recyclerViewListAdd(position: Int) {}

    override fun recyclerViewListDelete(position: Int) {}

    override fun recyclerViewListMove(fromPosition: Int, toPosition: Int) {}
}