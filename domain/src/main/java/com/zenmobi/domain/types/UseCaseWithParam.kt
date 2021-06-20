package com.zenmobi.domain.types

interface UseCaseWithParam<P,R> {
    fun execute(param:P):R
}