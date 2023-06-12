package ru.kapuchinka.kinosklad.zamenit_vso.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R

//class SearchAdapter(_filmList: MutableList<FilmModel>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
//    private var filmList : MutableList<FilmModel> = _filmList
//
//    class SearchViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var filmName : TextView = itemView.findViewById(R.id.item_film_name)
//        var filmCountry : TextView = itemView.findViewById(R.id.item_film_country)
//        var filmYear : TextView = itemView.findViewById(R.id.item_film_year)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
//        val filmItems : View = LayoutInflater.from(parent.context).inflate(R.layout.item_film_layout, parent, false)
//        return SearchAdapter.SearchViewHolder(filmItems)
//    }
//
//    override fun getItemCount(): Int {
//        return filmList.size
//    }
//
//    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
//        holder.filmName.text = filmList[position].filmName
//        holder.filmCountry.text = filmList[position].filmCountry
//        holder.filmYear.text = filmList[position].filmYear.toString()
//
//        val bundle = Bundle()
//        bundle.putString("filmName", filmList[position].filmName)
//        bundle.putString("filmCountry", filmList[position].filmCountry)
//        bundle.putInt("filmYear", filmList[position].filmYear)
//
//        holder.itemView.setOnClickListener {
//            it.findNavController().navigate(R.id.action_navigation_search_to_filmPageFragment, bundle)
//        }
//    }
//}