from django.contrib.auth.validators import UnicodeUsernameValidator
from django.db import models

from mysite.models import TimestampedModel, Status, UserType


# Create your models here.
class User(TimestampedModel):
    username_validator = UnicodeUsernameValidator()

    username = models.CharField(
        max_length=150,
        unique=True,
        help_text='Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only.',
        validators=[username_validator],
        error_messages={
            'unique': "A user with that username already exists.",
        },
    )
    email = models.EmailField(blank=True)
    password = models.CharField(max_length=128)
    status = models.IntegerField(choices=Status.choices)
    type = models.IntegerField(choices=UserType.choices)
