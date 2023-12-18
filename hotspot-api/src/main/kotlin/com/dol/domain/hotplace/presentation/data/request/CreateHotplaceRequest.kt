package com.dol.domain.hotplace.presentation.data.request

data class CreateHotplaceRequest(
    val name: String,
    val address : String,
    val instagramId: String,
    val imgURL: String
)