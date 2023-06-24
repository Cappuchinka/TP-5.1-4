package ru.kapuchinka.kinosklad.view.category

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.adapter.category.CategoryAdapter
import ru.kapuchinka.kinosklad.api.model.category.Category
import ru.kapuchinka.kinosklad.viewmodel.category.CategoryViewModel

class CategoryFragment : Fragment(), CategoryAdapter.OnItemClickListener {
    lateinit var adapter: CategoryAdapter
    lateinit var recyclerView: RecyclerView

    private val categoryViewModel: CategoryViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        recyclerView = view.findViewById(R.id.r_v_categories)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = CategoryAdapter(this)
        recyclerView.adapter = adapter

        categoryViewModel.getCategoryList()
        categoryViewModel.categoryList.observe(viewLifecycleOwner) { catList ->
            adapter.setList(catList.categories)
        }

        return view
    }

    override fun onItemClick(category: Category) {
        val categoryId = category.category_id
        categoryViewModel.categoryId.value = categoryId
        val bundle = Bundle()
        bundle.putInt("categoryId", categoryId)
        findNavController().navigate(R.id.action_navigation_home_to_filmsFragment, bundle)
    }
}
