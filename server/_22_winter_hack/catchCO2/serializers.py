from rest_framework import serializers
from .models import gpsInfo

class gpsInfoSerializer(serializers.ModelSerializer):
    class Meta:
        model = gpsInfo
        exclude = ['datetime']
