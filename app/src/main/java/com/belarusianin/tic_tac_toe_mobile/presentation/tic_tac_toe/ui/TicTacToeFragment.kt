package com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.belarusianin.common.presentation.fragment.BaseFragment
import com.belarusianin.game.core.interfaces.GameStatus
import com.belarusianin.tic_tac_toe_mobile.R
import com.belarusianin.tic_tac_toe_mobile.databinding.FragmentTicTacToeBinding
import com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.viewmodel.TicTacToeViewModel
import com.google.android.material.composethemeadapter.MdcTheme
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class TicTacToeFragment :
    BaseFragment<FragmentTicTacToeBinding, TicTacToeViewModel>(FragmentTicTacToeBinding::inflate) {

    override val viewModel by viewModel<TicTacToeViewModel>()

    private var snackbar: WeakReference<Snackbar>? = null

    override fun FragmentTicTacToeBinding.bindUI() {
        toolbar.setContent {
            MdcTheme {
                TicTacToeToolbar(
                    title = "",
                    navigateUp = viewModel::onNavigateUpClick,
                    settingsClick = viewModel::onSettingsClick
                )
            }
        }
        restartButton.setOnClickListener {
            viewModel.restartGame()
        }
    }

    override fun TicTacToeViewModel.subscribeUI() {
        binding.gameField.setContent {
            val gameState = state.collectAsState()
            val cellsState = cells.collectAsState()
            val isGameActive = gameState.value is GameStatus.Started
            MdcTheme {
                TicTacToeField(
                    cells = cellsState.value,
                    modifier = Modifier.alpha(if (isGameActive) 1.0f else 0.5f),
                    onCellClick = viewModel::makeMove
                )
            }
        }

        xScore.observe(viewLifecycleOwner) { xWinsCounter ->
            updateWinStates(xWinsCounter = xWinsCounter)
        }

        oScore.observe(viewLifecycleOwner) { oWinsCounter ->
            updateWinStates(oWinsCounter = oWinsCounter)
        }
    }

    private fun stateChangedNotification(state: GameStatus) {
        snackbar?.clear()
        snackbar = WeakReference(
            Snackbar.make(requireView(), state.toString(), Snackbar.LENGTH_SHORT).apply {
                setAction("Cancel") {}
                show()
            }
        )
    }

    private fun updateWinStates(xWinsCounter: Int? = null, oWinsCounter: Int? = null) {
        xWinsCounter?.let { counter ->
            binding.xWinsCounter.text = resources.getString(R.string.x_wins_counter_text, counter)
        }
        oWinsCounter?.let { counter ->
            binding.oWinsCounter.text = resources.getString(R.string.o_wins_counter_text, counter)
        }
    }

    override fun onStop() {
        super.onStop()
        snackbar?.clear()
        snackbar = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        snackbar?.clear()
        snackbar = null
    }
}