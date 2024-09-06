package com.example.pagingcompose.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingcompose.data.model.TestItem

class TestPagingSource: PagingSource<Int, TestItem>() {

    // Метод загрузки данных
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TestItem> {

        // Текущая страница или 1, если это первая загрузка
        val page = params.key ?: 1
        val pageSize = params.loadSize

        // Генерация тестовых данных
        val data = List(pageSize) { index ->
            TestItem(id = (page - 1) * pageSize + index, name = "Item ${(page - 1) * pageSize + index}")
        }

        // Определение следующей и предыдущей страниц
        val nextKey = if (data.isEmpty()) null else page + 1
        val prevKey = if (page == 1) null else page - 1

        return LoadResult.Page(
            data = data,
            prevKey = prevKey,
            nextKey = nextKey
        )

    }

    override fun getRefreshKey(state: PagingState<Int, TestItem>): Int? {
        return state.anchorPosition
    }

}