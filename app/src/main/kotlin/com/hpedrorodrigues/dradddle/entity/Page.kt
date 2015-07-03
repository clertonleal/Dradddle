package com.hpedrorodrigues.dradddle.entity

import com.google.gson.annotations.SerializedName

import java.io.Serializable

public class Page : Serializable {

    var page: String? = null

    SerializedName("per_page")
    var perPage: Int? = null

    var pages: Int? = null

    var total: Long? = null

    var shots: List<Shot>? = null
}