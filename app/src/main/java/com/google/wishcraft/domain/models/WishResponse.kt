package com.google.wishcraft.domain.models

import com.google.gson.annotations.SerializedName

data class WishResponse(
    @SerializedName("wishes")
    var wishes: ArrayList<Wish> = arrayListOf(),
    @SerializedName("meta")
    var meta: Meta? = Meta()
)

data class Wish(
    @SerializedName("id")
    var id: Int? = null,
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
    @SerializedName("userId")
    var userId: Int? = null,
    @SerializedName("staticObjectId")
    var staticObjectId: Int? = null,
    @SerializedName("createdAt")
    var createdAt: String? = null,
    @SerializedName("updatedAt")
    var updatedAt: String? = null,
    @SerializedName("user")
    var user: User? = null,
    @SerializedName("deletedAt")
    var deletedAt: String? = null,
    @SerializedName("staticObject")
    var staticObject: StaticObject? = null
)

data class Meta (
    @SerializedName("total"   )
    var total   : Int? = null,
    @SerializedName("page"    )
    var page    : Int? = null,
    @SerializedName("perPage" )
    var perPage : Int? = null
)

data class UserResponse(
    var user : User?= null
)

data class UserListResponse(
    val users: List<User>? = null
)

data class User (
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("birthDate")
    var birthDate: String? = null,
    @SerializedName("createdAt")
    var createdAt: String? = null,
    @SerializedName("updatedAt")
    var updatedAt: String? = null,
    @SerializedName("deletedAt")
    var deletedAt: String? = null,
    @SerializedName("staticObject")
    var staticObject: StaticObject? = null,
    @SerializedName("wishes")
    var wishes: ArrayList<Wish> = arrayListOf(),
    var followers : ArrayList<User>,
    var following : ArrayList<User>,
    var followed: Boolean = false,
    val gender: String? = null,
    val birthday: String? = null,
    val location: String? = null
)
