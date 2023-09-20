package com.example.assignmentproject

import com.example.assignmentproject.notes.NotesRepository
import com.example.assignmentproject.notes.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NotesRepoImpl*/
}
