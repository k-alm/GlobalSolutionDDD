package br.com.fiap.ddd.gs.exceptions;

public class RegraDeNegocioException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
