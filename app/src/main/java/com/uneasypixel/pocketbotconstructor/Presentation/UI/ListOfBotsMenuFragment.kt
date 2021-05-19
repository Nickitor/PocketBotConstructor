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
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentListOfBotsMenuBinding


class ListOfBotsMenuFragment : Fragment(), IRecyclerViewClickListener {
    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentListOfBotsMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListOfBotsViewModel by activityViewModels()

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initial(
            (requireActivity().application as ProgApplication).dependencyFactory,
            this,
            this
        )

        val newBotName = arguments?.getString("NEW_BOT_NAME_KEY")
        val newBotImage = arguments?.getInt("NEW_BOT_IMAGE_KEY")
        if (newBotName != null)
            viewModel.addItem(BotDTO(newBotName, newBotImage!!))

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

        viewModel.saveBots()
        val bundle = bundleOf("BOT_NAME_KEY" to viewModel.listOfBots[position].name)
        findNavController().navigate(R.id.action_listOfBotsFragment_to_botMenuFragment, bundle)
    }
}