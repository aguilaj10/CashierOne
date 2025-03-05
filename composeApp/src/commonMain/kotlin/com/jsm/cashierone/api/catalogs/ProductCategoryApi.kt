package com.jsm.cashierone.api.catalogs

import com.jsm.cashierone.model.CategoriaProducto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ProductCategoryApi(private val httpClient: HttpClient) {
    suspend fun getProductCategories(): List<CategoriaProducto> {
        return httpClient.get("productCategories").body()
    }

    suspend fun addProductCategory(productCategory: CategoriaProducto): CategoriaProducto {
        return httpClient.post("productCategories") {
            contentType(ContentType.Application.Json)
            setBody(productCategory)
        }.body()
    }
}