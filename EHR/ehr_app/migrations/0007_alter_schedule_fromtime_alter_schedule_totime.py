# Generated by Django 4.1.7 on 2023-03-02 10:22

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('ehr_app', '0006_alter_schedule_fromtime_alter_schedule_totime'),
    ]

    operations = [
        migrations.AlterField(
            model_name='schedule',
            name='Fromtime',
            field=models.TimeField(),
        ),
        migrations.AlterField(
            model_name='schedule',
            name='Totime',
            field=models.TimeField(),
        ),
    ]
