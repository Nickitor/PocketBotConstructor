package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModel.ListOfBotsItemAdapter
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.databinding.FragmentListOfBotsBinding


class ListOfBotsFragment : Fragment() {
    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentListOfBotsBinding? = null
    private val binding get() = _binding!!

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfBotsBinding.inflate(inflater, container, false)

        val listOfBots = (requireActivity().application as ProgApplication).dependencyFactory.provideGetBotsUseCase().getBots()

        val recyclerView = binding.recyclerView
        recyclerView.adapter = ListOfBotsItemAdapter(listOfBots)

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
}