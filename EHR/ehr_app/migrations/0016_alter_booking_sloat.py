# Generated by Django 4.1.7 on 2023-03-24 08:01

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('ehr_app', '0015_remove_booking_d_id_booking_sloat'),
    ]

    operations = [
        migrations.AlterField(
            model_name='booking',
            name='sloat',
            field=models.CharField(max_length=90),
        ),
    ]