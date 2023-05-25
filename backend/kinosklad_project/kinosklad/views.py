import json

import firebase
from rest_framework.decorators import api_view
from django.http import HttpResponse
from django.http import JsonResponse
from datetime import datetime

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

@api_view(['POST'])
def add_category(request):
    category_id = 0
    try:
        all_categories = database.child("categories").get().val()
        category_id = len(all_categories)
    except TypeError:
        pass
    name = 'category3'

    category_info = {"name": name}

    database.child("categories").child(category_id).set(category_info)
    return JsonResponse(database.child("categories").get().val()[category_id])

@api_view(['GET'])
def get_categories(request):
    response = database.child("categories").get().val()
    all_categories = dict()
    for i in range(len(response)):
        all_categories[i] = response[i]
    return JsonResponse(all_categories)


@api_view(['POST'])
def add_film(request):
    film_id = 0
    try:
        all_films = database.child("films").get().each()
        film_id = len(all_films)
    except TypeError:
        pass

    name = 'film'
    country = 'country'
    releaseDate = '1970-01-01'
    date = datetime.strptime(releaseDate, '%Y-%m-%d').date()
    month = __translate_name_month(date.strftime('%B'))
    releaseDate = f"{date.day} {month} {date.year}"
    category_id = film_id

    film_info = {
        "name": name,
        "country": country,
        "releaseDate": releaseDate,
        "categoryId": category_id
    }

    database.child("films").child(film_id).set(film_info)
    return JsonResponse(database.child("films").get().val()[film_id])

@api_view(['GET'])
def get_films(request):
    response = database.child("films").get().val()
    all_films = dict()
    for i in range(len(response)):
        all_films[i] = response[i]
    return JsonResponse(all_films)

def __translate_name_month(key):
    months = {
        "January": "Январь",
        "February": "Февраль",
        "March": "Март",
        "April": "Апрель",
        "May": "Май",
        "June": "Июнь",
        "July": "Июль",
        "August": "Август",
        "September": "Сентябрь",
        "October": "Октябрь",
        "November": "Ноябрь",
        "December": "Декабрь"
              }
    return months[key]