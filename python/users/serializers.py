from django.contrib.auth.validators import UnicodeUsernameValidator

from rest_framework import serializers

from users.models import User


# STATUS_CHOICES = (
#     (1, 'ACTIVE'),
#     (2, 'INACTIVE')
# )

STATUS_CHOICES = (
    ('ACTIVE', 1,),
    ('INACTIVE', 2,)
)

USER_TYPE_CHOICES = (
    (0, 'DEFAULT',),
    (1, 'ADMIN',)
)

USER_TYPE_CHOICES = (
    ('DEFAULT', 0,),
    ('ADMIN', 1,)
)


class UserCreateSerializer(serializers.ModelSerializer):
    username_validator = UnicodeUsernameValidator()

    username = serializers.CharField(required=True, validators=[username_validator])
    email = serializers.CharField(required=True)
    password = serializers.CharField(required=True)
    status = serializers.ChoiceField(choices=STATUS_CHOICES)
    type = serializers.ChoiceField(choices=USER_TYPE_CHOICES)

    class Meta:
        model = User
        fields = ('id', 'username', 'email', 'status', 'type', 'created_at', 'updated_at')
    pass


class UserSerializer(serializers.ModelSerializer):
    status = serializers.SerializerMethodField()

    class Meta:
        model = User
        fields = ('id', 'username', 'email', 'status', 'type', 'created_at', 'updated_at')

    def get_status(self, obj):
        return obj.get('status')
    pass
