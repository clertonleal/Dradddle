package com.hpedrorodrigues.dradddle.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Player : Serializable {

    private var id: Long? = null

    private var name: String? = null

    @SerializedName("avatar_url")
    private var avatarUrl: String? = null

    @SerializedName("username")
    private var userName: String? = null

    public fun getId(): Long? {
        return id
    }

    public fun setId(id: Long?) {
        this.id = id
    }

    public fun getName(): String? {
        return name
    }

    public fun setName(name: String?) {
        this.name = name
    }

    public fun getAvatarUrl(): String? {
        return avatarUrl
    }

    public fun setAvatarUrl(avatarUrl: String?) {
        this.avatarUrl = avatarUrl
    }

    public fun getUserName(): String? {
        return userName
    }

    public fun setUSerName(userName: String?) {
        this.userName = userName
    }
}