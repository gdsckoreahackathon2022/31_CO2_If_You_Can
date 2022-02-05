from django.db import models

# Create your models here.

class user_emissions(models.Model):
    user_id = models.CharField(max_length=20, null=False, primary_key=True)
    act = models.ForeignKey('emissions_list', on_delete=models.CASCADE)
    freq = models.PositiveIntegerField(null=False)
    sum = models.PositiveIntegerField(null=False)
    date = models.DateTimeField()

class emissions_list(models.Model):
    act = models.CharField(max_length=100, null=False, primary_key=True)
    unit = models.PositiveIntegerField(null=False)
    emissions = models.PositiveIntegerField(null=False)

    def __str__(self):
        return self.act
