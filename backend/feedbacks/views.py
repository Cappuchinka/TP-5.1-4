import json

from django.http import JsonResponse, HttpResponse
from drf_spectacular.utils import extend_schema
from python_jwt import _JWTError
from rest_framework.decorators import api_view
from rest_framework.exceptions import ValidationError

from feedbacks import models
from kinoskladProject import settings


@extend_schema(
    request=models.FeedbackAddSerializer,
    tags=['Feedbacks']
)
@api_view(['POST'])
def add_feedback(request):
    try:
        body_unicode = request.body.decode('utf-8')
        body_data = json.loads(body_unicode)
        feedback_id = 0
        try:
            all_feedbacks = settings.database.child(settings.FEEDBACKS_TABLE).get().each()
            feedback_id = len(all_feedbacks)
        except TypeError:
            pass
        body_data['feedback_id'] = feedback_id
        feedback = models.FeedbackAddSerializer(data=dict(body_data))
        if not feedback.is_valid():
            raise ValidationError
        feedback = feedback.save()
    except ValidationError:
        return JsonResponse({'error': 'INVALID_CREDENTIALS'})
    except _JWTError as token_error:
        if token_error.args[0] == 'invalid JWT format':
            return JsonResponse({'error': 'INVALID_TOKEN'})
        else:
            return JsonResponse({'error': 'EXPIRED_TOKEN'})
    feedback.add_feedback()

    return HttpResponse(HttpResponse.status_code)


@extend_schema(
    request=models.FeedbackListSerializer,
    tags=['Feedbacks']
)
@api_view(['GET'])
def get_feedback_by_film_id(request, film_id):
    raw_feedbacks = settings.database.child(settings.FEEDBACKS_TABLE).get().val()
    feedbacks = []
    for feedback in raw_feedbacks:
        if feedback is None:
            continue
        if feedback['film_id'] == film_id:
            feedback_serialized = models.FeedbackSerializer(data=feedback)
            if not feedback_serialized.is_valid():
                raise ValidationError
            feedbacks.append(feedback_serialized.data)
    feedbacks_serialized = dict()
    feedbacks_serialized['feedbacks'] = feedbacks
    json_feedbacks = models.FeedbackListSerializer(data=feedbacks_serialized)
    if not json_feedbacks.is_valid():
        raise ValidationError
    return JsonResponse(json_feedbacks.data)


@extend_schema(
    request=models.FeedbackDeleteSerializer,
    responses=models.FeedbackDeleteSerializer,
    tags=['Feedbacks']
)
@api_view(['DELETE'])
def delete_feedback_by_id(request, feedback_id):
    if not settings.database.child(settings.FEEDBACKS_TABLE).child(feedback_id).get().val():
        return JsonResponse({'error': 'INVALID_FEEDBACK_ID'})
    feedback_list = settings.database.child(settings.FEEDBACKS_TABLE).get().val()
    feedback_list.pop(feedback_id)
    settings.database.child(settings.FEEDBACKS_TABLE).set(feedback_list)
    return HttpResponse(HttpResponse.status_code)