# from django.db import models
from django.contrib.gis.db import models
# from django.contrib.auth.models import User

# Create your models here.
# class Post(models.Model):
#     name = models.CharField(max_length=20)
#     content = models.TextField()

class gpsInfo(models.Model):
    # user_id = models.OneToOneField(User, on_delete=models.CASCADE, related_name='gpsinfo', null=True, default=None)
    user_id = models.CharField(max_length=20, default='')
    datetime = models.DateTimeField(auto_now_add=True)
    lon = models.FloatField()
    lat = models.FloatField()
    transport = models.FloatField(default=0)

    def __str__(self):
        return ' '.join([
            self.user_id,
            self.datetime,
            self.lon,
            self.lat
        ])
