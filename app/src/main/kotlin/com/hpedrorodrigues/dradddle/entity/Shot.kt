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
    var viewsCount: Long? = null

    SerializedName("likes_count")
    var likesCount: Long? = null

    SerializedName("comments_count")
    var commentsCount: Long? = null

    SerializedName("rebounds_count")
    var reboundsCount: Long? = null

    SerializedName("buckets_count")
    var bucketsCount: Long? = null

    SerializedName("image_url")
    var imageUrl: String? = null

    SerializedName("image_teaser_url")
    var imageTeaserUrl: String? = null

    SerializedName("created_at")
    var postingDate: Date? = null

    var player: Player? = null
}