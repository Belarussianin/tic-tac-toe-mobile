package com.belarusianin.game.core

import com.belarusianin.game.core.interfaces.IGameScore
import com.belarusianin.game.core.interfaces.IGameScore.Companion.getStandardScoreState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameScore(
    override val initState: Map<Player, Int> = getStandardScoreState()
) : IGameScore {

    private val _state = MutableStateFlow(initState)
    override val state = _state.asStateFlow()

    override fun reset() {
        _state.update { initState }
    }

    override fun set(player: Player, score: Int) {
        _state.update { it.plus(player to score) }
    }

    override fun inc(player: Player) {
        _state.update { it.plus(player to (_state.value[player]?.plus(1) ?: 1)) }
    }
}