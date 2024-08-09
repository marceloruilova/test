# Microservices Application

Este proyecto es una aplicación de microservicios que incluye los siguientes componentes:

- **Gateway**: Maneja el enrutamiento de las solicitudes.
- **Service**: Provee lógica de negocio.
- **Movement**: Administra los movimientos de datos.
- **Client**: Interfaz para los clientes.

Cada microservicio cuenta con su propio archivo `docker-compose.yml` para su despliegue.

## Requisitos

- Docker
- Docker Compose
- JDK 11+

## Instrucciones de Ejecución

1. **Clona el repositorio:**

   git clone <URL_DEL_REPOSITORIO>
   cd project-root

2. **Navega a cada microservicio:**

   Desde el directorio raíz del proyecto, puedes navegar a cada microservicio y ejecutar el `docker-compose.yml` correspondiente.

   **Gateway:**

   cd gateway/src
   docker-compose up -d

   **Service:**

   cd ../../service/src
   docker-compose up -d

   **Movement:**

   cd ../../movement/src
   docker-compose up -d

   **Client:**

   cd ../../client/src
   docker-compose up -d

3. **Verifica el estado de los microservicios:**

   Después de ejecutar cada microservicio, verifica que todos estén corriendo correctamente:

   docker ps

4. **Llamar a cada Microservicio:**

   A continuación, se muestra cómo puedes configurar y llamar a cada microservicio:

    - **Gateway:**

      server.port=8091
      spring.application.name=gateway
      eureka.client.service-url.defaultZone=http://localhost:8761/eureka
      eureka.instance.hostname=localhost
      spring.cloud.compatibility-verifier.enabled=false
      spring.main.web-application-type=reactive

      spring.cloud.gateway.routes[0].id=person
      spring.cloud.gateway.routes[0].uri=lb://person
      spring.cloud.gateway.routes[0].predicates=Path=/api/person/**
      spring.cloud.gateway.routes[0].filters=StripPrefix=2

      spring.cloud.gateway.routes[1].id=movement
      spring.cloud.gateway.routes[1].uri=lb://movement
      spring.cloud.gateway.routes[1].predicates=Path=/api/movement/**
      spring.cloud.gateway.routes[1].filters=StripPrefix=2

      Para llamar a los microservicios a través del Gateway:

        - **Person Service**:  
          GET http://localhost:8091/api/person/{id}  
          Reemplaza `{id}` con el identificador correspondiente.

        - **Movement Service**:  
          GET http://localhost:8091/api/movement/{id}  
          Reemplaza `{id}` con el identificador correspondiente.

## Apagar la Aplicación

Para detener y eliminar los contenedores, puedes ejecutar:

docker-compose down

Asegúrate de correr este comando en la carpeta de cada microservicio para detener todos los contenedores asociados.

## Notas Adicionales

- Si necesitas realizar cambios en el código, asegúrate de reconstruir las imágenes de Docker:

  docker-compose build

- En caso de problemas, revisa los logs de los contenedores con:

  docker logs <NOMBRE_DEL_CONTENEDOR>
