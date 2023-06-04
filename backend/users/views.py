import json

from django.http import JsonResponse
from drf_spectacular.utils import extend_schema
from requests import HTTPError
from rest_framework.decorators import api_view
from rest_framework.exceptions import ValidationError

from users import models
from kinoskladProject import settings


@extend_schema(
    request=models.RegistrationSerializer,
    responses=models.RegistrationSerializer,
    tags=["Users"]
)
@api_view(['POST'])
def register_user(request):
    try:
        body_unicode = request.body.decode('utf-8')
        body_data = json.loads(body_unicode)
        credentials = complete_serialize(body_data, models.RegistrationSerializer)
    except ValidationError:
        return JsonResponse({'error': 'INVALID_CREDENTIALS'})
    try:
        auth_user = settings.auth.create_user_with_email_and_password(credentials.email, credentials.password)
    except HTTPError as exception:
        return JsonResponse({'error': extract_http_error_message(exception.args[1])})
    user = models.User(user_id=auth_user.get('localId'), email=credentials.email, nickname=credentials.nickname, password=" ", favorite_films=[], is_admin=False)
    try:
        user.save_user()
    except ValueError as e:
        settings.auth.delete_user_account(auth_user['idToken'])
        return JsonResponse({'error': e.args[0]})
    return JsonResponse({'idToken': auth_user.get('idToken')})


@extend_schema(
    request=models.AuthorizationSerializer,
    responses=models.AuthorizationSerializer,
    tags=["Users"]
)
@api_view(['POST'])
def auth_user(request):
    try:
        body_unicode = request.body.decode('utf-8')
        body_data = json.loads(body_unicode)
        auth_user = settings.auth.sign_in_with_email_and_password(body_data['email'], body_data['password'])
    except ValidationError:
        return JsonResponse({'error': 'INVALID_CREDENTIALS'})
    except HTTPError as exception:
        return JsonResponse({'error': extract_http_error_message(exception.args[1])})
    return JsonResponse({'idToken': auth_user.get('idToken')})


@extend_schema(
    request=models.ProfileUserSerializer,
    responses=models.ProfileUserSerializer,
    tags=["Users"]
)
@api_view(['GET'])
def user_info(request, user_id):
    try:
        user_info = settings.database.child(settings.USERS_TABLE).child(user_id).get().val()
        user_info.pop('password')
        user_info.pop('is_admin')
        user_info.pop('user_id')
        print(user_info)
        user_info = dict(user_info)
        user = models.ProfileUserSerializer(data=user_info)
        if not user.is_valid():
            raise ValidationError
        user = user.save()
    except ValidationError:
        return JsonResponse({'error': 'INVALID_USER_ID'})
    return JsonResponse(models.ProfileUserSerializer(user).data)


def complete_serialize(used_data: dict, serializer):
    serializing_object = serializer(data=used_data)
    if not serializing_object.is_valid():
        raise ValidationError
    return serializing_object.save()


def extract_http_error_message(exception_text):
    message_index = exception_text.find('"message": ') + 12
    message = ""
    while exception_text[message_index] != '"':
        message += exception_text[message_index]
        message_index += 1
    return message

