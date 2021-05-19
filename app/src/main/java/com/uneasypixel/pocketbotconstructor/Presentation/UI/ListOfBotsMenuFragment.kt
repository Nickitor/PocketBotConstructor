package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Bot
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.ListOfBotsItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.Presentation.DTO.BotDTO
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentListOfBotsMenuBinding


class ListOfBotsMenuFragment : Fragment(), IRecyclerViewClickListener {
    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentListOfBotsMenuBinding

    private lateinit var dependencyFactory: DependencyFactory

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

        val recyclerView = binding.listOfBotsRecyclerView
        adapter = ListOfBotsItemAdapter(
            getBotsDTO(),
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


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    private fun getBotsDTO(): MutableList<BotDTO> =
        dependencyFactory.provideGetBotsUseCase().getBots(this.requireContext())


    private fun saveBotsDTO() {
        dependencyFactory.provideSaveBotsUseCase().saveBots(this.requireContext(), adapter.dataset)
    }


    private fun createBot(bot: BotDTO) {
        saveBotsDTO()
        dependencyFactory.provideSaveBotsUseCase()
            .saveBot(this.requireContext(), Bot(bot.name, bot.imageResourceId))
    }


    private fun checkNewBot() {
        val newBotName = arguments?.getString("NEW_BOT_NAME_KEY")
        val newBotImage = arguments?.getInt("NEW_BOT_IMAGE_KEY")
        if (newBotName != null)
            addBot(BotDTO(newBotName, newBotImage!!))
    }


    private fun addBot(newBot: BotDTO) {
        adapter.addItem(newBot)
        createBot(newBot)
    }


    override fun recyclerViewListClicked(position: Int) {

        val bundle = bundleOf("BOT_NAME_KEY" to adapter.dataset[position].name)
        findNavController().navigate(R.id.action_listOfBotsFragment_to_botMenuFragment, bundle)
    }


    override fun recyclerViewListChanged() {
        saveBotsDTO()
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}