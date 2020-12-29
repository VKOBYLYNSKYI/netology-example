package com.example.loader.models

import com.example.loader.models.bodies.CourseInfo
import com.google.gson.annotations.SerializedName

data class CourseList(
        @SerializedName("title")
        val title : String,
        @SerializedName("items")
        val items : MutableList<CourseInfo>?,
        val courseSize : Int?

);
