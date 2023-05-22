import json

import firebase
from rest_framework.decorators import api_view
from django.http import HttpResponse
from django.http import JsonResponse

config = json.load(open('kinosklad/kinosklad_config.json'))
app = firebase.initialize_app(config)
auth = app.auth()
database = app.database()


@api_view(['POST'])
def register_user(request):
    email = 'www@gmail.com'
    passwd = '123qweasd'
    nickname = 'name'
    user = auth.create_user_with_email_and_password(email, passwd)
    user = auth.update_profile(user.get('idToken'), display_name=nickname)
    return JsonResponse(user)

@api_view(['POST'])
def auth_user(request):
    email = 'www4@gmail.com'
    passwd = '123qweasd'
    user = auth.sign_in_with_email_and_password(email, passwd)
    return JsonResponse(user)

# @api_view(['POST'])
# def add_category(request):
#
