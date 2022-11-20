package com.belarusianin.game.core.interfaces

import com.belarusianin.game.core.Player
import kotlinx.coroutines.flow.StateFlow

interface IGameScore {
    val initState: Map<Player, Int>
    val state: StateFlow<Map<Player, Int>>

    fun reset()
    fun set(player: Player, score: Int)

    companion object {
        fun getStandardScoreState() = mapOf(Player.X to 0, Player.O to 0)
    }
}