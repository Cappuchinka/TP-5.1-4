from django.urls import path
from . import views

urlpatterns = [
    path('add_category/', views.add_category),
    path('get_category_info/<int:category_id>', views.get_category_info),
    path('get_categories/', views.get_categories)
]