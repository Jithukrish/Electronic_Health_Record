from django.shortcuts import render,redirect
from django.db import connection
from django.http import HttpResponse
from django.contrib.auth.hashers import check_password
from django.core.files.storage import FileSystemStorage
from django.core.mail import send_mail
from django.utils import timezone
from datetime import datetime

from ehr_app.models import *
import base64

import requests
# Create your views here.
def index(request):
    return render(request,'home.html')
def log(request):
    return render(request,'Login.html')

def logins(request):
     if request.POST:
        username = request.POST['username']
        password = request.POST['pass']
        # hashed_password = make_password(password)
        # print(hashed_password)
        try:
            ob=login.objects.get(username=username,password=password)
        except:
            return HttpResponse('''<script>alert("login failed");window.location="/"</script>''')
        if ob :

            if ob.usertype == 'admin':
                return redirect('admindash')
           
            elif ob.usertype =='doctor':
                request.session['lid']=ob.id
                request.session['doctorname']=Doctor.objects.get(l_id=ob.id).Fname

                return redirect('/doctordash')
             
            elif ob.usertype =='hospital':
                request.session['lid']=ob.id
                request.session['hospitalname']=Hospital.objects.get(l_id=ob.id).hospitalname
                print(request.session['hospitalname'],'*******************************')
                return redirect('/hospdash')
            else :
                return HttpResponse('''<script>alert("login failed");window.location="/"</script>''')




     else :
         return HttpResponse('login failed') 
       
def admindash1(request):
    try :
        user =login.objects.get(pk = request.session['l_id'])
    except:
        return
    if user.usertype == 'admin':
        return render(request,'admin/addash.html')

    
def registrations(request):
    return render(request,'reg.html')
def regis(request):
    name1=request.POST['Hospitalname']
    place=request.POST['Place']
    Email=request.POST['email']
    pincode=request.POST['Pincode']
    image = request.FILES['file']
    description = request.POST['description']
    try:
        fs=FileSystemStorage()
        fp=fs.save(image.name,image)
        phone=request.POST['phone']
        address=request.POST['address']
        username=request.POST['username']
        password=request.POST['password']
        ob=login()
        ob.username =username
        ob.password =password
        ob.usertype = "pending"
        ob.save()

        ob2=Hospital()
        ob2.l_id =ob
        ob2.image=fp
        ob2.hospitalname =name1
        ob2.place =place
        ob2.lattitude=0.0
        ob2.logitude=0.0
        ob2.phone =phone
        ob2.email =Email
        ob2.address =address
        ob2.pincode = pincode
        ob2.description = description
        ob2.save()
        request.session['hid']=ob2.id
        return HttpResponse('''<script>alert("registration successfully");window.location="/map2"</script>''')
    except:
        return HttpResponse('''<script>alert("ALREADY EXIST");window.location="/map2"</script>''')




def map2(request):
    return render(request,"hospital/hoslocation.html")

def map3(request):
    lat=request.POST['lat']
    lon=request.POST['lon']
    ob4=Hospital.objects.get(id=request.session['hid'])
    ob4.lattitude=lat
    ob4.logitude=lon
    ob4.save()

    return HttpResponse('''<script>alert("Successfully Completed");window.location="/"</script>''')







def verify(request):
    ob=Hospital.objects.all()
    return render(request,'admint/verifyhospital.html',{'val':ob})
def accept(request,id):
    ob=login.objects.get(id=id)
    ob.usertype='hospital'
    ob.save()
    return HttpResponse('''<script>alert("accepted");window.location="/verify"</script>''')


def reject(request,id):
    ob=login.objects.get(id=id)
    ob.usertype='reject'
    ob.save()
    return HttpResponse('''<script>alert("rejected ");window.location="/verify"</script>''')


def viewmore(request,id):
    ob=Hospital.objects.get(id=id)
    return render(request,'admint/VIEWMORE.html',{'val':ob})

def viewhosp(request):
    ob=Hospital.objects.filter(l_id__usertype= 'hospital')
    return render(request,'admint/viewhosp.html',{'val':ob})



def veri(request):
    return render(request,'admin/verify.html')
def admindash(request):
    return render(request,'admint/addash.html')

def hospdash(request):
    
    return render(request,'hospital/Hospital dash.html',{'hospitalname':request.session['hospitalname']})
