package com.agilefreaks.sharedappsample.features.explore.repo_list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.agilefreaks.sharedappsample.BaseViewModel
import com.agilefreaks.sharedappsample.features.explore_shared.repo_list.Repo
import com.agilefreaks.sharedappsample.features.explore_shared.repo_list.ViewerRepository
import kotlinx.coroutines.flow.Flow

class ViewModel(private val viewerRepository: ViewerRepository) :
    BaseViewModel<Contract.Event, Contract.State, Contract.Effect>() {
    override fun setInitialState(): Contract.State = Contract.State(repos = fetchRepos())

    override fun handleEvents(event: Contract.Event) {
        when (event) {
            is Contract.Event.SelectRepo -> navigateToDetails(event.repoOwner, event.repoName)
        }
    }

    private fun fetchRepos(): Flow<PagingData<Repo>> =
        Pager(PagingConfig(pageSize = 20)) {
            RepoPagingSource(viewerRepository)
        }.flow.cachedIn(viewModelScope)

    private fun navigateToDetails(repoOwner: String, repoName: String) {
        setEffect {
            Contract.Effect.Navigation.ToDetails(repoOwner, repoName)
        }
    }
}