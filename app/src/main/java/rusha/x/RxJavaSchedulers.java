package rusha.x;

import io.reactivex.rxjava3.core.Scheduler;

public class RxJavaSchedulers {
    public final Scheduler subscribe;
    public final Scheduler observe;

    public RxJavaSchedulers(Scheduler subscribe, Scheduler observe) {
        this.subscribe = subscribe;
        this.observe = observe;
    }
}