package ru.kapuchinka.kinosklad.repository.category

import ru.kapuchinka.kinosklad.api.RetrofitInstance
import ru.kapuchinka.kinosklad.api.model.category.Categories

class CategoryRepository {
    suspend fun getCategories(): Categories {
        return RetrofitInstance.categoryApi.getCategories()
    }
}