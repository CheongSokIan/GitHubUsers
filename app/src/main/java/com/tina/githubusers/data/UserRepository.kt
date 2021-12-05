package com.tina.githubusers.data

import com.tina.githubusers.api.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val service: GitHubService, private val userDao: UserDao) {

    suspend fun getUserList(since: Int, perPage: Int): List<User> {
        return withContext(Dispatchers.IO) {
            fetchUserData(since, perPage)
            userDao.getUserList()
        }
    }

    suspend fun getTargetUser(userId: String) =
        withContext(Dispatchers.IO) { userDao.getUser(userId) }

    private suspend fun fetchUserData(since: Int, perPage: Int) {
        withContext(Dispatchers.IO) {
            val result = service.getUserList(since, perPage)
            userDao.insert(result)
        }
    }
}