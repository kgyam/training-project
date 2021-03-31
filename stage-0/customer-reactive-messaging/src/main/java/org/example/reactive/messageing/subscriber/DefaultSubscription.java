package org.example.reactive.messageing.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 默认Subscription，与Subscriber是一对一关系
 * <p>
 * 通过Subscription#request方法激活Subscriber
 * <p>
 * 通过Subscription#cancel方法令Subscriber失效
 */
public class DefaultSubscription implements Subscription {

    private final SubscriberDelegation subscriber;

    public DefaultSubscription(SubscriberDelegation subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long l) {
        this.subscriber.setRequest(l);
    }

    @Override
    public void cancel() {
        this.subscriber.setCancel(true);
    }
}
