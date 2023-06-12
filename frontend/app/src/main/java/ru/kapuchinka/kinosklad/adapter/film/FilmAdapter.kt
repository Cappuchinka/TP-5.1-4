package ru.kapuchinka.kinosklad.adapter.film

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.film.Film

class FilmAdapter() : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    var filmList = emptyList<Film>()

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
        holder.filmName.text = filmList[position].name
        holder.filmCountry.text = filmList[position].country
        holder.filmYear.text = filmList[position].releaseDate.toString()

        val bundle = Bundle()
        bundle.putString("filmName", filmList[position].name)
        bundle.putString("filmCountry", filmList[position].country)
        bundle.putInt("filmYear", filmList[position].releaseDate)

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.action_filmsFragment_to_filmPageFragment, bundle)
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