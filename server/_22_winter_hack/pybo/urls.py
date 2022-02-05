from django.urls import path
from . import views

urlpatterns = [
    path('', views.detail_list, name='index'),
    path('pybo/', views.index),
    path('api/result/', views.ResultAPIView.as_view(), name="result_api"),
    path('result/', views.ResultAPIView.result_detail, name='result_detail'),
]