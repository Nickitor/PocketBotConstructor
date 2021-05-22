package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentDialoguesMenuBinding
import com.uneasypixel.pocketbotconstructor.domain.entities.Conversation
import com.uneasypixel.pocketbotconstructor.presentation.adapters.DialoguesMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.presentation.adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.DialoguesViewModel
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel


class DialoguesMenuFragment : Fragment(), IRecyclerViewClickListener {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentDialoguesMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val viewModel: DialoguesViewModel by activityViewModels()
    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()

    var conversations: MutableList<Conversation> = mutableListOf()

    private lateinit var adapter: DialoguesMenuItemAdapter

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory
        setBotToViewModel(arguments?.getString("BOT_NAME_KEY"))
        viewModel.getConversations(dependencyFactory)
    }


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialoguesMenuBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val recyclerView = binding.dialoguesMenuRecyclerView
        adapter = DialoguesMenuItemAdapter(
            this
        )
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        setOnBackPressedCallback()
        return binding.root
    }


    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }


    override fun onStop() {
        super.onStop()
        viewModel.isUpdating = false
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_dialoguesMenuFragment_to_botMenuFragment)
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


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun recyclerViewListClicked(position: Int) {}

    override fun recyclerViewListAdd(position: Int) {}

    override fun recyclerViewListDelete(position: Int) {}

    override fun recyclerViewListMove(fromPosition: Int, toPosition: Int) {}
}