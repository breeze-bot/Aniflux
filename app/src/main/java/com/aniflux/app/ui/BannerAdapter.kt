package com.aniflux.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aniflux.R
import com.bumptech.glide.Glide

data class Banner(val imageUrl: String, val title: String)

class BannerAdapter(private val banners: List<Banner>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bannerImage: ImageView = view.findViewById(R.id.bannerImage)
        val bannerTitle: TextView = view.findViewById(R.id.bannerTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = banners[position]
        Glide.with(holder.itemView.context).load(banner.imageUrl).into(holder.bannerImage)
        holder.bannerTitle.text = banner.title
    }

    override fun getItemCount(): Int = banners.size
    }
