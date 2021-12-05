package com.tina.githubusers

import android.content.Context
import com.tina.githubusers.api.GitHubService
import com.tina.githubusers.data.AppDatabase
import com.tina.githubusers.data.UserRepository

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val service = GitHubService.getInstance()
        val database = AppDatabase.getInstance(context).getUserDao()

        return UserRepository(service, database)
    }
}


