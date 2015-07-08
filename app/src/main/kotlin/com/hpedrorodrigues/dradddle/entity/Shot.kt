package com.hpedrorodrigues.dradddle.entity

import com.google.gson.annotations.SerializedName

import java.io.Serializable
import java.util.*

public class Shot : Serializable {

    var id: Long? = null

    var title: String? = null

    var description: String? = null

    var url: String? = null

    SerializedName("views_count")
    var viewsCount: Long = 0

    SerializedName("likes_count")
    var likesCount: Long = 0

    SerializedName("comments_count")
    var commentsCount: Long = 0

    SerializedName("rebounds_count")
    var reboundsCount: Long = 0

    SerializedName("buckets_count")
    var bucketsCount: Long = 0

    SerializedName("image_url")
    var imageUrl: String? = null

    SerializedName("image_teaser_url")
    var imageTeaserUrl: String? = null

    var player: Player? = null
}