from django.urls import path
from . import views

urlpatterns = [
    path('register/', views.register_user),
    path('auth/', views.auth_user),
    path('get_user_by_token/<str:token>', views.get_user_by_token)
]