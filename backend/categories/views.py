import json

from django.http import JsonResponse, HttpResponse
from drf_spectacular.utils import extend_schema
from requests import Response
from rest_framework.decorators import api_view
from rest_framework.exceptions import ValidationError
from python_jwt import _JWTError

from categories import models
from kinoskladProject import settings


@extend_schema(
    request=models.CategoryAddSerializer,
    tags=['Categories']
)
@api_view(['POST'])
def add_category(request):
    try:
        body_unicode = request.body.decode('utf-8')
        body_data = json.loads(body_unicode)
        category_id = 0
        try:
            all_categories = settings.database.child(settings.CATEGORIES_TABLE).get().each()
            category_id = len(all_categories)
        except TypeError:
            pass
        body_data['category_id'] = category_id
        category = models.CategoryAddSerializer(data=dict(body_data))
        if not category.is_valid():
            raise ValidationError
        category = category.save()
    except ValidationError:
        return JsonResponse({'error': 'INVALID_CREDENTIALS'})
    except _JWTError as token_error:
        if token_error.args[0] == 'invalid JWT format':
            return JsonResponse({'error': 'INVALID_TOKEN'})
        else:
            return JsonResponse({'error': 'EXPIRED_TOKEN'})
    category.add_category()

    return HttpResponse(HttpResponse.status_code)
