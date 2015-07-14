package com.hpedrorodrigues.dradddle.view.fragment.shot

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hpedrorodrigues.dradddle.R
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.entity.Shot
import com.hpedrorodrigues.dradddle.network.DradddleNetwork
import com.hpedrorodrigues.dradddle.util.DradddlePicasso
import com.hpedrorodrigues.dradddle.view.activity.ShotActivity
import com.hpedrorodrigues.dradddle.view.fragment.base.BaseFragment
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.fragment_shot.collapsing_toolbar
import kotlinx.android.synthetic.fragment_shot.toolbar
import kotlinx.android.synthetic.fragment_shot.shot_image
import kotlinx.android.synthetic.fragment_shot.author_avatar
import kotlinx.android.synthetic.fragment_shot.shot_title
import kotlinx.android.synthetic.fragment_shot.author_name
import kotlinx.android.synthetic.fragment_shot.shot_description
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

public class ShotFragment(private val id: Long) : BaseFragment() {

    var dradddleNetwork: DradddleNetwork? = null
        @Inject set

    private var shot: Shot? = null

    private val loadImageCallback = object : Callback {
        override fun onSuccess() {
            val bitmap = (shot_image.getDrawable() as BitmapDrawable).getBitmap()
            Palette.from(bitmap).generate({ palette ->
                val defaultColor = getResources().getColor(R.color.pink_300)
                val color = getColor(palette, defaultColor)
                val darkColor = getDarkColor(palette, defaultColor)

                collapsing_toolbar.setContentScrimColor(color)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getActivity().getWindow().setStatusBarColor(darkColor)
                    getActivity().getWindow().setNavigationBarColor(darkColor)
                }
            })
        }
        override fun onError() {}
    }

    private fun getColor(palette: Palette, defaultColor: Int): Int {
        return palette.getVibrantColor(
                palette.getLightVibrantColor(
                        palette.getLightMutedColor(
                                palette.getMutedColor(
                                        palette.getDarkVibrantColor(
                                                palette.getDarkMutedColor(defaultColor))))))
    }

    private fun getDarkColor(palette: Palette, defaultColor: Int): Int {
        return palette.getDarkVibrantColor(
                palette.getDarkMutedColor(
                        palette.getVibrantColor(
                                palette.getMutedColor(
                                        palette.getLightMutedColor(
                                                palette.getLightVibrantColor(defaultColor))))))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shot, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configToolbar()
    }

    override fun onResume() {
        super.onResume()
        loadShot()
    }

    override fun injectMembers() {
        dradddleComponent().inject(this)
    }

    private fun configToolbar() {
        val activity = getActivity() as ShotActivity
        activity.setSupportActionBar(toolbar)
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        setTranslucentToolbar()
        collapsing_toolbar.setTitle(getString(R.string.app_name))
    }

    private fun setTranslucentToolbar() {
        (toolbar.getBackground() as ColorDrawable)
                .setAlpha(DradddleConstants.MIN_VALUE_ALPHA.toInt())
    }

    private fun loadShot() {
        if (shot == null) {
            val subscription = dradddleNetwork!!.retrieveShot(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ shot = it }, {}, { showShotInfo(shot) })
            compositeSubscription.add(subscription)
        } else {
            showShotInfo(shot)
        }
    }

    private fun showShotInfo(shot: Shot?) {
        DradddlePicasso.loadAvatar(shot!!.player!!.avatarUrl!!).into(author_avatar)
        DradddlePicasso.loadShot(shot.imageUrl!!).into(shot_image, loadImageCallback)
        collapsing_toolbar.setTitle(shot.title)
        shot_title.setText(shot.title)
        author_name.setText(shot.player!!.name)

        if (shot.description != null) {
            shot_description.setText(Html.fromHtml(shot.description))
        }
    }
}