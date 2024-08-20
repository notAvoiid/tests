[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]:https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[JUNIT_BADGE]:https://camo.githubusercontent.com/422c31a0a0add707d4617d3391c76cb5a2021a4457a8ff848b7443e85eee0cad/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a556e6974352d3444323132312e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d4a556e697435266c6f676f436f6c6f723d7768697465

<h1 align="center" style="font-weight: bold;">Tests Project 🧪</h1>

![spring][SPRING_BADGE]
![java][JAVA_BADGE]
![junit][JUNIT_BADGE]
<br>

<p align="center">
  <b>Projeto para testar métodos de um CRUD completo com cobertura de testes.</b>
</p>

## 🚀 Começando

Este projeto é uma API CRUD desenvolvida com Spring Boot e JPA, projetada para fornecer uma base sólida para testes automatizados. Foi utilizado JUnit e Mockito para garantir a alta confiabilidade do sistema.

## ⚙️ Tecnologias

- **Linguagem**: Java
- **Framework**: Spring Boot (Web, Jpa)
- **Banco de Dados**: PostgreSQL
- **Ferramenta de construção**: Maven
- **Testes**: JUnit 5, Mockito

## 🔄 Clonando

Clone o projeto usando HTTPS:
```bash
git clone https://github.com/notAvoiid/tests.git
```

Ou, se preferir usar SSH:
```bash
git clone git@github.com:notAvoiid/tests.git
```

## 🟢 Executando o projeto
```bash
# 1. Navega até o diretório do projeto
cd tests

# 2. (Opcional) Para Linux: Verifica e para o PostgreSQL caso esteja sendo usado em background
sudo service postgresql stop

# 3. Inicia o banco de dados usando Docker
docker compose up -d

# 4. Construa o projeto usando Maven
mvn clean install

# 5. Executa a aplicação Spring Boot
mvn spring-boot:run

# Ou
mvnw spring-boot:run
```

## 📄 Documentação

1. Certifique-se de que o projeto está rodando localmente.
2. Navegue até `http://localhost:8080/swagger-ui.html` no seu navegador ou clique aqui segurando CTRL: [Swagger](http://localhost:8080/swagger-ui.html)  

## 📫 Contribuição

Para me ajudar a melhorar o projeto ou me ajudar a melhorar:

1. Clone: `git clone https://github.com/notAvoiid/tests.git` ou `git clone git@github.com:notAvoiid/tests.git`
2. Criando sua própria feature: `git checkout -b feature/NAME`
3. Siga os padrões de commit.
4. Abra um Pull Request explicando o problema resolvido ou a feature implementada. Prints com detalhes são importantes!

## Documentações que podem ajudar

[📝 Como criar um Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Padrões de commits](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)
