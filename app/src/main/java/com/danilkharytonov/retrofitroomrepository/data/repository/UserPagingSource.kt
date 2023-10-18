package com.danilkharytonov.retrofitroomrepository.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteImageFilesUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromApiUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.InsertUsersToDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.SaveUserImagesInStorageUseCase

class UserPagingSource(
    private val getAllUsersFromApiUseCase: GetAllUsersFromApiUseCase,
    private val saveUserImagesInStorageUseCase: SaveUserImagesInStorageUseCase,
    private val insertUsersToDBUseCase: InsertUsersToDBUseCase,
    private val getAllUsersFromDBUseCase: GetAllUsersFromDBUseCase,
    private val deleteUsersFromDBUseCase: DeleteUsersFromDBUseCase,
    private val deleteImageFilesUseCase: DeleteImageFilesUseCase
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition.let { anchorPosition ->
            anchorPosition?.let { state.closestPageToPosition(it)?.nextKey }
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val users = getAllUsersFromApiUseCase.execute(params.loadSize, page)
            if (page == 1) {
                deleteImageFilesUseCase.execute()
                deleteUsersFromDBUseCase.execute()
            }
            saveUserImagesInStorageUseCase.execute(users)
            insertUsersToDBUseCase.execute(users)
            LoadResult.Page(
                data = users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            val page = params.key ?: 0
            val users = getAllUsersFromDBUseCase.execute()
            LoadResult.Page(
                data = users,
                prevKey = null,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        }
    }
}