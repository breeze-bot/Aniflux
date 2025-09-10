package com.aniflux.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aniflux.R
import com.bumptech.glide.Glide

data class Anime(val title: String, val imageUrl: String)

class AnimeAdapter(private val animeList: List<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val animePoster: ImageView = view.findViewById(R.id.animePoster)
        val animeTitle: TextView = view.findViewById(R.id.animeTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime_row, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        Glide.with(holder.itemView.context).load(anime.imageUrl).into(holder.animePoster)
        holder.animeTitle.text = anime.title
    }

    override fun getItemCount(): Int = animeList.size
    }
