[![Build Status](https://app.travis-ci.com/rafaelsulimann/spring-mongodb-dockerfile-dockercompose.svg?branch=main)](https://app.travis-ci.com/rafaelsulimann/spring-mongodb-dockerfile-dockercompose)
# API RESTFULL COM BANCO MONGODB, IMPLEMENTAÇÃO DE DOCKER, DOCKER COMPOSE E CI/CD USANDO TRAVIS CI

## INTRODUÇÃO

Este projeto é uma API posts, onde cada posts podem haver vários comentários com autores distintos. Aqui realizo o **BACKEND** para todo o funcionamento da mesma realizando o **CRUD** completo com **CONSULTAS** **SIMPLES** e **CONSULTAS COM VÁRIOS CRITÉRIOS**.

Foi feito o **DEPLOY** da API na nuvem do **HEROKU** para que possa ser acessado online.

Foi implementado o **DOCKER** com **Dockerfile** e **Docker Compose**, para criação da imagem da aplicação e armazenamento no Docker Hub, Docker Compose para subir os dois containers (API + Banco) e que eles possam interagir entre si.

Para finalizar, foi realizado o **CI/CD** utilizando o **TRAVIS CI** para a automação do versionamento da **IMAGEM**.

## COMO ACESSAR A API?

A API pode ser acessada através dos **ENDPOINTS** disponíveis através da URL do **HEROKU**.

A URL da API é:

- [https://projeto-springboot-nosql-mongo.herokuapp.com](https://projeto-springboot-nosql-mongo.herokuapp.com/)

Porém só será possível acessar a API através dos ENDPOINTS, e para isso será necessário a utilização do aplicativo **POSTMAN** onde você pode fazer as **REQUISIÇÕES** da API:

- GET (acessar as entidades)
- POST (inserir entidades)
- PUT (atualizar entidades)
- DELETE (deletar entidades)

# ENDPOINTS DISPONÍVEIS

## 1) POST

### GET

- **FIND ALL** (filtrar todos)
    - [https://projeto-springboot-nosql-mongo.herokuapp.com/posts/](https://projeto-springboot-nosql-mongo.herokuapp.com/posts/)
- **FIND BY ID** (filtrar por ID)
    - [https://projeto-springboot-nosql-mongo.herokuapp.com/posts/](https://projeto-springboot-nosql-mongo.herokuapp.com/posts/){IDPOST}
- **FIND ALL COMMENT** (filtrar comentários de um post)
    - [https://projeto-springboot-nosql-mongo.herokuapp.com/posts/](https://projeto-springboot-nosql-mongo.herokuapp.com/posts/){IDPOST}/comments
- **FIND COMMENT BY ID** (filtrar comentário por ID)
    - [https://projeto-springboot-nosql-mongo.herokuapp.com/posts/](https://projeto-springboot-nosql-mongo.herokuapp.com/posts/){IDPOST}/{IDCOMMENT}

### POST

- **INSERT POST** (inserir um POST)
    - Aqui será um pouco diferente, pois neste caso não estamos apenas buscando e sim incluindo uma entidade, então precisaremos inserir as informações que desejamos que ela tenha, e informar o **ENDPOINT** específico para inserir que é [**https://spring-postgresql-restfull.herokuapp.com/](https://spring-postgresql-restfull.herokuapp.com/)posts,**  então abaixo segue um exemplo de como deveríamos fazer para incluir uma entidade no Postman.
    
    ![Untitled4](https://user-images.githubusercontent.com/97992737/158035095-4de85c7e-198d-46d0-bf57-c5ec7bab2b9f.png)
    
    ```java
    {
      "date": "2022-03-12T07:56:50Z",
      "title": "Partiu viagem",
      "body": "Vou viajar para São Paulo. Abraços!",
      "author": {
    	  "id": "622c5242c8e631029d1bf89b",
        "name": "Maria Brown"
      }
    }
    ```
    
- **INSERT COMMENT** (inserir um COMENTÁRIO em um POST específico)
    - Para fazer isto, primeiro vamos fazer um requisição **GET** nos posts para verificar o **POST** que gostaríamos de incluir um **COMENTÁRIO,** e também copiar o **ID** dele pois vamos precisar para a inclusão do comentário.
    
    ![Untitled6](https://user-images.githubusercontent.com/97992737/158035153-f3a5012c-2b3f-4d78-a5b3-95435b73e747.png)
    
    - Agora que ja sabemos o **POST** que queremos incluir o **COMENTÁRIO**, vamos então copiar o **ID** e inserir as informações do **COMENTÁRIO** que desejamos incluir, e inserir o **ENDPOINT** .
  
     ![Untitled20](https://user-images.githubusercontent.com/97992737/158035664-5ec8d054-c98d-4672-b5c1-a89c881489eb.png)
    
    ```java
    {
        "text": "Aproveite!",
        "date": "2022-03-12T19:07:33Z",
        "author": {
            "id": "622cef75331ac1344440e5e2",
            "name": "Bob Grey"
        }
    }
    ```    

### PUT

- Aqui da mesma forma que fizemos no **POST** nós iremos realizar no **PUT**, a diferença é que neste caso não estamos inserindo nenhum nova entidade e sim alterando um entidade ja existente, por isso é necessário que a entidade ja exista para que funcione o **PUT**. Então também neste caso devemos filtrar por **ID** e alterar as informações necessárias. Abaixo segue exemplo:
    
    ![Untitled21](https://user-images.githubusercontent.com/97992737/158035740-50441704-1d0e-4517-9392-73e0c51fac26.png)
    
    ```java
    {
      "date": "2022-03-12T19:07:33",
      "title": "Partiu viagem",
      "body": "Vou viajar para São Paulo. Abraços!",
      "author": {
    	  "id": "622cef75331ac1344440e5e0",
        "name": "Maria Brown"
      }
    }
    ```    

### DELETE

- **DELETE POST** (deletar um POST)
    - Para deletar um **POST** basta inserimos o **ENDPOINT** abaixo:
    - [https://projeto-springboot-nosql-mongo.herokuapp.com/posts/](https://projeto-springboot-nosql-mongo.herokuapp.com/posts/){IDPOST}
    - Depois inserir a **REQUISIÇÃO** do tipo **DELETE** e apertar **SEND**.
    
    ![Untitled9](https://user-images.githubusercontent.com/97992737/158035210-5f953408-6307-4889-ae43-c47bc88e17da.png)
    
- **DELETE COMMENT** (deletar comentário de um POST)
    - Aqui também se faz necessário que a entidade exista pois caso contrário não será possível deletar. Basta inserirmos a entidade com o **IDPOST/IDCOMMENT** com a requisição **DELETE** para deletar.
    - Primeiro vamos filtrar os posts:
    
    ![Untitled10](https://user-images.githubusercontent.com/97992737/158035230-6d5d9048-6758-4791-a1bc-5944a41e6850.png)
    
    - Agora vamos deletar o comentário do post.
    
    ![Untitled11](https://user-images.githubusercontent.com/97992737/158035245-ac3baaa8-0c58-4a67-95a4-09ecddd5a142.png)
    

## 2) USER

### GET

- **FIND ALL** (filtrar todos)
    - [https://spring-postgresql-restfull.herokuapp.com/](https://spring-postgresql-restfull.herokuapp.com/)users
- **FIND BY ID** (filtrar por ID)
    - [https://spring-postgresql-restfull.herokuapp.com/](https://spring-postgresql-restfull.herokuapp.com/)users/{IDUSER}
- **FIND POSTS BY USER** (filtrar os POSTS de um USUÁRIO)
    - [https://spring-postgresql-restfull.herokuapp.com/](https://spring-postgresql-restfull.herokuapp.com/)users/{IDUSER}/posts

### POST

- Para adicionar um **USER,** basta inserirmos as informações que desejamos inserir para um novo usuário e enviar via **REQUISIÇÃO** do tipo **POST** no seguinte **ENDPOINT**:
    - [https://spring-postgresql-restfull.herokuapp.com/](https://spring-postgresql-restfull.herokuapp.com/)users/
    
    ![Untitled12](https://user-images.githubusercontent.com/97992737/158035273-7bc916ba-5d5f-42c3-97de-ba65d7ac5bb9.png)
    
    
    ```java
    {
        "name": "Rafael Sulimann",
        "email": "rafael@gmail.com"
    }
    ```    

### PUT

- Para atualizar um **USER,** basta inserirmos as informações que desejamos atualizar do específico usuário e enviar via **REQUISIÇÃO** do tipo **PUT** no seguinte **ENDPOINT**:
    - [https://spring-postgresql-restfull.herokuapp.com/](https://spring-postgresql-restfull.herokuapp.com/)users/{IDUSER}
    
    ```java
    {
        "name": "Rafael Sulimann",
        "email": "rafael@gmail.com"
    }
    ```
    
    ![Untitled13](https://user-images.githubusercontent.com/97992737/158035303-1ea7409f-e3f3-4161-845b-84d594002b1d.png)

### DELETE

- Para deletar um **USER**, basta inserirmos o **ENDPOINT** com o **ID** do **USER** que desejamos deletar:
    - [https://spring-postgresql-restfull.herokuapp.com/](https://spring-postgresql-restfull.herokuapp.com/)users/{IDUSER}
    
    ![Untitled14](https://user-images.githubusercontent.com/97992737/158035343-09bce139-7436-4c91-b181-bbcf4dbb75f3.png)
    

## 3) CONSULTAS

### **TITLE SEARCH -** CONSULTA SIMPLES DE TÍTULO

- Para consultas simples, temos a consulta de palavras que contém em um **TÍTULO**.
- Ou seja, podemos digitar uma palavra e filtrar todos os posts que contem estas palavra em seu título (IGNORANDO CAIXA ALTA E BAIXA).
- O **ENDPOINT** ficará assim:
    
    ```java
    https://projeto-springboot-nosql-mongo.herokuapp.com/posts/titlesearch?text=bom%20dia
    ``` 
    
    ![Untitled15](https://user-images.githubusercontent.com/97992737/158035359-8dc5744d-b6e4-415a-b1e3-0a5bec78284f.png)
    
    - Repare que quando digitamos o **“BOM DIA”** nós inserimos ao invés de **ESPAÇO EM BRANCO** um **“%20”**, isso acontece pois precisamos **ENCODAR** o texto e quando inserirmos o espaços em branco nós precisaremos inserir o %20 no lugar.

### **FULL SEARCH -** CONSULTA COM VÁRIOS CRITÉRIOS

- Basicamente o **FULL SEARCH** você irá digitar uma palavra para filtrar e se esta palavra conter no **TÍTULO**, **CORPO** ou nos **COMENTÁRIOS** do **POST**, será filtrado o post que conter esta palavra em algum dos lugares mencionados acima.
- Além de filtrar por **TEXTO**, podemos filtrar por **DATA**, podendo filtrar tanto por uma **DATA MÍNIMA**, **DATA MÁXIMA** ou as **DUAS JUNTAS.** Porém, caso não informamos as datas, o sistema não irá considerar as **DATAS** no filtro.
1. **TEXTO + DATA MÁXIMA**
    
    ```java
    https://projeto-springboot-nosql-mongo.herokuapp.com/posts/fullsearch?text=bom%20dia&maxDate=2022-03-12
    ```
    
    ![Untitled16](https://user-images.githubusercontent.com/97992737/158035386-e686d787-95a7-4d69-9d66-499530d4031c.png)    
    
2. **TEXTO + DATA MÍNIMA**
    
    ```java
    https://projeto-springboot-nosql-mongo.herokuapp.com/posts/fullsearch?text=bom%20dia&minDate=2022-03-12
    ```
    
    ![Untitled17](https://user-images.githubusercontent.com/97992737/158035404-7f7f81bf-308b-49cf-ace5-1dd2da59d2f3.png)    
    
3. **TEXTO + DATA MÍNIMA + DATA MÁXIMA**
    
    ```java
    https://projeto-springboot-nosql-mongo.herokuapp.com/posts/fullsearch?text=bom%20dia&minDate=2022-01-12&maxDate=2022-03-12
    ```
    
    ![Untitled18](https://user-images.githubusercontent.com/97992737/158035417-015b4259-2c11-47fc-bed6-9fc950bb47e2.png)
    

4 . **DATA MÍNIMA + DATA MÁXIMA**

    ```java
    https://projeto-springboot-nosql-mongo.herokuapp.com/posts/fullsearch?minDate=2022-01-12&maxDate=2022-03-12
    ```
    
    ![Untitled22](https://user-images.githubusercontent.com/97992737/158036017-4a1d0f0e-8eaa-4850-ada8-c73ad6271d57.png)

1. **DATA MÍNIMA**
    
    ```java
    https://projeto-springboot-nosql-mongo.herokuapp.com/posts/fullsearch?minDate=2022-01-12
    ```
    
    ![Untitled23](https://user-images.githubusercontent.com/97992737/158036040-0c528ead-9504-4f75-a203-e5b938846e54.png)
    
2. **DATA MÁXIMA**
    
    ```java
    https://projeto-springboot-nosql-mongo.herokuapp.com/posts/fullsearch?maxDate=2022-01-12    
    ```
    
    ![Untitled24](https://user-images.githubusercontent.com/97992737/158036196-7c5e2f5c-7c93-4758-be1e-c2ef7581667d.png)
    
    **OBS:***** No exemplo acima inserimos uma **DATA MÁXIMA** que não tem nenhum post criado de forma **PROPOSITAL** para verificar o que iria **RETORNAR** caso não houvesse nenhum post na data especificada.
    
3. **TEXTO**
    
    ```java
    https://projeto-springboot-nosql-mongo.herokuapp.com/posts/fullsearch?text=partiu    
    ```
    
    ![Untitled25](https://user-images.githubusercontent.com/97992737/158036215-145dc48d-a056-4803-a4b5-55efffd7f8ab.png)

# DOCKERFILE, DOCKER COMPOSE E TRAVIS CI

## 1) DOCKERFILE

- Utilizamos o **DOCKERFILE** para gerar a **IMAGEM** da nossa aplicação, e para fazer isso, podemos utilizar o **TERMINAL DE COMANDOS** ou se for o caso e voce estiver utilizando o **VSCODE**, podemos utilizar por ali também. Eu particularmente gosto de utilizar o **WSL** no terminal do VSCode para fazer o **BUILD** da imagem.
- Para fazer isso basta então abrirmos o projeto com o VSCODE e entrar na pasta **APP** pois é onde se encontra o **DOKCERFILE**. Para fazer isto basta inserir o comando abaixo:

```java
cd app
```

- Agora ja na pasta onde se encontra o **DOCKERFILE** vamos então fazer o **BUILD** da imagem da nossa API inserindo comando abaixo:

```java
docker build -t <nome da imagem>:1.0
```

## 2) DOCKER COMPOSE

- Vamos agora voltar para a pasta onde se encontra o arquivo **docker-compose.yml**, inserindo o seguinte comando:

```java
cd ..
```

- Após digitar no terminal o seguinte comando para subir os **2 containers**(API + banco) e se comunicarem entre si:

```java
docker-compose up
```

## 3) CI/CD COM TRAVIS CI

- Para configurar o **TRAVIS CI** nós criamos o arquivo **.travis.yml** onde sempre que nós realizar um **COMMIT** no nosso **GITHUB** será feito um novo **BUILD** da imagem com uma **NOVA VERSÃO** e será realizar o **PUSH** desta nova versão da imagem la para o nosso **DOCKER HUB**.
