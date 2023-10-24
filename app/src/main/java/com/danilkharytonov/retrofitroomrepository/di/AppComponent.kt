package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.presentation.detail_user.UserDetailFragment
import com.danilkharytonov.retrofitroomrepository.presentation.user_list.UserListFragment
import dagger.Component

@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        DomainModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun injectUserListFragment(userListFragment: UserListFragment)
    fun injectUserDetailFragment(userDetailFragment: UserDetailFragment)
}