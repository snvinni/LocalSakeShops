package com.jetbrains.vini.data

import com.jetbrains.vini.core.Resource
import com.jetbrains.vini.core.toResource
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

/**
 * Fetches a list of SakeShopResponse via injected HttpClient,
 * decodes JSON (mock or real API_URL), and wraps success/failure
 * in a unified Resource.Result for downstream handling.
 */
interface SakeShopApi {
    suspend fun getData(): Resource.Result<List<SakeShopResponse>, Throwable>
}

class KtorSakeShopApi(private val client: HttpClient) : SakeShopApi {
    companion object {
        private const val API_URL =
            "api_url"
    }

    override suspend fun getData(): Resource.Result<List<SakeShopResponse>, Throwable> {
        return runCatching<List<SakeShopResponse>> {
            // Call API
            // client.request(API_URL)
            Json.decodeFromString(ResponseMock.json)
        }.toResource()
    }
}
