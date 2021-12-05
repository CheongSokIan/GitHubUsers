package com.tina.githubusers.data


import com.tina.githubusers.api.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepository(private val service: GitHubService, private val userDao: UserDao) {

    private val cachedUsers = mutableListOf<User>()

    suspend fun getUserList(since: Long): List<User> {
        return withContext(Dispatchers.IO) {
            val local = userDao.getUserList(since, PER_PAGE)
            if (local.size != PER_PAGE) {
                val remote = getUserListFromRemote(since)
                cachedUsers.addAll(remote)
            } else {
                cachedUsers.addAll(local)
            }
            cachedUsers
        }
    }

    suspend fun getTargetUser(login: String): User {
        return withContext(Dispatchers.IO) {
            val user = userDao.getUser(login)
            if (user.isDetailSynced) {
                user
            } else {
                getUserFromRemote(login)
            }
        }
    }

    private suspend fun getUserFromRemote(login: String): User {
        val result = service.getUser(login)
        result.isDetailSynced = true
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