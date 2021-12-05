package com.tina.githubusers.data

import com.tina.githubusers.api.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepository(private val service: GitHubService, private val userDao: UserDao) {

    suspend fun getUserList(since: Long): List<User> {
        return withContext(Dispatchers.IO) {
            userDao.getUserList(since, PER_PAGE) ?: getUserListFromRemote(since)
        }
    }

    suspend fun getTargetUser(login: String): User {
        return withContext(Dispatchers.IO) {
            userDao.getUser(login) ?: getUserFromRemote(login)
        }
    }

    private suspend fun getUserFromRemote(login: String): User {
        val result = service.getUser(login)
        userDao.updateUser(result)
        return result
    }

    private suspend fun getUserListFromRemote(since: Long): List<User> {
        val result = service.getUserList(since, PER_PAGE)
        userDao.insert(result)
        return result
    }


    companion object {
        private const val PER_PAGE = 20
    }
}