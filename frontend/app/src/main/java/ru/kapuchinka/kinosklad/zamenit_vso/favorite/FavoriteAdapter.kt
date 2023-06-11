package ru.kapuchinka.kinosklad.zamenit_vso.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R

class FavoriteAdapter(_favoriteList : MutableList<FavoriteModel>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var favoriteList : MutableList<FavoriteModel> = _favoriteList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.FavoriteViewHolder {
        val filmItems : View = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_layout, parent, false)
        return FavoriteAdapter.FavoriteViewHolder(filmItems)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        holder.filmName.text = favoriteList[position].filmName
        holder.filmCountry.text = favoriteList[position].filmCountry
        holder.filmYear.text = favoriteList[position].filmYear.toString()

        val bundle = Bundle()
        bundle.putString("filmName", favoriteList[position].filmName)
        bundle.putString("filmCountry", favoriteList[position].filmCountry)
        bundle.putInt("filmYear", favoriteList[position].filmYear)

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_favorites_to_filmPageFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    class FavoriteViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var filmName : TextView = itemView.findViewById(R.id.item_fav_film_name)
        var filmCountry : TextView = itemView.findViewById(R.id.item_fav_film_country)
        var filmYear : TextView = itemView.findViewById(R.id.item_fav_film_year)
    }
}