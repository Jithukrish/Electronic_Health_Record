# Generated by Django 4.1.7 on 2023-04-07 15:23

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('ehr_app', '0018_alter_userrecord_date'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='feedback',
            name='H_id',
        ),
        migrations.AddField(
            model_name='feedback',
            name='email',
            field=models.CharField(default=5, max_length=100),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='feedback',
            name='subject',
            field=models.CharField(default=11, max_length=500),
            preserve_default=False,
        ),
    ]