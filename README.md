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

### 🛠 Técnologias e padrão utilizadas

- Arquitetura padrão MVC;
- Spring Security;
- Spring Data;
- Java 11;
- Maven;
- Docker;
- Flyway;
- Postgres;
- Lombok;
- Swagger;

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

### 💡 Regras de negócio implementadas

  - cada partida se encerrará quando o jogador alcançar um número maximo de erros. Esse número pode ser setado através da variável de ambiente VALOR-MAXIMO-ERROS, que  é inicializada com o default de 3;
  - cada novo round haverá opções de filmes, e o jogador terá de escolher entre eles qual possui a maior pontuação. A quantidade de filmes por round pode ser mudada através da variável de ambiente QUANTIDADE-FILMES-POR-ROUND, que  é inicializada com o default de 2;
  - O jogador pode deslogar ou sair do jogo a qualquer momento, mas quando ele voltar, a mesma partida e round(se houver um round que não foi respondido) será resgatado;

### 🎲 Iniciando um novo jogo
1 -Para jogar, é necessário adicionar um novo usuário através do endpoint POST /usuarios, informando no body um usuário e senha
![image](https://user-images.githubusercontent.com/29411848/183426414-957e5c81-09fb-484c-bc38-7999ddc78a5b.png)

2 - Em seguida, consultar o endpoint POST /login para gerar um token para o login
![image](https://user-images.githubusercontent.com/29411848/183426970-6c39b92c-21ca-4763-844e-8e7d31c258dc.png)

3 - Pegar o token gerado, e adicionar a palavra Bearer + token no Authorize para acessar os demais endpoints no swagger
![image](https://user-images.githubusercontent.com/29411848/183427396-3111e1db-f4e1-433d-8ba8-86e3df3887d2.png)

4 -Para iniciar Partida, acessar o endpoint POST /partidas/iniciar
![image](https://user-images.githubusercontent.com/29411848/183427789-b5e95739-66fc-4d8b-98b6-1c8a47a9242c.png)

5 -Após a partida estar criada, acessar os endpoints GET /round para pegar as opções de filmes para o round atual
![image](https://user-images.githubusercontent.com/29411848/183428993-c0eb2f9a-11aa-4903-b86b-7e4dcef4f165.png)

6 -Utilizar o campo opcaoId retornado no payload do GET do passo 5 para adicionar no payload POST /rounds do filme em que o jogador acha que possui maior pontuação.
Esse endpoint retrornará no corpo se o Jogador errou ou acertou o palpite.
![image](https://user-images.githubusercontent.com/29411848/183428918-c347f755-1c0a-49f2-aa7a-458bf2a48016.png)

Obs: Você Pode consultar sua colocação no ranking a qualquer momento, através do endpoint GET /ranking
![image](https://user-images.githubusercontent.com/29411848/183429366-d3f5ebf2-c290-41ab-8db4-cc4f2ed6e878.png)


### 🛠 Tecnologias

### ✅ Testes
Foram desenvolvidos testes unitários para os services e integrados para os controllers. Você pode executar diretamente através da sua IDE.
![image](https://user-images.githubusercontent.com/29411848/183432480-7a1f5e09-9fce-45d3-ac4b-c5f9bb59b314.png)
