# Development Project ToDo

# Back-end Test

---

## 🔃 Project Lifecycle

---

### **📁** Entity and Repository

- [x]  Criar entidade Pessoa
- [x]  Criar entidade de Endereço
- [x]  Mapear a relação das tabelas Pessoa e Endereço
- [x]  Criar Repositório de Pessoas
- [x]  Criar Repositório de Endereço

### **📁 Services**

- [x]  Criar uma pessoa
- [x]  Editar pessoa
- [x]  Consultar pessoa
- [x]  Listar pessoas
- [ ]  Criar endereço pessoa
- [ ]  Listar endereços da pessoa
- [x]  Poder informar qual principal endereço principal da pessoa

## 🚀 How to use

### Person Endpoint (”http://localhost8080:/api/person”)

### Create Person: POST

```json
{
    "name": "Pedro Sousa das Neves",
    "birthDate":"01/02/2000",
    "mainAddress": {
        "streetAddress": "Avenida Brigadeiro Eduardo Gomes",
        "number": 281,
        "city": "Rondonópolis",
        "zipCode": "78705-170"
    }
}
```

## Returns

## Created 201

```json
{
    "id": "ec417442-adba-43ee-b4f3-10930ac4c9e0",
    "name": "Pedro Sousa das Neves",
    "birthDate": "2000-02-01",
    "mainAddress": {
        "id": "ad9137df-c3ed-414a-ab37-a42b64852cc8",
        "streetAddress": "Avenida Brigadeiro Eduardo Gomes",
        "number": 281,
        "city": "Rondonópolis",
        "zipCode": "78705-170"
    }
}
```

## Bad Request 400 :/

```json
{
    "name": "Pedro Sousa das Neves"
}
```

```json
{
    "title": "Invalid Field(s). Check api docs",
    "status": 400,
    "details": "org.springframework.web.bind.MethodArgumentNotValidException",
    "message": "O campo data de nascimento não pode ser vazio.",
    "timestamp": "2023-02-02T23:39:17.3871754",
    "fields": "birthDate"
}
```

### With default exception messages.

### Find All Person: GET

### Returns

```json
[
    {
        "id": "cd4b3939-ce9e-4d94-93cf-3f3c470f007a",
        "name": "Luan Anthony da Rocha",
        "birthDate": "1971-01-03",
        "mainAddress": {
            "id": "61cad23a-88dd-49cb-af1f-2535a4d078aa",
            "streetAddress": "Avenida Brigadeiro Eduardo Gomes",
            "number": 281,
            "city": "Rondonópolis",
            "zipCode": "78705-170"
        }
    },
    {
        "id": "e5968302-c6c0-4263-bbea-5dc148c28efb",
        "name": "Emilly Adriana Santos",
        "birthDate": "2000-02-02",
        "mainAddress": {
            "id": "2796d77a-bf76-437f-b5ed-fc0636017c37",
            "streetAddress": "Avenida Brigadeiro Eduardo Gomes",
            "number": 281,
            "city": "Rondonópolis",
            "zipCode": "78705-170"
        }
    },
    {
        "id": "e5324ad1-d232-4b20-aaa1-6aeb5b1af399",
        "name": "Pedro Santos",
        "birthDate": "2004-06-04",
        "mainAddress": {
            "id": "648a87e8-3c5a-45db-995f-7ac1e194cde4",
            "streetAddress": "Travessa Parapitinga",
            "number": 572,
            "city": "Aracaju",
            "zipCode": "49047-430"
        }
    },
    {
        "id": "1c854478-298d-4771-be05-265d2e4da6ce",
        "name": "Andreia Aline Isadora das Neves",
        "birthDate": "1963-02-01",
        "mainAddress": {
            "id": "c0e663da-7b94-4154-a012-a0c3965ef78b",
            "streetAddress": "Beco das Flores",
            "number": 269,
            "city": "Belo Horizonte",
            "zipCode": "31872-087"
        }
    },
    {
        "id": "0923062b-d073-4cb5-9d7a-abe99bb278d3",
        "name": "Pedro Sousa das Neves",
        "birthDate": "2000-02-01",
        "mainAddress": null
    },
    {
        "id": "e9ce7c45-c3cf-4a1d-87fa-90d26921e798",
        "name": "Pedro Sousa das Neves",
        "birthDate": "2000-02-01",
        "mainAddress": {
            "id": "61cad23a-88dd-49cb-af1f-2535a4d078aa",
            "streetAddress": "Avenida Brigadeiro Eduardo Gomes",
            "number": 281,
            "city": "Rondonópolis",
            "zipCode": "78705-170"
        }
    },
    {
        "id": "ec417442-adba-43ee-b4f3-10930ac4c9e0",
        "name": "Pedro Sousa das Neves",
        "birthDate": "2000-02-01",
        "mainAddress": {
            "id": "ad9137df-c3ed-414a-ab37-a42b64852cc8",
            "streetAddress": "Avenida Brigadeiro Eduardo Gomes",
            "number": 281,
            "city": "Rondonópolis",
            "zipCode": "78705-170"
        }
    }
]
```

