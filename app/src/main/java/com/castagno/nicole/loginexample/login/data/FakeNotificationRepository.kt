package com.castagno.nicole.loginexample.login.data

import com.castagno.nicole.loginexample.login.domain.NotificationRepository
import com.castagno.nicole.loginexample.login.domain.NotificationSetting
import rx.Observable
import java.util.concurrent.TimeUnit

class FakeNotificationRepository : NotificationRepository {
    override fun monitorCurrentNotificationSettings(): Observable<NotificationSetting> {
        return Observable.combineLatest(
                monitorCurrentMorningTime(),
                monitorCurrentEveningTime()) { morningTime, eveningTime ->
            NotificationSetting(morningTime, eveningTime)
        }
    }

    private fun monitorCurrentMorningTime(): Observable<String> {
        return Observable.never<String>()
                .startWith("15:00")
                .delay(5, TimeUnit.SECONDS)
                .startWith("12:00")
    }

    private fun monitorCurrentEveningTime(): Observable<String> {
        return Observable.never<String>()
                .startWith("22:00")
                .delay(3, TimeUnit.SECONDS)
                .startWith("20:00")
    }
    //    |-----(DATA)----------------(DATA)----
    //    |-----(ContentSuggestion)---(ContentSuggestion)

    //    |----12:00--------------------15:00--------
    //    |----20:00-------22:00---------------------
    //    |---(12,20)-----(12,22)------(15,22)-------

    //    |--(COURSE SUGGESTION)----null-----------------------------(COURSE SUGGESTION)--------------------------------------
    //    |--(NO PLANNED TASKS)-----------------------------------------------------------------------(OMG PLANNED TASKS)-----
    //    |--(suggestion+no tasks)--(no suggestion+no tasks)----------(suggestion+no tasks)-----------(suggestion+tasks)------
}
