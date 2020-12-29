package com.example.loader.ui.main.list

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("setText")
fun setText(view: TextView, list : Int? ) {
    view.text = "$list курсов";
}
