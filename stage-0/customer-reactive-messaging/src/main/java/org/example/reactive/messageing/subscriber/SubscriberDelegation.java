package org.example.reactive.messageing.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscriberDelegation<T> implements Subscriber<T> {

    private final Subscriber subscriber;

    private Subscription subscription;

    /**
     * 从Subscription传入
     */
    private Long request = -1L;


    private boolean isCancel = false;

    private boolean isComplete = false;

    public SubscriberDelegation(Subscriber subscriber) {
        this.subscriber = subscriber;
    }


    @Override
    public void onSubscribe(Subscription subscription) {
        if (subscription == null) {
            throw new NullPointerException("Subscription must not null");
        }
        this.subscription = subscription;
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T t) {
        if (subscription == null) {
            throw new NullPointerException("Subscription must not null");
        }

        if (request <= 0 || request > Long.MAX_VALUE) {
            throw new IllegalArgumentException("subscription#request传入必须是正数");
        }

        if (isCancel()) {
            System.out.println("is cancel");
            return;
        }

        if (isComplete()) {
            System.out.println("is complete");
            return;
        }
        next(t);
    }


    private void next(T t) {
        try {
            subscriber.onNext(t);
        } catch (Throwable throwable) {
            subscriber.onError(throwable);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
        isComplete = true;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public boolean isComplete() {
        return isComplete;
    }


    public void setRequest(Long request) {
        this.request = request;
    }


    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }
}
