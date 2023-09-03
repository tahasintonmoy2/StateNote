package com.android.statenote.feature_note.domain.use_case

import androidx.compose.ui.text.toLowerCase
import com.android.statenote.feature_note.domain.model.Note
import com.android.statenote.feature_note.domain.repository.NoteRepository
import com.android.statenote.feature_note.domain.utils.NoteOrder
import com.android.statenote.feature_note.domain.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType){
                is OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                    }  
                }
            }
        }
    }
}