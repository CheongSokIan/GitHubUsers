package com.tina.githubusers.data

import com.tina.githubusers.api.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class UserRepository(private val service: GitHubService, private val userDao: UserDao) {

    suspend fun getUserList(since: Int, perPage: Int): List<User> {
        return withContext(Dispatchers.IO) {
            fetchUserData(since, perPage)
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
        coroutineScope {
            val result = service.getUser(login)
            userDao.updateUser(result)
        }
    }

    private suspend fun fetchUserData(since: Int, perPage: Int) {
        coroutineScope {
            val result = service.getUserList(since, perPage)
            userDao.insert(result)
        }
    }
}