def mandept(request):
    ob=departments.objects.filter(H_id__l_id__id=request.session['lid'])
    return render(request,'hospital/addviewdepp.html',{'val':ob,'hospitalname':request.session['hospitalname']})
def adddept1(request):
   
    return render(request,'hospital/ADDDEPARTMENT.html',{'hospitalname':request.session['hospitalname']})
def adddept(request):

    namedept=request.POST['textfield']
    ob=departments.objects.filter(H_id__l_id__id=request.session['lid'],departmentname=namedept)
    if len(ob)==0:


        description=request.POST['description']
        image = request.FILES['file']
        fs=FileSystemStorage()
    
        fp=fs.save(image.name,image)
        ob3=departments()
        ob3.departmentname = namedept
        ob3.description=description
        ob3.image=fp
        ob3.H_id=Hospital.objects.get(l_id__id=request.session['lid'])
        ob3.save()
        return HttpResponse('''<script>alert("add successfully");window.location="/mandept"</script>''')
    else:
        return HttpResponse('''<script>alert("Already exist");window.location="/mandept"</script>''')
    
#######################validate department

# views.py
from django.http import JsonResponse
# from .models import Department

def validate_department(request):
    if request.method == 'GET':
        namedept = request.GET.get('department_name',None)
        print(namedept,"*****************///////////////////")
        if namedept:
            print("======7777777777========88888======")
            Departments = departments.objects.filter(H_id__l_id__id=request.session['lid'],departmentname=namedept)
            print(Departments,"999999999999999999999999999")
            if Departments.exists():
                print('=======================================')
                return JsonResponse({'message': 'Department name already exists.'})
            else:
                return JsonResponse({'message': ''})




#######################validate department



def deledept(request,id):
    ob=departments.objects.get(pk=id)
    ob.delete()
    return redirect('mandept')




def viewdep(request):
    ob=Hospital.objects.all()
    return render(request,'admint/adviewdep.html',{'val':ob})
def viewdep1(request,id):
   ob=departments.objects.filter(H_id=id)
   
   return render(request,'admint/viewdept1.html',{'val':ob})




def docname(request):
    print('++++++++++++++++++++++++++++++++')
    return JsonResponse({'msg':'success','name':request.session['doctorname']})


def mandoc(request):
    ob=Doctor.objects.filter(d_id__H_id__l_id__id=request.session['lid'])
    
    return render(request,'hospital\docviewadd.html',{'val':ob,'hospitalname':request.session['hospitalname']})
def adddoc1(request):
    dept = departments.objects.filter(H_id__l_id__id=request.session['lid'])
    print(dept.values())
    return render(request,'hospital/template.html',{'val':dept,'hospitalname':request.session['hospitalname']})
def adddoc(request):
    fname=request.POST['textfield']
    place=request.POST['textfield2']

    dobs=request.POST['date']
    gen=request.POST['RadioGroup1']
    phno=request.POST['textfield5']
    deptt = request.POST['dept']
    email=request.POST['textfield6']
    spec=request.POST['textfield3']
    email=request.POST['textfield6']
    reg=request.POST['textfield7']
    image = request.FILES['file']
    try:
        fs=FileSystemStorage()
        fp=fs.save(image.name,image)
        username=request.POST['username']
        password=request.POST['password']
        ob=login()
        ob.username =username
        ob.password =password
        ob.usertype = "doctor"
        ob.save()
        ob4=Doctor()
        ob4.Fname = fname
        ob4.place = place
        ob4.gender= gen
        ob4.dob=dobs
        ob4.image=fp
        ob4.d_id=departments.objects.get(id=deptt)
        ob4.phone = phno
        ob4.l_id=ob
        ob4.lattitude=0.0
        ob4.logitude=0.0
        ob4.email = email
        ob4.specilization = spec
        ob4.regno = reg
        
        ob4.H_id=Hospital.objects.get(l_id__id=request.session['lid'])
        ob4.save()

        request.session['did']=ob4.id
    
        return HttpResponse('''<script>alert("Successfully Added, Select location ");window.location="/map"</script>''')
    except:
          return HttpResponse('''<script>alert("Successfully Added ");window.location="/map"</script>''')







def map(request):
    return render(request,"hospital/location.html")

