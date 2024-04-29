package co.edu.unbosque.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class TransporteMasivo {
	
	
    private Queue<Personas> colaPersonas;
    private Stack<String> pilaBus;
    private Graph<String, DefaultEdge> rutas;
    private JFrame frame;



    
    public TransporteMasivo() {
    
    	colaPersonas = new LinkedList<>();
    	pilaBus = new Stack<>();
    	
    	  // Crear el grafo
        rutas = new SimpleGraph<>(DefaultEdge.class);
        frame = new JFrame();

    }
    
    
    public String agregarColaPasajeros(String nombre) {
    	
    	if(nombre.isEmpty()) {
    	
    		return "No se ingreso ninguna Persona";
    	}
    	
    	colaPersonas.add(new Personas(nombre));
    	
    	return "Se ingreso la Persona "+ nombre +" Correctamente a la Cola de espera";
    }
    
    public String verColaPasajeros() {
        
    	if (colaPersonas.isEmpty()) {
            return "No hay ninguna persona esperando el transmilenio";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Personas en cola: ");
        for (Personas persona : colaPersonas) {
            sb.append(persona.getNombre()).append(", ");
        }
        // Eliminar la última coma y espacio adicionales
        sb.delete(sb.length() - 2, sb.length());
        
        return sb.toString();
    }

    
    
    public void ingresarPersonasBus() {
    	
    	Stack<String> pilaTemporal = new Stack<>();

        // Transferir elementos de la cola a la pila temporal
        while (!colaPersonas.isEmpty()) {
            pilaTemporal.push(colaPersonas.poll().getNombre());
        }

        // Transferir elementos de la pila temporal a la pila final (en el orden invertido)
        while (!pilaTemporal.isEmpty()) {
            pilaBus.push(pilaTemporal.pop());
        }

    }
    
    
    public String verPilaBus() {
        
    	if (pilaBus.isEmpty()) {
            return "La pila del transmilenio está vacía";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Personas en la pila del transmilenio: ");
        for (String nombre : pilaBus) {
            sb.append(nombre).append(", ");
        }
        // Eliminar la última coma y espacio adicionales
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public void generarRuta() {
        // Añadir nodos (portales)
        String[] portales = {"Portal Norte", "Portal 80", "Dorado", "Americas", "Portal Sur"};
        for (String portal : portales) {
            rutas.addVertex(portal);
        }

        // Crear aristas aleatorias entre los nodos
        Random rand = new Random();
        for (int i = 0; i < portales.length; i++) {
            for (int j = i + 1; j < portales.length; j++) {
                // Probabilidad de agregar una arista entre dos nodos
                if (rand.nextDouble() < 0.5) { // Cambia el valor de 0.5 según lo desees
                    agregarRutas(portales[i], portales[j]);
                }
            }
        }
        
     // Añadir botón para regresar al menú principal
        JButton backButton = new JButton("Regresar al Menú");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra la ventana del grafo
                // Podrías agregar aquí cualquier acción adicional que desees realizar al regresar al menú principal
            }
        });
        frame.add(backButton, BorderLayout.SOUTH); // Añade el botón al panel sur de la ventana
    
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("<html>Puedes mover los portales con el cursor deslizandolos igualmente<br> dandole un clik en el centro de un portal puede agregar una nueva ruta a este</html>");
        panel.add(label, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.NORTH);        

    }

    

    
    public void agregarRutas(String origen, String destino) {
        rutas.addEdge(origen, destino);
    }
    
    
    public void visualizarGrafo() {
        JGraphXAdapter<String, DefaultEdge> adapter = new JGraphXAdapter<>(rutas);
        mxGraphComponent graphComponent = new mxGraphComponent(adapter);
        frame.add(graphComponent);
        frame.pack();
        frame.setTitle("Rutas generadas");
        frame.setVisible(true);
        frame.setBounds(500,150,480,420);

        // Ajustar el diseño del grafo
        mxCircleLayout layout = new mxCircleLayout(adapter);
        layout.execute(adapter.getDefaultParent());
    }
    
    
    public boolean isGrafoVisible() {
        return frame.isVisible();
    }
    
    public String vaciarBus() {
        
    	if(pilaBus.isEmpty()) {
    		
    		return "No se puede vaciar el bus porque no hay pasajeros";
    	}
    	
    	pilaBus.clear();
    	
		return "Se vacio el transmilenio correctamente";

    }

}
