from django.db import models

# Create your models here.
class login(models.Model):
    username = models.CharField(max_length=100)
    password = models.CharField(max_length=100)
    usertype = models.CharField(default='pending',max_length=100)
class Hospital(models.Model):
    hospitalname = models.CharField(unique=True, max_length=100)
    image=models.ImageField()
    l_id = models.ForeignKey(login,on_delete=models.CASCADE)
    place = models.CharField(max_length=100)
    lattitude = models.FloatField()
    logitude =models.FloatField()
    pincode = models.BigIntegerField()
    description = models.CharField(max_length=500)
    phone = models.BigIntegerField()
    email = models.CharField(max_length=100)
    
    address = models.CharField(max_length=100)
class departments(models.Model):
    H_id =models.ForeignKey(Hospital,on_delete=models.CASCADE)
    departmentname = models.CharField(max_length=100)
    image =models.ImageField()
    description=models.CharField(max_length=500)
class User(models.Model):
    l_id =models.ForeignKey(login,on_delete=models.CASCADE)
    Fname = models.CharField(max_length=100)
    Lname = models.CharField(max_length=100)
    address = models.CharField(max_length=100)
    image=models.ImageField()
    gender = models.CharField(max_length=100)
    dob = models.DateField(max_length=100)
    place = models.CharField(max_length=100)
    email = models.CharField(max_length=100)
    phone = models.BigIntegerField()
class complaint(models.Model):
    u_id = models.ForeignKey(User,on_delete=models.CASCADE)
    complaints = models.CharField(max_length=100)
    date = models.DateField()
    H_id = models.ForeignKey(Hospital,on_delete=models.CASCADE)
class Doctor(models.Model):
    l_id = models.ForeignKey(login,on_delete=models.CASCADE)
    regno = models.IntegerField(unique=True)
    dob = models.DateField()
    d_id = models.ForeignKey(departments,on_delete=models.CASCADE)
    image =models.ImageField()
    Fname = models.CharField(max_length=100)
    place = models.CharField(max_length=100)
    lattitude = models.FloatField()
    logitude =models.FloatField()
    gender = models.CharField(max_length=100)
    phone = models.BigIntegerField()
    email = models.CharField(max_length=100)
    specilization = models.CharField(max_length=100)
class Schedule(models.Model):
    d_id = models.ForeignKey(Doctor,on_delete=models.CASCADE)
    H_id = models.ForeignKey(Hospital,on_delete=models.CASCADE)
    day = models.CharField(max_length=100)
    Fromtime = models.CharField(max_length=30)
    Totime = models.CharField(max_length=30)
class Booking(models.Model):
    u_id = models.ForeignKey(User,on_delete=models.CASCADE)
    s_id = models.ForeignKey(Schedule,on_delete=models.CASCADE)
    sloat = models.CharField(max_length=90)
    bdate = models.CharField(max_length=100)
    status = models.CharField(max_length=100)
class slot(models.Model):
    strength = models.IntegerField()
    available = models.IntegerField()
    d_id = models.ForeignKey(Doctor,on_delete=models.CASCADE)
    Fromtime =models.TimeField()
    Totime = models.TimeField()
class rating(models.Model):
    u_id = models.ForeignKey(User,on_delete=models.CASCADE)
    d_id = models.ForeignKey(Doctor,on_delete=models.CASCADE)
    rating =models.CharField(max_length=100)
    date= models.DateField()
class feedback(models.Model):
    u_id = models.ForeignKey(User,on_delete=models.CASCADE)
    
    feedback =models.CharField(max_length=100)
    date =models.DateField()
class userrecord(models.Model):
    b_id = models.ForeignKey(Booking,on_delete=models.CASCADE)
    d_id = models.ForeignKey(Doctor,on_delete=models.CASCADE)
    records = models.FileField()
    date = models.DateField(auto_now_add=True)
    



    


