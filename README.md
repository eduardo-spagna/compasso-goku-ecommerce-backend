# Goku Ecommerce - Compasso UOL

## Requisitos da entrega

* Cadastrar endereços
* Consultar endereços através do CEP
* Cadastrar usuários
* Autenticação de usuários

Considerar:
* Utilizar cache para melhorar a performance
* Não permitir o acesso de usuários não autorizados

## Design da aplicação

No projeto foram utilizados os padrões de projeto:
 - Singleton
    - para permitir que uma classe tenha apenas uma instância, como as classes do diretório "repositories" que acessam e realizam consultas no banco de dados, ou também as classes do diretório "service" que realizam as ações da regra de negócio, por exemplo.
 - Repository 
    - para permitir um encapsulamento da lógica de acesso a dados, seguindo assim o princípio da persistência ignorante, onde a forma que os dados são persistidos não cause impacto na regra de negócio.

Uma abordagem adotada para a definição e validação dos dados recebidos e definição dos dados retornados pela API foi a utilização de DTO's (data transfer objects), visto que em muitos outros casos utilizam-se dos models para receber e retornar os dados da aplicação, mas que não considerei viável para este projeto.

## Rodar o projeto com o Docker ou localmente com o ambiente configurado

### Docker

Pré Requisitos
- [Docker](https://www.docker.com)
- [Docker Compose](https://docs.docker.com/compose/install/)

Basta executar o comando:

```
docker-compose up --build -d
```

A API e o Banco de Dados já estarão configurados e prontos para utilização.

### Localmente com ambiente configurado

Primeiramente será necessário configurar as variáveis de ambiente. Para isso, no arquivo que se encontra no diretório "src\main\resources\application-dev.properties" há um exemplo com variáveis de ambiente. Basta copiar os dados desse arquivo e colar outro arquivo do mesmo diretório chamado "application.properties".

Em seguida, será necessário configurar um banco de dados localmente e informar as credenciais corretas no arquivo "application.properties".

A API pode ser iniciada clicando com o botão direito do mouse e "Run" na classe "src\main\java\com\compassouol\gokuecommerce\GokuEcommerceApplication.java".

### Link e documentação da API

Após rodar o projeto com o Docker ou com o ambiente configurado localmente, a API estará disponível em http://localhost:8080/ e a sua documentação com o Swagger estará disponível em http://localhost:8080/swagger-ui/#/

## Banco de Dados

As migrações e documentação do banco de dados se encontram no diretório "src\main\resources\database" desse projeto.

![DB](https://user-images.githubusercontent.com/37259280/110207540-e5122b00-7e62-11eb-8966-185c087500f3.png)

## Ferramentas

### Swagger

O Swagger é uma ferramenta para documentação de API's. Ela foi utilizada nesse projeto pela sua facilidade de integração e também pela garantia de que a API sempre estará com sua documentação de endpoints atualizada, uma vez que não é necessário salvar tal documentação em arquivos externos com risco de esquecimento, por exemplo.

Link para documentação: https://swagger.io/

### Flyway DB

O Flyway é uma ferramenta de migração de banco de dados. Ela foi utilizada nesse projeto para tornar explícitas todas as alterações no banco de dados como: criação de tabelas, inserções, alterações em atributos, etc...

Após configurar as variáveis de ambiente necessárias, a ferramenta buscará por códigos .sql para realizar as migrações. Tais códigos podem ser encontrados no diretório "src\main\resources\database\migrations".

Link para documentação: https://flywaydb.org/

### Lombok

O Lombok é uma biblioteca Java focada em produtividade e redução de código. Ela foi utilizada nesse projeto para reduzir "códigos sujos" como getters, setters, toString, equals, etc...

Link para documentação: https://projectlombok.org/

### Ehcache

O Ehcache é um cache distribuído Java de código aberto para armazenamento em cache de uso geral.

Link para documentação: https://www.ehcache.org/