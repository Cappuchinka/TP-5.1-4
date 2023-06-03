from django.db import models
from drf_spectacular.utils import extend_schema_serializer, OpenApiExample
from rest_framework import serializers

from kinoskladProject import settings


class Film(models.Model):
    film_id = models.IntegerField()
    name = models.CharField(max_length=255)
    country = models.CharField(max_length=255)
    description = models.TextField()
    releaseDate = models.CharField(max_length=255)
    categories = models.JSONField(default=None)

    def add_film(self):
        film_info = FilmSerializer(self).data
        settings.database.child(settings.FILMS_TABLE).child(self.film_id).update(film_info)


class FilmSerializer(serializers.Serializer):
    film_id = serializers.IntegerField()
    name = serializers.CharField(max_length=255)
    country = serializers.CharField(max_length=255)
    description = serializers.CharField()
    releaseDate = serializers.CharField(max_length=255)
    categories = serializers.JSONField(default=None)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Film',
            summary="Film template",
            value={
                'idToken': '...',
                'name': "Тьма",
                'country': "Германия",
                'description': "«Тьма» — немецкий драматический и научно-фантастический веб-сериал, созданный Бараном бо Одаром и Янтье Фризе. Он состоит из трёх сезонов, выходивших с 2017 по 2020 год. Действие сериала разворачивается в вымышленном городке Винден.",
                'releaseDate': "1 декабря 2017",
                'categories': "[..., ...]"
            },
            request_only=True
        )
    ]
)
class FilmAddSerializer(serializers.Serializer):
    film_id = serializers.IntegerField()
    name = serializers.CharField(max_length=255)
    country = serializers.CharField(max_length=255)
    description = serializers.CharField()
    releaseDate = serializers.CharField(max_length=255)
    categories = serializers.JSONField(default=None)

    def create(self, validated_data):
        return Film(**validated_data)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Delete film',
            summary="Delete",
            request_only=True,
            response_only=False
        )
    ]
)
class FilmDeleteSerializer(serializers.Serializer):
    film_id = serializers.IntegerField()
    idToken = serializers.CharField(max_length=1024)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Film info',
            summary='Info',
            value={
                0: {
                    'name': "Тьма"
                },
                1: {
                    'name': "1899"
                }
            },
            request_only=False,
            response_only=True
        )
    ]
)
class FilmsListSerializer(serializers.Serializer):
    film_id = serializers.IntegerField()


class FilmPublicSerializer(serializers.Serializer):
    name = serializers.CharField(max_length=255)
    country = serializers.CharField(max_length=255)
    description = serializers.CharField()
    releaseDate = serializers.CharField(max_length=255)
    categories = serializers.JSONField(default=None)
