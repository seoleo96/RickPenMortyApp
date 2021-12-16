package com.example.rickpenmortyapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class RickCommunication : IRickCommunication<List<UIState>> {

    private val liveData = MutableLiveData<List<UIState>>()
    override fun map(list: List<UIState>) {
        liveData.value = list
    }

    override fun observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<List<UIState>>) {
        liveData.observe(viewLifecycleOwner, observer)
    }
}