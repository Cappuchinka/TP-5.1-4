import json

import firebase
from rest_framework.decorators import api_view
from django.http import HttpResponse

config = json.load(open('kinosklad/kinosklad_config.json'))
app = firebase.initialize_app(config)
auth = app.auth()
database = app.database()


@api_view(['POST'])
def register_user(request):
    email = 'www4@gmail.com'
    passwd = '123qweasd'
    auth.create_user_with_email_and_password(email, passwd)
    return HttpResponse(HttpResponse.status_code)
