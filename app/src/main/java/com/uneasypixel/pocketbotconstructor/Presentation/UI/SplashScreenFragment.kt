package com.uneasypixel.pocketbotconstructor.Presentation.UI

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uneasypixel.pocketbotconstructor.Presentation.ViewModels.ListOfBotsViewModel
import com.uneasypixel.pocketbotconstructor.ProgApplication
import com.uneasypixel.pocketbotconstructor.R
import com.uneasypixel.pocketbotconstructor.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment() {

    // Объект привязки для получения объектов интерфейса
    private lateinit var binding: FragmentSplashScreenBinding

    private val listOfBotsViewModel: ListOfBotsViewModel by activityViewModels()


    // Создание фрагмента
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnBackPressedCallback()
    }


    // Создание макета фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    // Инициализация компонентов макета фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }


    override fun onStart() {
        super.onStart()

        Handler().postDelayed(Runnable {
            val dependencyFactory = (requireActivity().application as ProgApplication).dependencyFactory

            listOfBotsViewModel.getBots(dependencyFactory, this@SplashScreenFragment.requireContext())


            for (bot in listOfBotsViewModel.listOfBotsDTO)
                bot.isRunning = false

            for (bot in listOfBotsViewModel.listOfBots)
                bot.isRunning = false

            if (listOfBotsViewModel.listOfBotsDTO.isEmpty())
                findNavController().navigate(R.id.action_splashScreenFragment_to_firstStartStepOneFragment)
            else
                findNavController().navigate(R.id.action_splashScreenFragment_to_listOfBotsFragment)

        }, 500)
    }


    private fun setOnBackPressedCallback() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    // Удаление компнентов внутри фрагмента
    override fun onDestroyView() {
        super.onDestroyView()
    }
}