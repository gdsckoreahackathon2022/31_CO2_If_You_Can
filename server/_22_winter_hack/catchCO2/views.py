from django.shortcuts import render, redirect
from django.http import HttpResponse

from catchCO2.models import gpsInfo
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from django.views.generic import View
from haversine import haversine
from django.contrib.auth.decorators import login_required
from .models import *
from .serializers import gpsInfoSerializer
from django.utils import timezone
from django.core import serializers
from json import dumps
from django.core.serializers.json import DjangoJSONEncoder

# Create your views here.

@api_view(['POST',])
def index(request):

    serializer = gpsInfoSerializer(data=request.data)

    if serializer.is_valid():
        serial_data = serializer.data
        user_id, lon, lat, transport = serial_data['user_id'], float(serial_data['lon']), float(serial_data['lat']), float(serial_data['transport'])
        gps_data = gpsInfo(user_id=user_id, lon=lon, lat=lat, transport=transport)
        gps_data.save()

    return render(request, 'catchCO2/index.html')

def get_gps(user_name):
    positions = gpsInfo.objects.filter(user_id=user_name).order_by('datetime').values('lat', 'lon', 'transport')
    distance = 0
    if len(positions) < 1:
        return None

    before = (positions[0]['lat'], positions[0]['lon'])
    b_trans = positions[0]['transport']

    for pos in positions[1:]:
        now = (pos['lat'], pos['lon'])
        n_trans = pos['transport']
        if b_trans != n_trans:
            b_trans = n_trans
            before = now
            continue
        distance += (haversine(before, now, unit='km') * b_trans)
        before = now

    positions = gpsInfo.objects.filter(user_id=user_name).order_by('datetime')
    positions = serializers.serialize('json', positions)
    data = {
        'positions': positions,
        'distance': distance
    }

    #Response(data)
    return data

@login_required(login_url='accounts:login')
def mapview(request):
    gps_dist_data = get_gps(request.user)
    if gps_dist_data is None:
        return redirect('accounts:login')
    else:
        print(gps_dist_data)
        return render(request, 'catchCO2/mapview.html', gps_dist_data)
