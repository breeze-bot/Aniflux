package com.aniflux.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.aniflux.databinding.ActivityMainBinding
import com.aniflux.network.ApiService
import com.aniflux.repository.AniListRepository
import com.aniflux.ui.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiService = ApiService.create()
    private val repository = AniListRepository(apiService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load AniList data
        lifecycleScope.launch {
            val trending = withContext(Dispatchers.IO) { repository.getTrendingAnime() }
            val popular = withContext(Dispatchers.IO) { repository.getPopularAnime() }
            val airing = withContext(Dispatchers.IO) { repository.getAiringAnime() }

            // Banners (use trending top 5)
            val banners = trending.take(5).map {
                Banner(it.coverImage.large ?: "", it.title.english ?: it.title.romaji ?: "")
            }
            binding.bannerViewPager.adapter = BannerAdapter(banners)
            binding.bannerViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            // Categories
            val categories = listOf(
                Category("Trending", trending.map {
                    Anime(it.title.english ?: it.title.romaji ?: "", it.coverImage.large ?: "")
                }),
                Category("Popular", popular.map {
                    Anime(it.title.english ?: it.title.romaji ?: "", it.coverImage.large ?: "")
                }),
                Category("Airing", airing.map {
                    Anime(it.title.english ?: it.title.romaji ?: "", it.coverImage.large ?: "")
                })
            )
            binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.categoryRecyclerView.adapter = CategoryAdapter(categories)
        }
    }
}
