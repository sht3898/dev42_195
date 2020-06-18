from rest_framework import serializers
from django.contrib.auth import get_user_model, authenticate
# from .models import User

class UserListSerializers(serializers.ModelSerializer):
    class Meta:
        model = get_user_model()
        fields = '__all__'

class UserCreateSerializers(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True)
    password2 = serializers.CharField(write_only=True)
    class Meta:
        model = get_user_model()
        fields =['id', 'username', 'email', 'password', 'password2', 'birth']
    
    def create(self, validated_data):
        user = get_user_model().objects.create(
            # username과 email은 유일한 값이어야 함
            username=validated_data['username'],
            email=validated_data['email'],
            password=validated_data['password'],
        )
        user.set_password(validated_data['password'])
        user.save()
        return user

    def validate(self, data):
        non_alpha = set([s for s in "!@#$%^&*()|-=_+\[]{};':\",./?><"])
        print(get_user_model().objects.get(pk=1))
        error = dict({'username' : [], 'password': [], 'email': []})
        if data['password'] != data['password2']: # confirm error: 1
            error['password'].append('비밀번호 같게 해주세요')
        if not 8 <= len(data['password']) < 16:   # password length error: 2
            error['password'].append('비밀번호를 8 ~ 16자로 작성해주세요!')
        if data['password'].isdigit():  # password is only numbers: 3
            error['password'].append('비밀번호를 다른 문자와 조합해주세요!')
        if get_user_model().objects.filter(email=data['email']):
            error['email'].append('중복된 이메일이 있습니다.')
        if get_user_model().objects.filter(username= data['username']): # same username in db : 4
            error['username'].append('중복된 ID가 있습니다.')
        if not 0 < len(data['username']) < 16:  # username length error: 5 
            error['username'].append('ID를 1 ~ 16 글자로 해주세요!')
        for word in data['username']: # non_alph in username: 6
            if word in non_alpha:
                error['username'].append('ID에 특수문자를 넣지 말아주세요!')
                break
        return error

# 접속 유지 중인지 확인할 시리얼라이저
class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = get_user_model()
        fields = ('id', 'username')

# 로그인 시리얼라이저
class LoginUserSerializer(serializers.Serializer):
    username = serializers.CharField()
    password = serializers.CharField()

    def validate(self, data):
        user = authenticate(**data)
        if user and user.is_active:
            return user
        raise serializers.ValidationError("로그인 실패")