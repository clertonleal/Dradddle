package com.hpedrorodrigues.dradddle.entity

import com.google.gson.annotations.SerializedName

import java.io.Serializable

public class Shot : Serializable {

    private var id: Long? = null

    private var title: String? = null

    private var description: String? = null

    private var url: String? = null

    SerializedName("views_count")
    private var viewsCount: Long? = null

    SerializedName("image_url")
    private var imageUrl: String? = null

    SerializedName("image_teaser_url")
    private var imageTeaserUrl: String? = null

    SerializedName("image_400_url")
    private var image400Url: String? = null

    private var player: Player? = null

    public fun getId(): Long? {
        return id
    }

    public fun setId(id: Long?) {
        this.id = id
    }

    public fun getTitle(): String? {
        return title
    }

    public fun setTitle(title: String) {
        this.title = title
    }

    public fun getDescription(): String? {
        return description
    }

    public fun setDescription(description: String) {
        this.description = description
    }

    public fun getUrl(): String? {
        return url
    }

    public fun setUrl(url: String) {
        this.url = url
    }

    public fun getViewsCount(): Long? {
        return viewsCount
    }

    public fun setViewsCount(viewsCount: Long?) {
        this.viewsCount = viewsCount
    }

    public fun getImageUrl(): String? {
        return imageUrl
    }

    public fun setImageUrl(imageUrl: String) {
        this.imageUrl = imageUrl
    }

    public fun getImageTeaserUrl(): String? {
        return imageTeaserUrl
    }

    public fun setImageTeaserUrl(imageTeaserUrl: String) {
        this.imageTeaserUrl = imageTeaserUrl
    }

    public fun getImage400Url(): String? {
        return image400Url
    }

    public fun setImage400Url(image400Url: String) {
        this.image400Url = image400Url
    }

    public fun getPlayer(): Player? {
        return player
    }

    public fun setPlayer(player: Player) {
        this.player = player
    }
}