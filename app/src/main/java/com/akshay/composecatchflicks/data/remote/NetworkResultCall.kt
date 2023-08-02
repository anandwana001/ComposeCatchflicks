package com.akshay.composecatchflicks.data.remote

import com.akshay.composecatchflicks.util.ComposeCatchflicksNetworkResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by anandwana001 on
 * 02, August, 2023
 **/
class NetworkResultCall(
    private val data: Call<Any>
) : Call<ComposeCatchflicksNetworkResult<Any>> {

    override fun enqueue(callback: Callback<ComposeCatchflicksNetworkResult<Any>>) =
        data.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val result = if (response.isSuccessful) {
                    response.body()?.let {
                        return@let ComposeCatchflicksNetworkResult.Success(data = it)
                    }
                } else {
                    ComposeCatchflicksNetworkResult.Failure
                }

                callback.onResponse(
                    this@NetworkResultCall,
                    Response.success(result)
                )
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                callback.onResponse(
                    this@NetworkResultCall,
                    Response.success(ComposeCatchflicksNetworkResult.Failure)
                )
            }

        })

    override fun clone(): Call<ComposeCatchflicksNetworkResult<Any>> =
        NetworkResultCall(data.clone())

    override fun execute(): Response<ComposeCatchflicksNetworkResult<Any>> =
        throw NotImplementedError()

    override fun isExecuted(): Boolean = data.isExecuted

    override fun cancel() {
        data.cancel()
    }

    override fun isCanceled(): Boolean = data.isCanceled

    override fun request(): Request = data.request()

    override fun timeout(): Timeout = data.timeout()
}
