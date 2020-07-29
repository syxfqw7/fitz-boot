package cn.bw.fitzboot.redis.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
public abstract class AbstractLock implements Lock {
	
	protected volatile boolean locked;
	
	private Thread currentOwnerThread;
	
	public void lock() {
		
		try {
			lock(false, 0, null, false);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			Thread.currentThread().interrupt();
		}
	}
	
	public void lockInterruptibly() throws InterruptedException {
		lock(false, 0, null, true);
	}
	
	public boolean tryLock(long time, TimeUnit unit) {

		try {
			return lock(true, time, unit, false);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return false;
	}

	public boolean tryLockInterruptibly(long time, TimeUnit unit)
			throws InterruptedException {
		return lock(true, time, unit, true);
	}
	
	public void unlock() {
		unlock0();
		setCurrentOwnerThread(null);
	}
	
	protected void setCurrentOwnerThread(Thread thread) {
		currentOwnerThread = thread;
	}

	protected abstract void unlock0();

	protected final Thread getCurrentOwnerThread() {
		return currentOwnerThread;
	}

	protected abstract boolean lock(boolean useTimeout, long timeout, TimeUnit timeUnit, boolean interrupt) throws InterruptedException;
	
}
