package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAddNewDialogScriptMenuBinding
import com.uneasypixel.pocketbotconstructor.domain.entities.DialogScript
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.AddNewDialogScriptViewModel
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.ListOfBotsViewModel


class AddNewDialogScriptMenuFragment : Fragment() {
    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentAddNewDialogScriptMenuBinding

    private val viewModel: AddNewDialogScriptViewModel by activityViewModels()
    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBotToViewModel(arguments?.getString("BOT_NAME_KEY"))
    }

    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewDialogScriptMenuBinding.inflate(inflater, container, false)
        setOnBackPressedCallback()
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val listOfPhrases: MutableList<String> = mutableListOf("")
        for (set in viewModel.bot!!.setsOfPhrases) {
            listOfPhrases.add(set.name)
        }
        val adapter: ArrayAdapter<*> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            listOfPhrases
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.addNewSetNameValue.adapter = adapter

        binding.addNewPhraseButtonAdd.setOnClickListener {
            val phrase = binding.addNewPhraseName.text.toString()
            val response = binding.addNewResponseName.text.toString()
            val set = binding.addNewSetNameValue.selectedItem.toString()

            if (phrase.isEmpty()) {

                Toast.makeText(
                    requireContext(),
                    "Поле фразы не должно быть пустым!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }

            if (response.isEmpty() && set.isEmpty()) {

                Toast.makeText(
                    requireContext(),
                    "Заполните поле реакции или выберите набор!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }

            val dialogScript = DialogScript(phrase)
            if (set.isNotEmpty()) {
                dialogScript.setOfPhrases =
                    viewModel.bot!!.setsOfPhrases[binding.addNewSetNameValue.selectedItemPosition - 1]
            } else
                dialogScript.response = response
            val bundle = bundleOf("NEW_PHRASE_KEY" to dialogScript)
            findNavController().navigate(
                R.id.action_addNewPhraseMenuFragment_to_reactionsToPhrasesMenuFragment,
                bundle
            )
        }
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_addNewPhraseMenuFragment_to_reactionsToPhrasesMenuFragment)
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
}