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
}

