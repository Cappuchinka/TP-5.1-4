import json

from django.http import JsonResponse, HttpResponse
from drf_spectacular.types import OpenApiTypes
from drf_spectacular.utils import extend_schema, OpenApiParameter
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


@extend_schema(
    responses=models.CategoriesListSerializer,
    tags=['Categories']
)
@api_view(['GET'])
def get_categories(request):
    raw_category_info = settings.database.child(settings.CATEGORIES_TABLE).get().val()
    category_info = {}
    for i in range(len(raw_category_info)):
        serialized_raw = models.CategoryPublicSerializer(data=raw_category_info[i])
        serialized_raw.is_valid()
        category_info[i] = serialized_raw.validated_data
    return JsonResponse(category_info)


# @extend_schema(
#     request=models.CategoryDeleteSerializer,
#     responses=models.CategoryDeleteSerializer,
#     tags=['Categories'],
#     parameters=[OpenApiParameter("idToken", OpenApiTypes.STR, OpenApiParameter.QUERY, required=True)],
# )
# @api_view(['DELETE'])
# def delete_category(request, category_id):
#     try:
#         id_token = request.GET.get('idToken')
#     except ValidationError:
#         return JsonResponse({'error': 'INVALID_DATA'})
#     if not settings.database.child(settings.CATEGORIES_TABLE).child(category_id).get().val():
#         return JsonResponse({'error': 'INVALID_SESSION_ID'})
