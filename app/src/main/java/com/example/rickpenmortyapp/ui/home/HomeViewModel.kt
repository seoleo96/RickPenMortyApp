package com.example.rickpenmortyapp.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickpenmortyapp.R
import com.example.rickpenmortyapp.domain.ResultData
import com.example.rickpenmortyapp.domain.model.CharacterDomainModel
import com.example.rickpenmortyapp.domain.usecase.IFetchAllDataUseCase
import com.example.rickpenmortyapp.ui.IRickCommunication
import com.example.rickpenmortyapp.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

class HomeViewModel(
    private val fetchAllDataUseCase: IFetchAllDataUseCase,
    private val rickCommunication: IRickCommunication<List<UIState>>,
) : ViewModel() {


    fun fetchData() {
        rickCommunication.map(listOf(UIState.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val data: ResultData = fetchAllDataUseCase.fetchAllData()
            withContext(Dispatchers.Main) {
                when (data) {
                    is ResultData.Error -> {
                        val id = when (data.error) {
                            is UnknownHostException -> R.string.NO_CONNECTION
                            is HttpException -> R.string.SERVICE_UNAVAILABLE
                            else -> R.string.GENERIC_ERROR
                        }
                        rickCommunication.map(listOf(UIState.Error(id)))
                    }
                    is ResultData.Success -> {
                        data.data.collect {
                            rickCommunication.map(listOf(UIState.Success(it as List<CharacterDomainModel>)))
                        }
                    }
                }
            }
        }
    }

    fun observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<List<UIState>>) {
        rickCommunication.observe(viewLifecycleOwner, observer)
    }
}