package com.tina.githubusers.userList


import com.tina.githubusers.data.UserRepository

class UserListPresenter(private val userListView: UserListContract.View,
                        private val repository: UserRepository): UserListContract.Presenter {


    override suspend fun loadUserList(since: Int, perPage: Int) {
        val list = repository.getUserList(since, perPage)
        userListView.showUserList(list)
    }
}