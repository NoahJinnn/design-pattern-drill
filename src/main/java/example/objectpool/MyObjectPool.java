package example.objectpool;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class should be singleton
 * @param <T> Object type
 */
public abstract class MyObjectPool<T> {
    private final ConcurrentLinkedDeque<T> pool = new ConcurrentLinkedDeque<>();
    private ScheduledExecutorService validator;
    private boolean isShutdown = false;
    private int minIdle = 1;
    private int maxIdle = 5;
    private long validateCyclic = 5000;

    /**
     * Creates the pool.
     *
     * @param minIdle            minimum number of objects residing in the pool
     * @param maxIdle            maximum number of objects residing in the pool
     * @param validateCyclic time in seconds for periodical checking of minIdle / maxIdle conditions in a separate thread.
     *                           When the number of objects is less than minIdle, missing instances will be created.
     *                           When the number of objects is greater than maxIdle, too many instances will be removed.
     */
    public MyObjectPool(int minIdle, int maxIdle, long validateCyclic) {
        this.minIdle = minIdle;
        this.maxIdle = maxIdle;
        this.validateCyclic = validateCyclic;
        for(int i = 0; i < minIdle; i++) {
            pool.add(createObject());
        }
        initValidator();
    }

    private void initValidator() {
        validator = Executors.newSingleThreadScheduledExecutor();
        validator.scheduleWithFixedDelay(() -> {
                while(pool.size() > maxIdle) {
                    pool.poll();
                }
                while(pool.size() < minIdle) {
                    pool.add(createObject());
                }
        }, validateCyclic, validateCyclic, TimeUnit.MILLISECONDS);
    }

    abstract T createObject();

    /**
     * Gets the next free object from the pool. If the pool doesn't contain any objects,
     * a new object will be created and given to the caller of this method back.
     *
     * @return T borrowed object
     */
    public T borrowObject() {
        if(isShutdown) throw new RuntimeException("Pool is shutdown, need to be power up");
        T object;
        if ((object = pool.poll()) == null) {
            object = createObject();
        }

        return object;
    }

    /**
     * Returns object back to the pool.
     *
     * @param object object to be returned
     */
    public void returnObject(T object) {
        if (object == null) {
            return;
        }

        this.pool.offer(object);
        if(isShutdown) this.pool.poll();

    }

    /**
     * Power up this pool.
     */
    public void powerUp() {
        this.isShutdown = false;
        initValidator();
    }

    /**
     * Shutdown this pool.
     */
    public void shutdown() {
        this.isShutdown = true;
        while(!pool.isEmpty()) pool.poll();
        if (validator != null) {
            validator.shutdown();
        }
    }

}
