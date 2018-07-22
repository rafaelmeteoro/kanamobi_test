package com.meteoro.kanamobitest.core.data

class RepositoryDataResponse(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<RepositoryInfo>
)

class RepositoryInfo(
        val name: String,
        val description: String,
        val stargazers_count: Int,
        val forks_count: Int,
        val owner: User
)

class User(
        val login: String,
        val avatar_url: String
)