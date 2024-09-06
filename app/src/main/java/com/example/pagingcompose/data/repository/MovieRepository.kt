package com.example.pagingcompose.data.repository

import androidx.paging.PagingData
import com.example.pagingcompose.data.model.MovieResponseDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<PagingData<MovieResponseDto>>
}