from django.db import models


class TimestampedModel(models.Model):
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        abstract = True

        ordering = [
            # '-created_at',
            # '-updated_at'
        ]


class Status(models.IntegerChoices):
    INACTIVE = 0
    ACTIVE = 1


class UserType(models.IntegerChoices):
    DEFAULT = 0
    ADMIN = 1
