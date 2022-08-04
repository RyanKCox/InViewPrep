package com.revature.inviewprep.domain

import com.revature.inviewprep.data.NotesRepository
import io.reactivex.Single

object GetNotesUseCase {
    fun getNotes(): Single<List<NoteEntity>> = NotesRepository.getNotes()
}