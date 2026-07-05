package controller;

import java.time.LocalDate;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import model.Livro;

// Também faz parte do Controller da aplicação, com a função de validar os dados, armazenando as regras de negócio da aplicação

@ManagedBean
public class ValidatorLivro {
	public ValidatorLivro() {}
	
	// Pega o "array" atualizado dos livros e armazena ele dentro da variável livros
	LivroController controller = FacesContext.getCurrentInstance()
			.getApplication()
			.evaluateExpressionGet(
					FacesContext.getCurrentInstance(),
					"#{livroController}",
					LivroController.class);
	List<Livro> livros = controller.getLivros();
	
	// Método principal da classe, que redireciona para as tratativas baseado no ID HTML que foi encontrando a partir do evento de clique
	public void validateData(FacesContext context, 
					UIComponent component,
					Object value) throws ValidatorException {
		String type = component.getId().toString(); 
		String data = value.toString();
		String message = "";
		switch (type) {
			case "titulo":
				message = validateTitle(data);
				break;
			case "autor":
				message = validateAuthor(data);
				break;
			case "isbn":
				message = validateISBN(data);
				break;
			case "ano":
				message = validateAno(data);
				break;
			default:
				break;
		}
		
		// Se a mensagem não estiver vazia, exibir uma mensagem de erro customizada na view
		if (!(message.equals(""))) {
			FacesMessage views_response = new FacesMessage(message);
			throw new ValidatorException(views_response);
		}
		
		
	}
	
	// Valida aspectos do título, tamanho 
	public String validateTitle(String data) {
	    if (data.isBlank())       {   return "O título é obrigatório.";                        }
	    if (data.length() < 2)    {   return "O título deve possuir pelo menos 2 caracteres."; }
	    if (data.length() > 100)  {   return "O título não pode ultrapassar 100 caracteres.";  }
	    
	    return "";
	}

	// Valida aspectos do autor, aceitando apenas letras e sem caracteres especiais
	public String validateAuthor(String data) {
		if (data.isBlank())                     { return "O autor é obrigatório.";                                  }
	    if (data.length() < 3)                  { return "O nome do autor deve possuir pelo menos 3 caracteres.";   }
	    if (!data.matches("[A-Za-zÀ-ÿ ]+"))     { return "O nome do autor deve conter apenas letras e espaços.";    }

	    return "";
	}

	// Realiza uma verificação no ARRAY e verifica se existe algum dado com o mesmo ISBN cadastrado, impedindo que dois livros com mesmo ISBN estejam cadastrados, também verifica o regex
	public String validateISBN(String data) {
		for (int i = 0; i < livros.size(); i++) {
	        if (livros.get(i).getIsbn().equals(data)) {
	            return "Esse livro já está cadastrado!";
	        }
	    }

	    if (!data.matches("\\d{13}")) { return "O ISBN deve conter exatamente 13 números apenas!"; }

	    return "";
	}
	
	// Valida o ano, impedindo datas inválidas e datas maiores que o dia atual (apenas livros já publicados podem ser registrados)
	public String validateAno(String data) {
		try {
	        LocalDate dataPublicacao = LocalDate.parse(data);
	        if (dataPublicacao.isAfter(LocalDate.now())) { return "A data de publicação não pode ser uma data futura!"; }
	    } catch (Exception e) {
	        return "Data de publicação inválida.";
	    }
	    return "";
	}
	
	
}
