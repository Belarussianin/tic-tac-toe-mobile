package com.belarusianin.game.core.interfaces

import com.belarusianin.game.core.Player
import kotlinx.coroutines.flow.StateFlow

interface ITicTacToe {
    val score: IGameScore
    val initPlayer: Player
    val currentPlayer: StateFlow<Player>
    val initStatus: GameStatus
    val status: StateFlow<GameStatus>
    val field: IGameField

    fun restart()
    fun reset()
    fun makeMove(index: Int): Boolean
    fun makeMove(rowIndex: Int, columnIndex: Int): Boolean
}