package com.chinky.localandroidapplication.retrofit

import com.google.gson.InstanceCreator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.lang.reflect.Type

class FlowInstanceCreator<T> : InstanceCreator<Flow<T>> {
    override fun createInstance(type: Type): Flow<T> {
        return emptyFlow()
    }
}