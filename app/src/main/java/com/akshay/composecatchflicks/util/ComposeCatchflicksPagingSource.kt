package com.akshay.composecatchflicks.util

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * Created by anandwana001 on
 * 03, April, 2023
 **/
class ComposeCatchflicksPagingSource<T : Any>(
    private val block: suspend (Int) -> Pair<List<T>, Int>
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 1
        return try {
            val result = block(page)
            LoadResult.Page(
                data = result.first,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == result.second) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

fun <V : Any> createPager(
    pageSize: Int = 30,
    block: suspend (Int) -> Pair<List<V>, Int>
): Pager<Int, V> = Pager(
    config = PagingConfig(pageSize = pageSize),
    pagingSourceFactory = { ComposeCatchflicksPagingSource(block) }
)