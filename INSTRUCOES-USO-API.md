# Instruções para Testar a API REST no Postman

## 1. Importando a Coleção no Postman

Para testar esta API no Postman, siga estas instruções:

1. Abra o Postman.
2. Clique em "Import" na barra lateral esquerda do Postman.
3. Selecione o arquivo JSON da coleção que está na pasta raiz do projeto (`RESTAPIServerTask.postman_collection.json`).
4. Após importar a coleção, você verá todas as solicitações pré-definidas na coleção, incluindo GET, POST, PUT e DELETE para diferentes endpoints da API.

## 2. Configuração do Banco de Dados

Para que esta aplicação funcione corretamente, é necessário ter um banco de dados MySQL configurado. Siga as instruções abaixo para configurar o banco de dados:

1. Crie um banco de dados MySQL chamado `testdb`.
2. Abra o arquivo `application.properties` na pasta `src/main/resources` do projeto.
3. Atualize as seguintes propriedades para refletir as configurações do seu banco de dados:
    - `spring.datasource.username`: substitua `seu_username` pelo nome de usuário do seu banco de dados.
    - `spring.datasource.password`: substitua `sua_senha` pela senha correspondente ao seu banco de dados.

Exemplo de configuração para o banco MySQL:
```properties
# MySQL configuration
spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=seu_username
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
