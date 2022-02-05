from django.urls import path
from .views import *

urlpatterns = [
    # path('', post_gps, name='post_gps'),
    path('catchCO2/', index),
    path('catchCO2/map/', mapview)
]
