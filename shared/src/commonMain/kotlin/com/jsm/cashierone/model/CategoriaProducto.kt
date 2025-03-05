package com.jsm.cashierone.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoriaProducto(
    val categoriaProductoId: Int,
    val categoriaProducto: String,
    val categoriaProductoPadreId: Int?,
)
