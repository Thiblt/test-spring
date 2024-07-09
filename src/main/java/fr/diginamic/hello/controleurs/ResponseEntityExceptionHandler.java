package fr.diginamic.hello.controleurs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.diginamic.hello.exception.RestException;

@ControllerAdvice
public class ResponseEntityExceptionHandler {
	

	/**Gestion d'erreur de type RestException et rtourne le message de l'erreur.
	 * @param RestException ex, l'erreur attrapée
	 * @return  ResponseEntity<String>
	 */
	@ExceptionHandler({ RestException.class})
	protected ResponseEntity<String> traiterErreurs(RestException ex) {
	return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
