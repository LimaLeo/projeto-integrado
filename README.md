# projeto-integrado

Para que a aplicação funcione é preciso segui os passos a seguir:

•	Abrir sua ferramenta de banco de dados e executar a seguinte query:

  o	CREATE DATABASE controle_predial;
  
•	Importar o projeto como Maven;

•	Alterar os values das seguintes linhas, sendo login e senha do banco de dados local, na pasta META-INF do arquivo persistence.xml:

<property name="javax.persistence.jdbc.user" value="alunos" />

<property name="javax.persistence.jdbc.password" value="usjt@but2010" />

•	Execute a classe main do pacote init e aguarde a instalação do banco;

•	Usar os seguintes dados de login:

  o	Login: Usuario;
  
  o	Senha: 123;
