from django.conf.urls import url,include
from django.contrib import admin
from vidstream_app import views 
from django.conf import settings
from django.conf.urls.static import static

app_name = 'vidstream_app'

urlpatterns = [
	url(r'^index/', views.index, name='index'),
	url(r'^get_vid_names/', views.getall, name='get'),
	url(r'^get_vid/(?P<pk>[0-9]+)', views.getvid, name='getvid'),

]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)