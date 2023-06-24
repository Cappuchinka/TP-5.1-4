package ru.kapuchinka.kinosklad.adapter.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.film.Film

class SearchAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var searchList: MutableList<Film> = mutableListOf()

    interface OnItemClickListener {
        fun onItemClick(film: Film)
    }

    class SearchViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var filmName : TextView = itemView.findViewById(R.id.item_film_name)
        var filmCountry : TextView = itemView.findViewById(R.id.item_film_country)
        var filmYear : TextView = itemView.findViewById(R.id.item_film_year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val filmItems: View = LayoutInflater.from(parent.context).inflate(R.layout.item_film_layout, parent, false)
        return SearchViewHolder(filmItems)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val film = searchList[position]
        holder.filmName.text = film.name
        holder.filmCountry.text = film.country
        holder.filmYear.text = film.releaseDate.toString()

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(film)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(filmsList: List<Film>) {
        searchList.clear()
        searchList.addAll(filmsList)
        notifyDataSetChanged()
    }
}
