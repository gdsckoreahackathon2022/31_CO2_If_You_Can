from django.contrib import admin

# Register your models here.
from django.contrib import admin
from .models import emissions_list

class emissions_list_admin(admin.ModelAdmin):
    search_fields = ['act']

admin.site.register(emissions_list, emissions_list_admin)
