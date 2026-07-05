package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Livro;

// Controller do projeto, trabalhando como intermediário entre a view e o "model", utilizando os getters e setters da classe Livro para adicionar a um array.


// ApplicationScoped pois a requisição persiste ao longo de toda a sessão
@ManagedBean
@ApplicationScoped 
public class LivroController {
	
	private Livro livro = new Livro();
	private List<Livro> livros = new ArrayList<>();
	
	public LivroController() {}
	
	// Adiciona um livro em um array que será exibido na view 
	// Um novo livro é instanciado no momento que é adicionado um elemento no array, para que seja sempre um novo objeto em referência na memória
	public void addData() {
		livros.add(livro);
		livro = new Livro();
	}
	
	// Remove um livro do array, com a view repassando o exato objeto referenciado como parâmetro, a função "remove" encontra o objeto em questão dentro do array
	public void removeLivro(Livro livro) {
		livros.remove(livro);
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public List<Livro> getLivros() {
		return livros;
	}

}
