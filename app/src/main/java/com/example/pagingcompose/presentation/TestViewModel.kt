package com.example.pagingcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingcompose.data.model.MovieResponseDto
import com.example.pagingcompose.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val testRepository: MovieRepository
) : ViewModel() {

    private val _testDataState: MutableStateFlow<PagingData<MovieResponseDto>> =
        MutableStateFlow(value = PagingData.empty())
    val testDataState: MutableStateFlow<PagingData<MovieResponseDto>> get() = _testDataState

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            testRepository.getMovies()
                .cachedIn(viewModelScope)
                .collect {
                    _testDataState.value = it
                }
        }
    }

//    private fun loadPagingData() {
//        val pagingData = Pager(
//            config = PagingConfig(
//                pageSize = 10,
//                enablePlaceholders = false,
//                prefetchDistance = 5
//            ),
//            pagingSourceFactory = {
//                TestPagingSource()
//            }
//        ).flow
//        viewModelScope.launch {
//            pagingData.collect {
//                _testDataState.value = it
//            }
//        }
//    }

}