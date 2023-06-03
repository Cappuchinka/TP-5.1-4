import json

from django.http import JsonResponse, HttpResponse
from drf_spectacular.utils import extend_schema
from python_jwt import _JWTError
from rest_framework.decorators import api_view
from rest_framework.exceptions import ValidationError

from films import models
from kinoskladProject import settings


@extend_schema(
    request=models.FilmAddSerializer,
    tags=['Films']
)
@api_view(['POST'])
def add_film(request):
    try:
        body_unicode = request.body.decode('utf-8')
        body_data = json.loads(body_unicode)
        film_id = 0
        try:
            all_categories = settings.database.child(settings.FILMS_TABLE).get().each()
            film_id = len(all_categories)
        except TypeError:
            pass
        body_data['film_id'] = film_id
        film = models.FilmAddSerializer(data=dict(body_data))
        if not film.is_valid():
            raise ValidationError
        film = film.save()
    except ValidationError:
        return JsonResponse({'error': 'INVALID_CREDENTIALS'})
    except _JWTError as token_error:
        if token_error.args[0] == 'invalid JWT format':
            return JsonResponse({'error': 'INVALID_TOKEN'})
        else:
            return JsonResponse({'error': 'EXPIRED_TOKEN'})
    film.add_film()

    return HttpResponse(HttpResponse.status_code)


@extend_schema(
    responses=models.FilmsListSerializer,
    tags=['Films']
)
@api_view(['GET'])
def get_films(request):
    raw_film_info = settings.database.child(settings.FILMS_TABLE).get().val()
    film_info = {}
    for i in range(len(raw_film_info)):
        serialized_raw = models.FilmPublicSerializer(data=raw_film_info[i])
        serialized_raw.is_valid()
        film_info[i] = serialized_raw.validated_data
    return JsonResponse(film_info)