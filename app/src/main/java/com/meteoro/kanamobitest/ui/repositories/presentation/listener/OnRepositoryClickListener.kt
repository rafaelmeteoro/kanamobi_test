package com.meteoro.kanamobitest.ui.repositories.presentation.listener

import com.meteoro.kanamobitest.ui.repositories.presentation.data.RepositoryClickData

interface OnRepositoryClickListener {
    fun onClick(data: RepositoryClickData)
}