package com.belarusianin.tic_tac_toe_mobile.presentation.main.ui

import com.belarusianin.common.presentation.BaseActivity
import com.belarusianin.tic_tac_toe_mobile.databinding.ActivityMainBinding
import com.belarusianin.tic_tac_toe_mobile.presentation.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    override val viewModel by viewModel<MainViewModel>()
}