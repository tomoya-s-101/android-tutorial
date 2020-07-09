package com.axiaworks.tutorial.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("gender")
fun TextView.gender(gender: Int) {
    text = if (gender == 0) "男性" else "女性"
}

@BindingAdapter("age")
fun TextView.age(age: Int) {
    text = age.toString()
}