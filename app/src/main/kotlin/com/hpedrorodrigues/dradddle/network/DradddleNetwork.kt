package com.hpedrorodrigues.dradddle.network

import com.hpedrorodrigues.dradddle.entity.Page
import com.hpedrorodrigues.dradddle.entity.Shot
import retrofit.http.GET
import retrofit.http.Path
import retrofit.http.Query
import rx.Observable

public interface DradddleNetwork {

    GET("/shots/popular?sort=recent")
    public fun retrievePopularPage(Query("page") page: Int): Observable<Page>

    GET("/shots/debuts")
    public fun retrieveDebutPage(Query("page") page: Int): Observable<Page>

    GET("/shots/{dribbleId}")
    public fun retrieveShotById(Path("dribbleId") dribbleId: Long?): Observable<Shot>
}