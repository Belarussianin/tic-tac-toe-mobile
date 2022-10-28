package com.belarusianin.tic_tac_toe

sealed interface CellState {
    class Full(player: Player) : CellState
    object Empty : CellState
}