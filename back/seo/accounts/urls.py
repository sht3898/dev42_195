from django.urls import path
from . import views

app_name = 'accounts'
urlpatterns = [
    path('', views.index, name='index'),
    path('signup/', views.signup, name='signup'),
    path('login/', views.Login.as_view(), name='login'),
    path('user/', views.UserAPI.as_view(), name="userapi"),
]