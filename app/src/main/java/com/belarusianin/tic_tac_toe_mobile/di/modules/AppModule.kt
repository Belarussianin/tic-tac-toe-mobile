package com.belarusianin.tic_tac_toe_mobile.di.modules

import com.belarusianin.game.core.TicTacToe
import com.belarusianin.game.core.interfaces.ITicTacToe
import com.belarusianin.tic_tac_toe_mobile.presentation.main.viewmodel.MainViewModel
import com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.viewModel.TicTacToeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<ITicTacToe> { TicTacToe() }

    viewModel { MainViewModel() }
    viewModel { TicTacToeViewModel(get()) }
}