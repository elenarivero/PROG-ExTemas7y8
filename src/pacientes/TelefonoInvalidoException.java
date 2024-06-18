package pacientes;

public class TelefonoInvalidoException extends Exception {
	@Override
	public String toString() {
		return "ERROR: el formato del teléfono no es correcto";
	}
	
	@Override
	public String getMessage() {
		return "ERROR: el teléfono debe tener 9 dígitos";
	}
}
