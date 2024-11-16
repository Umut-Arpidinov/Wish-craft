package com.google.wishcraft.domain.models

import com.google.gson.annotations.SerializedName

data class StaticObjectResponse(
    @SerializedName("icon") var
    icon: Icon? = Icon()
)


data class Icon(
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("deletedAt")
    var deletedAt: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("createdAt")
    var createdAt: String? = null,
    @SerializedName("updatedAt")
    var updatedAt: String? = null
)