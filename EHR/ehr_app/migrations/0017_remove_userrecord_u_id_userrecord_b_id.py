# Generated by Django 4.1.7 on 2023-03-25 07:44

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('ehr_app', '0016_alter_booking_sloat'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='userrecord',
            name='u_id',
        ),
        migrations.AddField(
            model_name='userrecord',
            name='b_id',
            field=models.ForeignKey(default=6, on_delete=django.db.models.deletion.CASCADE, to='ehr_app.booking'),
            preserve_default=False,
        ),
    ]