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
Para jogar, é necessário adicionar um novo usuário através do endpoint /usuarios, informando no body um usuário e senha:
![image](https://user-images.githubusercontent.com/29411848/183426414-957e5c81-09fb-484c-bc38-7999ddc78a5b.png)



### 🛠 Tecnologias

### ✅ Testes
Para rodar os testes, utilize o comando abaixo:


### 🔗 Links

 
