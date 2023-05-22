from django.urls import path
from . import views

urlpatterns = [
    path('register/', views.register_user),
    path('authorize/', views.auth_user),
    path('add_category/', views.add_category)
]