def map1(request):
    lat=request.POST['lat']
    lon=request.POST['lon']
    ob4=Doctor.objects.get(id=request.session['did'])
    ob4.lattitude=lat
    ob4.logitude=lon
    ob4.save()

    return HttpResponse('''<script>alert("Successfully Completed");window.location="/mandoc"</script>''')


def viewdoc(request):
    ob=Doctor.objects.filter(d_id__H_id__l_id__id=request.session['lid'])
    
    return render(request,'admint\VIEWDC.html',{'val':ob})



# ADMIN VIEW DOCTORS
def admin_view_doc(request):
    ob=Hospital.objects.all()
    return render(request,'admint/AD_VIEW_DOC.html',{'val':ob})
def admin_view_doc1(request,id):
   ob=Doctor.objects.filter(d_id__H_id=id)
   
   return render(request,'admint/AD_VIEW_DOC1.html',{'val':ob})

# SCHEDULE MANAGEMENT
def advische(request):
    hsptl = Hospital.objects.get(l_id = request.session['lid'])
    with connection.cursor() as cursor:
      
        cursor.execute("SELECT * FROM `ehr_app_doctor` WHERE `id` IN( SELECT DISTINCT `d_id_id` FROM `ehr_app_schedule` WHERE `H_id_id`="+str(hsptl.pk)+")")
        row = cursor.fetchall()
    ob=Schedule.objects.filter(H_id__l_id__id=request.session['lid'])
    # print(ob.values(),"****************************")
    result =[]
    print(row)
    for i in row:
        cr = {"drname": i[4] ,'det':[]}
        Schedule_details = Schedule.objects.filter(d_id__id=i[0])
        for ii in Schedule_details:
            cr['det'].append(ii)
        result.append(cr)
    print(result)



    return render(request,"hospital/manage schedule.html",{'val':result,'hospitalname':request.session['hospitalname']})

def advische2(request):
    doctor = Doctor.objects.filter(d_id__H_id__l_id__id=request.session['lid'])

    
    return render(request,'hospital/Add manage schedule.html',{'val':doctor,'hospitalname':request.session['hospitalname']})

def advische1(request):
    doctor=request.POST['select']
    day=request.POST.getlist('day')
    fromtime=request.POST['from']
    totime=request.POST['totime']
    for i in day:
        ob4=Schedule()
        ob4.d_id=Doctor.objects.get(id=doctor)
        ob4.H_id=Hospital.objects.get(l_id__id=request.session['lid'])
        ob4.day=i
        ob4.Fromtime=fromtime
        ob4.Totime=totime
        ob4.save()
   
    return HttpResponse('''<script>alert("Successfully Added");window.location="/advische"</script>''')


# EDIT OR DELETE DATA IN SCHEDULE


def edit(request,id):
    doctor = Doctor.objects.filter(d_id__H_id__l_id__id=request.session['lid'])
    ob=Schedule.objects.get(id=id)
    request.session['schedule_id']=id
    return render(request,'hospital/editschedule.html',{'val':ob, 'val1':doctor,'hospitalname':request.session['hospitalname']})
    
def edit1(request):
   
    doctor=request.POST['select']
    day=request.POST['select1']
    fromtime=request.POST['from']
    totime=request.POST['totime']
    ob4=Schedule.objects.get(id=request.session['schedule_id'])
   
    ob4.d_id=Doctor.objects.get(id=doctor)
    ob4.H_id=Hospital.objects.get(l_id__id=request.session['lid'])
    ob4.day=day
    ob4.Fromtime=fromtime
    ob4.Totime=totime
    ob4.save()
    return HttpResponse('''<script>alert("Successfully Edited");window.location="/advische"</script>''')
    


def deletes(request,id):
    ob=Schedule.objects.get(id=id)
    ob.delete()
    
    return HttpResponse('''<script>alert("Deleted");window.location="/advische"</script>''')

def doctordash(request):
    return render(request,'doctor/DOCTOR_DASH.html')

def view_schedule(request):
    ob=Schedule.objects.filter(d_id__l_id__id=request.session['lid'])
    
    return render(request,'doctor/view_schedule.html',{'val':ob})


#####################HOME_HOSPITAL################################################
    
def page(request):
    return render(request,'hospital/page.html',{'hospitalname':request.session['hospitalname']})

