from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth import get_user_model
from .serializers import UserListSerializers, UserCreateSerializers, LoginUserSerializer, UserSerializer
from rest_framework import viewsets, permissions, generics
from rest_framework.response import Response
from rest_framework.decorators import api_view
from knox.models import AuthToken

# Create your views here.
@api_view(['GET'])
def index(request):
    users = get_user_model().objects.all()
    serializer = UserListSerializers(users, many=True)
    return Response(serializer.data)

@api_view(['POST'])
def signup(request):
    error = UserCreateSerializers.validate(get_user_model(), data=request.data)
    if error['password'] or error['username']:
        print('error')
        return Response(error, status=400)
    serializer = UserCreateSerializers(data=request.data)
    if serializer.is_valid(raise_exception=True):
        user = UserCreateSerializers.create(get_user_model(), request.data)
        serializer = UserCreateSerializers(user)
        print(serializer.data)
        return Response(serializer.data)

class Login(generics.GenericAPIView):
    serializer_class = LoginUserSerializer
    
    def post(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        user = serializer.validated_data
        return Response(
            {
                "user": UserSerializer(
                    user, context = self.get_serializer_context()
                ).data,
                "token": AuthToken.objects.create(user)[1],
                "message": "로그인이 완료되었습니다",
            }
        )

class UserAPI(generics.RetrieveAPIView):
    permission_classes = [permissions.IsAuthenticated]
    serializer_class = UserSerializer

    def get_object(self):
        return self.request.user