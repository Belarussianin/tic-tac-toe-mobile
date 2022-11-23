package com.belarusianin.tic_tac_toe_mobile.presentation.settings.ui

import com.belarusianin.common.presentation.fragment.BaseFragment
import com.belarusianin.tic_tac_toe_mobile.databinding.FragmentSettingsBinding
import com.belarusianin.tic_tac_toe_mobile.presentation.settings.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment :
    BaseFragment<FragmentSettingsBinding, SettingsViewModel>(FragmentSettingsBinding::inflate) {

    override val viewModel by viewModel<SettingsViewModel>()

}