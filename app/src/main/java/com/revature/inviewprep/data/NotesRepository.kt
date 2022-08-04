package com.revature.inviewprep.data

import com.revature.inviewprep.domain.NoteEntity
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit

object NotesRepository {

    fun getNotes(): Single<List<NoteEntity>> = Single.just(generateNotes()).delay(2,TimeUnit.SECONDS)

    private fun generateNotes() = (1..(2..50).random()).map { NoteEntity(it,"note $it") }

    private fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start
}