### Server set up
## Step 1:Clone the Repo
### (Before continuing change pwd to Vidstream)
## Step 2:Create virtual environment : $virtualenv -p python3 env
## Step 3:Activate the virtual environment :$source env/bin/activate
## Step 4:$pip install requirements.txt
## Step 5:$python manage.py makemigrations
## Step 6:$python manage.py migrate 
## Step 6:$python manage.py runserver 0.0.0.0:8000

### App set up
## open app folder 
## open vidstream folder in android studio 
## locate 2 funtions jsonurl and jsonparse
## change ip address to global ip of your system($ip addr show - try out the ips there)
## install apk on phone