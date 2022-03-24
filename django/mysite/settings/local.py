from .base import *


# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = '9pkcerwl+ao97o_fox@h2pcp0b-s_2xtd^g)w&+*vs+seobms!'

INSTALLED_APPS += [
    'django_extensions',
]

ELASTIC_SEARCH_PROTOCOL = 'http://'
ELASTIC_SEARCH_HOST = '127.0.0.1'
ELASTIC_SEARCH_PORT = '9200'
