from django.shortcuts import render, get_object_or_404
from django.http import JsonResponse
from .models import Notice
from accounts.serializers import UserSerializer
from .serializers import NoticeSerializers
from rest_framework.response import Response
from rest_framework.decorators import api_view
from django.contrib.auth import get_user_model

# Create your views here.
@api_view(['GET'])
def notice_index(request):
    notices = Notice.objects.all()
    serializer = NoticeSerializers(notices, many=True)
    # print(serializer.data[0].username)
    return Response(serializer.data)

@api_view(['POST'])
def notice_create(request, user_pk):
    serializer = NoticeSerializers(data=request.data)
    if serializer.is_valid(raise_exception=True):
        serializer.save(user_id=user_pk)
    return Response({'message': '리뷰가 작성되었습니다'})
