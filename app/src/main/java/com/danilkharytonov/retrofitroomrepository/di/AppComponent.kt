package com.danilkharytonov.retrofitroomrepository.di

import dagger.Component

@Component(modules = [
    ApiModule::class,
    AppModule::class,
    DatabaseModule::class,
    DomainModule::class,
    RepositoryModule::class
])
interface AppComponent {
}