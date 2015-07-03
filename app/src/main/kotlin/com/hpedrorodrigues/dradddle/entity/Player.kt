package com.hpedrorodrigues.dradddle.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Player : Serializable {

    var id: Long? = null

    var name: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("username")
    var userName: String? = null
}