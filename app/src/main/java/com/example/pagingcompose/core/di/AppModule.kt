package com.example.pagingcompose.core.di

import com.example.pagingcompose.core.network.MovieTestApi
import com.example.pagingcompose.data.repository.MovieRepository
import com.example.pagingcompose.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMovieRepository(
        api: MovieTestApi
    ): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}