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
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentAddNewReactionToEventMenuBinding
import com.uneasypixel.pocketbotconstructor.domain.entities.DialogScript
import com.uneasypixel.pocketbotconstructor.presentation.viewmodels.AddNewReactionToEventViewModel

class AddNewReactionToEventMenuFragment : Fragment() {
    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentAddNewReactionToEventMenuBinding

    private val viewModel: AddNewReactionToEventViewModel by activityViewModels()


    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val event = arguments?.getParcelable<DialogScript>("EVENT_KEY")
        val eventPos = arguments?.getInt("EVENT_POS_KEY")
        if (event != null) {
            viewModel.event = event
            viewModel.eventPos = eventPos
        }
    }


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewReactionToEventMenuBinding.inflate(inflater, container, false)
        setOnBackPressedCallback()
        return binding.root
    }


    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addNewReactionToEventMenuNameEventSubtitle.text =
            viewModel.event!!.phrase.toString()
        if (viewModel.event!!.response.isNotEmpty())
            binding.addNewReactionToEventMenuNameResponseValue.setText(viewModel.event!!.response)

        binding.addNewReactionToEventMenuButtonAdd.setOnClickListener {
            val event = binding.addNewReactionToEventMenuNameEventSubtitle.text.toString()
            val response = binding.addNewReactionToEventMenuNameResponseValue.text.toString()

            val dialogScript = DialogScript(event)
            dialogScript.response = response
            val bundle =
                bundleOf("EVENT_KEY" to dialogScript, "EVENT_POS_KEY" to viewModel.eventPos)
            findNavController().navigate(
                R.id.action_addNewReactionToEventMenuFragment_to_reactionsToEventsMenuFragment,
                bundle
            )
        }
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_addNewReactionToEventMenuFragment_to_reactionsToEventsMenuFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}