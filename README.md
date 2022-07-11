# RestfulWebService

### Implementation of RESTful Web Service:

* On backend implemented H2 dabase, with single table - "DATA"
* "DATA" contains fields:
  id, content.
* In content stored some string values that can be obtained from end-points:

```
1. http://localhost:8080/get-data/v1/all
2. http://localhost:8080/get-data/v1/?id=
```

1) Get all data from th table.
2) Get data with specific id.

### For single data instance response will be presented as:

```
{
  "content": "Some content: 1",
  "id": 2
}
```

###To start application - run the [RestApplication](src/main/java/com/example/rest/RestApplication.java)