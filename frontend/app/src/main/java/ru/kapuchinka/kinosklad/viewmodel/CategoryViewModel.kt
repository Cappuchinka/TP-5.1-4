package ru.kapuchinka.kinosklad.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kapuchinka.kinosklad.api.model.category.Categories
import ru.kapuchinka.kinosklad.repository.category.CategoryRepository

class CategoryViewModel() : ViewModel() {
    var repository = CategoryRepository()
    val categoryList: MutableLiveData<Categories> = MutableLiveData()

    fun getCategoryList() {
        viewModelScope.launch {
            categoryList.value = repository.getCategories()
        }
    }
}
