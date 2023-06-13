from django.db import models
from drf_spectacular.utils import extend_schema_serializer, OpenApiExample
from rest_framework import serializers

from kinoskladProject import settings


class Feedback(models.Model):
    feedback_id = models.IntegerField()
    film_id = models.IntegerField()
    nickname = models.CharField(max_length=255)
    feedback_text = models.TextField()

    def add_feedback(self):
        feedback_info = FeedbackSerializer(self).data
        settings.database.child(settings.FEEDBACKS_TABLE).child(self.feedback_id).update(feedback_info)


class FeedbackSerializer(serializers.Serializer):
    feedback_id = serializers.IntegerField()
    film_id = serializers.IntegerField()
    nickname = serializers.CharField(max_length=255)
    feedback_text = serializers.CharField()


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Feedback',
            summary="Feedback template",
            value={
                'film_id': 0,
                'nickname': "ПользовательN",
                'feedback_text': "Это просто вау! Шик! Блеск! Нет слов!"
            },
            request_only=True
        )
    ]
)
class FeedbackAddSerializer(serializers.Serializer):
    feedback_id = serializers.IntegerField()
    film_id = serializers.IntegerField()
    nickname = serializers.CharField(max_length=255)
    feedback_text = serializers.CharField()

    def create(self, validated_data):
        return Feedback(**validated_data)


@extend_schema_serializer(
    examples=[
        OpenApiExample(
            'Feedback info',
            summary='Info',
            value={
                "feedbacks": [
                    {
                        'nickname': "Пользователь1",
                        'feedback_text': "Это просто вау! Шик! Блеск! Нет слов!"
                    },
                    {
                        'nickname': "Пользователь2",
                        'feedback_text': "Фильм ужасный!"
                    }
                ]
            },
            request_only=False,
            response_only=True
        )
    ]
)
class FilmsListSerializer(serializers.Serializer):
    films = serializers.JSONField()
