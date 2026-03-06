package ru.effectivemobile.courses.base.di

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.effectivemobile.courses.base.resources.StringResources
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class StringResourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : StringResources {

    private fun string(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    override fun getDateFormatted(date: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateAsDate = LocalDate.parse(date, inputFormatter)
        val russianLocale = Locale("ru", "RU")
        val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", russianLocale)
        return dateAsDate.format(outputFormatter)
    }

    override fun getPriceFormatted(price: String): String {
        val currencySymbol = "₽"
        return "$price $currencySymbol"
    }
}