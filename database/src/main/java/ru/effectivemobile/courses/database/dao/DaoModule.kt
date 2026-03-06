package ru.effectivemobile.courses.database.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.effectivemobile.courses.database.AppDb

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideCoursesDao(db: AppDb): CoursesDao = db.coursesDao()
}
