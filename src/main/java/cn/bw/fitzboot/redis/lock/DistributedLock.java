package cn.bw.fitzboot.redis.lock;


import lombok.Setter;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class DistributedLock extends AbstractLock {

    @Setter
    private StringRedisTemplate stringRedisTemplate;

    protected String lockKey;
    protected long lockExpire;

    public DistributedLock(StringRedisTemplate stringRedisTemplate, String lockKey, long lockExpire) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockKey = lockKey;
        this.lockExpire = lockExpire;
    }

    @Override
    public boolean tryLock() {
        long lockExpireTime = System.currentTimeMillis() + lockExpire + 1;
        String expire = String.valueOf(lockExpireTime);
        if (this.stringRedisTemplate.opsForValue().setIfAbsent(lockKey, expire)) {
            // 设置相关标识
            this.locked = true;
            setCurrentOwnerThread(Thread.currentThread());
            return true;
        }
        String value = this.stringRedisTemplate.opsForValue().get(lockKey);
        if (value != null && isTimeExpire(value)) {
            String oldValue = this.stringRedisTemplate.opsForValue().getAndSet(lockKey, expire);
            if (oldValue != null && isTimeExpire(oldValue)) {
                this.locked = true;
                setCurrentOwnerThread(Thread.currentThread());
                return true;
            }
        }
        return false;
    }

    private boolean isTimeExpire(String value) {
        return Long.parseLong(value) < System.currentTimeMillis();
    }

    @Override
    protected void unlock0() {
    	doUnlock();
    }

    /**
     * 释放锁
     */
    public void doUnlock() {
    	this.locked = false;
        this.stringRedisTemplate.delete(lockKey);
    }

    public boolean isLocked() {
        if (locked) {
            return true;
        } else {
            String value = this.stringRedisTemplate.opsForValue().get(lockKey);
            if (value == null) {
                return false;
            }
            return !isTimeExpire(value);
        }

    }

    @Override
    protected boolean lock(boolean useTimeout, long timeout, TimeUnit timeUnit,
                           boolean interrupt) throws InterruptedException {
        if (interrupt) {
            checkInterruptIon();
        }
        long start = System.currentTimeMillis();
        long timeoutMillis = timeUnit.toMillis(timeout);

        while (useTimeout ? isTimeOut(start, timeoutMillis) : true) {
            if (interrupt) {
                checkInterruptIon();
            }
            long lockExpireTime = System.currentTimeMillis() + lockExpire + 1;
            String expire = String.valueOf(lockExpireTime);
            if (this.stringRedisTemplate.opsForValue().setIfAbsent(lockKey, expire)) {
                this.locked = true;
                setCurrentOwnerThread(Thread.currentThread());
                return true;
            }
        }

        return false;
    }

    public boolean isTimeOut(long start, long timeoutMillis) {
        return start + timeoutMillis > System.currentTimeMillis();
    }

    /**
     * 检查当前线程是否阻塞
     *
     * @throws InterruptedException
     */
    public void checkInterruptIon() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }


}
