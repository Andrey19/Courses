package ru.effectivemobile.courses.base.mapper

interface Mapper<I, O> {
    fun map(type: I): O
}