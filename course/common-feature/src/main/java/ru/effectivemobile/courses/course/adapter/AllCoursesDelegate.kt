package ru.effectivemobile.courses.course.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.effectivemobile.courses.course.databinding.CardCourseBinding
import ru.effectivemobile.courses.uikit.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

internal fun createCoursesDelegate(
    onCoursesFavouriteButtonItemClick: (item: CoursesItem) -> Unit,
) = adapterDelegateViewBinding<CoursesItem,
        CoursesItem, CardCourseBinding>(
    { layoutInflater, root ->
        CardCourseBinding.inflate(layoutInflater, root, false)
    }
) {
    bind {
        with(binding) {

            val favouriteDrawable =
                getDrawable(
                    if (item.hasLike) {
                        R.drawable.favourite_flag_marked
                    } else {
                        R.drawable.favorite_flag_unmarked
                    }
                )
            favouriteFlag.setImageDrawable(favouriteDrawable)

            favouriteFlag.setOnClickListener {
                onCoursesFavouriteButtonItemClick(item.copy())
            }

            fun convertStringToLocalDateTime(dateString: String): String {
                val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val date = LocalDate.parse(dateString, inputFormatter)
                val russianLocale = Locale("ru", "RU")
                val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", russianLocale)
                val formattedDate = date.format(outputFormatter)
                return formattedDate
            }

            val options = RequestOptions()

            Glide.with(image)
                .load(R.drawable.course_image)
                .apply(options)
                .placeholder(R.drawable.ic_loading_100dp)
                .error(R.drawable.ic_error_100dp)
                .timeout(10_000)
                .into(image)

            courseName.text = item.title
            courseDescription.text = item.text
            rating.text = item.rate
            datePublished.text = item.startDate
            price.text = item.price
        }
    }
}
