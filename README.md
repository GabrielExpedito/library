=============================

COMO RODAR A APLICAÇÃO

==============================

Para rodar essa aplicação em Java é necessário ter a classe library.main, onde a IDE automáticamente irá copiar todos os arquivos JAR


**Configuração do Banco de dados:**

Porém, antes é necessário configurar seu banco de dados dentro do arquivo que está no caminho: 

  - library / target / classes / META-INF / persistence.xml

Nesse arquivo será preciso informar a URL do JDBC, Usuário do banco e sua Senha caso tenha. Importante se atentar de que toda a configuração dessa aplicação foi pensada para ser usada com PostgreSQL.

**Baixar as dependências do Maven:**

A aplicação possuí dependências externas configuradas dentro do arquivo pom.xml, antes de inicializar a aplicação garanta que as dependências foram baixadas corretamente:

Caso esteja usando a IDE Netbeans: basta rodar o comando Clean and Build

Caso queira rodar por comando utilize:

  - mvn clean install

**Tabelas do Banco de dados: **

Importante possuir a tabela no seu banco de dados correta para que possa ter uma comunicação eficiente ente a aplicação e o banco de dados, abaixo está a estrutura da tabela necessária em código para ser rodado:



create table livro(

    id serial primary key,
    titulo varchar(255) not null,
    autor varchar(100) not null,
    dataPublicacao date,
    isbn varchar(100) not null,
    editora varchar(100) not null,
    classificacao varchar(100) not null
);

**Para rodar a aplicação**

Se for utilizar um IDE ex: Netbeans
  - Abra o projeto
  - Procure a classe library.main
  - Clique em Run

Se for utilizar via terminal:
  - java -jar target/library-1.0-SNAPSHOT.jar  
