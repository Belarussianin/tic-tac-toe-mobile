package com.belarusianin.tic_tac_toe_mobile.presentation.main.viewmodel

import com.belarusianin.common.presentation.navigation.NavEvent
import com.belarusianin.common.presentation.viewmodel.BaseViewModel
import com.belarusianin.tic_tac_toe_mobile.presentation.main.ui.MainFragmentDirections

class MainViewModel : BaseViewModel() {

    fun onPlayButtonClick() {
        navigate(NavEvent.To(MainFragmentDirections.actionMainFragmentToTicTacToeFragment()))
    }

    fun onSettingsButtonClick() {
        navigate(NavEvent.To(MainFragmentDirections.actionMainFragmentToSettingsFragment()))
    }

    fun onExitButtonClick() {}
}