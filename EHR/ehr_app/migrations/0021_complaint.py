# Generated by Django 4.1.7 on 2023-04-26 04:17

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('ehr_app', '0020_remove_feedback_email_remove_feedback_subject'),
    ]

    operations = [
        migrations.CreateModel(
            name='complaint',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('complaints', models.CharField(max_length=100)),
                ('date', models.DateField()),
                ('d_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='ehr_app.doctor')),
                ('u_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='ehr_app.user')),
            ],
        ),
    ]
