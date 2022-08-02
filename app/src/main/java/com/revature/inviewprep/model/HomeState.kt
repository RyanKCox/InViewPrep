package com.revature.inviewprep.model

import androidx.constraintlayout.motion.utils.ViewState

sealed class HomeState: ViewState() {
    object Loading:HomeState()
    data class ResultAll(val data:List<String>):HomeState()
    data class ResultSearch(val data:List<String>):HomeState()
//    data class Exception(val callErrors:CallErrors):HomeState()

}