package com.belarusianin.game.core

import com.belarusianin.game.core.interfaces.GameCell
import com.belarusianin.game.core.interfaces.IGameField
import com.belarusianin.game.core.interfaces.IGameField.Companion.getStandardFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameField(
    override val initState: List<List<GameCell>> = getStandardFieldState()
) : IGameField {

    init {
        if (initState.size != initState.getOrElse(0) { listOf() }.size) throw Exception("Row and Column count must be the same")
    }

    private val _state = MutableStateFlow(initState)
    override val state = _state.asStateFlow()

    override fun reset() {
        _state.update { initState }
    }

    override fun set(rowIndex: Int, columnIndex: Int, gameCell: GameCell): Boolean {
        var result = true
        _state.update {
            it.mapIndexed { index, currentGameCells ->
                if (index == rowIndex) {
                    currentGameCells.mapIndexed { index, currentGameCell ->
                        if (index == columnIndex) {
                            if (currentGameCell is GameCell.OccupiedCell) {
                                result = false
                                currentGameCell
                            } else gameCell
                        } else currentGameCell
                    }
                } else currentGameCells
            }
        }
        return result
    }
}