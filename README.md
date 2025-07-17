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
  - application-javamailsender.properties -> incluir no arquivo as seguintes propriedades
    - spring.mail.host=
    - spring.mail.port=
    - spring.mail.username=
    - spring.mail.password=
    - spring.mail.properties.mail.smtp.auth=
    - spring.mail.properties.mail.smtp.starttls.enable= 
  - application-jpa.properties -> incluir no arquivo as seguintes propriedades
    - spring.datasource.url=
    - spring.datasource.username=
    - spring.datasource.password=
    - spring.datasource.driver-class-name=
    - spring.jpa.show-sql=
    - spring.jpa.database-platform=
    - spring.jpa.defer-datasource-initialization=
    - spring.jpa.hibernate.ddl-auto=
    - spring.jpa.properties.hibernate.format_sql=
    - spring.jpa.properties.hibernate.dialect=
    - spring.sql.init.mode=

Ao finalizar o passo a passo a aplicação estará disponível na porta 8080 do localhost (http://localhost:8080/sistema-nf) e seu banco de dados estará disponível na porta 3306 do localhost.