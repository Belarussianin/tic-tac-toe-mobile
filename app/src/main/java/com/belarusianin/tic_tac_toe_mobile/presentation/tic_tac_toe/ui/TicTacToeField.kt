package com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.belarusianin.game.core.Player
import com.belarusianin.game.core.interfaces.GameCell
import com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.Cell
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
fun TicTacToeField(
    cells: List<Cell>,
    modifier: Modifier = Modifier,
    columns: Int = 3,
    cellSize: Int = 100,
    onCellClick: (Int) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        var size by remember { mutableStateOf(IntSize.Zero) }
        FieldGrid(gridSize = size.height, columns)
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = modifier
                .background(Color.Transparent)
                .scrollable(ScrollableState { 0F }, Orientation.Vertical)
                .onSizeChanged {
                    size = it
                },
            userScrollEnabled = false
        ) {
            itemsIndexed(cells) { index, item ->
                CellItem(item, cellSize, onClick = { onCellClick(index) })
            }
        }
    }
}

@Composable
private fun CellItem(
    cell: Cell,
    cellSize: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier
            .aspectRatio(1f)
            .size(cellSize.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = onClick
            )
    ) {
        AnimatedVisibility(
            visible = cell.idRes != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            cell.idRes?.let {
                Image(
                    painter = painterResource(id = cell.idRes),
                    contentDescription = cell.toString(),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun FieldGrid(
    gridSize: Int,
    columns: Int,
    gridColor: Color = MaterialTheme.colors.onSecondary
) {
    Canvas(
        modifier = Modifier.size(gridSize.dp),
    ) {
        drawLine(
            color = gridColor,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width / columns, y = 0f),
            end = Offset(x = size.width / columns, y = size.height)
        )
        drawLine(
            color = gridColor,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 2 / columns, y = 0f),
            end = Offset(x = size.width * 2 / columns, y = size.height)
        )
        drawLine(
            color = gridColor,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height / columns),
            end = Offset(x = size.width, y = size.height / columns)
        )
        drawLine(
            color = gridColor,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 2 / columns),
            end = Offset(x = size.width, y = size.height * 2 / columns)
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_3A_XL)
@Composable
fun FieldPreview() {
    MdcTheme {
        TicTacToeField(cells = List(9) { Cell(GameCell.OccupiedCell(Player.X)) })
    }
}