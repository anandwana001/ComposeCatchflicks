package com.akshay.composecatchflicks.data.remote

import com.akshay.composecatchflicks.util.ComposeCatchflicksNetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Created by anandwana001 on
 * 02, August, 2023
 **/
class NetworkResponseCallAdapter(
    private val type: Type
) : CallAdapter<Any, Call<ComposeCatchflicksNetworkResult<Any>>> {

    override fun responseType(): Type = type

    override fun adapt(call: Call<Any>): Call<ComposeCatchflicksNetworkResult<Any>> =
        NetworkResultCall(call)
}