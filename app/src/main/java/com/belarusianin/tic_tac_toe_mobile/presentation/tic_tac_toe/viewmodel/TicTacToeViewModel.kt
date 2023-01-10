package com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.belarusianin.common.presentation.navigation.NavEvent
import com.belarusianin.common.presentation.viewmodel.BaseViewModel
import com.belarusianin.game.core.Player
import com.belarusianin.game.core.interfaces.ITicTacToe
import com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.Cell
import com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.ui.TicTacToeFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TicTacToeViewModel(
    private val game: ITicTacToe
) : BaseViewModel() {

    private val _xScore = MutableStateFlow(0)
    val xScore = _xScore.asLiveData()
    private val _oScore = MutableStateFlow(0)
    val oScore = _oScore.asLiveData()

    private val _cells = MutableStateFlow<List<Cell>>(emptyList())
    val cells = _cells.asStateFlow()

    val state = game.status

    init {
        viewModelScope.launch(Dispatchers.IO) {
            game.score.state.map { it[Player.X] ?: 0 }.onEach {
                _xScore.value = it
            }.collect()
        }
        viewModelScope.launch(Dispatchers.IO) {
            game.score.state.map { it[Player.O] ?: 0 }.onEach {
                _oScore.value = it
            }.collect()
        }
        viewModelScope.launch(Dispatchers.IO) {
            game.field.state.map { lists ->
                lists.flatten().map { Cell(it) }
            }.onEach {
                _cells.value = it
            }.collect()
        }
    }

    fun makeMove(index: Int) {
        game.makeMove(index)
    }

    fun restartGame() {
        game.restart()
    }

    fun onNavigateUpClick() {
        navigate(NavEvent.Up)
    }

    fun onSettingsClick() {
        navigate(NavEvent.To(TicTacToeFragmentDirections.actionTicTacToeFragmentToSettingsFragment()))
    }
}