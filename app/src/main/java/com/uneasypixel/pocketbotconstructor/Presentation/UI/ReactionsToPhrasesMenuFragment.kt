package com.uneasypixel.pocketbotconstructor.Presentation.UI

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
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Phrase
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.ReactionsToPhrasesMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.ReactionsToPhrasesViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentReactionsToPhrasesMenuBinding


class ReactionsToPhrasesMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentReactionsToPhrasesMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val viewModel: ReactionsToPhrasesViewModel by activityViewModels()

    private lateinit var adapter: ReactionsToPhrasesMenuItemAdapter
    private lateinit var callback: ItemTouchHelper.Callback
    private lateinit var touchHelper: ItemTouchHelper

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory
        getBot()
    }


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReactionsToPhrasesMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.reactionsToPhrasesMenuRecyclerView
        adapter = ReactionsToPhrasesMenuItemAdapter(
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


    fun getBot() {
        val botName = arguments?.getString("BOT_NAME_KEY")
        if (botName != null) {
            viewModel.bot =
                dependencyFactory.provideGetBotsUseCase().getBot(this.requireContext(), botName)
        }
    }


    private fun saveBot() {
        dependencyFactory.provideSaveBotsUseCase().saveBot(this.requireContext(), viewModel.bot!!)
    }


    private fun createPhrase(phrase: Phrase) {
        viewModel.bot!!.reactionsToPhrases = adapter.dataset
        saveBot()
    }


    private fun checkNewPhrase() {
        val newPhrase = arguments?.getParcelable<Phrase>("NEW_PHRASE_KEY")
        if (newPhrase != null)
            addPhrase(newPhrase)
    }


    private fun addPhrase(phrase: Phrase) {
        adapter.addItem(phrase)
        createPhrase(phrase)
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_reactionsToPhrasesMenuFragment_to_botMenuFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun recyclerViewListClicked(position: Int) {
    }


    override fun recyclerViewListChanged() {
        viewModel.bot!!.reactionsToPhrases = adapter.dataset
        saveBot()
    }
}