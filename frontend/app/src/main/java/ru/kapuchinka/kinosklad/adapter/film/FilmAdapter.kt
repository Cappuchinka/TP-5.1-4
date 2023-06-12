package ru.kapuchinka.kinosklad.adapter.film

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.film.Film

class FilmAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    var filmList = emptyList<Film>()

    interface OnItemClickListener {
        fun onItemClick(film: Film)
    }

    class FilmViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var filmName : TextView = itemView.findViewById(R.id.item_film_name)
        var filmCountry : TextView = itemView.findViewById(R.id.item_film_country)
        var filmYear : TextView = itemView.findViewById(R.id.item_film_year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val filmItems : View = LayoutInflater.from(parent.context).inflate(R.layout.item_film_layout, parent, false)
        return FilmViewHolder(filmItems)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = filmList[position]
        holder.filmName.text = film.name
        holder.filmCountry.text = film.country
        holder.filmYear.text = film.releaseDate.toString()

        val bundle = Bundle()
        bundle.putString("filmName", film.name)
        bundle.putString("filmCountry", film.country)
        bundle.putInt("filmYear", film.releaseDate)
        bundle.putInt("filmId", film.film_id)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(film)
        }
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(filmsList: List<Film>) {
        filmList = filmsList
        notifyDataSetChanged()
    }
}