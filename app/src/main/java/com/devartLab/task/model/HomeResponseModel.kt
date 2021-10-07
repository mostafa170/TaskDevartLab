package com.devartLab.task.model

data class HomeResponseModel(
    val address_form: String,
    val address_to: String,
    val cost_order: String,
    val customer_name: String,
    val customer_rate: Float,
    val date: String,
    val detail_order: String,
    val lat_form: String,
    val lat_to: String,
    val lng_form: String,
    val lng_to: String,
    val order: String,
    val phone: String,
    val profile_img: String
)