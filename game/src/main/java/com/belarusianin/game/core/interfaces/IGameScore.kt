package com.belarusianin.game.core.interfaces

import com.belarusianin.game.core.Player
import kotlinx.coroutines.flow.StateFlow

interface IGameScore {
    val initState: Map<Player, Int>
    val state: StateFlow<Map<Player, Int>>

    fun reset()

    fun set(player: Player, score: Int)

    fun inc(player: Player)

    companion object {
        fun getStandardScoreState() = mapOf(
            Player.All to 0, Player.None to 0, Player.X to 0, Player.O to 0
        )
    }
}