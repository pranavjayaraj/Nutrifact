package com.zenmobi.nutrifact.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import io.reactivex.Single
import jp.wasabeef.glide.transformations.BlurTransformation
import java.io.File


class GlideDelegate {

    private lateinit var glide: RequestManager
    private val YOU_TUBE_URL = "https://img.youtube.com/vi/%s/hqdefault.jpg"

    private constructor() {

    }

    constructor(fragment: Fragment) {
        glide = Glide.with(fragment)
    }

    constructor(context: Context) {
        glide = Glide.with(context)
    }

    fun loadUrlWithPlaceHolder(
        imageView: AppCompatImageView,
        url: String?,
        placeholder: Int,
        isCache: Boolean = true
    ) {
        glide.load(url ?: "")
            .apply(
                RequestOptions().apply {
                    if (placeholder != -1) {
                        placeholder(placeholder)
                    }
                    if (!isCache) {
                        diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                    }
                })
            .into(imageView)
    }

    fun loadDrawable(imageView: AppCompatImageView, @DrawableRes drawable: Int) {
        glide.load(drawable)
            .into(imageView)
        //imageView.setImageResource(drawable)
    }

    fun loadCircularImageWithPlaceholder(
        imageView: AppCompatImageView,
        url: String?,
        placeholder: Int,
        isCache: Boolean = true
    ) {
        glide.load(url ?: "")
            .apply(RequestOptions().placeholder(placeholder).apply(RequestOptions.circleCropTransform())
                .apply {
                    if (placeholder != -1) {
                        placeholder(placeholder)
                    }
                    if (!isCache) {
                        diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                    }
                })
            .into(imageView)
    }

    fun loadDrawable(url: String, requestListener: RequestListener<Drawable>, size: Pair<Int, Int>?=null) {
        glide.load(url)
            .addListener(requestListener)
            .let {
                if (size != null){
                    it.centerCrop().submit(size.first, size.second)
                }else{
                    it.submit()
                }
            }

    }

    fun loadBlurDrawable(imageView: AppCompatImageView, @DrawableRes drawable: Int, placeholder: Int){
        glide.load(drawable).placeholder(placeholder).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                apply(RequestOptions().transform(BlurTransformation(12)))
            }
        }.into(imageView)
    }

    fun loadYouTubeImage(
        imageView: AppCompatImageView,
        url: String?,
        placeholder: Int,
        isCache: Boolean = true
    ) {
        val youtubeUrl = if (!url.isNullOrEmpty()) {
            String.format(YOU_TUBE_URL, url)
        } else ""
        loadUrlWithPlaceHolder(imageView, youtubeUrl, placeholder, isCache)
    }

    fun downloadImage(url: String, size: Pair<Int, Int>, isCache: Boolean = false): Bitmap {
        return glide.asBitmap()
            .load(url)
            .apply(RequestOptions().centerCrop().apply {
                if (!isCache) {
                    diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                }
            })
            .submit(size.first, size.second)
            .get()
    }

    fun loadImageFromFileLocation(
        fileLocation: String,
        requestOptions: RequestOptions,
        imageView: AppCompatImageView
    ): Single<Boolean> {
        return Single.create<Boolean> { emitter ->
            try {
                glide.load(File(fileLocation))
                    .apply(requestOptions)
                    .addListener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            emitter.onSuccess(false)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            emitter.onSuccess(true)
                            return true
                        }
                    })
                    .into(imageView)
            } catch (e: Exception) {
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }
    }
}