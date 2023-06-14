package ru.kapuchinka.kinosklad.adapter.favorite

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yandex.metrica.YandexMetrica
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.favorite.FavoriteFilm
import ru.kapuchinka.kinosklad.utils.db.YandexMetrica.YandexEvents
import ru.kapuchinka.kinosklad.viewmodel.favorite.FavoriteFilmViewModel

class FavoriteAdapter(private val itemClickListener: OnItemClickListener,
                      private val favoriteFilmViewModel: FavoriteFilmViewModel,
                      private val context: Context) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    var favoriteList = emptyList<FavoriteFilm>()

    interface OnItemClickListener {
        fun onItemClick(favoriteFilm: FavoriteFilm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val filmItems : View = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_layout, parent, false)
        return FavoriteViewHolder(filmItems)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteFilm = favoriteList[position]
        holder.filmName.text = favoriteFilm.filmName
        holder.filmCountry.text = favoriteFilm.filmCountry
        holder.filmYear.text = favoriteFilm.filmYear.toString()

        val bundle = Bundle()
        bundle.putString("filmName", favoriteFilm.filmName)
        bundle.putString("filmCountry", favoriteFilm.filmCountry)
        bundle.putInt("filmYear", favoriteFilm.filmYear)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(favoriteFilm)
        }

        holder.deleteButton.setOnClickListener {
            YandexMetrica.reportEvent(YandexEvents.REMOVE_FROM_FAVORITE)
            favoriteFilmViewModel.deleteFavoriteFilm(context, favoriteFilm.favFilmId)
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    class FavoriteViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var filmName : TextView = itemView.findViewById(R.id.item_fav_film_name)
        var filmCountry : TextView = itemView.findViewById(R.id.item_fav_film_country)
        var filmYear : TextView = itemView.findViewById(R.id.item_fav_film_year)
        var deleteButton: ImageButton = itemView.findViewById(R.id.item_delete_fav_button)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(filmsList: List<FavoriteFilm>) {
        favoriteList = filmsList
        notifyDataSetChanged()
    }
}