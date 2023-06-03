from django.urls import path
from . import views

urlpatterns = [
    path('add_film/', views.add_film),
    # path('get_film_info/<int:category_id>', views.get_film_info),
    # path('delete_film/<int:category_id>', views.delete_film),
    path('get_films/', views.get_films)
]