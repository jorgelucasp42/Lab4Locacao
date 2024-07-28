## Projeto de Sistema Imobiliário

### Descrição

Este projeto consiste em um sistema imobiliário desenvolvido em Java 18. Ele utiliza JPA (Java Persistence API) para persistência de dados em um banco de dados MySQL. O sistema permite gerenciar informações sobre imóveis, clientes, locações, aluguéis e serviços realizados nos imóveis.

### Requisitos

- Java 18
- MySQL 8.0 ou superior
- IDE de sua preferência (recomendado: IntelliJ IDEA)

### Configuração do Banco de Dados

Antes de executar o projeto, certifique-se de configurar corretamente as credenciais de acesso ao banco de dados MySQL. As configurações padrão estão localizadas no arquivo `persistence.xml`. Altere as configurações conforme necessário:

```xml
<persistence-unit name="lab_jpa">
    <properties>
        <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/lab_jpa?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true"/>
        <property name="javax.persistence.jdbc.user" value="seu_usuario"/>
        <property name="javax.persistence.jdbc.password" value="sua_senha"/>
        <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
        <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
        <property name="hibernate.hbm2ddl.auto" value="update"/>
    </properties>
</persistence-unit>
```
### Populando o Banco de Dados
Para garantir a consistência das chamadas das demais classes em relação aos dados persistidos, é fundamental executar inicialmente a classe PopulaBanco. Esta classe irá criar e inserir dados iniciais no banco de dados, necessários para os testes e funcionamento correto das demais funcionalidades do sistema.

- Abra a classe PopulaBanco localizada no pacote com.imobiliaria.teste.
- Execute a classe.
Após a execução da classe PopulaBanco, já é possível visualizar diversas entidades no MySQL, tais como Cliente, Imóvel e TipoImóvel.

### Executando Testes
Após a execução da classe PopulaBanco, você pode realizar testes nas demais classes de teste, como TesteLocacao e TesteAluguel.

#### Testando Locações
- Abra a classe TesteLocacao localizada no pacote com.imobiliaria.teste.
- Execute a classe para testar a criação, leitura, atualização e exclusão de locações.
#### Testando Aluguéis
-Abra a classe TesteAluguel localizada no pacote com.imobiliaria.teste.
-Execute a classe para testar a criação, leitura, atualização, exclusão e outras operações relacionadas a aluguéis.
### Pacotes
- com.imobiliaria.model: Contém as entidades do sistema.
- com.imobiliaria.repository: Contém os repositórios para acesso aos dados.
- com.imobiliaria.service: Contém a lógica de negócios do sistema.
- com.imobiliaria.teste: Contém as classes de teste para as funcionalidades do sistema.
