from django.urls import path
from . import views

app_name='board'
urlpatterns = [
    path('', views.notice_index, name='notice_index'),
    path('<int:user_pk>/create/', views.notice_create, name='notice_create'),
]
