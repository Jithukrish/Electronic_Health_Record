
from django.urls import path
from ehr_app import views

urlpatterns = [
    path('',views.index),
    path('registrations',views.registrations,name="registrations"),
     path('regis',views.regis,name="regis"),
    path('logins',views.logins,name="logins"),
    path('verify',views.verify,name='verify'),
    path('admindash',views.admindash,name="admindash" ),
     path('hospdash',views.hospdash,name="hospdash" ),
   path('mandept',views.mandept,name="mandept" ),
   path('adddept1',views.adddept1,name="adddept1" ),
   path('adddept',views.adddept,name="adddept" ),
   path('mandoc',views.mandoc,name="mandoc" ),
   path('adddoc1',views.adddoc1,name="adddoc1" ),
   path('adddoc',views.adddoc,name="adddoc" ),
   path('deledept/<int:id>',views.deledept,name="deledept" ),
   path('viewdep',views.viewdep,name="viewdep" ),
   path('viewdep1/<int:id>',views.viewdep1,name="viewdep1" ),
   path('accept/<int:id>',views.accept,name="accept" ),
   path('reject/<int:id>',views.reject,name="reject" ),
   path('viewmore/<int:id>',views.viewmore,name="viewmore" ),
   path('viewhosp',views.viewhosp,name="viewhosp" ),
   path('viewdoc',views.viewdoc,name="viewdoc" ),
   path('map',views.map,name="map" ),
   path('map1',views.map1,name="map1" ),
   path('map3',views.map3,name="map3" ),
  path('map2',views.map2,name="map2" ),
  path('advische',views.advische,name="advische" ),
  path('advische1',views.advische1,name="advische1" ),
  path('advische2',views.advische2,name="advische2" ),
  path('deletes/<int:id>',views.deletes,name="deletes" ),
  path('edit/<int:id>',views.edit,name="edit" ),
  path('edit1',views.edit1,name="edit1" ),
  path('view_schedule',views.view_schedule,name="view_schedule" ),
  path('doctordash',views.doctordash,name="doctordash" ),
  path('admin_view_doc',views.admin_view_doc,name="admin_view_doc" ),
  path('admin_view_doc1/<int:id>',views.admin_view_doc1,name="admin_view_doc1" ),
  path('log',views.log,name="log" ),
  path('page',views.page,name="page" ),
   path('home_admin',views.home_admin,name="home_admin" ),
   path('android_login',views.android_login,name="android_login" ),
    path('android_reg',views.android_reg,name="android_reg" ),
    path('android_view_doctor_schedule',views.android_view_doctor_schedule,name="android_view_doctor_schedule" ),
    path('android_search_view',views.android_search_view,name="android_search_view" ),
     path('android_view_schedule_hospital',views.android_view_schedule_hospital,name="android_view_schedule_hospital" ),
     path('android_booking_sloat_select',views.android_booking_sloat_select,name="android_booking_sloat_select" ),
     path('android_confirm_booking',views.android_confirm_booking,name="android_confirm_booking" ),
     path('validate_department/', views.validate_department, name='validate_department'),
     path('new_user', views.new_user, name='new_user'),
     path('cancel_booking', views.cancel_booking, name='cancel_booking'),
    path('user_view_schedule', views.user_view_schedule, name='user_view_schedule'),
     path('doc_view_schedule', views.doc_view_schedule, name='doc_view_schedule'),
      path('accept_user/<int:id>', views.accept_user, name='accept_user'),
     path('reject_user /<int:id>', views.reject_user, name='reject_user'),
     path('doct_view', views.doct_view, name='doct_view'),
      path('doct_search', views.doct_search, name='doct_search'),
       path('view_file', views.view_file, name='view_file'),
      path('file_upload', views.file_upload, name='file_upload'),
      path('docname/', views.docname, name='docname'),
      path('download_file/<int:id>', views.download_file, name='download_file'),
       path('user_download_file', views.user_download_file, name='user_download_file'),
       path('send_feedback', views.send_feedback, name='send_feedback'),
       path('view_rate_us', views.view_rate_us, name='view_rate_us'),
       path('add_rating', views.add_rating, name='add_rating'),
      path('view_feedback', views.view_feedback, name='view_feedback'),
      path('send_comp', views.send_comp, name='send_comp'),
      path('view_complaint', views.view_complaint, name='view_complaint'),

      path('hospital_search1', views.hospital_search1, name='hospital_search1'),
      path('view_report', views.view_report, name='view_report'),
      path('report', views.report, name='report'),













       
       
      
   
   
   
]
