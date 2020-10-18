package net.qamar.daggerexample.models

import com.squareup.moshi.Json


class User {
    @field:Json(name ="id")
    var id: Int? = null

    @field:Json(name ="name")
    var name: String? = null

    @field:Json(name ="username")
    var username: String? = null

    @field:Json(name ="email")
    var email: String? = null



}