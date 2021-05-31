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
import androidx.recyclerview.widget.ItemTouchHelper
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentSetOfPhrasesMenuBinding
import com.uneasypixel.pocketbotconstructor.domain.entities.SetOfPhrases
import com.uneasypixel.pocketbotconstructor.presentation.adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.presentation.adapters.SetOfPhrasesMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.presentation.adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.SetOfPhrasesViewModel

class SetOfPhrasesMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentSetOfPhrasesMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val viewModel: SetOfPhrasesViewModel by activityViewModels()
    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()

    private lateinit var adapter: SetOfPhrasesMenuItemAdapter
    private lateinit var callback: ItemTouchHelper.Callback
    private lateinit var touchHelper: ItemTouchHelper

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
        binding = FragmentSetOfPhrasesMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.setOfPhrasesMenuRecyclerView
        adapter = SetOfPhrasesMenuItemAdapter(
            viewModel.bot!!.setsOfPhrases,
            this
        )
        callback = SimpleItemTouchHelperCallback(adapter)
        touchHelper = ItemTouchHelper(callback)
        recyclerView.adapter = adapter
        touchHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(true)

        setOnBackPressedCallback()
        return binding.root
    }


    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setOfPhrasesMenuButtonAdd.setOnClickListener {
            val newSet = SetOfPhrases()
            val position = adapter.dataset.size
            addSet(newSet)
            val bundle = bundleOf(
                "SET_KEY" to newSet
            )
            findNavController().navigate(
                R.id.action_setOfPhrasesMenuFragment_to_addNewSetOfPhrasesMenuFragment,
                bundle
            )
        }
    }


    override fun onStop() {
        super.onStop()
        listOfBotsViewModel.saveBots(dependencyFactory, this.requireContext())
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_setOfPhrasesMenuFragment_to_botMenuFragment)
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


    private fun addSet(set: SetOfPhrases) {
        adapter.addItem(set)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun recyclerViewListClicked(position: Int) {
        val bundle = bundleOf(
            "SET_KEY" to viewModel.bot!!.setsOfPhrases[position]
        )
        findNavController().navigate(
            R.id.action_setOfPhrasesMenuFragment_to_addNewSetOfPhrasesMenuFragment,
            bundle
        )
    }

    override fun recyclerViewListAdd(position: Int) {}

    override fun recyclerViewListDelete(position: Int) {}

    override fun recyclerViewListMove(fromPosition: Int, toPosition: Int) {}
}