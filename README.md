# Prices REST API

## Desarrollador

* **Nombre:** Juan José Puchol Marchuet
* **Email:** chuano@gmail.com

## Descripción

Se ha implementado una API REST que permite consultar los precios de un producto en una cadena para la fecha dada.

El framework utilizado es Spring Boot 3.1.4 con Java 17. Se han utilizado estas versiones por ser las versiones por 
defecto de Spring Initializr, en caso de querer utilizar Java 11 debería cambiarse a la versión 2.7.* de Spring Boot, 
pues la versión 3 requiere Java 17, además de sustituir los records por clases normales.

Se ha optado por un único endpoint que recibe el identificador de producto, el identificador de cadena y la fecha a
través de parámetros por query string.

## Formato de parámetros

* **brandId:** Identificador de la cadena.
* **productId:** Identificador del producto.
* **date:** Fecha UTC de aplicación del precio en formato ISO 8601 (yyyy-MM-dd'T'HH:mm:ss).

```shell
http://localhost:3001/api/v1/price?brandId=1&productId=35455&date=2020-06-14T16:00:00
```

## Arquitectura

En cuanto a arquitectura, se ha seguido el patrón de arquitectura hexagonal, con una capa de dominio, una capa de
aplicación y una capa de infraestructura, respetando las reglas de dependencia entre ellas.

Aunque el propósito principal de la prueba no sea este, se ha modelado la entidad Price haciendo uso de Value Objects,
proporcionando una mejor semántica y una agrupación de propiedades más cohesiva, así como permitiendo llevar la
validación de los datos a estos.

## Decisiones de desarrollo

Por cuestiones de practicidad, se ha asumido el compromiso del uso de algunas anotaciones del framework en la capa de
aplicación, como por ejemplo @Service, entendiendo que este acoplamiento al framework es un coste asumible y que podŕia
reemplazarse por una anotación propia que eliminase esta dependencia. Del mismo modo, se ha hecho uso de la librería
Lombok para reducir la verbosidad del código, asumiendo ese acoplamiento por productividad.

Estas dos decisiones deberían tomarse en equipo y seguir el consenso.

## Ejecución

La aplicación se ejecuta en el puerto 3001, aunque dicho puerto puede cambiarse en el fichero
[application.properties](src/main/resources/application.properties).

```shell
./mvnw spring-boot:run
```

Se incluye un fichero Dockerfile para la creación de una imagen docker de la aplicación.

```shell
docker build -t prices-rest-api .
docker run -p 3001:3001 prices-rest-api
```

## Testing

Se han realizado tests unitarios y de integración que pueden ejecutarse con el comando:

```shell
./mvnw test
```

Además, se incluye una colección de Postman con tests e2e en el fichero [Get_Price.postman_collection.json](postman/Get_Price.postman_collection.json).

## Documentación

Se incluye la documentación en formato OpenAPI 3, generada mediante springdoc-openapi.

La descripción del API puede encontrarse, una vez levantado el proyecto, en la
url http://localhost:3001/api/v1/api-docs.

Así mismo, puede consultarse la documentación de forma interactiva en con swagger-ui en la
url http://localhost:3001/swagger-ui/index.html.

## Enunciado

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final
(pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un
ejemplo de la tabla con los campos relevantes:

### PRECIOS

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|---------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

**Campos**:

* **BRAND_ID**: foreign key de la cadena del grupo (1 = ZARA).
* **START_DATE, END_DATE**: rango de fechas en el que aplica el precio tarifa indicado.
* **PRICE_LIST**: Identificador de la tarifa de precios aplicable.
* **PRODUCT_ID**: Identificador código de producto.
* **PRIORITY**: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de
  mayor prioridad (mayor valor numérico).
* **PRICE**: precio final de venta.
* **CURR**: iso de la moneda.</br></br>

**Se pide:**

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.

Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de 
aplicación y precio final a aplicar. 

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el
nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los 
mismos).

Desarrollar unos test al endpoint rest que validen las siguientes peticiones al servicio con los datos del ejemplo:

* Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
* Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
* Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
* Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
* Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)

**Se valorará:**

* Diseño y construcción del servicio.
* Calidad de Código.
* Resultados correctos en los test.