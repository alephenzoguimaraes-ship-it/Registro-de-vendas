## Sistema de Vendas - Java Desktop

Sistema de vendas desktop desenvolvido em Java com interface gráfica Swing, persistência em banco Firebird e geração de relatórios em PDF.

O projeto foi estruturado utilizando padrão DAO (Data Access Object), separando modelo, acesso a dados e interface.

---

## Funcionalidades

### Clientes
- Cadastrar
- Editar
- Excluir
- Listar
- Relatorios dos Clientes

### Funcionários
- Cadastrar
- Editar
- Excluir
- Listar
- Relatorios dos Funcionários

### Produtos
- Cadastrar
- Editar
- Excluir
- Listar
- Relatorios dos Produtos

### Vendas
- Registro de vendas
- Associação entre cliente, funcionário e produtos
- Persistência em banco de dados
- Relatorios das Vendas


---

## Estrutura do Projeto

- `src` → Código-fonte do sistema  
- `model` → Classe Beans (Cliente, Funcionário, Produto, Vendas) e Classe Dao para Acesso ao banco de dados  
- `conexao` → Classe de conexão com Firebird (`FBConexao`)  
- `swing` → Telas do sistema que tem funções de CRUD
- `bin` → Arquivos compilados  

---

## Banco de Dados

Banco utilizado: Firebird SQL.

Para executar:
"Os arquivos já estão disponíveis no codigo java que tem comentários para que você dev possa entender e a maioria já está vem configurado, talvez voce tem que alterar pouca coisa para poder deixa ele funcionando"
"Um detalhe, deixei a senha do banco no proprio codigo java para que você dev possa usar o banco de dados sem dor de cabeça"

1. Criar o banco no Firebird  
2. Criar tabelas e/ou procedures necessárias  
3. Configurar conexão em `FBConexao.java`

---

## Como Executar

1. Abrir o projeto na IDE  
2. Configurar o banco de dados  
3. Executar a classe principal  

---

## Tecnologias Utilizadas

- Java 21  
- Java Swing  
- JDBC  
- Firebird SQL  
- iText (Geração de PDF)  
- Padrão DAO  
