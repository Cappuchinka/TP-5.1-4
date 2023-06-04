from django.urls import path
from . import views

urlpatterns = [
    path('register/', views.register_user),
    path('auth/', views.auth_user),
    path('user_info/<str:user_id>', views.user_info)
]