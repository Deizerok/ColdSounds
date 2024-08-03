package com.example.coldsounds.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coldsounds.core.ProvideLiveData
import com.example.coldsounds.core.SingleLiveEvent
import com.example.coldsounds.core.UiUpdate

interface LiveDataWrapper<T : Any> : UiUpdate<T>, ProvideLiveData<T> {

    abstract class Single<T : Any>(
        protected val liveData: MutableLiveData<T> = SingleLiveEvent()
    ) : LiveDataWrapper<T> {

        override fun updateUi(value: T) {
            liveData.value = value
        }

        override fun liveData(): LiveData<T> = liveData
    }
}