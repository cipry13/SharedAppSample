package com.agilefreaks.sharedappsample.features.explore_shared

import com.agilefreaks.sharedappsample.di.dataModule
import com.agilefreaks.sharedappsample.features.explore_shared.repo_list.ViewerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

fun loadModules() {
    loadKoinModules(modules())
}

fun initKoin(properties: Map<String, String>) {
    startKoin {
        modules(dataModule)
        properties(properties)
    }
    loadModules()
}

class RepositoryHelper() : KoinComponent {
    private val viewerRepository: ViewerRepository by inject()

    fun viewerRepository(): ViewerRepository = viewerRepository
}