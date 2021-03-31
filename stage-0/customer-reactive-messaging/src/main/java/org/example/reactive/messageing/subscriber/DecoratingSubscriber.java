package org.example.reactive.messageing.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-31 15:22
 * @since
 */
public class DecoratingSubscriber<T> implements Subscriber<T> {

    private final Subscriber<T> source;

    private final Logger logger;

    private long maxRequest = -1;

    private boolean canceled = false;

    private boolean completed = false;

    private long requestCount = 0;

    public DecoratingSubscriber(Subscriber<T> source) {
        this.source = source;
        this.logger = Logger.getLogger (source.getClass ().getName ());
    }

    @Override
    public void onSubscribe(Subscription s) {
        source.onSubscribe (s);
    }

    @Override
    public void onNext(T t) {

        assertRequest ();

        if (isCompleted ()) {
            logger.severe ("it is completed");
            return;
        }

        if (isCanceled ()) {
            logger.warning(String.format("The Subscriber has canceled the data subscription," +
                    " current data[%s] will be ignored!", t));
            return;
        }

        /**
         * 这段代码是做背压处理？
         */
        if (requestCount >= maxRequest && maxRequest < Long.MAX_VALUE) {
            onComplete ();
            logger.warning (String.format ("The number of requests is up to the max threshold[%d]," +
                    " the data subscription is completed!", maxRequest));
            return;
        }
        next (t);
    }


    private void next(T t) {
        try {
            ++requestCount;
            source.onNext (t);
        } catch (Exception e) {
            onError (e);
        } finally {
            requestCount--;
        }
    }


    private void assertRequest() {
        if (maxRequest < 1) {
            throw new IllegalStateException ("the number of request must be initialized before " +
                    "Subscriber#onNext(Object) method, please set the positive number to " +
                    "Subscription#request(int) method on Publisher#subscribe(Subscriber) phase.");
        }
    }

    @Override
    public void onError(Throwable t) {
        source.onError (t);
    }

    @Override
    public void onComplete() {
        source.onComplete ();
        this.completed = true;
    }


    public boolean isCanceled() {
        return canceled;
    }


    public boolean isCompleted() {
        return completed;
    }

    public void setMaxRequest(long maxRequest) {
        this.maxRequest = maxRequest;
    }

    /**
     * 取消订阅
     */
    public void cancel() {
        canceled = true;
    }
}
