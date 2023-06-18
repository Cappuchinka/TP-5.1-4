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
    raw_films = settings.database.child(settings.FILMS_TABLE).get().val()
    films_serialized = []
    for film in raw_films:
        serialized_film = models.FilmPublicSerializer(data=film)
        serialized_film.is_valid()
        films_serialized.append(dict(serialized_film.validated_data))
    film_list = dict()
    film_list['films'] = films_serialized

    films = models.FilmsListSerializer(data=film_list)
    if not films.is_valid():
        raise ValidationError
    return JsonResponse(films.data)


@extend_schema(
    responses=models.FilmAddSerializer,
    tags=['Films']
)
@api_view(['GET'])
def get_film_by_id(request, film_id):
    try:
        film_info = settings.database.child(settings.FILMS_TABLE).child(film_id).get().val()
        if not film_info:
            raise ValidationError
        else:
            film_info = dict(film_info)
        film_info['film_id'] = film_id
        film = models.FilmAddSerializer(data=film_info)
        if not film.is_valid():
            raise ValidationError
        film = film.save()
    except ValidationError:
        return JsonResponse({'error': 'INVALID_FILM_ID'})
    return JsonResponse(models.FilmAddSerializer(film).data)


@extend_schema(
    request=models.FilmDeleteSerializer,
    responses=models.FilmDeleteSerializer,
    tags=['Films']
)
@api_view(['DELETE'])
def delete_film(request, film_id):
    if not settings.database.child(settings.FILMS_TABLE).child(film_id).get().val():
        return JsonResponse({'error': 'INVALID_FILM_ID'})
    films_list = settings.database.child(settings.FILMS_TABLE).get().val()
    films_list.pop(film_id)
    settings.database.child(settings.FILMS_TABLE).set(films_list)
    return HttpResponse(HttpResponse.status_code)

@extend_schema(
    request=models.FilmPublicSerializer,
    responses=models.FilmPublicSerializer,
    tags=['Films']
)
@api_view(['GET'])
def get_film(request, name):
    raw_films = settings.database.child(settings.FILMS_TABLE).get().val()
    film_serialized = None
    films = []
    for film in raw_films:
        if film is None:
            continue
        if name.upper() in film['name'].upper():
            film_serialized = film
            if film_serialized is not None:
                film = models.FilmSerializer(data=film_serialized)
            if not film.is_valid():
                raise ValidationError
            films.append(dict(film.validated_data))
    films_serialized = dict()
    films_serialized['films'] = films
    json_response = models.FilmsListSerializer(data=films_serialized)
    if not json_response.is_valid():
        raise ValidationError

    return JsonResponse(json_response.data)


@extend_schema(
    responses=models.FilmsListSerializer,
    tags=['Films']
)
@api_view(['GET'])
def get_film_by_category(request, category_id):
    raw_films = settings.database.child(settings.FILMS_TABLE).get().val()
    films_serialized = []
    film_list = dict()

    for film in raw_films:
        if film is None:
            continue
        if category_id in eval(film['categories']):
            serialized_film = models.FilmSerializer(data=film)
            serialized_film.is_valid()
            films_serialized.append(dict(serialized_film.validated_data))

    film_list['films'] = films_serialized
    films = models.FilmsListSerializer(data=film_list)
    if not films.is_valid():
        raise ValidationError
    return JsonResponse(films.data)

@extend_schema(
    request=models.FilmAddSerializer,
    tags=['Films']
)
@api_view(['PUT'])
def edit_film(request, film_id):
    try:
        body_unicode = request.body.decode('utf-8')
        body_data = json.loads(body_unicode)
        film_info = dict(body_data)
        film_info['film_id'] = film_id
        film_info = models.FilmSerializer(data=film_info)
        if not film_info.is_valid():
            raise ValidationError
        settings.database.child(settings.FILMS_TABLE).child(film_id).update(dict(film_info.validated_data))
    except ValidationError:
        return JsonResponse({'error': 'INVALID_FILM_ID'})
    return HttpResponse(status=200)

