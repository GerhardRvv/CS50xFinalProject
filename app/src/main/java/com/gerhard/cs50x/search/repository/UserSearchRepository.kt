package com.gerhard.cs50x.search.repository

import android.util.Log
import com.gerhard.cs50x.core.api.ApiTwitterDataService
import com.gerhard.cs50x.core.api.model.TwitterUser
import com.gerhard.cs50x.core.mapper.TwitterUserMapper
import com.gerhard.cs50x.core.utils.Resource
import com.gerhard.cs50x.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSearchRepository @Inject constructor(
    private val service: ApiTwitterDataService,
    private val twitterUserMapper: TwitterUserMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getTwitterUser(screenName: String): Flow<Resource<TwitterUser>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.getTwitterUser(screenName)
            Log.d("TEST", response.toString())
            if (response.user_id == 0L) {
                emit(Resource.Error(Throwable("User Not Found")))
            } else {
                val mapResponse = twitterUserMapper.map(response)
                emit(Resource.Success(mapResponse))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(Throwable("Network error: ${e.message}")))
        } catch (e: IOException) {
            emit(Resource.Error(Throwable("Network error: Check your connection")))
        } catch (e: Exception) {
            emit(Resource.Error(Throwable(e.message)))
        }
    }.flowOn(dispatcher)
}
