package com.hrhn.presentation.util

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun <T : Any> fakePagingItems(
    data: List<T>,
): LazyPagingItems<T> {
    val pagingData = PagingData.from(data)
    val fakeDataFlow = MutableStateFlow(pagingData)
    val pagingItems = fakeDataFlow.collectAsLazyPagingItems()
    return pagingItems
}
