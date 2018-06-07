package funciones;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import principal.Alumno;
import principal.Persona;
import principal.Profesor;

public class Fprincipales{
	public static Map<String,Persona> InsertaPersona(Map<String,Persona> mapaPersonas,String persona) {
		persona=persona.trim();
		persona=faux.quitarespacios(persona);
		String[] datos;
		int errores=0;
		datos=persona.split(" ");
		String dni= datos[2];
		String nombre=datos[3];
		if(!Comprobar.dni(dni)) {
			errores++;
			Escribirtxt.Avisos("DNI incorrecto\r\n");
		}
		String fechaNac=datos[4];
		if(!Comprobar.fecha(fechaNac)) {
			errores++;
			Escribirtxt.Avisos("Fecha incorrecta");
		}
		String perfil= faux.minusculas(datos[1]);//DATOS 1 E O PERFIL
			if(perfil.equals("alumno")) {
					String fechaIng=datos[5];
					if(!Comprobar.fecha(fechaIng)) {
									errores++;
									Escribirtxt.Avisos("Fecha incorrecta");
									}
					if(!Comprobar.compara_fechas(fechaNac, fechaIng)) {
									errores++;
									Escribirtxt.Avisos("Fecha de ingreso incorrecta");
									}
					if(faux.existepersona(dni, mapaPersonas)) {
							errores++;
							Escribirtxt.Avisos("Alumno ya existente");
									}
					if(errores==0) {
								//temos que crear un mapa de asignaturas e agregalo no constructor, pero o mapa de doncenia?
								GregorianCalendar fechaNAC=cambios.String_GregorianCalendar(fechaNac);
								GregorianCalendar fechaING= cambios.String_GregorianCalendar(fechaIng);
								mapaPersonas.put(dni, new Alumno(dni,nombre,fechaNAC,fechaING));
									}		
				     return mapaPersonas;
						}
			
			else if(perfil.equals("profesor")) {
						String categoria=datos[5];
						String departamento=datos[6];
						Iterator it=mapaPersonas.keySet().iterator();
						if(faux.existepersona(dni, mapaPersonas)) {
							errores++;
							Escribirtxt.Avisos("Profesor ya existente");
						}
					if(errores==0) {
						GregorianCalendar fechaNAC=cambios.String_GregorianCalendar(fechaNac);
						mapaPersonas.put(dni, new Profesor(dni,nombre,fechaNAC,categoria,departamento));		
					}
			
		return mapaPersonas;
		
	}
			
		else return mapaPersonas;//comando incorrecto,tamen se pode E DEBERIASE(pola prioridade de fallos)facer antes de invocar o metodo este
			
			//O return esta pa que pare o fallo este dos huevos
			
			
		}			
	}
