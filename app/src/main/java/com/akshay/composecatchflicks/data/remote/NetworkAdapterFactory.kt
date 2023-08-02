package com.akshay.composecatchflicks.data.remote

import com.akshay.composecatchflicks.util.ComposeCatchflicksNetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by anandwana001 on
 * 02, August, 2023
 **/
class NetworkAdapterFactory : CallAdapter.Factory() {

    companion object {
        fun create(): NetworkAdapterFactory = NetworkAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != ComposeCatchflicksNetworkResult::class.java) {
            return null
        }
        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkResponseCallAdapter(resultType)
    }
}
