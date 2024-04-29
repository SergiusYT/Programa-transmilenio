package co.edu.unbosque.controller;

import co.edu.unbosque.model.*;
import co.edu.unbosque.view.View;

public class Controller {

	private TransporteMasivo model;
	private View view;
	
	
	public Controller() {
		
		view = new View();
	    model = new TransporteMasivo();
		
		ejecutar();
	}
	
	public void ejecutar () {
				
						
	 boolean salir = false;
				
	 while(!salir) {
				
	 int  opcion = view.ingresarOpcion();
				
				switch(opcion) {
				
			  	case 0:
			  		
			  		String nombrePasajero = view.ingresarNombre();
	                
			  		view.mostrarMensaje(model.agregarColaPasajeros(nombrePasajero));
	                
	                break;	
			  	
			  	case 1:
			  		
			  		view.mostrarMensaje(model.verColaPasajeros());
			  				  		
			  		break;
				
			  	case 2:
			  					  		
			  	    model.ingresarPersonasBus();
			  	    view.mostrarMensaje("La cola de personas abordaron el transmilenio");
			  		
				  	break;	
				  	
			  	case 3:
			  		
			  		view.mostrarMensaje(model.verPilaBus());

				  	break;	  
				  	
			  	case 4:
			  		
			  		model.generarRuta();
			        model.visualizarGrafo();
			        
                    // Detener el bucle hasta que se cierre la ventana del grafo
                    while (model.isGrafoVisible()) {
                        try {
                            Thread.sleep(100); // Pausa para evitar que el bucle consuma muchos recursos
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
			  		
			  		break;
			  		
			  	case 5:
			  		
			  		view.mostrarMensaje(model.vaciarBus());
			  		
			  		break;
			  	
			  	case 6:
			  		
					view.mostrarMensaje("Nos veremos en otro momento :D");
					salir = true;
			  		
				  	break;	
				  	
				default:
					
					view.mostrarMensaje("Ingrese un número dentro del rango de opciones porfavor");					
					break;
			  
			  		
				}
			}
	 }
}
