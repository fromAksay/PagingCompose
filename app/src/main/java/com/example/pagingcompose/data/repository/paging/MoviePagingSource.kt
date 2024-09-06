package com.example.pagingcompose.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingcompose.core.app.Constants
import com.example.pagingcompose.core.network.MovieTestApi
import com.example.pagingcompose.data.model.MovieResponseDto
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val apiService: MovieTestApi,
) : PagingSource<Int, MovieResponseDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseDto> {
        return try {
            val currentPage = params.key ?: 1
            val movies = apiService.getMovies(
                apiKey = Constants.MOVIE_API_KEY,
                pageNumber = currentPage
            )

            val newCount = movies.results!!.size
            val total = movies.totalPages
            val itemsBefore = currentPage * Constants.MAX_PAGE_SIZE
            val itemsAfter = total!! - (itemsBefore + newCount)

            LoadResult.Page(
                data = movies.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (itemsAfter == 0) null else currentPage + 1,
                itemsAfter = itemsAfter
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponseDto>): Int? {
        return state.anchorPosition
    }

}