########################HOME_ADMIN####################################################
def home_admin(request):
    return render(request,'admint/home_admin.html')




from django.core import serializers
import json
from django.http import JsonResponse
from django.db.models.fields.files import ImageFieldFile

def android_login(request):
    try:
        print(request.POST)
        un=request.POST['username']
        pwd=request.POST['password']
        print(un,pwd)
        user = login.objects.get( username=un, password=pwd,usertype='user')

        if user is None:
                data = {"task": "invalid"}
        else:
                print("in user function")
                data = {"task":"valid","id":str(user.id)}
        r = json.dumps(data)
        print (r)
        return HttpResponse(r)
    except:
            data = {"result": "invalid"}
            r = json.dumps(data)
            print (r)
            return HttpResponse(r)
def android_reg(request):
        print(request.POST)
        fn=request.POST['firstname']
        ln=request.POST['lastname']
        add=request.POST['address']
        gen=request.POST['gender']
        dob=request.POST['dob']
        eml=request.POST['email']
        ph=request.POST['phone']
        place2=request.POST['place']
        un=request.POST['username']
        pwd=request.POST['password']
        image=request.FILES['file']
        fs=FileSystemStorage()
        fp=fs.save(image.name,image)
        print(un,pwd)
        ob1=login()
        ob1.username=un 
        ob1.password=pwd
        ob1.usertype="user"
        ob1.save()
        ob=User()
        ob.l_id=ob1
        ob.Fname=fn
        ob.Lname=ln 
        ob.address=add 
        ob.gender=gen 
        ob.email=eml 
        ob.image=fp
        ob.place=place2
        ob.dob=dob 
        ob.phone=ph 
        ob.save()
        data = {"task": "success"}
        r = json.dumps(data)
        print (r)
        return HttpResponse(r)
        

def android_search_view(request):
    # type=request.POST['type']
    # if type == 'doctor':
    txt=request.POST['txt']
    from django.db import connection
    cursor=connection.cursor()
    sen_res=[]
    cursor.execute("SELECT `ehr_app_doctor`.*,AVG(`ehr_app_rating`.`rating`) AS rating FROM `ehr_app_doctor` LEFT JOIN `ehr_app_rating` ON `ehr_app_doctor`.`id`=`ehr_app_rating`.`d_id_id` JOIN `ehr_app_departments` ON `ehr_app_doctor`.`d_id_id`=`ehr_app_departments`.`id`  WHERE ehr_app_doctor.Fname LIKE '%"+str(txt)+"%' OR `ehr_app_departments`.`departmentname` LIKE '%"+str(txt)+"%' GROUP BY `ehr_app_doctor`.`id` ")
    res=cursor.fetchall()
    print(res,"==========================")
    # cursor.execute("SELECT* FROM `ehr_app_hospital` WHERE hospitalname LIKE '%"+str(txt)+"%'")
    # res=cursor.fetchall()
    data = []
    for a in res:
        if a[14] is None:
            print(a[14],"======================================")
            rating=0
        else:
            rating=a[14]
        row = {"id":a[0],"name": a[4], "rating":rating,"schedule":0,"email":a[8],"phn":a[7],"adrs":a[9],"img":a[3],"latti":a[12],"longi":a[13],"type":'doctor',"place":a[5]}
        data.append(row)
    # r = json.dumps(data)
    # return HttpResponse(r)
    # txt=request.POST['txt']
    from django.db import connection
    cursor=connection.cursor()
    sen_res=[]
    cursor.execute("SELECT* FROM `ehr_app_hospital` WHERE hospitalname LIKE '%"+str(txt)+"%'")
    res=cursor.fetchall()
    # data = []
    for a in res:
        row = {"id":a[0],"name": a[1], "rating":3,"schedule":0,"email":a[6],"phn":a[5],"adrs":a[7],"img":a[2],"latti":a[9],"longi":a[10],"type":'Hospital',"place":a[3]}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def android_view_schedule_hospital(request):
        did=request.POST['did']
        ob=Schedule.objects.filter(d_id__id=did)
        data=[]
        hlist=[]
        for i in ob:
           if i.H_id.hospitalname not in hlist:
               hlist.append(i.H_id.hospitalname)
               row={ "hname":i.H_id.hospitalname,"lid":i.H_id.id} 
               data.append(row)
        r = json.dumps(data)
        return HttpResponse(r)   
        
