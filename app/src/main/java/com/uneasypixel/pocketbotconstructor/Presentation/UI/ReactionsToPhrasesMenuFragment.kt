package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Phrase
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.ReactionsToPhrasesViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentReactionsToPhrasesMenuBinding


class ReactionsToPhrasesMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentReactionsToPhrasesMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReactionsToPhrasesViewModel by activityViewModels()
    private lateinit var dependencyFactory: DependencyFactory

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory

        var botName = arguments?.getString("BOT_NAME_KEY")
        if (botName != null) {
            viewModel.botName = botName
        }
        else
            botName = viewModel.botName

        viewModel.initial(
            dependencyFactory,
            this,
            this,
            botName
        )

        val newPhrase = arguments?.getParcelable<Phrase>("NEW_PHRASE_KEY")
        if (newPhrase != null)
            viewModel.addItem(newPhrase)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.saveBot()
                    findNavController().navigate(R.id.action_reactionsToPhrasesMenuFragment_to_botMenuFragment)
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReactionsToPhrasesMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.reactionsToPhrasesMenuRecyclerView
        recyclerView.adapter = viewModel.adapter
        viewModel.touchHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(true)

        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.reactionsToPhrasesMenubuttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_reactionsToPhrasesMenuFragment_to_addNewPhraseMenuFragment)
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.saveBot()
    }

    // Переходы к фрагментам по кнопкам меню
    override fun recyclerViewListClicked(position: Int) {

    }
}