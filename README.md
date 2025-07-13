# sistema-nf
Back-end para um sistema de controle de notas fiscais.

# Documentação interativa
Swagger disponível através do link: http://localhost:8080/sistema-nf/swagger-ui/index.html#/

# Collection do Postman para testes
Disponível através do arquivo: sistema-nf.postman_collection.json

# Execução local
- clonar repositório do projeto
- criar dentro da pasta src/main/resources os arquivos:
  - app.key -> incluir no arquivo a chave privada para gerenciamento de tokens JWT
  - app.pub -> incluir no arquivo a chave pública para gerenciamento de tokens JWT

Ao finalizar o passo a passo a aplicação estará disponível na porta 8080 do localhost (http://localhost:8080/sistema-nf) e seu banco de dados estará disponível na porta 3306 do localhost.