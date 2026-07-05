package model;


// Model do Projeto, contendo os dados e referenciais ao Livro. Possui os atributos inicias, getters e setters.
public class Livro {
	
	private String title;
	private String author;
	private String isbn; 
	private String ano;
	
	public Livro() {
	
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
}
