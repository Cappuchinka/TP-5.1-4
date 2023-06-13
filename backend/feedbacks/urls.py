from django.urls import path
from . import views

urlpatterns = [
    path('add_feedback/', views.add_feedback)
]