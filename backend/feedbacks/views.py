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
