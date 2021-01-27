# LaunchWhenDemo
A demonstration of data loss using launchWhenResumed / launchWhenStarted.

Start the app and note the logs.

You should see something like 
```
********** ViewModel: Emitting ON_CREATE 0 on the flow
********** View: Received ON_CREATE 0 in lifecycle state STARTED
********** ViewModel: Emitting ON_START 1 on the flow
********** View: Received ON_START 1 in lifecycle state STARTED
********** ViewModel: Emitting ON_RESUME 2 on the flow
********** View: Received ON_RESUME 2 in lifecycle state RESUMED
```

Rotate the device to perform a configuration change.
```
********** ViewModel: Emitting ON_PAUSE 3 on the flow
********** View: Received ON_PAUSE 3 in lifecycle state STARTED
********** ViewModel: Emitting ON_STOP 4 on the flow
********** View: Received ON_STOP 4 in lifecycle state CREATED
********** ViewModel: Emitting ON_DESTROY 5 on the flow
********** ViewModel: Emitting ON_CREATE 6 on the flow
********** View: Received ON_CREATE 6 in lifecycle state STARTED
********** ViewModel: Emitting ON_START 7 on the flow
********** View: Received ON_START 7 in lifecycle state STARTED
********** ViewModel: Emitting ON_RESUME 8 on the flow
********** View: Received ON_RESUME 8 in lifecycle state RESUMED
```

Note the `ON_DESTROY 5` event is never received by the observer.
