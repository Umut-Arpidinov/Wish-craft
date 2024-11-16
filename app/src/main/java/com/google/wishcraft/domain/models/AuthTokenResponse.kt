package com.google.wishcraft.domain.models

data class AuthTokenResponse(
    val access: Access? = null,
    val refresh: Refresh? = null
)

data class Access(
    val token: String? = null,
    val expiresAt: String? = null,
)

data class Refresh(
    val token: String? = null,
    val expiresAt: String? = null,
)