from django.db import models
from django.contrib.auth.models import AbstractUser
from django.conf import settings
# Create your models here.

class User(AbstractUser):
    email = models.CharField(max_length=100)
    phone = models.CharField(max_length=100)
    auth = models.IntegerField()
    job = models.CharField(max_length=200)
    birth = models.DateField(null=True, blank=True)
    token = models.TextField(max_length=200, blank=True)
    info = models.TextField(max_length=400, blank=True)