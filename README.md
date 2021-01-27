# LaunchWhenDemo
A demonstration of data loss using launchWhenResumed / launchWhenStarted.

Start the app and note the logs.

You should see something like 
```
********** event: ON_CREATE
********** ViewModel: Emitting ON_CREATE 0 on the flows
********** event: ON_START
********** ViewModel: Emitting ON_START 1 on the flows
********** View: launch when started collector received ON_CREATE 0 in lifecycle state STARTED
********** View: launch when started collector received ON_START 1 in lifecycle state STARTED
********** View: observe in collector received ON_CREATE 0 in lifecycle state STARTED
********** View: observe in collector received ON_START 1 in lifecycle state STARTED
********** event: ON_RESUME
********** ViewModel: Emitting ON_RESUME 2 on the flows
********** View: launch when started collector received ON_RESUME 2 in lifecycle state RESUMED
********** View: observe in collector received ON_RESUME 2 in lifecycle state RESUMED
```

Rotate the device to perform a configuration change.
```
********** event: ON_PAUSE
********** ViewModel: Emitting ON_PAUSE 3 on the flows
********** View: launch when started collector received ON_PAUSE 3 in lifecycle state STARTED
********** View: observe in collector received ON_PAUSE 3 in lifecycle state STARTED
********** event: ON_STOP
********** ViewModel: Emitting ON_STOP 4 on the flows
********** event: ON_DESTROY
********** ViewModel: Emitting ON_DESTROY 5 on the flows
********** event: ON_CREATE
********** ViewModel: Emitting ON_CREATE 6 on the flows
********** event: ON_START
********** ViewModel: Emitting ON_START 7 on the flows
********** View: launch when started collector received ON_DESTROY 5 in lifecycle state STARTED
********** View: launch when started collector received ON_CREATE 6 in lifecycle state STARTED
********** View: launch when started collector received ON_START 7 in lifecycle state STARTED
********** View: observe in collector received ON_STOP 4 in lifecycle state STARTED
********** View: observe in collector received ON_DESTROY 5 in lifecycle state STARTED
********** View: observe in collector received ON_CREATE 6 in lifecycle state STARTED
********** View: observe in collector received ON_START 7 in lifecycle state STARTED
********** event: ON_RESUME
********** ViewModel: Emitting ON_RESUME 8 on the flows
********** View: launch when started collector received ON_RESUME 8 in lifecycle state RESUMED
********** View: observe in collector received ON_RESUME 8 in lifecycle state RESUMED
```

Note the `ON_STOP 4` event is never received by the "launch when" observer but it is by the "observe in" observer.
