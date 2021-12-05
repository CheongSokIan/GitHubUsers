package com.tina.githubusers.data

import com.squareup.moshi.Json

data class Users(
    @Json(name = "avatar_url")
    val avatarUrl: String? = null,
    val login: String? = null,
    @Json(name = "site_admin")
    var isAdmin: Boolean = false,
    val name: String? = null,
    val location: String? = null,
    val bio: String? = null,
    val blog: String? = null
)
