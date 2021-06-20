package com.zenmobi.domain.types

import io.reactivex.Completable

interface CompletableUseCaseWithParam<P> {
    fun execute(param:P):Completable
}