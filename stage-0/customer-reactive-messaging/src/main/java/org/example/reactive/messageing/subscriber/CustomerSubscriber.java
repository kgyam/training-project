package org.example.reactive.messageing.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class CustomerSubscriber implements Subscriber<Long> {
    private Subscription subscription;


    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(Long aLong) {


        if (aLong >=3L) {
            System.out.println("数据达到阈值:"+aLong);
            onComplete();
        }
        System.out.println("接收数据:" + aLong);

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
    }
}
