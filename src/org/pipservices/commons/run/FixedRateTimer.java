package org.pipservices.commons.run;

import java.util.*;

import org.pipservices.commons.errors.*;

public class FixedRateTimer implements IClosable {
	private INotifiable _task;
	private long _delay;
	private long _interval;
	private Timer _timer = new Timer("pip-commons-timer", true);
	private boolean _started = false;
	private Object _lock = new Object();
	
	public FixedRateTimer() {}

	public FixedRateTimer(INotifiable task, long interval, long delay) {
		_task = task;
		_interval = interval;
		_delay = delay;
	}

	public INotifiable getTask() { return _task; }
	public void setTask(INotifiable task) { _task = task; }

	public long getDelay() { return _delay; }
	public void setDelay(long delay) { _delay = delay; }
	
	public long getInterval() { return _interval; }
	public void setInterval(long interval) { _interval = interval; }
	
	public boolean isStarted() { return _started; }
	
	public void start() {
		synchronized (_lock) {
	    	// Stop previously set timer
	    	_timer.purge();
	    	
	    	// Set a new timer
	    	TimerTask task = new TimerTask() {
	    		@Override
	    		public void run() {
	    			try {
	    				_task.notify("pip-commons-timer", new Parameters());
	    			} catch (Exception ex) {
	    				// Ignore or better log!
	    			}
	    		}
	    	};
	    	_timer.scheduleAtFixedRate(task, _delay, _interval);
	    	
	    	// Set started flag
	    	_started = true;
		}
	}
	
	public void stop() {
		synchronized (_lock) {
			// Stop the timer
			_timer.purge();
			
			// Unset started flag
			_started = false;
		}
	}
	
	public void close(String correlationId) throws ApplicationException {
		stop();
	}
}
