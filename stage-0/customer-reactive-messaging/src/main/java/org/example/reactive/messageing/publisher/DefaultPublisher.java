package org.example.reactive.messageing.publisher;

import org.example.reactive.messageing.subscriber.DefaultSubscription;
import org.example.reactive.messageing.subscriber.SubscriberDelegation;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.LinkedList;
import java.util.List;

public class DefaultPublisher<T> implements Publisher<T> {
    private List<Subscriber<? super T>> subscribers = new LinkedList<>();


    @Override
    public void subscribe(Subscriber<? super T> subscriber) {
        assert subscriber != null;

        Subscription subscription = new DefaultSubscription(new SubscriberDelegation(subscriber));
        subscriber.onSubscribe(subscription);
        subscription.request(1L);
        subscribers.add(subscriber);
    }
}
