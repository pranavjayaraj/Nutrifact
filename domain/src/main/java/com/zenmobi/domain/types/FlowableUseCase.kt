package com.zenmobi.domain.types

import io.reactivex.Flowable

interface FlowableUseCase<R> {
    fun execute():Flowable<R>
}