import os
import json
import requests

from django.core.management.base import BaseCommand, CommandError
from django.conf import settings

from users.constants import USERS_INDEX_NAME


class Command(BaseCommand):
    help = 'Creates elasticsearch index for users'

    def add_arguments(self, parser):
        pass

    def handle(self, *args, **options):
        users_index_path = os.path.join(settings.BASE_DIR, '../../shared/indices/users.json')
        with open(users_index_path) as json_file:
            users_index = json.dumps(json.load(json_file))
            print(users_index)

            url = '{}{}:{}/{}'.format(
                settings.ELASTIC_SEARCH_PROTOCOL, settings.ELASTIC_SEARCH_HOST,
                settings.ELASTIC_SEARCH_PORT, USERS_INDEX_NAME
            )
            headers = {'Content-Type': 'application/json'}
            users_index_request = requests.put(url, headers=headers, data=users_index)

            print(users_index_request.json())
            if users_index_request.status_code == requests.codes.ok:
                print('Users index created successfully')
        pass
    pass
