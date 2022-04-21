#Mutantes - Mercadolibre

Examen entrevista para Mercado Libre

Se deployo la aplicacion en Heroku:

#Heroku
* URL = https://mutantes-meli.herokuapp.com/

### Implementacion y tecnologias usadas

- SpringBoot
- HSQLDB (en memoria)
- jUnit 4
- MAVEN
- Lombok
- Tomcat (embebido)


## Setup

### Instrucciones
Para compilar y ejecutar proyecto es necesario contar con la version 1.8 de la JDK y Maven para la gestion de las dependencias.

Se guardo un .json de la collection de Postman dentro del proyecto.

### Uso


```
mvn spring-boot:run
```

### API Url

URL local: http://localhost:8081
URL Heroku https://mutantes-meli.herokuapp.com/

### Servicios
#### Es mutante

Request:
- POST https://mutantes-meli.herokuapp.com/mutant/

Request body (caso dna mutante):

```
  {"dna": ["TGGGCC","CTTTAG","TATGTC","ATCTTC","TCCCCT","CAAGTC"]}
```

Response 200 OK:

```
  {"mutant": true}
```
Request body (caso dna humano):

```
  {"dna":["AATACT", "CCCAGA", "GGGATT", "AATTCC", "GGATCG", "TCACTG"]}
```

Response 403 Forbidden:

```
  {"mutant": false}
```

Request body (caso dna Invalido):

```
  {"dna":["AAA", "TTT"]}
```

Response 400 Bad Request

#### Estadisticas

Request:
- GET https://mutantes-meli.herokuapp.com/mutant/stats

Response: 200 (application/json)

```
{
    "countMutantDna": 1,
    "countHumanDna": 1,
    "ratio": 0.5
}
```
#### Obtener todos los registros
Este servicio lo agregue a modo de prueba para que vean como se persisten los objetos en la BD
- GET https://mutantes-meli.herokuapp.com/mutant/

Response: 200 (application/json)
```
[{
"id": 1,
"dna": ["TGGGCC","CTTTAG","TATGTC","ATCTTC","TCCCCT","CAAGTC"],
"amountMutantSequences": 3,
"mutant": true
},
{
"id": 2,
"dna": ["TGGGCC","CTTTAG","TATGTC","ATCTTC","TCAACT","CAAGTC"],
"amountMutantSequences": 0,
"mutant": false
}
```
#### Obtener Sequencia Random
- GET https://mutantes-meli.herokuapp.com/mutant/random

Este servicio se implemento a modo de prueba para que puedan probar con diferentes secuencias facilmente a modo de prueba.

Response: 201 (application/json)
```
["GTCAG","GGTTC","TGTAG","CATTT","TTCTA"]
```