package pacientes;

public class Paciente implements Comparable<Paciente> {

	private String nombre = "";
	private String direccion = "";
	private int telefono;
	private boolean baja;

	public Paciente(String nombre, int telefono) throws TelefonoInvalidoException {
		if (nombre != null && !nombre.equals("")) {
			this.nombre = nombre;
		}

		compruebaTelefono(telefono);
	}

	public Paciente(String nombre, String direccion, int telefono, boolean baja) throws TelefonoInvalidoException {
		this(nombre, telefono);

		if (direccion != null && !direccion.equals("")) {
			this.direccion = direccion;
		}

		this.baja = baja;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		if (direccion != null && !direccion.equals("")) {
			this.direccion = direccion;
		}
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) throws TelefonoInvalidoException {
		compruebaTelefono(telefono);
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * @param telefono
	 * @throws TelefonoInvalidoException
	 */
	private void compruebaTelefono(int telefono) throws TelefonoInvalidoException {
		if (telefono >= 100000000 && telefono <= 999999999) {
			this.telefono = telefono;
		} else {
			throw new TelefonoInvalidoException();
		}
	}

	@Override
	public boolean equals(Object obj) {
		boolean res = false;

		if (obj instanceof Paciente pac) {
			if (this.nombre.equalsIgnoreCase(pac.nombre) && this.telefono == pac.telefono) {
				res = true;
			}
		}

		return res;
	}
	
	@Override
	public String toString() {
		String res = "Nombre: " + this.nombre + "\n";
		res += "Dirección: " + this.direccion + "\n";
		res += "Teléfono: " + this.telefono + "\n";
		res += "Baja: " + (this.baja ? "Sí" : "No") + "\n";
		
		return res;
	}

	@Override
	public int compareTo(Paciente o) {
		int res = 0;
		
		res = this.nombre.compareTo(o.nombre);
		
		if(res == 0) {
			res = this.telefono - o.telefono;
		}
		
		return res;
	}

}
