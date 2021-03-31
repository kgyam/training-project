package org.example.reactive.messageing.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author kg yam
 * @date 2021-03-31 17:38
 * @since
 */
public class BusinessSubscriber<T> implements Subscriber<T> {

    private Subscription subscription;

    private int count = 0;

    private final long maxRequest;

    public BusinessSubscriber(long maxRequest) {
        this.maxRequest = maxRequest;
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
    }

    @Override
    public void onNext(T t) {
        if (count++ > 2) {
            System.out.println ("达到请求处理上限:"+t);
            subscription.cancel ();
            return;
        }
        System.out.println ("data:" + t);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println ("遇到异常:" + t);
    }

    @Override
    public void onComplete() {
        System.out.println ("收到数据完成");
    }
}
