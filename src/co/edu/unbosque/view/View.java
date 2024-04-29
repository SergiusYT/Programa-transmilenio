package co.edu.unbosque.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class View {

   
	public int ingresarOpcion() {
		
		 // Implementación las opciones del JComboBox
      String[] opciones = {"1) agregar personas a la cola de espera.\n", "2) Ver cola de Personas.\n", "3) desencolar y apilar personas en el Bus.\n",
    		  			 "4) ver pila de personas (BUS).\n","5) Generar GRAFO con rutas.\n","6) Desapilar personas (vaciar bus).\n","7) Salir.\n",
      					  };

      // Crear el JComboBox con las opciones creadas anteriormente
      JComboBox<String> comboBox = new JComboBox<>(opciones);
   // Crear un ImageIcon con la ruta de la imagen
      ImageIcon icono = new ImageIcon("Recursos/Estacion.gif");

      // Mostrar el JOptionPane con el JComboBox implementado
      int opcionElegida = JOptionPane.showOptionDialog(
              null,
              comboBox,
              "Bienvenido A Transmilenio",
              JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              icono,
              null,
              null
      );

      // Verificar si se seleccionó una opción y devolver el índice seleccionado
      if (opcionElegida == JOptionPane.OK_OPTION) {
          return comboBox.getSelectedIndex();
      } else if (opcionElegida == JOptionPane.CLOSED_OPTION) {
          // Si preciona cancel pues devuelve 6 para salir
          return 6;
      } else {
          // devuelve 6 si presion la x
          return 6;
      }
	}
	
	public String ingresarNombre() {
		
		String nombre = "";
        boolean nombreValido = false;
        
        while (!nombreValido) {
            nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la persona: ");
            
            // Validar que solo contenga letras y espacios
            Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
            Matcher matcher = pattern.matcher(nombre);
            
            if (matcher.matches()) {
                nombreValido = true;
            } else {
                JOptionPane.showMessageDialog(null, "El nombre ingresado no es válido. Por favor, ingrese solo letras y espacios.");
            }
        }
        
        return nombre;
	}
	
	
	
	public void mostrarMensaje(String msg) {
		
		 // Crear un ImageIcon con la ruta de la imagen
       ImageIcon icono = new ImageIcon("Recursos/Transmilenio.gif");

       // Mostrar el mensaje con la imagen
       JOptionPane.showMessageDialog(null, msg, "Proceso Final", JOptionPane.INFORMATION_MESSAGE, icono);	
       
	}

}
