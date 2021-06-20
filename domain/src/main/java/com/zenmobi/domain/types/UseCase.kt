package com.zenmobi.domain.types

interface UseCase<R> {
    fun execute():R
}