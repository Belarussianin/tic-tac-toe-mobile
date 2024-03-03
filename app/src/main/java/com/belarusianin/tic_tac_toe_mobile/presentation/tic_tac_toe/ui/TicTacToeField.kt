package com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.belarusianin.game.core.Player
import com.belarusianin.game.core.interfaces.GameCell
import com.belarusianin.tic_tac_toe_mobile.presentation.tic_tac_toe.Cell
import com.google.accompanist.themeadapter.material.MdcTheme

@Composable
fun TicTacToeField(
    cells: List<Cell>,
    modifier: Modifier = Modifier,
    columnCount: Int = 3,
    onCellClick: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .aspectRatio(0.9f)
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        FieldGrid(modifier = Modifier.matchParentSize(), columnCount)
        LazyVerticalGrid(
            columns = GridCells.Fixed(columnCount),
            userScrollEnabled = false
        ) {
            itemsIndexed(cells) { index, cell ->
                CellItem(cell, onClick = { onCellClick(index) })
            }
        }
    }
}

@Composable
private fun CellItem(
    cell: Cell,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val mutableInteractionSource = remember { MutableInteractionSource() }
    Box(
        modifier
            .aspectRatio(1f)
            .clickable(
                interactionSource = mutableInteractionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        AnimatedVisibility(
            visible = cell.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            cell.idRes?.let {
                Image(
                    painter = painterResource(id = cell.idRes),
                    contentDescription = cell.toString(),
                    modifier = Modifier.aspectRatio(1f)
                )
            }
        }
    }
}

@Composable
fun FieldGrid(
    modifier: Modifier,
    columns: Int,
    gridColor: Color = MaterialTheme.colors.onSecondary
) {
    Canvas(
        modifier = modifier,
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

@Composable
private fun FieldPreview() {
    MdcTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            TicTacToeField(cells = List(9) {
                if (it % 2 == 0) {
                    Cell(GameCell.OccupiedCell(Player.O))
                } else Cell(GameCell.OccupiedCell(Player.X))
            })
        }
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_3A_XL)
@Composable
fun FieldPreviewPortrait() {
    FieldPreview()
}

const val deviceLandscape = "spec:orientation=landscape,width=411dp,height=891dp, dpi=250"

@Preview(showSystemUi = true, device = deviceLandscape)
@Composable
fun FieldPreviewLandscape() {
    FieldPreview()
}

@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun FieldPreviewTablet() {
    FieldPreview()
}

@Preview(showSystemUi = true, device = Devices.DESKTOP)
@Composable
fun FieldPreviewDesktop() {
    FieldPreview()
}