# Blog

## Descripción
Aplicación Rest creada usando Java y el framework de Spring que permite subir entradas y comentarios simulando un blog. Los módulos de Sring utilizados fueron Web,Security y Data JPA (con una base de datos H2).
Para poner a prueba la aplicación fue usado Junit, MockMVC y Mockito.- 

## Tabla de Contenidos
- [Blog](#blog)
  - [Descripción](#descripción)
  - [Tabla de Contenidos](#tabla-de-contenidos)
  - [Funcionalidades](#funcionalidades)
    - [Entradas](#entradas)
    - [Categorias](#categorias)
  - [Comentarios](#comentarios)



## Funcionalidades

### Entradas

Está modelado a través de una clase con el mismo nombre y los siguientes atributos:
- tituloEntrada:
- contenido
- fechaDeCreación: se actualiza automáticamente al subir una nueva entrada y no se puede modificar posteriormente.
- fechaDeActualización: se actualiza automáticamente luego de modificar una entrada y no se puede modificar manualmente.
- categorias: las entradas se clasifican según el tema del que tratan para facilitar lu busqueda. Al crear o modificar una entrada se puede añadir o cambiar una o más categorias.
- comentarios: se actualiza cuando se crea un comentario relacionado a la entrada

Admite los siguientes metodos
-   GET ("/entradas"): devuelve todas las entradas
-   GET ("/entradsa/{id}): devuelve la entrada a la que le corresponda {id}
 -   POST ("/entradas): crea una entrada con los datos pasados
-   DELETE ("/entradas/{id}): borra la entrada a la que le corresponda el id pasado
- PUT ("/entradas/{id}): modifica la entrada correspondiente al id pasado
- PATCH ("/entradas/{id}): modifica parcialmente el id pasado

### Categorias

Tiene únicamente el siguiente atributo:
- nombre: no puede haber más de una categoría con e mismo nombre

## Comentarios

Está modelado a través de una clase con el mismo nombre y los siguientes atributos:
- contenido: contenido dentro del comentario
- fechaCreaci: se actualiza automáticamente al subir una nuevo comentario y no se puede modificar posteriormente.
- fechaActualizacion: se actualiza automáticamente luego de modificar un comentario y no se puede modificar manualmente.
- entrada: entrada donde realiza el comentario

Admite los siguientes metodos
-   GET ("/entradas/{entradaId}/comentario/{id}"): devuelve el comentario  con id {id} en la entrada 
 -   POST ("/entradas/{entradaId}/comentar"): crea un comentario en la entrada a la que le corresponde entradaId
-   DELETE ("/entradas/{entradaId}/comentario/{id}"): borra el comentario cuyo id es id en la entrada con id entradaID
- PUT ("/entradas/{entradaId}/comentario/{id}"): modifica la entrada correspondiente al id pasado
