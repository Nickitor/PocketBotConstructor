package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.uneasypixel.pocketbotconstructor.DependencyFactory
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAddNewSetOfPhrasesMenuBinding
import com.uneasypixel.pocketbotconstructor.domain.entities.SetOfPhrases
import com.uneasypixel.pocketbotconstructor.presentation.adapters.AddNewSetOfPhrasesMenuItemAdapter
import com.uneasypixel.pocketbotconstructor.presentation.adapters.SimpleItemTouchHelperCallback
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.AddNewSetOfPhrasesViewModel
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel

class AddNewSetOfPhrasesMenuFragment : Fragment() {
    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentAddNewSetOfPhrasesMenuBinding

    private lateinit var dependencyFactory: DependencyFactory
    private val viewModel: AddNewSetOfPhrasesViewModel by activityViewModels()
    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()

    private lateinit var adapter: AddNewSetOfPhrasesMenuItemAdapter
    private lateinit var callback: ItemTouchHelper.Callback
    private lateinit var touchHelper: ItemTouchHelper

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory
        val set = arguments?.getParcelable<SetOfPhrases>("SET_KEY")
        if (set != null) {
            viewModel.set = set
        }
    }


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewSetOfPhrasesMenuBinding.inflate(inflater, container, false)

        val recyclerView = binding.addNewSetOfPhrasesMenuRecyclerView
        adapter = AddNewSetOfPhrasesMenuItemAdapter(
            viewModel.set!!,
        )
        callback = SimpleItemTouchHelperCallback(adapter)
        touchHelper = ItemTouchHelper(callback)
        recyclerView.adapter = adapter
        touchHelper.attachToRecyclerView(recyclerView)
        recyclerView.setHasFixedSize(true)

        setOnBackPressedCallback()
        return binding.root
    }


    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.addNewSetOfPhrasesMenuSetNameValue.setText(viewModel.set!!.name)

        binding.addNewSetOfPhrasesMenuSetNameValue.addTextChangedListener {
            viewModel.set!!.name = binding.addNewSetOfPhrasesMenuSetNameValue.text.toString()
        }

        binding.addNewSetOfPhrasesMenuButtonGroup.setOnClickListener {
            viewModel.set!!.isTextResource = false
            binding.addNewSetOfPhrasesMenuButtonText.isClickable = true
            binding.addNewSetOfPhrasesMenuButtonGroup.isClickable = false

            adapter.notifyDataSetChanged()
        }

        binding.addNewSetOfPhrasesMenuButtonText.setOnClickListener {
            viewModel.set!!.isTextResource = true
            binding.addNewSetOfPhrasesMenuButtonText.isClickable = false
            binding.addNewSetOfPhrasesMenuButtonGroup.isClickable = true

            adapter.notifyDataSetChanged()
        }

        binding.addNewSetOfPhrasesMenuButtonNew.setOnClickListener {
            adapter.addItem("")
        }

        binding.addNewSetOfPhrasesMenuButtonAdd.setOnClickListener {

            if (binding.addNewSetOfPhrasesMenuSetNameValue.text.isEmpty()) {
                Toast.makeText(requireContext(), "Поле с названием набора не должно быть пустым!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            for (item in adapter.dataset.textSources) {
                if (item.isEmpty()){
                    Toast.makeText(requireContext(), "Поля с текстом сообщения не должны быть пустыми!", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }

            for (item in adapter.dataset.groupSources) {
                if (item.isEmpty()) {
                    Toast.makeText(requireContext(), "Поля с id групп не должны быть пустыми!", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }

            if (adapter.dataset.textSources.isEmpty() && adapter.dataset.groupSources.isEmpty()) {
                Toast.makeText(requireContext(), "Создайте хотя бы один вариант ответа!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            findNavController().navigate(R.id.action_addNewSetOfPhrasesMenuFragment_to_setOfPhrasesMenuFragment)
        }
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.addNewSetOfPhrasesMenuSetNameValue.text.isEmpty()) {
                        Toast.makeText(requireContext(), "Поле с названием набора не должно быть пустым!", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }

                    for (item in adapter.dataset.textSources) {
                        if (item.isEmpty()){
                            Toast.makeText(requireContext(), "Поля с текстом сообщения не должны быть пустыми!", Toast.LENGTH_SHORT)
                                .show()
                            return
                        }
                    }

                    for (item in adapter.dataset.groupSources) {
                        if (item.isEmpty()) {
                            Toast.makeText(requireContext(), "Поля с id групп не должны быть пустыми!", Toast.LENGTH_SHORT)
                                .show()
                            return
                        }
                    }

                    if (adapter.dataset.textSources.isEmpty() && adapter.dataset.groupSources.isEmpty()) {
                        Toast.makeText(requireContext(), "Создайте хотя бы один вариант ответа!", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }

                    findNavController().navigate(R.id.action_addNewSetOfPhrasesMenuFragment_to_setOfPhrasesMenuFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    override fun onStop() {
        super.onStop()
        listOfBotsViewModel.saveBots(dependencyFactory, this.requireContext())
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}