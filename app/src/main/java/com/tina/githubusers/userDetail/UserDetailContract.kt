package com.tina.githubusers.userDetail

import com.tina.githubusers.data.User

interface UserDetailContract {

    interface View {

        fun showDetail(user: User)
    }

    interface Presenter {

        suspend fun loadDetail(login: String)
    }
}