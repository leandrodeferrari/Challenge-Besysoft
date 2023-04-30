# TIENDA DE PRODUCTOS

## Enunciado:

Deberá implementarse la funcionalidad básica de una tienda de productos:
- Se podrán almacenar productos (código, nombre, precio, categoría), vendedor (código, nombre, sueldo).
- Al registrar una venta, se tendrá que relacionar el producto con el vendedor que realizo la venta.
- Se debe de poder calcular la comisión de ventas por cada vendedor, el cual se obtiene de un 5% de las ventas realizadas
en el caso de vender hasta dos productos y un 10% al vender más de dos productos.
- Debe implementarse distintos tipos de buscadores de productos, por ejemplo el buscar por categoría.
- La aplicación tendrá que implementar el manejo de excepciones correctamente.
- Deberá diseñarse un Diagrama de Entidad Relación para la solución.
- Deberá ejecutarse por consola y se almacenarán los datos en memoria.

## ESPECIFICACIONES:

- Java 17.
- H2, para los datos en memoria.

## ACLARACIONES:

###### Cuando se ejecute el proyecto, les van a aparecer estos logs, al conectarse a la base de datos (en este caso, en H2, pero he visto que también sucede utilizando bases de datos físicas. Por ejemplo, MySQL).

![](https://res.cloudinary.com/dwirzebm8/image/upload/v1682871197/Captura_de_pantalla_600_q6vuna.png)

###### La aplicación de consola, igualmente, funciona lo más bien (Como se puede ver en la imagen de abajo). Pero, aún no he descubierto el porqué de esos logs.

![](https://res.cloudinary.com/dwirzebm8/image/upload/v1682871586/Captura_de_pantalla_602_bgyxuf.png)

## DIAGRAMA ENTIDAD-RELACIÓN

![](https://res.cloudinary.com/dwirzebm8/image/upload/v1682872556/Captura_de_pantalla_604_fxscgr.png)
