package com.zenmobi.data

import io.reactivex.disposables.Disposable

fun Disposable?.checkAndDispose(){
    if (this?.isDisposed == false){
        this.dispose()
    }
}