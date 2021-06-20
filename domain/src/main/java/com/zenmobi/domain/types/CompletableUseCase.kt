package com.zenmobi.domain.types

import io.reactivex.Completable

interface CompletableUseCase {
    fun execute():Completable
}