# Generated by Django 4.1.7 on 2023-03-06 06:23

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('ehr_app', '0007_alter_schedule_fromtime_alter_schedule_totime'),
    ]

    operations = [
        migrations.AlterField(
            model_name='doctor',
            name='regno',
            field=models.IntegerField(unique=True),
        ),
        migrations.AlterField(
            model_name='hospital',
            name='hospitalname',
            field=models.CharField(max_length=100, unique=True),
        ),
    ]
