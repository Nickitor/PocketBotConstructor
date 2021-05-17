package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.IRecyclerViewClickListener
import com.uneasypixel.pocketbotconstructor.Presentation.Adapters.ListOfBotsItemAdapter
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentListOfBotsMenuBinding


class ListOfBotsMenuFragment : Fragment(), IRecyclerViewClickListener {
    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentListOfBotsMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListOfBotsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getBots((requireActivity().application as ProgApplication).dependencyFactory)
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfBotsMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.listOfBotsRecyclerView
        recyclerView.adapter = ListOfBotsItemAdapter(
            viewModel.listOfBots,
            this)

        recyclerView.setHasFixedSize(true)

        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun recyclerViewListClicked(position: Int) {

        val bundle = bundleOf("SOME_BUNDLE_KEY" to viewModel.listOfBots[position])
        findNavController().navigate(R.id.action_listOfBotsFragment_to_botMenuFragment, bundle)
    }
}