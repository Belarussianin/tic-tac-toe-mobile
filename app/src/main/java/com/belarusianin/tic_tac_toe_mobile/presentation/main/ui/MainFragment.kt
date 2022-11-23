package com.belarusianin.tic_tac_toe_mobile.presentation.main.ui

import com.belarusianin.common.presentation.fragment.BaseFragment
import com.belarusianin.tic_tac_toe_mobile.databinding.FragmentMainBinding
import com.belarusianin.tic_tac_toe_mobile.presentation.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseFragment<FragmentMainBinding, MainViewModel>(FragmentMainBinding::inflate) {

    override val viewModel by viewModel<MainViewModel>()

    override fun FragmentMainBinding.bindUI() {
        playButton.setOnClickListener { onPlayButtonClick() }
        settingsButton.setOnClickListener { onSettingsButtonClick() }
        exitButton.setOnClickListener { onExitButtonClick() }
    }

    private fun onPlayButtonClick() = viewModel.onPlayButtonClick()

    private fun onSettingsButtonClick() = viewModel.onSettingsButtonClick()

    private fun onExitButtonClick() {
        viewModel.onExitButtonClick()
        requireActivity().finishAffinity()
    }
}