package com.tina.githubusers.userDetail

import com.tina.githubusers.data.UserRepository

class UserDetailPresenter(
    private val userDetailView: UserDetailContract.View,
    private val repository: UserRepository
) : UserDetailContract.Presenter {


    override suspend fun loadDetail(login: String) {
        val user = repository.getTargetUser(login)
        userDetailView.showDetail(user)
    }
}