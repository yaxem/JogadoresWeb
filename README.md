# Documentação

## Jogadores

### GET
```
/pagamento/jogadores/ 
```
obter todos os jogadores, e seus respectivos pagamentos

```
/pagamento/jogadores/{id}
```

obter um jogador por id

```
/pagamento/pagamentos/ano/{ano} 
```

obter pagamentos por ano

### POST

``` 
/pagamento/jogadores/
```
cadastrar novo jogador

### PUT

``` 
/pagamento/jogadores/{id}
```
editar um jogador por id

### DELETE

```
/pagamento/jogadores/{id} 
```
deletar um jogador por id

```
/pagamento/jogadores/ 
```
deletar todos os jogadores

## Pagamentos

### GET

```
/pagamento/pagamentos/ 
```
obter todos os pagamentos

```
pagamento/pagamentos/{id} 
```
obter um pagamento por id

### POST

```
/pagamento/pagamentos/ 
```
cadastrar novo pagamento

### PUT

```
/pagamento/pagamentos/{id} 
```
editar um pagamento por id 

### DELETE

```
/pagamento/pagamentos/{id} 
```
deletar um pagamento por id

```
/pagamento/pagamentos/ 
```
deletar todos os pagamentos

**Autores:**  
- Gustavo Burgath  
- Ricardo Santolaia  
- Gabriel Fontes Bay

**Requisitos técnicos:**
- Java 21 (ou superior)
- PostgreSQL
- Spring Boot 3 (Spring Web MVC, Spring Data JPA, Developer Tools, PostgreSQL Driver)
- Maven 3.8 (ou superior)

**Requisitos de software:**
- Cada jogador possui um id, título, e-mail e data de nascimento. Segue exemplo de do corpo de uma requisição em json:
```exemplo do jogador:
{
  "nome": "claudio",
  "email": "claudio@gmail.com",
  "datanasc": "221198"
}
```
- Cada pagamento possui um id, ano, mês e o id do jogador a quem pertence o pagamento. Segue exemplo:
```
{
    "ano": 2023,
    "mes": 10,
    "valor": 100.0,
    "jogador": {
        "codJogador": 1
    }
}
```