def android_view_doctor_schedule(request):
        did=request.POST['did'] 
        hid=request.POST['hid']
        print(did)
        print(hid)
        from datetime import datetime
        
        ob=Schedule.objects.filter(d_id__id=did,H_id__id=hid)
        data=[]
        for i in ob:
            try:
                d = datetime.strptime(i.Fromtime.split('.')[0], "%H:%M:%S")
                fd=d.strftime("%I:%M %p")

                d = datetime.strptime(i.Totime.split('.')[0], "%H:%M:%S")
                td=d.strftime("%I:%M %p")

                row={"ftime":fd,"ttime":td,"day":i.day}
                data.append(row)
            except:
                d = datetime.strptime(i.Fromtime.split('.')[0], "%H:%M")
                fd=d.strftime("%I:%M %p")

                d = datetime.strptime(i.Totime.split('.')[0], "%H:%M")
                td=d.strftime("%I:%M %p")

                row={"ftime":fd,"ttime":td,"day":i.day}
                data.append(row)
        r = json.dumps(data)
        return HttpResponse(r)    

def android_booking_sloat_select(request):
    print(request.POST)
    did=request.POST['did']
    hid=request.POST['hid']
    date=request.POST['date']
    day = findDay(date)
    print(did,hid,"***************")
    ob=Schedule.objects.filter(d_id__id=did,H_id__id=hid,day=day)   
    print(day)
    print(ob,"================================")
    if len(ob) == 0:
        return HttpResponse({"task":"na"}) 
    else:
        ftime=ob[0].Fromtime
        ttime=ob[0].Totime
        from datetime import datetime
        from datetime import timedelta

        list=[]

        time_str = ftime
        date_format_str = '%H:%M:%S.%f'
        try:
            # create datetime object from timestamp string
            ftime = datetime.strptime(time_str, date_format_str)
            ttime = datetime.strptime(ttime, date_format_str)
        except:
            date_format_str = '%H:%M'
            ftime = datetime.strptime(time_str, date_format_str)
            ttime = datetime.strptime(ttime, date_format_str)
        while True:
            date_format_str = '%H:%M:%S'
            n = 15
            # Add 15 minutes to datetime object
            final_time = str(ftime + timedelta(minutes=n)).split(' ')[1]
            final_time = datetime.strptime(final_time, date_format_str)
            print('Final Time (15 minutes after given time ): ', final_time)
            # Convert datetime object to string in specific format 
            final_time_str = final_time.strftime('%H:%M:%S')
            d = datetime.strptime(str(ftime).split('.')[0], "%Y-%m-%d %H:%M:%S")
            ft=d.strftime("%I:%M %p")
            d = datetime.strptime(str(final_time_str).split('.')[0], "%H:%M:%S")
            lt=d.strftime("%I:%M %p")
            sloat=ft+" - "+lt
            list.append(sloat)

            ftime=final_time
            if ftime>=ttime:
                break
        print(list)
        result=[]
        for i in list:
            obb=Booking.objects.filter(s_id=ob[0],bdate=date,sloat=i)
            if len(obb)>0:
                row={"sloat":i,"status":"booked","sid":ob[0].id,"dname":ob[0].d_id.Fname,"hname":ob[0].H_id.hospitalname}
                result.append(row)
            else:
                row={"sloat":i,"status":"available","sid":ob[0].id,"dname":ob[0].d_id.Fname,"hname":ob[0].H_id.hospitalname}
                result.append(row)
        r = json.dumps(result)
        return HttpResponse(r)    
            

    # Python program to Find day of
# the week for a given date
import datetime
import calendar

def findDay(date):
	born = datetime.datetime.strptime(date, '%Y-%m-%d').weekday()
	return (calendar.day_name[born])

# Driver program


