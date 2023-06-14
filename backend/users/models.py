from django.core.validators import MinLengthValidator
from django.db import models
from drf_spectacular.utils import extend_schema_serializer, OpenApiExample
from rest_framework import serializers

from kinoskladProject import settings


class User(models.Model):
    user_id = models.IntegerField()
    nickname = models.CharField(max_length=64)
    email = models.CharField(max_length=64)
    password = models.CharField(max_length=64)
    token = models.CharField(max_length=1024)

    def save_user(self):
        user = dict(UserSerializer(self).data)
        settings.database.child(settings.USERS_TABLE).child(self.user_id).update(user)


class UserSerializer(serializers.Serializer):
    user_id = serializers.IntegerField()
    nickname = serializers.CharField(max_length=64)
    email = serializers.CharField(max_length=64)
    password = serializers.CharField(max_length=64)
    token = serializers.CharField(max_length=1024)


class Credentials(models.Model):
    email = models.CharField(max_length=64, default=None, unique=True)
    nickname = models.CharField(max_length=64, validators=[MinLengthValidator(4)], default=None)
    password = models.CharField(max_length=64, validators=[MinLengthValidator(6)], default=None)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Reg data',
            summary='Reg',
            description="Used values:\n"
                        "1. email - email-address of user.\n"
                        "2. nickname - nickname of user, can' be less than 4 symbols.\n"
                        "3. password - can't be less than 6 symbols.",
            value={
                'email': "qwerty@gmail.com",
                'nickname': 'qwerty',
                'password': "ytrewq"
            },
            request_only=True,
            response_only=False
        )
    ]
)
class RegistrationSerializer(serializers.Serializer):
    email = serializers.CharField(max_length=64, validators=[MinLengthValidator(3)], default=None)
    password = serializers.CharField(max_length=64, validators=[MinLengthValidator(6)], default=None)
    nickname = serializers.CharField(max_length=64, default=None)

    def create(self, validated_data):
        return Credentials(**validated_data)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Auth data',
            summary='Auth',
            description="Used values:\n"
                        "1. email - email-address of user.\n"
                        "2. password - can't be less than 6 symbols.",
            value={
                'email': "qwerty@gmail.com",
                'password': "ytrewq"
            },
            request_only=True,
            response_only=False
        )
    ]
)
class AuthorizationSerializer(serializers.Serializer):
    email = serializers.CharField(max_length=64, default=None)
    password = serializers.CharField(max_length=64, validators=[MinLengthValidator(6)], default=None)

    def create(self, validated_data):
        return Credentials(**validated_data)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Profile data',
            summary='Info',
            value={
                'email': "qwerty@gmail.com",
                'nickname': "qwerty"
            },
            request_only=True,
            response_only=False
        )
    ]
)
class ProfileUserSerializer(serializers.Serializer):
    email = serializers.CharField(max_length=64, default=None)
    nickname = serializers.CharField(max_length=64, default=None)

    def create(self, validated_data):
        return Credentials(**validated_data)

