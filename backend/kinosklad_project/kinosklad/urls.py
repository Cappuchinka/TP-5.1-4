from django.urls import path
from . import views

urlpatterns = [
    path('register/', views.register_user),
    path('authorize/', views.auth_user),
    path('add_category/', views.add_category),
    path('get_categories/', views.get_categories),
    path('add_film/', views.add_film),
    path('get_films/', views.get_films)
]