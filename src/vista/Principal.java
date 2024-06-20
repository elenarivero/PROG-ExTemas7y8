package vista;

import java.util.Scanner;

import crud.GestionCRUD;
import pacientes.Paciente;
import pacientes.TelefonoInvalidoException;

public class Principal {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int opc;
		Paciente pac;
		
		GestionCRUD.inicializarLista();
		
		System.out.println("BIENVENIDO/A AL SISTEMA DE SALUD");
		do {
			menu();
			opc = sc.nextInt();
			sc.nextLine();
			
			switch (opc) {
			case 1:  // Añadir paciente
				pac = pedirDatos();
				// Llamamos a la función que añade el paciente
				if(pac != null) {
					if(GestionCRUD.addPaciente(pac)) {
						System.out.println("Paciente añadido correctamente");
					} else {
						System.out.println("Paciente ya existe");
					}// fin addPaciente
				} // fin null
				break;
			//fin caso 1
			case 2:
				GestionCRUD.listarPacientes();
				break;
			case 3:
				
				break;
			case 4: // Eliminación de paciente
				pac = pedirNombreTelfno();
				if(pac != null) {
					if(GestionCRUD.eliminaPaciente(pac)) {
						System.out.println("El paciente se ha eliminado correctamente");
					} else {
						System.out.println("El paciente no existe");
					}
				}
				break;
			case 5: // Escribe en el fichero los pacientes
				GestionCRUD.guardarLista();
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida");
			}
			
		} while(opc!=0);
		
		
		sc.close();
	}

	private static String pideNombre() {
		String nombre;
		System.out.println("Introduzca el nombre del paciente:");
		nombre = sc.nextLine();
		return nombre;
	}
	
	
	private static int pideTelefono() {
		int telefono;
		System.out.println("Introduzca el teléfono del paciente:");
		telefono = sc.nextInt();
		sc.nextLine();
		return telefono;
	}
	
	private static Paciente pedirNombreTelfno() {
		String nombre;
		int telefono;
		Paciente pac = null;
		
		nombre = pideNombre();
		telefono = pideTelefono();
		
		try {
			pac = new Paciente(nombre, telefono);
		} catch (TelefonoInvalidoException e) {
			System.out.println("Error en el teléfono");
			System.out.println(e);
		}
		return pac;
	}
	
	private static Paciente pedirDatos() {
		Paciente pac = null;
		String nombre;
		String direccion;
		int telefono;
		String baja;
		boolean bajaB;
		
		nombre = pideNombre();
		telefono = pideTelefono();
		
		System.out.println("Introduzca la dirección del paciente:");
		direccion = sc.nextLine();
						
		System.out.println("Introduzca si el paciente está de baja (Sí/No):");
		baja = sc.next();
		sc.nextLine();
		
		bajaB = (baja.equals("Sí") ? true : false);
		
		try {
			pac = new Paciente(nombre, direccion, telefono, bajaB);
		} catch (TelefonoInvalidoException e) {
			System.out.println("Se ha producido un error durante la creación del paciente:");
			System.out.println(e);
		}
		return pac;
	}
	private static void menu() {
		System.out.println("1. Añadir paciente");
		System.out.println("2. Listar pacientes");
		System.out.println("3. Modificar paciente");
		System.out.println("4. Eliminar paciente");
		System.out.println("5. Guardar pacientes");
		System.out.println("0. Salir");
		System.out.println("Introduzca la opción deseada:");
	}

}
