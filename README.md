# bci-crud-users-v1
- Al iniciar el servicio se ejecutan los archivos #schema.sql y data.sql
- El archivo data.sql inserta el registro de un usuario que ya tiene un token válido. Con este token ya se pueden ejecutar los servicios CRUD. Los datos del usuarios son los siguientes
      - name: administrator
      - token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhdG9yIiwiZXhwIjoxNjk0OTYxMDE5LCJpYXQiOjE2OTMxNjEwMTl9.vCnIZeQB6iMaD5KduTDEKNSs0goRY-YQ8mSJVBdWyUjDrx2dczsHA9uqx6pq8ODmKqsB37QYTE0Hxcxk_q_SsQ

  
- Para ejecutar cualquier servicio de la Api, se debe crear el header "Authorization".  El valor del header debe ser ingresado de la siguiente manera:
      - Bearer [token]. Ejemplo:

            Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhdG9yIiwiZXhwIjoxNjk0OTYxMDE5LCJpYXQiOjE2OTMxNjEwMTl9.vCnIZeQB6iMaD5KduTDEKNSs0goRY-YQ8mSJVBdWyUjDrx2dczsHA9uqx6pq8ODmKqsB37QYTE0Hxcxk_q_SsQ
        
      

## Diagramas

![spring-boot-security-login-jwt-flow](save-update-delete.drawio.png)
