package ru.effectivemobile.courses.course.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

fun createCoursesAdapter(
    onCoursesFavouriteButtonItemClick: (item: CoursesItem) -> Unit,
): AsyncListDifferDelegationAdapter<CoursesItem> =
    AsyncListDifferDelegationAdapter(
        object : DiffUtil.ItemCallback<CoursesItem>() {
            override fun areItemsTheSame(oldItem: CoursesItem, newItem: CoursesItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CoursesItem, newItem: CoursesItem): Boolean {
                return oldItem == newItem
            }
        },
        createCoursesDelegate(onCoursesFavouriteButtonItemClick)
    )
