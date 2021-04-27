La logica de los end-point del ejercicio de arrays esta en el componente ArrayController.java , asi como tambien la logica de los vectores A, numeros primos, B y el vector resultado
en la ruta  /springboot.restapitest/src/main/java/springboot/restapitest/RestController/

El servicio esta construido en Spring Boot version 1.5.4 
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.4.RELEASE)
 
Springboot al ser un sub proyecto de spring framework facilita la creación de servicios REST, también implementa el servidor de aplicaciones apache para su respectivo despliegue sin necesidad de configurar un servidor externo.

El patron de diseño utilizado obedece a 5 capas embebidas en package bien diferenciados para su respectiva comunicación secuencial.

springboot.restapitest.service => Implementación logica injectando la interface de JPA Repository para el CRUD contra base de datos MYSQL.
springboot.restapitest.model => Abstraccion del modelo a trabajar, en este caso el modelo se denomina ArrayEntity.java, también se almacenan modelos de Error de objetos en caso de error de respuesta
springboot.restapitest.repository => Interface que implementa JPA Repository
springboot.restapitest.RestController => Controlador del servicio que contiene todas las deifniciones de los path params para su respectivo uso, asi como tambien la definicion de End point del servicio get.

