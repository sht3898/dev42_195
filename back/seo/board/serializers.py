from rest_framework import serializers
from .models import Notice
from django.contrib.auth import get_user_model, authenticate

class NoticeSerializers(serializers.ModelSerializer):
    class Meta:
        model = Notice
        fields = ['id', 'content', 'date', 'name']
