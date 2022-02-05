from django.shortcuts import render, redirect
from django.http import HttpResponse

from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from django.views.generic import View
from django.contrib.auth.decorators import login_required
from .models import *
from .serializers import ContentSerializer
from django.utils import timezone
from django.core import serializers
from json import dumps
from django.core.serializers.json import DjangoJSONEncoder

@api_view(['POST',])
def index(request):
    serializer = ContentSerializer(data=request.data)
    print(serializer.is_valid())

    if serializer.is_valid():
        serial_data = serializer.data
        user_id, act, freq = serial_data['user_id'], serial_data['act'], int(serial_data['freq'])
        print(user_id, act, freq)
        emission = emissions_list.objects.get(act=act).act.emissions
        data = user_emissions(user_id=user_id, act=act, freq=freq, sum=(int(emission) * freq))
        data.save()

    return render(request, 'pybo/test.html')

def detail_list(request):
    list = emissions_list.objects.order_by('act')
    context = {'list': list}
    return render(request, 'pybo/test.html', context)

@login_required(login_url='common:login')
def chartview(request):
    data = user_emissions.objects.filter(user_id=request.user).values('sum')

    if len(data) < 1:
        return redirect('common:login')
    else:
        return render(request, 'pybo/chart.html')

class ResultAPIView(APIView):

    authentication_classes = []
    permission_classes = []

    def get(self, request):
        data = request.session.get('result')
        return Response(data)

    def result_detail(request):

        context = {}
        return render(request, 'pybo/chart.html', context)
