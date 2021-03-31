package org.example.reactive.messageing.publisher;

import org.example.reactive.messageing.subscriber.BusinessSubscriber;
import org.example.reactive.messageing.subscriber.SubscriptionAdapter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kg yam
 * @date 2021-03-31 15:12
 * @since
 */
public class DefaultPublisher<T> implements Publisher<T> {

    List<Subscriber> subscribers = new LinkedList<> ();

    @Override
    public void subscribe(Subscriber<? super T> s) {
        SubscriptionAdapter subscription = new SubscriptionAdapter (s);
        s.onSubscribe (subscription);
        subscription.request (Long.MAX_VALUE);
        subscribers.add (subscription.getSubscriber ());
    }


    public void publish(T t) {
        subscribers.forEach (subscriber ->
                subscriber.onNext (t)
        );
    }


    public static void main(String[] args) {
        DefaultPublisher publisher = new DefaultPublisher ();

        publisher.subscribe (new BusinessSubscriber (5));

        for (int i = 0; i < 5; i++) {
            publisher.publish (i);
        }
    }
}
