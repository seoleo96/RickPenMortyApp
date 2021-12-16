package com.example.rickpenmortyapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface IRickCommunication<T> {

    fun map(list : T)
    fun observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<T>)
}