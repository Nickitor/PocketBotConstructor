package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.Domain.Entities.Phrase
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAddNewPhraseMenuBinding

class AddNewPhraseMenuFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private var _binding: FragmentAddNewPhraseMenuBinding? = null
    private val binding get() = _binding!!

    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true ) {
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
        _binding = FragmentAddNewPhraseMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addNewPhraseButtonAdd.setOnClickListener {
            val phraseName = binding.addNewPhraseName.text.toString()
            val responseName = binding.addNewResponseName.text.toString()
            val phrase = Phrase(phraseName)
            phrase.response.add(responseName)
            val bundle = bundleOf("NEW_PHRASE_KEY" to phrase)
            findNavController().navigate(R.id.action_addNewPhraseMenuFragment_to_reactionsToPhrasesMenuFragment, bundle)
        }
    }

    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}