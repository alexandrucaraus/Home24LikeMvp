package eu.caraus.home24.application.common.schedulers

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler

}
