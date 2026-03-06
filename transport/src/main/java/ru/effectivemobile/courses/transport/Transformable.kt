package ru.effectivemobile.courses.transport

interface Transformable<T> {
    fun transform(): T
}

fun <T> List<Transformable<T>>.transform(): List<T> =
    map { item -> item.transform() }