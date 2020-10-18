package net.qamar.daggerexample.data.models

import com.squareup.moshi.Json


class Post {
    @field:Json(name ="userId")
    var userId: Int? = null

    @field:Json(name ="id")
    var id: Int? = null

    @field:Json(name ="title")
    var title: String? = null

    @field:Json(name ="body")
    var body: String? = null



}