### Or…

```json
[]
```

### Find One Person By ID: GET (localhost:8080/api/person/{id}

### Returns

```json
{
    "id": "1c854478-298d-4771-be05-265d2e4da6ce",
    "name": "Andreia Aline Isadora das Neves",
    "birthDate": "1963-02-01",
    "mainAddress": {
        "id": "c0e663da-7b94-4154-a012-a0c3965ef78b",
        "streetAddress": "Beco das Flores",
        "number": 269,
        "city": "Belo Horizonte",
        "zipCode": "31872-087"
    }
}
```

### Or

```json
{
    "title": "Bad Request. Check api docs",
    "status": 400,
    "details": "com.lucas.attornatustest.exception.bad_request.BadRequestException",
    "message": "Não existe nenhuma pessoa com esse id.",
    "timestamp": "2023-02-02T23:43:45.7077852"
}
```

### Find All Addresses By Person: GET (localhost:8080/api/person/address{id}

### Returns

```json
[
    {
        "id": "c0e663da-7b94-4154-a012-a0c3965ef78b",
        "streetAddress": "Beco das Flores",
        "number": 269,
        "city": "Belo Horizonte",
        "zipCode": "31872-087"
    },
    {
        "id": "d811b9cd-5279-499e-a397-168c48d701a0",
        "streetAddress": "Servidão Almerinda Batista Cavalcante",
        "number": 619,
        "city": "Vitória",
        "zipCode": "29043-352"
    }
]
```

### Or

```json
{
    "title": "Bad Request. Check api docs",
    "status": 400,
    "details": "com.lucas.attornatustest.exception.bad_request.BadRequestException",
    "message": "Não existe nenhuma pessoa com esse id.",
    "timestamp": "2023-02-02T23:45:04.8567762"
}
```

### Update Person: PUT (locahost:8080/api/person/{id}

```json

{
    "Id": "1c854478-298d-4771-be05-265d2e4da6ce",
    "name": "Andreia Aline Isadora das Neves",
    "birthDate": "1963-02-01",
    "mainAddress": {
        "id": "71430872-f6bd-4e52-a14b-85fd670ee83e",
        "streetAddress": "Beco das Flores",
        "number": 269,
        "city": "Belo Horizonte",
        "zipCode": "31872-087"
    }
}
```

```json
"PathVariable" "Id" "1c854478-298d-4771-be05-265d2e4da6ce"
{
    "name": "Pedro Silva",
    "birthDate": "04/12/2004",
    "mainAddress": {
        "id": "71430872-f6bd-4e52-a14b-85fd670ee83e",
        "streetAddress": "Beco das Flores",
        "number": 269,
        "city": "Belo Horizonte",
        "zipCode": "31872-087"
    }
}
```

### Returns

```json
{
    "id": "1c854478-298d-4771-be05-265d2e4da6ce",
    "name": "Pedro Silva",
    "birthDate": "2004-12-04",
    "mainAddress": {
        "id": "bcba082b-d68d-46fa-a190-5bc4280f540b",
        "streetAddress": "Beco das Flores",
        "number": 269,
        "city": "Belo Horizonte",
        "zipCode": "31872-087"
    }
}
```

### Address Endpoint (”http://localhost:8080/api/address”)

### Create Address: POST

### Request Body

```json
{
    "streetAddress": "Travessa Maria Edite Linhares",
    "number": 667,
    "city": "Sobral",
    "zipCode": "62031-066",
    "personId":"cd4b3939-ce9e-4d94-93cf-3f3c470f007a"
}
```

### Response

```json
{
    "streetAddress": "Travessa Maria Edite Linhares",
    "number": 667,
    "city": "Sobral",
    "zipCode": "62031-066",
    "personId":"cd4b3939-ce9e-4d94-93cf-3f3c470f007a"
}
```

### Exception

```json
{
    "title": "Invalid Field(s). Check api docs",
    "status": 400,
    "details": "org.springframework.web.bind.MethodArgumentNotValidException",
    "message": "O id da pessoa (dona do endereço) é obrigatório.",
    "timestamp": "2023-02-02T23:51:59.2181673",
    "fields": "personId"
}
```