package ru.effectivemobile.courses.course.adapter

data class CoursesItem(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val publishDate: String,
    val hasLike: Boolean,
)