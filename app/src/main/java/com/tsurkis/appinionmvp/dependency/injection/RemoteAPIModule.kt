package com.tsurkis.appinionmvp.dependency.injection

import com.tsurkis.appinionmvp.BuildConfig
import com.tsurkis.appinionmvp.dependency.injection.annotations.ApplicationScope
import com.tsurkis.appinionmvp.remoteapi.opinionatedquotes.OpinionatedAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RemoteAPIModule {

    @Provides
    @ApplicationScope
    fun provideRetrofitCore(): Retrofit =
            Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.OPINIONATED_API_END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides
    @ApplicationScope
    fun provideOpinionatedAPIRetrofitClient(retrofit: Retrofit): OpinionatedAPI =
            retrofit.create(OpinionatedAPI::class.java)

}