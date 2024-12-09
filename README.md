# Cadastro de PDF

Este projeto tem como objetivo fornecer uma solução para cadastrar e gerenciar arquivos PDF de forma simples e eficiente. O sistema é composto por uma API (backend) e uma interface frontend. 

## Tecnologias

- **Backend**: Java com Spring Boot (Versão mais recente)
- **Frontend**: React (versão mais recente) com npm start

## Instalação

### Backend (API)
1. Clone o repositório do backend:
    ```bash
    git clone https://github.com/usuario/cadastro-pdf-backend.git
    cd cadastro-pdf-backend
    ```

2. Certifique-se de que o **Java** e o **Maven** estão instalados e configurados no seu ambiente. 
   - **Java**: Versão mais recente do JDK.
   - **Maven**: Para compilar e rodar o projeto.

3. Compile e rode o backend:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

O backend estará disponível em `http://localhost:8080`.

### Frontend (Interface de Usuário)
1. Clone o repositório do frontend:
    ```bash
    git clone https://github.com/usuario/cadastro-pdf-frontend.git
    cd cadastro-pdf-frontend
    ```

2. Certifique-se de ter o **Node.js** e o **npm** instalados.
   - **Node.js**: Versão mais recente.
   - **npm**: Usado para gerenciar pacotes.

3. Instale as dependências do frontend:
    ```bash
    npm install
    ```

4. Inicie o servidor de desenvolvimento:
    ```bash
    npm start
    ```

O frontend estará disponível em `http://localhost:3000`.

## Funcionalidades

- **Cadastro de PDFs**: Permite que o usuário envie e cadastre arquivos PDF na plataforma.
- **Visualização de PDFs**: O usuário pode visualizar os PDFs cadastrados diretamente na interface.
- **Armazenamento de Metadados**: Além do arquivo, são armazenados metadados como nome, data e tipo do arquivo.

## Autor

- **Nome**: Wellerson Ferreira de Carvalho
- **LinkedIn**: [https://www.linkedin.com/in/wellfc/](https://www.linkedin.com/in/wellfc/)

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
