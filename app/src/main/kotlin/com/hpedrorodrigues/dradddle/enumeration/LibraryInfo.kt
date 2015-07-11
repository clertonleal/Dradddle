package com.hpedrorodrigues.dradddle.enumeration

import com.hpedrorodrigues.dradddle.R
import kotlin.platform.platformStatic

public enum class LibraryInfo(val name: String, val authorName: String, val url: String) {
    DAGGER("Dagger", "Square", "http://square.github.io/dagger/"),
    ANDROID_DESIGN("Android Design", "Google", "http://developer.android.com/tools/support-library/index.html"),
    ANDROID_PALLETE("Android Pallete", "Google", "https://developer.android.com/reference/android/support/v7/graphics/Palette.html"),
    SUPER_RECYCLER_VIEW("SuperRecyclerView", "Malinskiy", "https://github.com/Malinskiy/SuperRecyclerView"),
    RECYCLER_VIEW("RecyclerView", "Google", "https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html"),
    PICASSO("Picasso", "Square", "http://square.github.io/picasso/"),
    OK_HTTP("OkHttp", "Square", "http://square.github.io/okhttp/"),
    JAVAX_ANNOTATION("Javax Annotation", "Glassfish", "http://mvnrepository.com/artifact/org.glassfish/javax.annotation/10.0-b28"),
    RX_ANDROID("RxAndroid", "ReactiveX", "https://github.com/ReactiveX/RxAndroid"),
    CARD_VIEW("CardView", "Google", "https://developer.android.com/reference/android/support/v7/widget/CardView.html"),
    CIRCLE_IMAGE_VIEW("CircleImageView", "Hdodenhof", "https://github.com/hdodenhof/CircleImageView"),
    IMAGE_VIEW_ZOOM("ImageViewZoom", "Sephiroth", "https://github.com/sephiroth74/ImageViewZoom"),
    GIF_IMAGE_VIEW("GifImageView", "Felipe Lima", "https://github.com/felipecsl/GifImageView"),
    GSON("Gson", "Google", "https://github.com/google/gson"),
    RETROFIT("Retrofit", "Square", "http://square.github.io/retrofit/");

    companion object {

        fun find(position: Int): LibraryInfo {
            return LibraryInfo.values()[position]
        }

        fun size(): Int = LibraryInfo.values().size()
    }
}