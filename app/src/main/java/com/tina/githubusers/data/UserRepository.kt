package com.tina.githubusers.data

import com.tina.githubusers.api.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepository(private val service: GitHubService, private val userDao: UserDao) {

    suspend fun getUserList(since: Long): List<User> {
        return withContext(Dispatchers.IO) {
            fetchUserData(since)
            userDao.getUserList()
        }
    }

    suspend fun getTargetUser(login: String): User {
        return withContext(Dispatchers.IO) {
            updateUser(login)
            userDao.getUser(login)
        }
    }

    private suspend fun updateUser(login: String) {
        val result = service.getUser(login)
        userDao.updateUser(result)
    }

    private suspend fun fetchUserData(since: Long) {
        val result = service.getUserList(since, PER_PAGE)
        userDao.insert(result)
    }


    companion object {
        private const val PER_PAGE = 20
    }
}