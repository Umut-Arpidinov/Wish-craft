package com.google.wishcraft.domain.models

import com.google.gson.annotations.SerializedName

data class WishRequest(
    @SerializedName("link")
    var link: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("giftName")
    var giftName: String? = null,
    @SerializedName("price")
    var price: Int? = null,
    @SerializedName("desireRate")
    var desireRate: Int? = null,
    @SerializedName("staticObjectId")
    var staticObjectId: Int? = null
)