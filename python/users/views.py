from elasticsearch import Elasticsearch

from rest_framework import generics, viewsets, status
from rest_framework.response import Response

from users.constants import USERS_INDEX_NAME
from users.models import User
from users.serializers import UserCreateSerializer, UserSerializer


# Create your views here.
class UserViewSet(viewsets.ViewSet):
    # queryset = User.objects.all()
    serializer_class = UserSerializer

    def get_serializer_class(self):
        if self.request.method == 'POST':
            return UserCreateSerializer
        return self.serializer_class
        pass

    def list(self, request):
        es = Elasticsearch()
        res = es.search(index=USERS_INDEX_NAME, body={})
        print("Got %d Hits:" % res['hits']['total']['value'])
        queryset = []
        for hit in res['hits']['hits']:
            queryset.append(hit['_source'])
        serializer = UserSerializer(queryset, many=True)
        return Response(serializer.data)
        pass

    def create(self, request, *args, **kwargs):
        # serializer = self.get_serializer_class()(data=request.data)
        # serializer.is_valid(raise_exception=True)
        # print(serializer.data)
        # return Response(serializer.data, status=status.HTTP_201_CREATED)
        pass

    def retrieve(self, request, *args, **kwargs):
        user_id = kwargs.get('pk')
        es = Elasticsearch()
        res = es.get(index=USERS_INDEX_NAME, id=user_id)
        serializer = self.get_serializer_class()(res['_source'])
        return Response(serializer.data)
        pass
