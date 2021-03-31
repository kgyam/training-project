package org.example.reactive.messageing.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author kg yam
 * @date 2021-03-31 15:19
 * @since
 */
public class SubscriptionAdapter implements Subscription {

    private final DecoratingSubscriber subscriber;

    public SubscriptionAdapter(Subscriber subscriber) {
        this.subscriber = new DecoratingSubscriber (subscriber);
    }

    @Override
    public void request(long n) {
        if (n < 1) {
            throw new IllegalArgumentException ("The number of elements to requests must be more than zero!");
        }
        this.subscriber.setMaxRequest (n);
    }

    @Override
    public void cancel() {
        subscriber.cancel ();
    }

    public DecoratingSubscriber getSubscriber() {
        return subscriber;
    }
}
