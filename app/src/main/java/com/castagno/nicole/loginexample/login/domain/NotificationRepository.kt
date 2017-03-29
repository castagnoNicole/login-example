package com.castagno.nicole.loginexample.login.domain

import rx.Completable
import rx.Observable

data class NotificationSetting(val morningTime: String, val eveningTime: String)

interface NotificationRepository {
    fun monitorCurrentNotificationSettings(): Observable<NotificationSetting>
}