def android_confirm_booking(request):
    
        print(request.POST)
        lid=request.POST['lid']
        sid=request.POST['sid']
        date=request.POST['date']
        sloat=request.POST['sloat']
        ob=Booking()
        ob.u_id=User.objects.get(l_id__id=lid)
        ob.s_id=Schedule.objects.get(id=sid)
        ob.sloat=sloat
        ob.bdate=date
        ob.status="pending"
        ob.save()
        message = f'''Successfully booked  to
        {ob.sloat} {ob.bdate}
        Hospital : {ob.s_id.H_id.hospitalname}
        Doctor : {ob.s_id.d_id.Fname} '''

        send_mail('Meditech', message, 'yejukrishna0087@gmail.com',[ob.u_id.email], fail_silently=False)
        # messages.info(request,"Password sent to your registered email address !!!")
        # return redirect('loginn')
        data = {"result": "ok"}
        r = json.dumps(data)
        print (r)
        
        return HttpResponse(r)


########################android user#######################################
def new_user(request):
    lid=request.POST['lid']
    ob=User.objects.get(l_id__id=lid)
    # row = {"fname": ob.Fname+" "+ob.Lname, "email":ob.email}
    # ob.append(row)
    data = {"name": ob.Fname+" "+ob.Lname,"email":ob.email,"img":str(ob.image)}
    r= json.dumps(data)
    print(r)
    return HttpResponse(r)
def user_view_schedule(request):
    lid=request.POST['lid']
    ob1=Booking.objects.filter(u_id__l_id__id=lid,status='confirm')
    print('$$$$$$$$$$$$$$$$$$$$$$$$$$')            

    print(ob1.values())
    data = []
    
    for i in ob1:
             if len(ob1)>0:
                
                row={"hname":i.s_id.H_id.hospitalname,"dname":i.s_id.d_id.Fname,"sloat":i.sloat,"date":i.bdate,"id":i.id}
                data.append(row)
    print(data,'$$$$$$$$$$$$$$$$$$$$$$$$$$')            
    r = json.dumps(data)
    return HttpResponse(r)   

def cancel_booking(request):
    bid=request.POST['bid']
    ob=Booking.objects.get(id=bid)
    ob.status='canceled'
    ob.save()
    data = {"task":"valid"}
    r = json.dumps(data)
    print (r)
    return HttpResponse(r)




#############################doc view appoinment##################################################

def view_booking_user(request):
    lid=request.POST['lid']
    ob1=Booking.objects.filter(u_id__l_id__id=lid)
    data = []
    for i in ob1:
             if len(ob1)>0:
                row={"hospital":i.s_id.H_id.hospitalname,"user":i.u_id.Fname,"address":i.u_id.address,"phone":i.u_id.phone,"email":i.u_id.email,"date":i.bdate,"time":i.time,"id":i.id}
                data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)   


def cancel(request):
    bid=request.POST['bid']
    ob=Booking.objects.get(id=bid)
    ob.status='canceled'
    ob.save()
    data = {"task":"valid"}
    r = json.dumps(data)
    print (r)
    return HttpResponse(r)

def confirm(request):
    bid=request.POST['bid']
    ob=Booking.objects.get(id=bid)
    ob.status='confirm'
    ob.save()
    data = {"task":"valid"}
    r = json.dumps(data)
    print (r)
    return HttpResponse(r)
##########################################doctor view schedule######################################

def doc_view_schedule(request):
# ,bdate_gte=datetime.datetime.now().date()
    ob=Booking.objects.filter(s_id__d_id__l_id__id=request.session['lid']).order_by('-bdate')
    
    for x in ob:
        print(x.u_id.pk)
    return render(request,'doctor/doc_view_schedule.html',{'val':ob})

def accept_user(request,id):
    ob=Booking.objects.get(id=id)
    ob.status='confirm'
    ob.save()
    return HttpResponse('''<script>alert("confirm booking");window.location="/doc_view_schedule"</script>''')


def reject_user(request,id):
    ob=Booking.objects.get(id=id)
    ob.status='reject'
    ob.save()
    return HttpResponse('''<script>alert("Booking rejected ");window.location="/doc_view_schedule"</script>''')
def doct_view(request):
    ob=Doctor.objects.filter(d_id__H_id__l_id__id=request.session['lid'])
    # ob=Booking.objects.filter(s_id__H_id__id=request.session['lid'])
    return render(request,'Hospital/doct_view_schedule.html',{'val':ob})

def doct_search(request):
    dr=request.POST['select']
    ob=Doctor.objects.filter(d_id__H_id__l_id__id=request.session['lid'])
    ob1=Booking.objects.filter(s_id__d_id__id=dr)
    print(ob1)
    return render(request,'Hospital/doct_view_schedule.html',{'val':ob,'val1':ob1})
