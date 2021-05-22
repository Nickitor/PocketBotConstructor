package com.uneasypixel.pocketbotconstructor.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.domain.entities.DialogScript
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAddNewDialogScriptMenuBinding

class AddNewDialogScriptMenuFragment : Fragment() {
    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentAddNewDialogScriptMenuBinding

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.addNewPhraseButtonAdd.setOnClickListener {
            val phraseName = binding.addNewPhraseName.text.toString()
            val responseName = binding.addNewResponseName.text.toString()
            val phrase = DialogScript(phraseName)
            phrase.response.add(responseName)
            val bundle = bundleOf("NEW_PHRASE_KEY" to phrase)
            findNavController().navigate(R.id.action_addNewPhraseMenuFragment_to_reactionsToPhrasesMenuFragment, bundle)
        }
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}