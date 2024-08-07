# Bookstore

Aplicação simples de Livraria construída usando Java e Spring Boot. 
<p>O projeto demonstra o uso do Spring Boot para construção de um serviço RESTful, juntamente com JPA para interações com o banco de dados. A aplicação permite que os usuários: </p>

- Naveguem por uma coleção de livros.
- Pesquisem livros por título, autor ou gênero.
- Visualizem informações detalhadas sobre cada livro.
- Adicionem, atualizem ou removam livros da coleção.

## Funcionalidades

### Books:
- *@PostMapping saveBook* - Adiciona um novo livro ao banco de dados, com as informações dos autores, review e editora. 
- *@GetMapping getAllBooks* - Lista todos os livros salvos. 
- *@DeleteMapping("/{id}") deleteBook* - Deleta um livro através do seu identificador. 
- *@GetMapping("/{id}") getOneBook* - Localiza um livro através do seu identificador.

### Author
- *@PostMapping saveAuthor* - Realiza o cadastro de um novo autor, com verificação de duplicidade através do nome.
- *@GetMapping("/{id}") getOneAuthor* - Localiza um autor através do seu identificador.
- *@GetMapping getAllAuthors* - Lista todos os autores salvos.
  
## Tecnologias 

Spring Boot, Spring Data JPA, Java 17, MySQL, Postman, Eureka Discovery. 
