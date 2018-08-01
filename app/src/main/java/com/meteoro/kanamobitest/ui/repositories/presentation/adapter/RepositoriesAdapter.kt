package com.meteoro.kanamobitest.ui.repositories.presentation.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.meteoro.kanamobitest.core.adapter.AdapterConstants
import com.meteoro.kanamobitest.core.adapter.ViewType
import com.meteoro.kanamobitest.core.adapter.ViewTypeDelegateAdapter
import com.meteoro.kanamobitest.ui.repositories.domain.model.RepositoryItem
import com.meteoro.kanamobitest.ui.repositories.presentation.listener.OnRepositoryClickListener

class RepositoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.ITEM, RepositoriesDelegateAdapter())
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        items = ArrayList()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    fun addRepositories(repositories: List<RepositoryItem>) {
        // first remote loading and notify
        val initPosition = items.size - 1
        if (items.isNotEmpty() && items[initPosition].getViewType() == AdapterConstants.LOADING) {
            items.removeAt(initPosition)
            notifyItemRemoved(initPosition)
        }

        // insert repositories
        items.addAll(repositories)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun removeLoadingMore() {
        val initPosition = items.size - 1;
        if (items.isNotEmpty() && items[initPosition].getViewType() == AdapterConstants.LOADING) {
            items.removeAt(initPosition)
            notifyItemRemoved(initPosition)
        }
    }

    fun setListener(listener: OnRepositoryClickListener) {
        (delegateAdapters.get(AdapterConstants.ITEM) as RepositoriesDelegateAdapter).setListener(listener)
    }
}