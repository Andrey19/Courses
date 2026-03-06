package ru.effectivemobile.courses.base.resources

interface StringResources {
    fun getDateFormatted(date: String): String
    fun getPriceFormatted(price: String): String
}