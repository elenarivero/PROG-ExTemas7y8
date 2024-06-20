package crud;

import java.util.Set;
import java.util.TreeSet;

import gestion.ficheros.GestionFicheros;
import pacientes.Paciente;

public class GestionCRUD {
	private static Set<Paciente> pacientes = new TreeSet<Paciente>();
	
	public static boolean addPaciente(Paciente p) {
		return pacientes.add(p);
		
	}
	
	public static boolean eliminaPaciente(Paciente p) {
		return pacientes.remove(p);
	}
	
	public static void inicializarLista() {
		pacientes = GestionFicheros.leerFichero();
	}
	
	public static void listarPacientes() {
		for(Paciente p: pacientes) {
			System.out.println(p);
			System.out.println("-----------------------------------");
		}
	}
	
	public static void guardarLista() {
		GestionFicheros.escribeFichero(pacientes);
	}
	
	public static Paciente buscaPaciente(Paciente p) {
		Paciente pac = null;
		if(pacientes.contains(p)) {
			for(Paciente paciente : pacientes) {
				if(paciente.equals(p)) {
					pac = paciente;
				}
			}
		}
		return pac;
	}
	public static boolean modDireccion(Paciente p, String direccion) {
		boolean res = false;
		Paciente pac = buscaPaciente(p);
		if(pac != null) {
			pac.setDireccion(direccion);
			res = true;
		}
		return res;
	}
}

