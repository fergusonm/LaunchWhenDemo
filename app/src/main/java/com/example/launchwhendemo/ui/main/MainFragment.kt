package com.example.launchwhendemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.launchwhendemo.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)

        // Only collect in the resumed state
        viewLifecycleOwner
                .lifecycleScope
                .launchWhenStarted {
                    viewModel.flow.collect {
                        print("********** View: Received $it in lifecycle state ")
                        println(viewLifecycleOwner.lifecycle.currentState.name)
                    }
                }

        // Notify the view model of a lifecycle event,
        // specifically the view lifecycle, just to cause
        // emissions during lifecycle states.  In practice
        // the view model will emit values down the flow
        // without care to the observer's lifecycle state.
        // This just forces the conditions
        // necessary to demonstrate the issue.
        viewLifecycleOwner.lifecycle.addObserver(
            LifecycleEventObserver {
                    source: LifecycleOwner, event: Lifecycle.Event ->
                viewModel.lifecycleEvent(event.name)
            })
    }
}

class MainViewModel : ViewModel() {

    private var counter = AtomicInteger(0)

    private val channel = Channel<String>(Channel.BUFFERED)
    val flow = channel.receiveAsFlow()

    fun lifecycleEvent(eventName: String) {
        viewModelScope.launch {
            // Send the event name down the flow to make some traffic
            val i = counter.getAndIncrement()
            val event = "$eventName $i"
            println("********** ViewModel: Emitting $event on the flow")
            channel.send("$event")
        }
    }
}