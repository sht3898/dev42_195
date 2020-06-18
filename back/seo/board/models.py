from django.db import models
from django.conf import settings

# Create your models here.
class Notice(models.Model):
    kind = models.BooleanField(default=True)
    content = models.TextField()
    date = models.DateField()
    name = models.TextField()
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)