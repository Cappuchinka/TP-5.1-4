from django.db import models
from drf_spectacular.utils import extend_schema_serializer, OpenApiExample
from rest_framework import serializers

from kinoskladProject import settings


class Category(models.Model):
    category_id = models.IntegerField()
    category_name = models.CharField(max_length=255)

    def add_category(self):
        category_info = CategorySerializer(self).data
        settings.database.child(settings.CATEGORIES_TABLE).child(self.category_id).update(category_info)


class CategorySerializer(serializers.Serializer):
    category_id = serializers.IntegerField()
    category_name = serializers.CharField(max_length=255)

    def create(self, validated_data):
        return Category(**validated_data)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Category',
            summary="Category template",
            value={
                'category_name': "Комедии"
            },
            request_only=True
        )
    ]
)
class CategoryAddSerializer(serializers.Serializer):
    category_id = serializers.IntegerField()
    category_name = serializers.CharField(max_length=255)

    def create(self, validated_data):
        return Category(**validated_data)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Category info',
            summary='Info',
            value=[
                {
                    "category_id" : 0,
                    "category_name": "Комедии"
                },
                {
                    "category_id" : 1,
                    "category_name": "Драмы"
                }
            ],
            request_only=False,
            response_only=True
        )
    ]
)
class CategoriesListSerializer(serializers.Serializer):
    categories = serializers.JSONField()


class CategoryPublicSerializer(serializers.Serializer):
    category_name = serializers.CharField(max_length=255)

    def create(self, validated_data):
        return Category(**validated_data)
