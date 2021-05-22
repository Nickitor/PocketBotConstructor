package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentDialogScriptMenuBinding
import com.uneasypixel.pocketbotconstructor.domain.entities.DialogScript
import com.uneasypixel.pocketbotconstructor.presentation.adapters.DialogScriptMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.presentation.adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.presentation.adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ReactionsToPhrasesViewModel


class DialogScriptMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentDialogScriptMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val viewModel: ReactionsToPhrasesViewModel by activityViewModels()
    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()

    private lateinit var adapter: DialogScriptMenuItemAdapter
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
        binding = FragmentDialogScriptMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.reactionsToPhrasesMenuRecyclerView
        adapter = DialogScriptMenuItemAdapter(
            viewModel.bot!!.reactionsToPhrases,
            this
        )
        callback = SimpleItemTouchHelperCallback(adapter)
        touchHelper = ItemTouchHelper(callback)
        recyclerView.adapter = adapter
        touchHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(true)

        checkNewPhrase()
        setOnBackPressedCallback()
        return binding.root
    }


    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.reactionsToPhrasesMenubuttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_reactionsToPhrasesMenuFragment_to_addNewPhraseMenuFragment)
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
                    findNavController().navigate(R.id.action_reactionsToPhrasesMenuFragment_to_botMenuFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    fun setBotToViewModel(botName : String?) {
        if (botName != null) {
            for (bot in listOfBotsViewModel.listOfBots) {
                if (bot.name == botName) {
                    viewModel.bot = bot
                    return
                }
            }
        }
    }


    private fun checkNewPhrase() {
        val newPhrase = arguments?.getParcelable<DialogScript>("NEW_PHRASE_KEY")
        if (newPhrase != null)
            addPhrase(newPhrase)
    }


    private fun addPhrase(phrase: DialogScript) {
        adapter.addItem(phrase)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun recyclerViewListClicked(position: Int) {}

    override fun recyclerViewListAdd(position: Int) {}

    override fun recyclerViewListDelete(position: Int) {}

    override fun recyclerViewListMove(fromPosition: Int, toPosition: Int) {}
}