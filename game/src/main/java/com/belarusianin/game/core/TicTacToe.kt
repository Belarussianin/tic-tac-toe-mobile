package com.belarusianin.game.core

import com.belarusianin.game.core.interfaces.GameCell
import com.belarusianin.game.core.interfaces.GameStatus
import com.belarusianin.game.core.interfaces.IGameField
import com.belarusianin.game.core.interfaces.IGameScore
import com.belarusianin.game.core.interfaces.ITicTacToe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update

class TicTacToe(
    override val score: IGameScore = GameScore(),
    override val initPlayer: Player = Player.X,
    override val initStatus: GameStatus = GameStatus.Started,
    override val field: IGameField = GameField(),
) : ITicTacToe {

    private val _currentPlayer = MutableStateFlow(initPlayer)
    override val currentPlayer = _currentPlayer.asStateFlow()

    private val _status = MutableStateFlow(initStatus)
    override val status = _status.asStateFlow()

    override fun restart() {
        _currentPlayer.update { initPlayer }
        _status.update { initStatus }
        field.reset()
    }

    override fun reset() {
        score.reset()
        restart()
    }

    override fun makeMove(index: Int): Boolean {
        val fieldSize = field.state.value.size
        return makeMove(index / fieldSize, index % fieldSize)
    }

    /**
     * returns pair: is make move successful and message if not
     */
    override fun makeMove(rowIndex: Int, columnIndex: Int): Boolean {
        return when (_status.value) {
            GameStatus.Started -> {
                val currentPlayer = _currentPlayer.value
                val isSuccessful = field.set(
                    rowIndex,
                    columnIndex,
                    GameCell.OccupiedCell(currentPlayer)
                )
                if (isSuccessful) _currentPlayer.update { !currentPlayer }
                statusUpdate()
                true
            }
            GameStatus.Draw -> false
            is GameStatus.Win -> false
        }
    }

    private fun statusUpdate() {
        _status.getAndUpdate { checkForDraw() ?: checkForWin() ?: it }
        when (val newStatus = _status.value) {
            is GameStatus.Win -> score.inc(newStatus.player)
            is GameStatus.Draw -> score.inc(Player.None)
            else -> {}
        }
    }

    private fun checkForWin(row: List<GameCell>): GameStatus.Win? {
        return when {
            row.any { it is GameCell.EmptyCell } -> null
            row.all {
                it is GameCell.OccupiedCell && it.player == Player.X
            } -> GameStatus.Win(Player.X)

            row.all {
                it is GameCell.OccupiedCell && it.player == Player.O
            } -> GameStatus.Win(Player.O)

            else -> null
        }
    }

    private fun checkForWin(): GameStatus? {
        field.state.value.apply {
            forEach { row ->
                checkForWin(row)?.let {
                    return it
                }
            }
            for (columnIndex in indices) {
                val column = List(size) { this[it][columnIndex] }
                checkForWin(column)?.let {
                    return it
                }
            }
            val mainDiagonal = List(size) { this[it][it] }
            val secondDiagonal = List(size) { this[it][lastIndex - it] }
            checkForWin(mainDiagonal)?.let {
                return it
            }
            checkForWin(secondDiagonal)?.let {
                return it
            }
        }
        return null
    }

    private fun checkForDraw(): GameStatus? {
        field.state.value.apply {
            return when {
                flatten().all { it is GameCell.OccupiedCell } -> checkForWin() ?: GameStatus.Draw
                else -> null
            }
        }
    }
}