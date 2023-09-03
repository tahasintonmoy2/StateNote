package com.android.statenote.feature_note.presentation.notes

import com.android.statenote.feature_note.domain.model.Note
import com.android.statenote.feature_note.domain.utils.NoteOrder
import com.android.statenote.feature_note.domain.utils.OrderType

data class NoteState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
