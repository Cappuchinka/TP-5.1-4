import firebase
from django.core.validators import MinLengthValidator
from django.db import models
from django.forms import ModelForm
from rest_framework import serializers
from rest_framework.exceptions import ValidationError

# class Film(models.Model):
#     idToken = models.CharField(max_length=1024, default=None)
#     name = models.CharField(max_length=255, default=None)
#     country = models.CharField(max_length=255, default=None)
#     releaseDate = models.CharField(max_length=255, default=None)
#     categories = models.JSONField(default=None)
#
# class Category(models.Model):
#     idToken = models.CharField(max_length=1024, default=None)
#     name = models.CharField(max_length=255, default=None)
#
# class CategorySerializer(serializers.Serializer):
#     idToken = serializers.CharField(max_length=1024, default=None)
#     name = serializers.CharField(max_length=255, default=None)
#
#     def create(self, validated_data):
#         return Category(**validated_data)