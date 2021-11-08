package com.example.githubrepo.loadImages

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubrepo.R

class GlideImageLoader : ImageLoaderGlide<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background))
            .apply(RequestOptions.errorOf(R.drawable.ic_baseline_error_24))
            .into(container)
    }
}