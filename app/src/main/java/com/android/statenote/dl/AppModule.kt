package com.android.statenote.dl

import android.app.Application
import androidx.room.Room
import com.android.statenote.feature_note.data.data_source.NoteDatabase
import com.android.statenote.feature_note.data.repository.NoteRepositoryImp
import com.android.statenote.feature_note.domain.repository.NoteRepository
import com.android.statenote.feature_note.domain.use_case.AddNote
import com.android.statenote.feature_note.domain.use_case.DeleteNote
import com.android.statenote.feature_note.domain.use_case.GetNote
import com.android.statenote.feature_note.domain.use_case.GetNotes
import com.android.statenote.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application):NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepository {
        return NoteRepositoryImp(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}