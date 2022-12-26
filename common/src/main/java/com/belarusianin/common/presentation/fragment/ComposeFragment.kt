package com.belarusianin.common.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.belarusianin.common.presentation.navigation.NavigationObserver
import com.belarusianin.common.presentation.viewmodel.BaseViewModel

abstract class ComposeFragment<ViewModel : BaseViewModel> : Fragment() {

    private var _composeView: ComposeView? = null
    protected val composeView: ComposeView get() = _composeView!!
    protected abstract val viewModel: ViewModel
    protected open val navObserver by lazy { NavigationObserver(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navObserver.register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).also {
        _composeView = it
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {
            Content()
        }
    }

    @Composable
    protected open fun Content() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _composeView = null
    }
}