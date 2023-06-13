from django.urls import path
from . import views

urlpatterns = [
    path('add_feedback/', views.add_feedback),
    path('get_feedback_by_film_id/<int:film_id>', views.get_feedback_by_film_id)
]