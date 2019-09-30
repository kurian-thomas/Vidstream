# VidStream - video streaming app

## play App_demo.mp4 in app folder

# Server set up
## Step 1:Clone the Repo
### (Before continuing change pwd to vidstream)
## Step 2:Create virtual environment : $virtualenv -p python3 env
## Step 3:Activate the virtual environment :$source env/bin/activate
## Step 4:$pip install requirements.txt
## Step 5:$python manage.py makemigrations
## Step 6:$python manage.py migrate 
## Step 7:$python manage.py runserver 0.0.0.0:8000

# App Set Up
## Step 1: Open app folder 
## Step 2: Open Vidstream folder in android studio 
## Step 3: Locate 2 funtions jsonurl and jsonparse
## Step 4: Change url string with ip of your system($ip addr show - try out the ips there) eg:192.168.0.1:8000 will be changed to another address according to your system.
## Step 5: Install apk on phone
