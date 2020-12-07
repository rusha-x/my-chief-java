package rusha.x

import io.reactivex.rxjava3.core.Scheduler

class RxJavaSchedulers(
    val subscribe: Scheduler,
    val observe: Scheduler
)