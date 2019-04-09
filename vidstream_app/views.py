from django.shortcuts import render
from django.core.files.storage import FileSystemStorage
from django.db import connection
from django.http import JsonResponse
# Create your views here.
def index(request):
	context={}
	if request.method == 'POST':
		upload_file= request.FILES['document']
		print(upload_file.name)
		print(upload_file.size)
		fs=FileSystemStorage()
		name=fs.save(upload_file.name,upload_file)
		context['url']='/vidstream'+fs.url(name)
		cursor=connection.cursor()
		cursor.execute("INSERT INTO VID(name,url) values('{}','{}')".format(name,context['url']))
		connection.close()	
		#print("INSERT INTO VID values('{}','{}')".format(name,context['url']))
	return render(request,'vidstream_app/index.html',context)

def getall(request):
	li=[]
	dic={}
	cursor=connection.cursor()
	cursor.execute('SELECT id,name FROM VID')
	data=cursor.fetchall()
	connection.close()
	
	for i in data:
		dic['id']=i[0]
		dic['name']=i[1]
		li.append(dic.copy())
	return JsonResponse({'vid':li})	

def getvid(request, pk):
	dic={}
	l=[]
	cursor=connection.cursor()
	cursor.execute("SELECT url FROM VID where id={}".format(pk))
	data=cursor.fetchone()
	connection.close()
	#print(data[0])
	url=str(request.META['HTTP_HOST']+data[0])
	dic["vid"]=url
	l.append(dic)
	return JsonResponse({'url':l})
