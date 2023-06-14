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
        user_id = 0

        try:
            all_users = settings.database.child(settings.USERS_TABLE).get().each()
            user_id = len(all_users)
        except TypeError:
            pass

    except ValidationError:
        return JsonResponse({'error': 'INVALID_CREDENTIALS'})

    try:
        auth_user = settings.auth.create_user_with_email_and_password(credentials.email, credentials.password)
    except HTTPError as exception:
        return JsonResponse({'error': extract_http_error_message(exception.args[1])})

    user = models.User(user_id=user_id, email=credentials.email, nickname=credentials.nickname, password=credentials.password, token=auth_user.get('localId'))

    try:
        user.save_user()
    except ValueError as e:
        settings.auth.delete_user_account(auth_user['token'])
        return JsonResponse({'error': e.args[0]})

    return JsonResponse({'token': auth_user.get('localId')})


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
    json_response = dict()
    json_response['token'] = str(auth_user.get('localId'))
    return JsonResponse(json_response)


@extend_schema(
    request=models.ProfileUserSerializer,
    responses=models.ProfileUserSerializer,
    tags=["Users"]
)
@api_view(['GET'])
def get_user_by_token(request, token):
    raw_users = settings.database.child(settings.USERS_TABLE).get().val()
    user_serialized = dict()
    for raw_user in raw_users:
        if raw_user is None:
            continue
        if token == raw_user['token']:
            user_serialized['email'] = raw_user['email']
            user_serialized['nickname'] = raw_user['nickname']
            user = models.ProfileUserSerializer(data=user_serialized)
    if not user.is_valid():
        raise ValidationError
    return JsonResponse(user.validated_data)


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

