package ru.effectivemobile.courses.course.impl.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import ru.effectivemobile.courses.course.api.source.CourseLocalDataSource
import ru.effectivemobile.courses.course.impl.mapper.CoursesToCoursesEntityMapper
import ru.effectivemobile.courses.database.dao.CoursesDao
import ru.effectivemobile.courses.domain.course.Courses
import ru.effectivemobile.courses.transport.transform
import javax.inject.Inject

internal class CourseLocalDataSourceImpl @Inject constructor(
    private val courseDao: CoursesDao,
    private val mapper: CoursesToCoursesEntityMapper
) : CourseLocalDataSource {

    override suspend fun clearCourses() {
        courseDao.deleteAll()
    }

    override suspend fun saveCourses(courses: List<Courses>) {
        courseDao.insert(courses.map { mapper.map(it) })
    }

    override suspend fun changeCourseIsLiked(id: Int, hasLike: Boolean) {
        courseDao.insert(id, hasLike)
    }

    override fun observeCourses(): Flow<List<Courses>> =
        courseDao.getAllOnFlow().map { it.transform() }


    override fun observeLikedCourses(): Flow<List<Courses>> =
        courseDao.getLikedOnFlow().map { it.transform() }
}