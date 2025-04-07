package com.example.retrofit.framework_driver.di

import com.example.retrofit.domain.repository.UserRepository
import com.example.retrofit.domain.use_cases.CreateUser
import com.example.retrofit.domain.use_cases.DeleteUser
import com.example.retrofit.domain.use_cases.GetUsers
import com.example.retrofit.domain.use_cases.UpdateUser
import com.example.retrofit.domain.use_cases.UserUseCases
import com.example.retrofit.framework_driver.repository.UserRepositoryImpl
import com.example.retrofit.framework_driver.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {

        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository = UserRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideUserUseCases(userRepository: UserRepository): UserUseCases = UserUseCases(
        getUsers = GetUsers(userRepository),
        createUser = CreateUser(userRepository),
        updateUser = UpdateUser(userRepository),
        deleteUser = DeleteUser(userRepository),
    )
}

const val BASE_URL = "http://192.168.0.102:3000/"

