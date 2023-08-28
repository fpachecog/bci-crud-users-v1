# bci-crud-users-v1
- Al iniciar el servicio se ejecutan los archivos #schema.sql y data.sql
- El archivo data.sql inserta el registro de un usuario que ya tiene un token válido. Con este token ya se pueden ejecutar los servicios CRUD. Los datos del usuarios son los siguientes
      - name: administrator
      - token:
           eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhdG9yIiwiZXhwIjoxNjk0OTYxMDE5LCJpYXQiOjE2OTMxNjEwMTl9.vCnIZeQB6iMaD5KduTDEKNSs0goRY-YQ8mSJVBdWyUjDrx2dczsHA9uqx6pq8ODmKqsB37QYTE0Hxcxk_q_SsQ

  
- Para ejecutar cualquier servicio de la Api, se debe crear el header "Authorization".  El valor del header debe ser ingresado de la siguiente manera:
      - Bearer [token]. Ejemplo:

            Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhdG9yIiwiZXhwIjoxNjk0OTYxMDE5LCJpYXQiOjE2OTMxNjEwMTl9.vCnIZeQB6iMaD5KduTDEKNSs0goRY-YQ8mSJVBdWyUjDrx2dczsHA9uqx6pq8ODmKqsB37QYTE0Hxcxk_q_SsQ

![Imagen de ejemplo](authotization-header-example.png)  
        

## Endpoints
En el siguiente link se puede ver en detalle la especificación de la API: https://studio-ws.apicur.io/sharing/b1063fbc-30db-4760-8d67-dbcf70974e01
Todos los endpoints retornan información en un JSON. Los endpoints POST y PUT sólo aceptan peticiones JSON

- GET endpoint: http://localhost:8080/getUserByName/admin
- GET endpoint: http://localhost:8080/getAllUsers
- DELETE endpoint: http://localhost:8080/deleteUserByName/francisco
- POST endpoint: http://localhost:8080/saveUser
- PUT endpoint: http://localhost:8080/updateUser

![Descargar Colección para Postman ](bci-crud-users-v1.postman_collection.json)

![Descargar archivo Swagger ](bci-crud-api-v1.yaml)




## Diagramas

![Diagrama endpoints: Save, Update, Delete](save-update-delete.drawio.png)
