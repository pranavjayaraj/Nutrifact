package com.zenmobi.domain.types

import io.reactivex.Single

interface SingleUseCase<R> {
    fun execute():Single<R>
}