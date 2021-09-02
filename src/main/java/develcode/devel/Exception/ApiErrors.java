package develcode.devel.Exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

public class ApiErrors {

  private List<FormErrorValidation> formErrors;
  private List<String> erro;
  private ResponseStatusError responseStatusError;

  public ApiErrors() {
  }

  public ApiErrors(BindingResult bindingResult) {
    this.formErrors = new ArrayList<>();
    bindingResult.getFieldErrors().forEach(fieldError -> {
      String mensagem = fieldError.getDefaultMessage();
      FormErrorValidation erro = new FormErrorValidation(fieldError.getField(), mensagem);
      formErrors.add(erro);

    });
  }

  public ApiErrors(BusinessException exception) {
    this.erro = new ArrayList<>();
    this.erro.add(exception.getMessage());
  }

  public ResponseStatusError ResponseStatusError(ResponseStatusException exception) {
    int status = exception.getRawStatusCode();
    String mensagem = exception.getReason();
    return new ResponseStatusError(status, mensagem);

  }

  public List<String> getErro() {
    return erro;
  }

  public List<FormErrorValidation> getFormErrors() {
    return formErrors;
  }

  public ResponseStatusError getResponseStatusError() {
    return responseStatusError;
  }
}
