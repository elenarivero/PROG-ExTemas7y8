package gestion.ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import pacientes.Paciente;
import pacientes.TelefonoInvalidoException;

public class GestionFicheros {
	private static final String RUTA_FICHERO = "src/ficheros/pacientes.txt";
	private static final String RUTA_CARPETA = "src/ficheros/"; 

//	public static HashMap<String, TreeSet<Paciente>> leerCentrosSalud(){
//		HashMap<String, TreeSet<Paciente>> centrosSalud = new HashMap<String, TreeSet<Paciente>>();
//		TreeSet<Paciente> pacientes = new TreeSet<Paciente>();
//		
//		for(int i=1; i<=5; i++) {
//			pacientes = leerFichero(i);
//			centrosSalud.put("Centro"+i, pacientes);
//		}
//		
//		return centrosSalud;
//	}
	
	public static TreeSet<Paciente> leerFichero(){
		TreeSet<Paciente> pacientes = new TreeSet<Paciente>();
		Paciente pac;
		String linea;
		String[] datos;
		int telefono;
		boolean bajaB;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(RUTA_FICHERO));
			linea = br.readLine();
			
			while(linea != null) {
				datos = linea.split(";");
				telefono = Integer.parseInt(datos[2]);
				bajaB = (datos[3].equals("Sí")) ? true : false;
				
				pac = new Paciente(datos[0], datos[1], telefono, bajaB);
				pacientes.add(pac);
				linea = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("La ruta del fichero no existe");
			System.out.println(e);
		} catch (IOException e) {
			System.out.println("Error en la lectura del fichero");
			System.out.println(e);
		} catch (TelefonoInvalidoException e) {
			System.out.println("Error en la creación del paciente");
			System.out.println(e);
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el buffer");
				System.out.println(e);
			}
		}
		
		return pacientes;
	}
	
	public static void escribeFichero(Set<Paciente> pacientes) {
		String linea;
		BufferedWriter bw = null;
		try {
			
			bw = new BufferedWriter(new FileWriter(RUTA_FICHERO));
			
			for(Paciente p : pacientes) {
				linea = "";
				linea += p.getNombre() + ";";
				linea += p.getDireccion() + ";";
				linea += p.getTelefono() + ";";
				linea += p.isBaja() ? "Sí" : "No";
				bw.write(linea);
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error en la escritura del fichero");
			System.out.println(e);
		} finally {
			try {
				if(bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				System.out.println("Error al terminar de escribir en el fichero");
				System.out.println(e);
			}
		}
	}
	
	public static void escribeFichero2(Map<String, TreeSet<Paciente>> centrosSalud) {
		String linea = "";
		BufferedWriter bw = null;
		Set<String> claves = centrosSalud.keySet();
		TreeSet<Paciente> pacientes;
		try {
			
			for(String centro : claves) {
				bw = new BufferedWriter(new FileWriter(RUTA_CARPETA+centro+".txt"));
				
				pacientes = centrosSalud.get(centro);
				
				for(Paciente p : pacientes) {
					linea += p.getNombre() + ";";
					linea += p.getDireccion() + ";";
					linea += p.getTelefono() + ";";
					linea += p.isBaja() ? "Sí" : "No";
					bw.write(linea);
					bw.newLine();
				}
			}
			
		} catch (IOException e) {
			System.out.println("Error en la escritura del fichero");
			System.out.println(e);
		} finally {
			try {
				if(bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				System.out.println("Error al terminar de escribir en el fichero");
				System.out.println(e);
			}
		}
	}
}
