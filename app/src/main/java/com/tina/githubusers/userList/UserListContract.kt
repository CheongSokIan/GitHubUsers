package com.tina.githubusers.userList

import com.tina.githubusers.data.User

interface UserListContract {

    interface View {

        fun showUserList(users: List<User>)
    }

    interface Presenter {

        suspend fun loadUserList(since: Long)
    }
}