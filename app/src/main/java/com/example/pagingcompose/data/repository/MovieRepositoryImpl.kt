package com.example.pagingcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagingcompose.core.app.Constants
import com.example.pagingcompose.core.network.MovieTestApi
import com.example.pagingcompose.data.model.MovieResponseDto
import com.example.pagingcompose.data.repository.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieTestApi
): MovieRepository{

    override suspend fun getMovies(): Flow<PagingData<MovieResponseDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.MAX_PAGE_SIZE,
                prefetchDistance = 2,
                enablePlaceholders = true,
                initialLoadSize = Constants.INITIAL_PAGE_SIZE
//                maxSize = 100,
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiService = movieApi)
            }
        ).flow
    }
}