package com.example.launchwhendemo.ui.main

import FlowObserver
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.Flow

class FragmentObserver<T>(
    fragment: Fragment,
    private val flow: Flow<T>,
    private val collector: suspend (T) -> Unit
) {
    init {
        fragment.viewLifecycleOwnerLiveData.observe(
            fragment,
            Observer { viewLifeCycleOwner ->
                FlowObserver(viewLifeCycleOwner, flow, collector)
            }
        )
    }
}

inline fun <reified T> Flow<T>.observe(
    fragment: Fragment,
    noinline collector: suspend (T) -> Unit
) = FragmentObserver(fragment, this, collector)

inline fun <reified T> Flow<T>.observeIn(
    fragment: Fragment
) = FragmentObserver(fragment, this, {})