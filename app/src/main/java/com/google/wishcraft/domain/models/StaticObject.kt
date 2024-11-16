package com.google.wishcraft.domain.models

import com.google.gson.annotations.SerializedName

data class StaticObject (
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("createdAt")
    var createdAt: String? = null,
    @SerializedName("updatedAt")
    var updatedAt: String? = null,
    @SerializedName("deletedAt")
    var deletedAt: String? = null
)
