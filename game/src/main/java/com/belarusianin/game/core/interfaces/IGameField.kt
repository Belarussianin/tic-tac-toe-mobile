package com.belarusianin.game.core.interfaces

import kotlinx.coroutines.flow.StateFlow

interface IGameField {
    val initState: List<List<GameCell>>
    val state: StateFlow<List<List<GameCell>>>

    fun reset()
    fun set(rowIndex: Int, columnIndex: Int, gameCell: GameCell): Boolean
    fun get(rowIndex: Int, columnIndex: Int): GameCell

    companion object {
        fun getStandardFieldState(size: Int = 3) = List(size) {
            List<GameCell>(size) {
                GameCell.EmptyCell
            }
        }
    }
}