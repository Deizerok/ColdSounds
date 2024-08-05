package com.example.coldsounds.di

import com.example.coldsounds.main.Navigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    abstract fun bindsNavigationNavigate(navigation: Navigation.Base): Navigation.Navigate

    @Binds
    abstract fun bindsNavigationMutable(navigation: Navigation.Base): Navigation.Mutable

}