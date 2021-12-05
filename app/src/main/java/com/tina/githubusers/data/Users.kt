package com.tina.githubusers.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "user_table")
data class Users(
    @PrimaryKey
    val id: Long = 0,
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