########################### uploading user record ################################################


def download_file(request,id):
    print("#########",id)
    request.session['user']=id

    user1 = userrecord.objects.filter(b_id__id=request.session['user'])
    print(user1)
    return render(request,'doctor/view_files.html',{'val':user1})

def view_file(request):
    # request.session['bookid']=id
    return render(request,'doctor/fileupload.html')

def file_upload(request):
    if request.POST:
        print("****",request.session['bookid'])
        # ob=Doctor.objects.get(l_id__id=request.session['lid'])
        fileupload = request.FILES['file']
        fs=FileSystemStorage()
        fp=fs.save(fileupload.name,fileupload)
        print(fp,"==================================")
        with open( r"C:\django\EHR\media\\"+fp, "rb") as image2string:
                            image_string = base64.b64encode(image2string.read())
                            # f = myScreenshot.read()
                            # print (f)
                            # image_string = base64.b64encode(f)
                            image_string = image_string.decode('utf-8')

                            myobj = {'fn': fp, "p": image_string}

                            requests.post("http://127.0.0.1:1234/upload1", params=myobj)

        ob=userrecord()
        ob.records=fileupload
        ob.b_id=Booking.objects.get(id=request.session['user'])
        ob.d_id=Doctor.objects.get(l_id__id=request.session['lid'])
        ob.save()
        return HttpResponse('''<script>alert("Successfully Uploaded ");window.location="/doc_view_schedule"</script>''')

    

    ###################################download_file#####################################################
def user_download_file(request):
    lid=request.POST['lid']
    ob=userrecord.objects.filter(b_id__u_id__l_id=lid)
    data=[]
    hlist=[]
    for i in ob:
        
        row={"id":i.id, "dname":i.d_id.Fname,"hname":i.b_id.s_id.H_id.hospitalname,"date":i.b_id.bdate,"record":i.records.url} 
        data.append(row)
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)   
#############################################send feedback#####################################################
def send_feedback(request):
    print(request.POST)
    uid=request.POST['lid']
    feedback1=request.POST['feedback']
    ob=feedback()
    ob.u_id=User.objects.get(l_id__id=uid)
    ob.date=datetime.datetime.now()
    ob.feedback=feedback1
   
    ob.save()
    data = {"result": "ok"}
    r = json.dumps(data)
    print (r)
        
    return HttpResponse(r)

#################################################################################################################
def view_rate_us(request):
    lid=request.POST['lid']
    ob=Booking.objects.filter(u_id__l_id__id=lid,status='confirm')
    


   
    data = []
    for i in ob:
        row = {"did":i.s_id.d_id.l_id.id,"name": i.s_id.d_id.Fname,"phn":i.s_id.d_id.phone,"specilization":i.s_id.d_id.specilization,"type":'doctor'}
        data.append(row)
    
    
    r = json.dumps(data)
    return HttpResponse(r)


def add_rating(request):
    print(request.POST)
    uid=request.POST['lid']
    did=request.POST['did']
    print(did)
    ratings=request.POST['rating']
    # date=request.POST['date']
    ob=rating()
    ob.u_id=User.objects.get(l_id__id=uid)
    ob.d_id=Doctor.objects.get(l_id__id=did)
    ob.rating=ratings
    ob.date= datetime.datetime.now()
    ob.save()
    data = {"result":"ok"}
    r = json.dumps(data)
    print (r)
        
    return HttpResponse(r)
    ############################################################################################################
def view_feedback(request):
    ob=feedback.objects.all()
    return render(request,'doctor/view_feedback.html',{'val':ob}) 



    ################################################comaplaints####################################
def send_comp(request):
    
    print(request.POST)
    uid=request.POST['lid']
    hid=request.POST['hid']
    complaints=request.POST['complaint']
    ob=complaint()
    ob.u_id=User.objects.get(l_id__id=uid)
    ob.date=datetime.datetime.now()
    ob.h_id=Hospital.objects.get(l_id__id=hid)
    ob.complaints=complaints
   
    ob.save()
    data = {"result": "ok"}
    r = json.dumps(data)
    print (r)
        
    return HttpResponse(r)
    