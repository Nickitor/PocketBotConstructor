package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentListOfBotsMenuBinding


class ListOfBotsMenuFragment : Fragment(), IRecyclerViewClickListener {
    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentListOfBotsMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListOfBotsViewModel by viewModels()

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initial(
            (requireActivity().application as ProgApplication).dependencyFactory,
            this,
            this
        )

        val newBot = arguments?.getParcelable<Bot>("NEW_BOT_KEY")
        if (newBot != null)
            viewModel.addItem(newBot)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfBotsMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.listOfBotsRecyclerView
        recyclerView.adapter = viewModel.adapter
        viewModel.touchHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(true)

        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.listOfBotsButtonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listOfBotsFragment_to_addNewBotMenuFragment)
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun recyclerViewListClicked(position: Int) {

        val bundle = bundleOf(
            "BOTS_KEY" to viewModel.listOfBots,
            "POS_KEY" to position
        )
        findNavController().navigate(R.id.action_listOfBotsFragment_to_botMenuFragment, bundle)
    }
}