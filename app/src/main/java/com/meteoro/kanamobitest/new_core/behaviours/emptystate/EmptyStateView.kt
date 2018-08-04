package com.meteoro.kanamobitest.new_core.behaviours.emptystate

import io.reactivex.functions.Action

interface EmptyStateView {
    fun showEmptyState(): Action
    fun hideEmptyState(): Action
}