# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-caso12)
  - [Recurso C1](#recurso-c1)
    - [GET /c1s](#GET-/c1s)
    - [GET /c1s/{id}](#GET-/c1s/{id})
    - [POST /c1s](#POST-/c1s)
    - [PUT /c1s/{id}](#PUT-/c1s/{id})
    - [DELETE /c1s/{id}](#DELETE-/c1s/{id})
    - [GET c1s/{c1sid}/c3](#GET-c1s/{c1sid}/c3)
    - [GET c1s/{c1sid}/c3/{c3id}](#GET-c1s/{c1sid}/c3/{c3id})
    - [POST c1s/{c1sid}/c3/{c3id}](#POST-c1s/{c1sid}/c3/{c3id})
    - [PUT c1s/{c1sid}/c3](#PUT-c1s/{c1sid}/c3)
    - [DELETE c1s/{c1sid}/c3/{c3id}](#DELETE-c1s/{c1sid}/c3/{c3id}])
  - [Recurso C2](#recurso-c2)
    - [GET /c2s](#GET-/c2s)
    - [GET /c2s/{id}](#GET-/c2s/{id})
    - [POST /c2s](#POST-/c2s)
    - [PUT /c2s/{id}](#PUT-/c2s/{id})
    - [DELETE /c2s/{id}](#DELETE-/c2s/{id})
    - [GET c2s/{c2sid}/c4](#GET-c2s/{c2sid}/c4)
    - [GET c2s/{c2sid}/c4/{c4id}](#GET-c2s/{c2sid}/c4/{c4id})
    - [POST c2s/{c2sid}/c4/{c4id}](#POST-c2s/{c2sid}/c4/{c4id})
    - [PUT c2s/{c2sid}/c4](#PUT-c2s/{c2sid}/c4)
    - [DELETE c2s/{c2sid}/c4/{c4id}](#DELETE-c2s/{c2sid}/c4/{c4id}])
  - [Recurso C3](#recurso-c3)
    - [GET /c3s](#GET-/c3s)
    - [GET /c3s/{id}](#GET-/c3s/{id})
    - [POST /c3s](#POST-/c3s)
    - [PUT /c3s/{id}](#PUT-/c3s/{id})
    - [DELETE /c3s/{id}](#DELETE-/c3s/{id})
  - [Recurso C4](#recurso-c4)
    - [GET /c4s](#GET-/c4s)
    - [GET /c4s/{id}](#GET-/c4s/{id})
    - [POST /c4s](#POST-/c4s)
    - [PUT /c4s/{id}](#PUT-/c4s/{id})
    - [DELETE /c4s/{id}](#DELETE-/c4s/{id})

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /Caso12.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación Caso12
### Recurso C1
El objeto C1 tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    number: '' /*Tipo Integer*/,
    date: '' /*Tipo Date*/
}
```




#### GET /c1s

Retorna una colección de objetos C1 en representación Detail.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-c1)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /c1s/{id}

Retorna una colección de objetos C1 en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C1 a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto C1 en [representaciones Detail](#recurso-c1)
404|No existe un objeto C1 con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /c1s

Es el encargado de crear objetos C1.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto C1 que será creado|Sí|[Representación Detail](#recurso-c1)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto C1 ha sido creado|[Representación Detail](#recurso-c1)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto C1|Mensaje de error

#### PUT /c1s/{id}

Es el encargado de actualizar objetos C1.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C1 a actualizar|Sí|Integer
body|body|Objeto C1 nuevo|Sí|[Representación Detail](#recurso-c1)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto C1 actualizado|[Representación Detail](#recurso-c1)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto C1|Mensaje de error

#### DELETE /c1s/{id}

Elimina un objeto C1.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C1 a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

#### GET c1s/{c1sid}/c3

Retorna una colección de objetos C3 asociados a un objeto C1 en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C1 a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos C3 en [representación Detail](#recurso-c3)
500|Error consultando c3 |Mensaje de error

#### GET c1s/{c1sid}/c3/{c3id}

Retorna un objeto C3 asociados a un objeto C1 en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
c1sid|Path|ID del objeto C1 a consultar|Sí|Integer
c3id|Path|ID del objeto C3 a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto C3 en [representación Detail](#recurso-c3)
404|No existe un objeto C3 con el ID solicitado asociado al objeto C1 indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST c1s/{c1sid}/c3/{c3id}

Asocia un objeto C3 a un objeto C1.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
c1sid|PathParam|ID del objeto C1 al cual se asociará el objeto C3|Sí|Integer
c3id|PathParam|ID del objeto C3 a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto C3 asociado|[Representación Detail de C3](#recurso-c3)
500|No se pudo asociar el objeto C3|Mensaje de error

#### PUT c1s/{c1sid}/c3

Es el encargado de remplazar la colección de objetos C3 asociada a un objeto C1.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
c1sid|Path|ID del objeto C1 cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos C3|Sí|[Representación Detail](#recurso-c3)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se remplazó la colección|Colección de objetos C3 en [Representación Detail](#recurso-c3)
500|No se pudo remplazar la colección|Mensaje de error

#### DELETE c1s/{c1sid}/c3/{c3id}

Remueve un objeto C3 de la colección en un objeto C1.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
c1sid|Path|ID del objeto C1 asociado al objeto C3|Sí|Integer
c3id|Path|ID del objeto C3 a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error



[Volver arriba](#tabla-de-contenidos)
### Recurso C2
El objeto C2 tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    test: '' /*Tipo String*/,
    number: '' /*Tipo Integer*/
}
```




#### GET /c2s

Retorna una colección de objetos C2 en representación Detail.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-c2)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /c2s/{id}

Retorna una colección de objetos C2 en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C2 a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto C2 en [representaciones Detail](#recurso-c2)
404|No existe un objeto C2 con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /c2s

Es el encargado de crear objetos C2.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto C2 que será creado|Sí|[Representación Detail](#recurso-c2)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto C2 ha sido creado|[Representación Detail](#recurso-c2)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto C2|Mensaje de error

#### PUT /c2s/{id}

Es el encargado de actualizar objetos C2.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C2 a actualizar|Sí|Integer
body|body|Objeto C2 nuevo|Sí|[Representación Detail](#recurso-c2)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto C2 actualizado|[Representación Detail](#recurso-c2)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto C2|Mensaje de error

#### DELETE /c2s/{id}

Elimina un objeto C2.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C2 a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error

#### GET c2s/{c2sid}/c4

Retorna una colección de objetos C4 asociados a un objeto C2 en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C2 a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos C4 en [representación Detail](#recurso-c4)
500|Error consultando c4 |Mensaje de error

#### GET c2s/{c2sid}/c4/{c4id}

Retorna un objeto C4 asociados a un objeto C2 en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
c2sid|Path|ID del objeto C2 a consultar|Sí|Integer
c4id|Path|ID del objeto C4 a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto C4 en [representación Detail](#recurso-c4)
404|No existe un objeto C4 con el ID solicitado asociado al objeto C2 indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST c2s/{c2sid}/c4/{c4id}

Asocia un objeto C4 a un objeto C2.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
c2sid|PathParam|ID del objeto C2 al cual se asociará el objeto C4|Sí|Integer
c4id|PathParam|ID del objeto C4 a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto C4 asociado|[Representación Detail de C4](#recurso-c4)
500|No se pudo asociar el objeto C4|Mensaje de error

#### PUT c2s/{c2sid}/c4

Es el encargado de remplazar la colección de objetos C4 asociada a un objeto C2.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
c2sid|Path|ID del objeto C2 cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos C4|Sí|[Representación Detail](#recurso-c4)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se remplazó la colección|Colección de objetos C4 en [Representación Detail](#recurso-c4)
500|No se pudo remplazar la colección|Mensaje de error

#### DELETE c2s/{c2sid}/c4/{c4id}

Remueve un objeto C4 de la colección en un objeto C2.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
c2sid|Path|ID del objeto C2 asociado al objeto C4|Sí|Integer
c4id|Path|ID del objeto C4 a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error



[Volver arriba](#tabla-de-contenidos)
### Recurso C3
El objeto C3 tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    c1: {
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    number: '' /*Tipo Integer*/,
    date: '' /*Tipo Date*/    }
}
```



#### GET /c3s

Retorna una colección de objetos C3 en representación Detail.
Cada C3 en la colección tiene embebidos los siguientes objetos: C1.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-c3)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /c3s/{id}

Retorna una colección de objetos C3 en representación Detail.
Cada C3 en la colección tiene los siguientes objetos: C1.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C3 a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto C3 en [representaciones Detail](#recurso-c3)
404|No existe un objeto C3 con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /c3s

Es el encargado de crear objetos C3.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto C3 que será creado|Sí|[Representación Detail](#recurso-c3)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto C3 ha sido creado|[Representación Detail](#recurso-c3)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto C3|Mensaje de error

#### PUT /c3s/{id}

Es el encargado de actualizar objetos C3.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C3 a actualizar|Sí|Integer
body|body|Objeto C3 nuevo|Sí|[Representación Detail](#recurso-c3)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto C3 actualizado|[Representación Detail](#recurso-c3)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto C3|Mensaje de error

#### DELETE /c3s/{id}

Elimina un objeto C3.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C3 a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error



[Volver arriba](#tabla-de-contenidos)
### Recurso C4
El objeto C4 tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    c2: {
    test: '' /*Tipo String*/,
    number: '' /*Tipo Integer*/    }
}
```



#### GET /c4s

Retorna una colección de objetos C4 en representación Detail.
Cada C4 en la colección tiene embebidos los siguientes objetos: C2.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-c4)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /c4s/{id}

Retorna una colección de objetos C4 en representación Detail.
Cada C4 en la colección tiene los siguientes objetos: C2.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C4 a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto C4 en [representaciones Detail](#recurso-c4)
404|No existe un objeto C4 con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /c4s

Es el encargado de crear objetos C4.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto C4 que será creado|Sí|[Representación Detail](#recurso-c4)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto C4 ha sido creado|[Representación Detail](#recurso-c4)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto C4|Mensaje de error

#### PUT /c4s/{id}

Es el encargado de actualizar objetos C4.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C4 a actualizar|Sí|Integer
body|body|Objeto C4 nuevo|Sí|[Representación Detail](#recurso-c4)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto C4 actualizado|[Representación Detail](#recurso-c4)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto C4|Mensaje de error

#### DELETE /c4s/{id}

Elimina un objeto C4.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto C4 a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error



[Volver arriba](#tabla-de-contenidos)
