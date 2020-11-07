# soaint
Evaluacion
- La base de datos utilizada es Mysql 8.0
- Los scripts para la base de datos estan incluidos en el repositorio carpeta "scriptbd" en la raiz.
- Los usuarios que están en la BD tienen un mismo password, que es "123456" se listan a continuación:
    - danticona
    - mcanepa
- Actualmente funciona el CRUD de clientes, para actualizar o crear un nuevo cliente, debe usar el mismo método, el sistema valida si el ID del cliente existe, entonces actualiza los datos, si no existe, crea un cliente nuevo.
- Las rutas para el sistema son las siguientes:
  - Login: POST http://localhost:9191/v1/login
  - Listar clientes: GET http://localhost:9191/v1/cliente
  - Encontrar cliente por ID: GET http://localhost:9191/v1/cliente/1
  - Actualizar o crear cliente:  POST http://localhost:9191/v1/cliente/
- En el repositorio tambien encontrará un archivo JSON en donde estan los ejemplos utilizados con Postman, se encuentra en la carpeta "Postman-json" de la raiz.
