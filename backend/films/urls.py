from django.urls import path
from . import views

urlpatterns = [
    path('add_film/', views.add_film),
    path('get_film_by_id/<int:film_id>', views.get_film_by_id),
    path('delete_film/<int:film_id>', views.delete_film),
    path('get_films/', views.get_films),
    path('get_film/<str:name>', views.get_film),
    path('get_films_by_category/<int:category_id>', views.get_film_by_category),
    path('edit_film/<int:film_id>', views.edit_film)
]