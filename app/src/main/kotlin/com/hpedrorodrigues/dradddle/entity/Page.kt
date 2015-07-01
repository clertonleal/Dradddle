package com.hpedrorodrigues.dradddle.entity

import com.google.gson.annotations.SerializedName

import java.io.Serializable

public class Page : Serializable {

    private var page: String? = null

    SerializedName("per_page")
    private var perPage: Int? = null

    private var pages: Int? = null

    private var total: Long? = null

    private var shots: List<Shot>? = null

    public fun getPage(): String? {
        return page
    }

    public fun setPage(page: String?) {
        this.page = page
    }

    public fun getPerPage(): Int? {
        return perPage
    }

    public fun setPerPage(perPage: Int?) {
        this.perPage = perPage
    }

    public fun getPages(): Int? {
        return pages
    }

    public fun setPages(pages: Int?) {
        this.pages = pages
    }

    public fun getTotal(): Long? {
        return total
    }

    public fun setTotal(total: Long?) {
        this.total = total
    }

    public fun getShots(): List<Shot>? {
        return shots
    }

    public fun setShots(shots: List<Shot>?) {
        this.shots = shots
    }
}