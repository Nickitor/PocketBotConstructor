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
import com.uneasypixel.pocketbotconstructor.domain.entities.Bot
import com.uneasypixel.pocketbotconstructor.presentation.adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.presentation.adapters.ListOfBotsItemAdapter
import com.uneasypixel.pocketbotconstructor.presentation.adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.presentation.dto.BotDTO
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentListOfBotsMenuBinding
import java.util.*


class ListOfBotsMenuFragment : Fragment(), IRecyclerViewClickListener {
    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentListOfBotsMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()

    private lateinit var adapter: ListOfBotsItemAdapter
    private lateinit var callback: ItemTouchHelper.Callback
    private lateinit var touchHelper: ItemTouchHelper

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory
    }


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfBotsMenuBinding.inflate(inflater, container, false)

        val listOfBotsDTO : MutableList<BotDTO> = mutableListOf()
        for (botDTO in listOfBotsViewModel.listOfBotsDTO)
            listOfBotsDTO.add(botDTO.copy())

        listOfBotsViewModel.listOfBotsDTO
        val recyclerView = binding.listOfBotsRecyclerView
        adapter = ListOfBotsItemAdapter(
            listOfBotsDTO,
            this
        )
        callback = SimpleItemTouchHelperCallback(adapter)
        touchHelper = ItemTouchHelper(callback)
        recyclerView.adapter = adapter
        touchHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(true)

        checkNewBot()
        setOnBackPressedCallback()
        return binding.root
    }


    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.listOfBotsButtonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listOfBotsFragment_to_addNewBotMenuFragment)
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

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    private fun checkNewBot() {
        val newBotName = arguments?.getString("NEW_BOT_NAME_KEY")
        val newBotImage = arguments?.getInt("NEW_BOT_IMAGE_KEY")
        if (newBotName != null)
            addBotToAdapter(BotDTO(newBotName, newBotImage!!))
    }


    private fun addBotToAdapter(newBotDTO: BotDTO) {
        adapter.addItem(newBotDTO)
    }


    override fun recyclerViewListClicked(position: Int) {
        val bundle = bundleOf("BOT_NAME_KEY" to adapter.dataset[position].name)
        findNavController().navigate(R.id.action_listOfBotsFragment_to_botMenuFragment, bundle)
    }


    override fun recyclerViewListAdd(position: Int) {
        val botDTO = adapter.dataset[position].copy()
        listOfBotsViewModel.listOfBotsDTO.add(botDTO)

        val bot = Bot(botDTO.name, botDTO.imageResourceId)
        bot.token = "88a1d21f807fe5a534ebd62721612411fe5e2fcdd6d15a65fb879b84754f91d60d25f68b0cc116b9c3f78"
        bot.groupID = "193525063"
        listOfBotsViewModel.listOfBots.add(bot)
    }


    override fun recyclerViewListDelete(position: Int) {
        listOfBotsViewModel.listOfBotsDTO.removeAt(position)
        listOfBotsViewModel.listOfBots.removeAt(position)
    }


    override fun recyclerViewListMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(listOfBotsViewModel.listOfBotsDTO, fromPosition, toPosition)
        Collections.swap(listOfBotsViewModel.listOfBots, fromPosition, toPosition)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}