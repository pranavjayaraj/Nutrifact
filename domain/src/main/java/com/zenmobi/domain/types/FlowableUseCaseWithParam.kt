package com.zenmobi.domain.types

import io.reactivex.Flowable

interface FlowableUseCaseWithParam<P,R> {
    fun execute(param:P):Flowable<R>
}