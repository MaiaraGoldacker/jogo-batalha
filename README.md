### Batalha de Filmes 


### 💻 Sobre o projeto
Jogo de Batalha de filmes, onde o jogador receberá duas opções de filmes, e terá que adivinhar qual possui a maior pontuação média levando em consideração a pontuação IMDB

### ⚙️ Funcionalidades

- [x] Jogadores:
  - [x] Adicionar novo jogador;
  - [x] Fazer login de jogador;
  - [x] Fazer logout de jogador;

- [x] Partidas:
  - [x] Iniciar nova partida;
    
- [x] Round:
  - [x] Resgatar opções de filme para novo Round;
  - [x] Escolher opção de filme com mais pontos;
  
- [x] Ranking:
  - [x] Consultar ranking com pontuação total dos jogadores;

### ✏️ Técnologias Utilizadas

- Spring Security
- Spring Data
- Java 11
- Maven
- Docker
- Flyway
- Postgres
- Lombok
- Swagger

### 🧭 Rodando a aplicação

#### Clonar o repositório do projeto

```sh
git clone https://github.com/MaiaraGoldacker/jogo-batalha.git
```
Importar o projeto na IDE de sua preferência.

#### Dentro do diretório do projeto, buildar com o Maven
```sh
mvn clean install
```

O projeto baixará as dependências necessárias e buildará com sucesso. Caso não complete com sucesso, verifique o log do build para encontrar possíveis erros.

#### Configurar o DB Postgres localmente com o Docker
Com o terminal dentro do diretório do projeto, executar o docker-compose abaixo
```sh
docker-compose up
```

Conectar-se ao DB com o manager de sua preferência. O usuário e senha do DB podem ser consultados no arquivo application.yml

#### Documentação da API
A documentação da API é feita através do swagger, e quando a aplicação estiver rodando em ambiente local você pode acessá-la pelo [link](http://localhost:8080/swagger-ui/index.html#/)

### 🎲 Iniciando um novo jogo
1 -Para jogar, é necessário adicionar um novo usuário através do endpoint /usuarios, informando no body um usuário e senha
![image](https://user-images.githubusercontent.com/29411848/183426414-957e5c81-09fb-484c-bc38-7999ddc78a5b.png)

2 - Em seguida, consultar o endpoint /login para gerar um token para o login
![image](https://user-images.githubusercontent.com/29411848/183426970-6c39b92c-21ca-4763-844e-8e7d31c258dc.png)

3 - Pegar o token gerado, e adicionar a palavra Bearer + token no Authorize para acessar os demais endpoints no swagger
![image](https://user-images.githubusercontent.com/29411848/183427396-3111e1db-f4e1-433d-8ba8-86e3df3887d2.png)

4 -Para iniciar Partida, acessar o endpoint /partidas/iniciar passando como parâmetro o nome de usuário pelos Parameters
![image](https://user-images.githubusercontent.com/29411848/183427789-b5e95739-66fc-4d8b-98b6-1c8a47a9242c.png)

5 -Após a partida estar criada, acessar os endpoints GET /round para pegar as opções de filmes para o round atual, passando como parâmetro o nome de usuário pelos Parameters
![image](https://user-images.githubusercontent.com/29411848/183428133-7372b978-fa7d-44b5-89f8-74263bcc7c63.png)




### 🛠 Tecnologias

### ✅ Testes
Para rodar os testes, utilize o comando abaixo:


### 🔗 Links

 
