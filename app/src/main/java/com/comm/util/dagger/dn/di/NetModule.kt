/**
 * ClassName:      NetModule
 * Description:    Description
 * Author:         zh
 * CreateDate:     2021/8/29 7:14 PM
 * UpdateUser:     zh
 * UpdateDate:     2021/8/29 7:14 PM
 * UpdateRemark:   Modify the description
 */
package com.comm.util.dagger.dn.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetModule{


    //第二种方式告知Dagger,可以通过调用该方法来获取到注入对象的实例
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.google.com")
            .build()
    }

    //@Provides
    //public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
    //    return new Retrofit.Builder()
    //        .client(okHttpClient)
    //        .baseUrl("http://www.google.com")
    //        .build();
    //}
    //Module已经知道怎么获取retrofit实例
    //接着同一个Module中的方法，就可以作为参数直接传入 使用
    // 比如provideApiService需要Retrofit实例，会从当前容器查找是否已经有Retrofit实例
    // 然后从当前容器直接获取
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    //@Singleton是Dagger提供的一种作用域
    // 作用域就是用来管理Component来获取对象实例的生命周期
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}