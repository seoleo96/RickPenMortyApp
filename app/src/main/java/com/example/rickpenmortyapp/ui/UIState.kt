package com.example.rickpenmortyapp.ui

import com.example.rickpenmortyapp.domain.model.CharacterDomainModel

sealed class UIState {
    data class Success(val data: List<CharacterDomainModel>) : UIState()
    data class Error(val id: Int) : UIState()
    object Progress : UIState()
}
