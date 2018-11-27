
package ejecucion;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame{
    
    private CardLayout tarjeta; //CardLayout para poder cambiar entre paneles
    private JPanel panel; //Panel contenedor
    private JPanel inicio; //Panel del menu de inicio.
    private JPanel usuario; //Panel de nombre de usuario.
    private JPanel juego; //Panel de Juego
    private JPanel instruc; //Panel de instrucciones
    private JPanel topten;
    private JPanel creditos; //Panel para los créditos.
    private JLabel titulo; //Etiqueta que contiene la imagen del título.
    private JButton bJugar; //Botón de Jugar.
    private JButton bInstrucciones; //Botón de Instrucciones.
    private JButton bTop10; //Botón de top10 mejores jugadores.
    private JButton bCreditos; // Botón de los créditos.
    private JButton bSalir; //Botón para salir.
    private JButton bContinuar; //Botón para continuar
    private JLabel imagenCreditos; // Etiqueta que alberga la imagen de los créditos.
    private JLabel decoracion1; //Etiqueta de decoración.
    private JLabel decoracion2; //Etiqueta de decoración.
    private JLabel decoracion3; //Etiqueta de decoración.
    private Pelota pelota[][]; // Matriz de pelotas.
    private JLabel inombre; //Etiqueta para guardar la imagen que enseña "nombre"
    private JLabel nombre; //Etiqueta para guardar el nombre del usuario.
    private JLabel ipuntos; //Etiqueta para guardar la imagen que enseña "puntos"
    private JLabel puntos; //Etiqueta para guardar el puntaje del usuario.
    private JLabel imagenInstruc; // Imagen de las instrucciones
    private JLabel top10mejores; //Label que guarda la imagen del top10 mejores jugadores.
    private JButton pausa; //Botón para pausar el juego.
    private Timer timerBajada; // Timer para controlar la tarea de bajada.
    private TimerTask tareaBajada; // Tarea para controlar la bajada de las esferas.
    private int contador[]; // Atributo que funciona junto a la tarea para controlar la bajada de las esferas.
    private int aux[]; //Atributo que funciona junto a la tarea para controlar la posición que se necesita bajar.
    private boolean bajando[]; //Atributo que funciona junto a la tarea para marcar cuando hay una bajada en curso.
    private boolean clicPresionado; //Atributo para saber si el clic está presionado.
    private int pelotasVan; //Atributo para medir cuantas pelotas van seleccionadas.
    private Pelota pelotaSeleccionada[]; //Vector de pelotas auxiliar para el trazo.
    private int valorAcumulado; // Atributo para medir el valor de pelotas acumulado.
    private boolean valido; //Atributo para saber si el trazo es válido.
    private boolean selAntes; //Atributo para saber si se seleccionó antes.
    private boolean devolucion; //Atributo para saber si se está devolviendo.
    private JTextField txt_Nombre; //TextField para obtener el nombre del jugador.
    private boolean pausado; //Atributo que controla la pausa de juego.
    private int multiplicador; //Atributo multiplicador que controla el avance del juego.
    private JLabel[] puntuaciones; //Labels que guardan las puntuaciones.
    
    /**
     * Constructor por defecto de ventana.
     */
    public Ventana(){
        
        
        inicializar(); // Llamada al método que inicializa el frame.
        iniciarPanelInicio(); // Llamada al método que inicializa el panel de inicio.
        iniciarPanelUsuario();
        iniciarPanelJuego(); // Llamada al método que inicializa el panel.
        iniciarInstrucciones();
        iniciarTop();
        iniciarCreditos(); // Llamada al método que inicializa los créditos.
        iniciarBajada(); //Llamada al método que inicia el Timer que maneja la bajada de las pelotas.
        activarSonido(4);
        
        panel.add(inicio, "inicio"); //Se le agrega al panel contenedor los demás paneles.
        panel.add(juego, "juego");
        panel.add(instruc,"instrucciones");
        panel.add(topten,"topten");
        panel.add(creditos,"creditos");
        panel.add(usuario,"usuario");
        
        tarjeta.show(panel, "inicio"); //Se inicia el programa con el menú de inicio.
        
        // c f
        
    }
    
    /**
    * Método que inicializa el frame.
    *
    */
    private void inicializar(){ // Método para inicializar el frame.
        
        tarjeta = new CardLayout(); // Se inicializa el CardLayout.
        
        panel = new JPanel(tarjeta); //Se inicia el Panel contenedor con el CardLayout.
        
        setBounds(0,0,506,530); // Le da el tamaño y el lugar donde aparecerá en la pantalla.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Marca que se parará el programa al cerrar la ventana.
        setLayout(null); // Se deja el layout en nulo para colocar manualmente los componentes del juego.
        setResizable(false); //Se prohibe el cambio de tamaño de la ventana.
        setTitle("2248 por: Carlos Rosales y Luis Acosta");
        
        
        
        setContentPane(panel); //Se marca el panel contenedor como el panel por defecto.
        
    }
    
    /**
    * Método que inicializa el panel prinicpal.
    *
    */
    private void iniciarPanelInicio(){ // Método que inicializa el panel de inicio.
        
        inicio = new JPanel(); //Se crea el panel.
        inicio.setLayout(null); //Layout nulo para colocar componentes libremente.
        inicio.setBackground(new Color(7 ,105 ,178)); //Se setea el fondo deseado.
        
        titulo = new JLabel(); //Creada la etiqueta para el título.
        titulo.setIcon(new ImageIcon("imagenes/titulo.jpg")); // Seteada la imagen del título.
        titulo.setBounds(0,0,500,200); //Se setea la posición deseada.
        inicio.add(titulo); //Se agrega el título.
        titulo.setVisible(true); //Se hace visible el título.
        
        bJugar = new JButton(); //Creado el botón de Jugar.
        bInstrucciones = new JButton(); //Creado el botón de Instrucciones.
        bTop10 = new JButton(); //Creado el botón del top 10 mejores jugadores.
        bCreditos = new JButton(); //Creado el botón de los créditos.
        bSalir = new JButton(); //Creado el botón de Salir.
        
        bJugar.setIcon(new ImageIcon("imagenes/jugar.jpg")); // Seteada la imagen del botón de Jugar.
        bInstrucciones.setIcon(new ImageIcon("imagenes/instrucciones.jpg")); // Seteada la imagen del botón de Instrucciones.
        bTop10.setIcon(new ImageIcon("imagenes/top10.jpg")); // Seteada la imagen del botón del top 10 mejores jugadores.
        bCreditos.setIcon(new ImageIcon("imagenes/creditos.jpg")); // Seteada la imagen del botón de los créditos.
        bSalir.setIcon(new ImageIcon("imagenes/salir.jpg")); // Seteada la imagen del botón de salir.
        
        bJugar.setBounds(200,210,100,50); //Se setea la posición deseada.
        bInstrucciones.setBounds(200,270,100,50); //Se setea la posición deseada.
        bTop10.setBounds(200,330,100,50); //Se setea la posición deseada.
        bCreditos.setBounds(200,390,100,50); //Se setea la posición deseada.
        bSalir.setBounds(200,450,100,50); //Se setea la posición deseada.
        
        bJugar.addMouseListener(new listenerMenu()); //Se les agrega el listener de mouse.
        bInstrucciones.addMouseListener(new listenerMenu());
        bTop10.addMouseListener(new listenerMenu());
        bCreditos.addMouseListener(new listenerMenu());
        bSalir.addMouseListener(new listenerMenu());
        
        inicio.add(bJugar); //Se agrega el botón de Jugar.
        inicio.add(bInstrucciones); //Se agrega el botón de Instrucciones.
        inicio.add(bTop10); //Se agrega el botón del top 10 mejores jugadores.
        inicio.add(bCreditos); //Se agrega el botón de los créditos.
        inicio.add(bSalir); //Se agrega el botón de salir.
        
        bJugar.setVisible(true); //Se hace visible el botón de Jugar
        bInstrucciones.setVisible(true); //Se hace visible el botón de Instrucciones
        bTop10.setVisible(true); //Se hace visible el botón del top 10 mejores jugadores
        bCreditos.setVisible(true); //Se hace visible el botón de los créditos
        bSalir.setVisible(true); //Se hace visible el botón de salir.
        
        decoracion1 = new JLabel(); //Se le agrega unas pelotas de decoración.
        decoracion2 = new JLabel();
        decoracion3 = new JLabel();
        
        decoracion1.setIcon(new ImageIcon("imagenes/8.jpg"));
        decoracion2.setIcon(new ImageIcon("imagenes/32.jpg"));
        decoracion3.setIcon(new ImageIcon("imagenes/512.jpg"));
        
        decoracion1.setBounds(50,340,50,50);
        decoracion2.setBounds(415,435,50,50);
        decoracion3.setBounds(340,220,50,50);
        
        inicio.add(decoracion1);
        inicio.add(decoracion2);
        inicio.add(decoracion3);
        
        decoracion1.setVisible(true);
        decoracion2.setVisible(true);
        decoracion3.setVisible(true);
        
        
        inicio.setVisible(true); //Se hace visible el panel.
        
    }
    
    /**
    * Método que inicializa el panel de nombre de usuario.
    *
    */
    private void iniciarPanelUsuario(){
        
        usuario = new JPanel(); //Se crea el panel.
        usuario.setLayout(null); //Layout nulo para colocar componentes libremente.
        usuario.setBackground(new Color(7 ,105 ,178)); //Se setea el fondo deseado.
        
        bContinuar = new JButton("Seguir");
        bContinuar.setIcon(new ImageIcon("imagenes/continuar.jpg"));
        bContinuar.setBounds(200, 300, 90, 50);
        bContinuar.addMouseListener(new listenerMenu());
        bContinuar.setVisible(true);
        usuario.add(bContinuar);
        
        txt_Nombre = new JTextField("Ingrese su nombre aqui");
        txt_Nombre.setBounds(175, 250, 150, 30);
        txt_Nombre.setBackground(new Color(7 ,105 ,178));
        txt_Nombre.setVisible(true);
        usuario.add(txt_Nombre);
        
    }
    
    /**
    * Método que inicializa el panel de juego.
    *
    */
    private void iniciarPanelJuego(){
        pausado = false; //Se inicia el juego sin pausa.
        juego = new JPanel();
        juego.setLayout(null); // Se deja el layout en nulo para colocar manualmente los componentes del juego.
        juego.setBackground(new Color(7 ,105 ,178));
        iniciarPelotas(); // Llamada al método que inicializa las pelotas.
        inombre = new JLabel();
        inombre.setBounds(385,50,100,50);
        inombre.setIcon(new ImageIcon("imagenes/nombre.jpg"));
        inombre.setVisible(true);
        juego.add(inombre);
        nombre = new JLabel();
        nombre.setBounds(395,110,200,50);
        nombre.setFont(new Font("Serif",Font.PLAIN,18));
        nombre.setVisible(true);
        juego.add(nombre);
        ipuntos = new JLabel();
        ipuntos.setBounds(385,170,100,50);
        ipuntos.setIcon(new ImageIcon("imagenes/puntos.jpg"));
        ipuntos.setVisible(true);
        juego.add(ipuntos);
        puntos = new JLabel("0");
        puntos.setBounds(395,230,160,50);
        puntos.setFont(new Font("Serif",Font.PLAIN,18));
        puntos.setVisible(true);
        juego.add(puntos);
        pausa = new JButton();
        pausa.setIcon(new ImageIcon("imagenes/pausa.jpg"));
        pausa.setBounds(450, 0, 50, 50);
        pausa.addMouseListener(new listenerMenu());
        pausa.setVisible(true);
        juego.add(pausa);
        JButton bVolver = new JButton(); // Boton para volver al menu principal.
        bVolver.setIcon(new ImageIcon("imagenes/volver.jpg")); // Seteada la imagen del botón de volver.
        bVolver.setBounds(400,450,100,50); //Se setea la posición deseada
        bVolver.setVisible(true);
        bVolver.addMouseListener(new listenerMenu());
        juego.add(bVolver);
    }
    
    
    /**
    * Método que inicializa las pelotas.
    *
    */
    private void iniciarPelotas(){
        
        clicPresionado=false; // Se inicializa el atributo de medir si un clic está presionado.
        pelotasVan = 0; //Se inicia el contador de pelotas seleccionadas.
        pelotaSeleccionada = new Pelota[48]; //Se inicializa el vector de pelotas auxiliares
        pelota = new Pelota[6][]; // Se inicializa la matriz de pelotas.
        valorAcumulado = 0; //Se inicializa el atributo para medir el valor acumulado en el trazo.
        valido = true; //Se inicializa el atributo para saber si el trazo es válido.
        selAntes = false; //Se inicializa el atributo para saber si habia seleccionada antes una pelota.
        
        for (int i = 0; i < pelota.length; i++) { // Ciclo exterior, para la inicializar cada pelota.
            
            pelota[i] = new Pelota[8];
            
            for (int j = 0; j < pelota[i].length; j++) { // Ciclo para inicializar individualmente cada pelota.
                
                pelota[i][j] = new Pelota();
                pelota[i][j].setBounds(5+(i*60),5+(j*60),50,50); //Se coloca la pelota en la posición indicada.
                pelota[i][j].setVisible(true); // Se hace visible la pelota.
                pelota[i][j].addMouseListener(new listenerPelota()); //Se le agrega el Mouse Listener para las pelotas.
                juego.add(pelota[i][j]); // Se añade la pelota al panel.
                
            }
            
        }
        
        resetPelotas(); // Se llama al método de reiniciar pelotas para darle valores aleatorios iniciales.
        
    }
    
    
    /**
    * Método que inicializa las instrucciones
    *
    */
    private void iniciarInstrucciones(){
        
        instruc = new JPanel();
        instruc.setLayout(null);
        instruc.setBackground(new Color(7 ,105 ,178));
        
        imagenInstruc = new JLabel();
        imagenInstruc.setIcon(new ImageIcon("imagenes/comojugar.jpg")); //Imágen que contiene los créditos.
        imagenInstruc.setBounds(0,0,500,500);
        
        instruc.add(imagenInstruc);
        imagenInstruc.setVisible(true);
        
        JButton bVolver = new JButton(); // Boton para volver al menu principal.
        bVolver.setIcon(new ImageIcon("imagenes/volver.jpg")); // Seteada la imagen del botón de volver.
        bVolver.setBounds(400,450,100,50); //Se setea la posición deseada
        bVolver.addMouseListener(new listenerMenu());
        bVolver.setVisible(true);
        instruc.add(bVolver);
        
    }
    
    /**
     * Método que inicializa el panel de TOP 10 jugadores.
     */
    private void iniciarTop(){
        
        topten = new JPanel(); //Se crea el panel.
        topten.setLayout(null); //Layout nulo para colocar componentes libremente.
        topten.setBackground(new Color(7 ,105 ,178)); //Se setea el fondo deseado.
        
        top10mejores = new JLabel();
        top10mejores.setBounds(0, 0, 500, 100);
        top10mejores.setIcon(new ImageIcon("imagenes/top10mejores.jpg"));
        top10mejores.setVisible(true);
        topten.add(top10mejores);
        
        puntuaciones = new JLabel[10];
        for(int i = 0; i < puntuaciones.length; i++){
            puntuaciones[i] = new JLabel();
            puntuaciones[i].setBounds(130, 110+(i*35), 250, 30);
            puntuaciones[i].setVisible(true);
            puntuaciones[i].setFont(new Font("Serif",Font.PLAIN,18));
            topten.add(puntuaciones[i]);
        }
        
        cargarPuntuaciones();
        
        JButton bVolver = new JButton(); // Boton para volver al menu principal.
        bVolver.setIcon(new ImageIcon("imagenes/volver.jpg")); // Seteada la imagen del botón de volver.
        bVolver.setBounds(400,450,100,50); //Se setea la posición deseada
        bVolver.addMouseListener(new listenerMenu());
        bVolver.setVisible(true);
        topten.add(bVolver);
        
    }
    
    /**
     * Método que carga las puntuaciones desde el .txt
     */
    private void cargarPuntuaciones(){
        
        FileReader Archivo = null;
        BufferedReader lector = null; //Objetos para leer el archivo.
        try {
            
            Archivo = new FileReader("puntuaciones.txt"); //Se abre el archivo donde están las puntuaciones.
            lector = new BufferedReader(Archivo);
            
            for(int i = 0; i < 10; i++){ //Se realiza el cargado en los label.
                
                int posicion;
                posicion = i + 1;
                String auxiliar = String.valueOf(posicion) + ".- " + lector.readLine();
                puntuaciones[i].setText(auxiliar);
                System.out.println(puntuaciones[i].getText());
                
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Archivo.close();
                lector.close(); //Se cierran los objetos de lectura.
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /**
     * Método invocado para guardar un nuevo puntaje.
     */
    private void guardarNuevoPuntaje(){
        
        FileReader Archivo = null;
        BufferedReader lector = null; //Objetos para leer el archivo.
        try {
            
            Archivo = new FileReader("puntuaciones.txt"); //Se abre el archivo donde están las puntuaciones.
            lector = new BufferedReader(Archivo);
            String puntuaString1[] = new String[10];
            String puntuaString2[] = new String[10]; //Se crean 2 cadenas de strings para manipular el archivo.
            boolean cambio = false;
            
            for(int i = 0; i < 10; i++){
                
                puntuaString1[i] = lector.readLine(); //Se guardan en ambos vector de String los datos leidos del archivo.
                puntuaString2[i] = puntuaString1[i];
                
            }
            
            for(int i = 0; i < 10; i++){
                
                String parts[] = puntuaString1[i].split("-");
                int puntosAux = Integer.parseInt(parts[1]);
                
                if(puntosAux < Integer.parseInt(puntos.getText()) && !cambio){ // Se comprueba en qué linea tiene que posicionarse la nueva puntuación.
                    cambio = true;
                    for(int j = 8; j >= i; j--){
                        puntuaString2[j+1] = puntuaString2[j];
                    }
                    String nuevodato = nombre.getText() + "-" + puntos.getText();
                    puntuaString2[i] = nuevodato;
                    
                }
                
            }
            
            if(cambio){
                
                FileWriter aEscribir = new FileWriter("puntuaciones.txt"); //Se crean objetos para sobreescribir el archivo.
                BufferedWriter escritura = new BufferedWriter(aEscribir);
                
                for(int i = 0; i < 10; i++){
                    escritura.write(puntuaString2[i]); //Se introducen los nuevos datos.
                    escritura.newLine();
                }
                
                escritura.close(); //Se cierra la escritura de los archivos.
                aEscribir.close();
                
            }
            
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Archivo.close(); //Se cierra la lectura.
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        puntos.setText("0");
        
    }
    
    
    /**
    * Método que inicializa los créditos.
    *
    */
    private void iniciarCreditos(){ // Método que inicia la pantalla de créditos
        creditos = new JPanel(); //Se crea el panel correspondiente con los atributos necesarios.
        creditos.setLayout(null);
        creditos.setBackground(new Color(7 ,105 ,178));
        
        imagenCreditos = new JLabel();
        imagenCreditos.setIcon(new ImageIcon("imagenes/desarrolladores.jpg")); //Imágen que contiene los créditos.
        imagenCreditos.setBounds(0,0,500,500);
        
        creditos.add(imagenCreditos); //Se agrega y se hace visible.
        imagenCreditos.setVisible(true);
        JButton bVolver = new JButton(); // Boton para volver al menu principal.
        bVolver.setIcon(new ImageIcon("imagenes/volver.jpg")); // Seteada la imagen del botón de volver.
        bVolver.setBounds(400,450,100,50); //Se setea la posición deseada
        bVolver.addMouseListener(new listenerMenu());
        bVolver.setVisible(true);
        creditos.add(bVolver);
    }
    
    
    /**
    * Método que reinicia la matriz de pelotas.
    *
    */
    private void resetPelotas(){ // Método para darle valores aleatorios a la pelota de tablero de juego.
        
        
        multiplicador = 1;
        
        
        for (int i = 0; i < pelota.length; i++) {
            
            for (int j = 0; j < pelota[i].length; j++) {
                
                pelota[i][j].setValor(valorAleatorioInicial()); // Se coloca el valor inicial para la pelota.
                pelota[i][j].cambiarImagen(); // Se setea la imagen inicial.
                Pelota pelotaza = new Pelota();
                
            }
            
        }
        
    }
    
    
    /**
    * Método que inicializa la bajada de las pelotas.
    *
    */
    private void iniciarBajada(){
        
        contador = new int[pelota.length]; //Se crea un contador que manejará la cantidad de veces que se debe bajar cada pelota por espacio.
        aux = new int[pelota.length]; //Un auxiliar encargado de albergar la posición vacia hasta la cual hay que bajar.
        bajando = new boolean[pelota.length]; //Atributo que marca si hay una bajada en curso.
        for(int i=0;i<bajando.length;i++){ //Se inicializan todos los atributos anteriores.
            bajando[i] = false;
            aux[i] = 0;
            contador[i] = 0;
        }
        
        timerBajada = new Timer(); //Se inicializa el Timer para la bajada de pelotas.
        
        tareaBajada = new TimerTask(){ //Se inicializa la tarea para la bajada de pelotas.
          
            @Override
            public void run(){ // Método run de Timer.
                
                for(int k = 0; k < pelota.length; k++){ //Ciclo externo que maneja cada columna individualmente.
                    
                contador[k]++; //Se suma el contador para marcar que se ha realizado una iteración
                if(pelota[k][0].getValor() == 0 && !bajando[k]){ //Si se encuentra en la parte máxima de la matriz un espacio vacio, se genera un nuevo número aleatorio.
                    pelota[k][0].setValor(valorAleatorioInicial());
                    pelota[k][0].cambiarImagen();
                }
                
                for(int i=pelota[k].length-1;i>0;i--){ //Ciclo para identificar si existe un espacio vacio en la columna.
                    
                    if(pelota[k][i].getValor() == 0){
                        
                        if(!bajando[k]){ // Se comprueba que no haya una bajada en curso.
                            contador[k]=0; //Se inicia el contador en cero para iniciar la bajada.
                            aux[k] = i-1; //Se marca el auxiliar con la posición hasta la cual se debe bajar.
                            bajando[k] = true; //Se marca que comenzará la bajada.
                        }
                        
                    }
                    
                    
                    
                }
                
                if(bajando[k]){ //Se comprueba si hay una bajada en curso.
                        
                    if(contador[k]<10){ //Condicional para comprobar si ya se ha bajado hasta el máximo.
                            
                        for(int j = aux[k]; j >= 0; j--){
                        
                            pelota[k][j].setLocation( pelota[k][j].getX(), pelota[k][j].getY()+5); //Se baja la pelota.
                        
                        }
                            
                    }else{
                        bajando[k]=false; //Se para la bajada al llegar al máximo.
                        for(int j = aux[k]; j >= 0; j--){ //Ciclo para reordenar las pelotas en la matriz lógica y asignarles los valores correspondientes después de la bajada.
                        
                            pelota[k][j+1].setValor(pelota[k][j].getValor()); //Se le asigna a cada pelota su valor correspodiente.
                            pelota[k][j].setLocation( pelota[k][j].getX(), pelota[k][j].getY()-50); //Se devuelven a su posición original con el valor nuevo.
                            if(j == 0){ //Si se está en el máximo se cambia el valor a 0.
                                pelota[k][j].setValor(0);
                                pelota[k][j].cambiarImagen();
                            }
                            pelota[k][j+1].cambiarImagen(); //Se refrescan las imágenes.
                            
                        }
                            
                    }
                        
                }
                    
                }
                
            }
            
        };
        
        timerBajada.schedule(tareaBajada, 0, 40); //Se inicia la tarea.
        
    }
    
    /**
    * Retorna un valor aleatorio inicial para las pelotas según el multiplicador actual.
    *@return Retorna el valor aleatorio según el multiplicador actual.
    */
    private int valorAleatorioInicial(){ // Método para generar valores aleatorios iniciales para pelotas.
        
        int aleatorio; //Se crea la variable donde se va a albergar el número aleatorio.
        
        aleatorio = (int)(Math.random() * 8) + 2; // Marcamos un número en un rango de 2 a 8.
        
        while( ((aleatorio % 2) != 0)){ //Se realizan las comprobaciones para asegurarse de que sea un número par.
            aleatorio = (int)(Math.random() * 8) + 2;
        }
        if(aleatorio == 6){ // Sí es un 6 se convierte en 2 para aumentar las posibilidades de 2.
            aleatorio = 2;
        }
        
        return aleatorio * multiplicador; // Se devuelve el número aleatorio.
    }
    
    
    /**
    * Clase de tipo MouseListener que maneja los botones del juego.
    *
    */
    private class listenerMenu extends MouseAdapter{ //Clase para detectar el evento de presionar los botones del menú.
        @Override
        public void mouseClicked(MouseEvent event){
            
            if(event.getSource().equals(bJugar)){
                tarjeta.show(panel, "usuario"); // Se detecta el botón para iniciar el juego.
                txt_Nombre.setText("Ingrese su nombre aquí.");
                resetPelotas();
                
            }else if(event.getSource().equals(bContinuar)){
                puntos.setText("0");
                tarjeta.show(panel, "juego");
                String name = txt_Nombre.getText();
                if(name.length() > 8){
                    name = name.substring(0, 8);
                }
                nombre.setText(name);
                
            }else if(event.getSource().equals(bInstrucciones)){
                
                tarjeta.show(panel, "instrucciones");
                
            }else if(event.getSource().equals(bTop10)){
                
                cargarPuntuaciones();
                tarjeta.show(panel, "topten");
                
            }else if(event.getSource().equals(bCreditos)){
                tarjeta.show(panel, "creditos"); // Se detecta el botón para entrar a los créditos.
                
            }else if(event.getSource().equals(bSalir)){
                System.exit(0); // Se detecta el botón para salir de juego.
                
            }else if(event.getSource().equals(pausa)){
                pausado = !pausado;
                
            }else{
                
                tarjeta.show(panel, "inicio");
                if(!"0".equals(puntos.getText())){
                    
                    guardarNuevoPuntaje();
                    System.out.println("hay que guardar puntos");
                }
                
            }
            
            activarSonido(1);
            
        }
        
        @Override
        public void mouseEntered(MouseEvent e){
            
            activarSonido(3);
            
        }
    
    }
    
    
    /**
    * Clase de tipo MouseListener que realiza acciones para pelotas.
    *
    */
    private class listenerPelota extends MouseAdapter{
        
        @Override
        
        public void mousePressed(MouseEvent event){ //Evento para detectar el inicio de un trazo.
            
            for(int i = 0;i < pelota.length; i++){
                
                for(int j = 0; j < pelota[i].length;j++){
                    
                    if(event.getSource().equals(pelota[i][j]) && !pausado){ //Se comprueba cual pelota fue presionada y que no esté pausado el juego.
                        
                        pelotaSeleccionada[pelotasVan] = pelota[i][j];
                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                        pelotaSeleccionada[pelotasVan].ponerBorde();
                        pelotasVan++;
                        clicPresionado = true;
                        
                    }
                    
                }
                
            }
            
        }
        
        @Override
        public void mouseEntered(MouseEvent event){
            
            devolucion = false;
            
            for(int i = 0; i<pelotasVan; i++){ //Se comprueba si hay una devolución.
                if (event.getSource().equals(pelotaSeleccionada[i])){
                    for(int j = pelotasVan-1; j >= i+1; j--){
                        pelotaSeleccionada[j].quitarBorde();
                        pelotasVan--;
                        valorAcumulado -= pelotaSeleccionada[j].getValor();
                    }
                    devolucion = true;
                }
            }
            
            
            if(clicPresionado && !devolucion){ //Se sigue el trazo si no hay una devolución y hay uno iniciado.
                
                for(int i = 0;i < pelota.length; i++){
                    
                    for(int j = 0; j < pelota[i].length;j++){
                        
                        for(int k=0; k < pelotasVan; k++){ 
                            if(event.getSource().equals(pelotaSeleccionada[k])){ //Se evita que la pelota no pueda ser seleccionada dos veces.
                                selAntes = true;
                            }
                        }
                        
                    }
                    
                }
                
                
                for(int i = 0;i < pelota.length; i++){
                
                    for(int j = 0; j < pelota[i].length;j++){
                    
                        if(event.getSource().equals(pelota[i][j]) && !selAntes){ //Se comprueba que pelota siguió el trazo y que no haya sido seleccionada antes.
                            
                            if(i==0 && j ==0){
                                
                                if(pelota[i+1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i][j+1].equals(pelotaSeleccionada[pelotasVan-1])  //Serie de ifs anidados para comprobar que solo se usen casillas adyacentes.
                                   || pelota[i+1][j+1].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j]; //Se guarda la pelota seleccionada en el vector de pelotas seleccionadas.
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor() //Se comprueba que la unión pueda ser realizada.
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor(); //Se suma el valor de la pelota trazada al acumulado.
                                        pelotaSeleccionada[pelotasVan].ponerBorde(); //Se marca como seleccionada.
                                        pelotasVan++; //Se suma la cantidad de pelotas que van.
                                    }
                                    
                                    
                                }
                                
                            }else if(i == 0 && j == pelota[i].length-1){
                                
                                if(pelota[i][j-1].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i+1][j-1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i+1][j].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j];
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor()
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                                        pelotaSeleccionada[pelotasVan].ponerBorde();
                                        pelotasVan++;
                                    }
                                    
                                }
                                
                            }else if(i==0){
                                
                                if(pelota[i][j-1].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i+1][j-1].equals(pelotaSeleccionada[pelotasVan-1]) ||
                                   pelota[i+1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i][j+1].equals(pelotaSeleccionada[pelotasVan-1]) 
                                   || pelota[i+1][j+1].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j];
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor()
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                                        pelotaSeleccionada[pelotasVan].ponerBorde();
                                        pelotasVan++;
                                    }
                                    
                                }
                                
                            }else if(i == pelota.length-1 && j == 0){
                                
                                if(pelota[i-1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i-1][j+1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i][j+1].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j];
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor()
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                                        pelotaSeleccionada[pelotasVan].ponerBorde();
                                        pelotasVan++;
                                    }
                                    
                                }
                                
                            }else if(j==0){
                                
                                if(pelota[i+1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i][j+1].equals(pelotaSeleccionada[pelotasVan-1]) 
                                   || pelota[i+1][j+1].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i-1][j+1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i-1][j].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j];
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor()
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                                        pelotaSeleccionada[pelotasVan].ponerBorde();
                                        pelotasVan++;
                                    }
                                    
                                }
                                
                            }else if(i==pelota.length-1 && j == pelota[i].length-1){
                                
                                if(pelota[i-1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i-1][j-1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i][j-1].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j];
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor()
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                                        pelotaSeleccionada[pelotasVan].ponerBorde();
                                        pelotasVan++;
                                    }
                                    
                                }
                                
                            }else if(i==pelota.length-1){
                                
                                if(pelota[i-1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i-1][j+1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i][j+1].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i-1][j-1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i][j-1].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j];
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor()
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                                        pelotaSeleccionada[pelotasVan].ponerBorde();
                                        pelotasVan++;
                                    }
                                    
                                }
                                
                            }else if(j==pelota[i].length-1){
                                
                                if(pelota[i-1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i-1][j-1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i][j-1].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i+1][j-1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i+1][j].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j];
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor()
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                                        pelotaSeleccionada[pelotasVan].ponerBorde();
                                        pelotasVan++;
                                    }
                                    
                                }
                                
                            }else{
                                
                                if(pelota[i+1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i][j+1].equals(pelotaSeleccionada[pelotasVan-1]) 
                                   || pelota[i+1][j+1].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i-1][j+1].equals(pelotaSeleccionada[pelotasVan-1])
                                   || pelota[i-1][j].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i][j-1].equals(pelotaSeleccionada[pelotasVan-1]) 
                                   || pelota[i+1][j-1].equals(pelotaSeleccionada[pelotasVan-1]) || pelota[i-1][j-1].equals(pelotaSeleccionada[pelotasVan-1])){
                                    
                                    pelotaSeleccionada[pelotasVan] = pelota[i][j];
                                    if(pelotaSeleccionada[pelotasVan].getValor() == pelotaSeleccionada[pelotasVan-1].getValor()
                                       || (pelotaSeleccionada[pelotasVan].getValor() <= valorAcumulado && 
                                           pelotaSeleccionada[pelotasVan].getValor() / 2 == pelotaSeleccionada[pelotasVan-1].getValor())){
                                        valorAcumulado+=pelotaSeleccionada[pelotasVan].getValor();
                                        pelotaSeleccionada[pelotasVan].ponerBorde();
                                        pelotasVan++;
                                    }
                                    
                                }
                                
                            }
                            
                        
                        }
                    
                    }
                
                }
                
            }
            
            selAntes=false;
            
        }
        
        @Override
        public void mouseReleased(MouseEvent event){ //Evento para comprobar el fin del trazo
            
            if(clicPresionado){ //Se comprueba que antes hubiese un trazo en curso.
                
                for(int i = 0;i < pelota.length; i++){
                
                    for(int j = 0; j < pelota[i].length;j++){
                    
                        if(event.getSource().equals(pelota[i][j])){
                            
                            for(int k = 0; k < pelotasVan; k++){ //Se deseleccionan todas las pelotas trazadas y se limpia su valor.
                                pelotaSeleccionada[k].quitarBorde();
                                if(k>0){
                                    pelotaSeleccionada[k-1].setValor(0);
                                    pelotaSeleccionada[k-1].cambiarImagen();
                                }
                            }
                            
                            if(valorAcumulado == 2){ // Ifs anidados para colocarle cambiarle el valor a una esfera al final de un trazo.
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(2);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                
                            }else if(valorAcumulado <4){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(2);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                
                            }else if(valorAcumulado <8){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(4);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                
                            }else if(valorAcumulado <16){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(8);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                
                            }else if(valorAcumulado <32){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(16);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                
                            }else if(valorAcumulado <64){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(32);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                multiplicador = 2;
                                
                            }else if(valorAcumulado <128){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(64);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                multiplicador = 4;
                                
                            }else if(valorAcumulado <256){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(128);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                multiplicador = 8;
                                
                            }else if(valorAcumulado <512){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(256);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                multiplicador = 16;
                                
                            }else if(valorAcumulado <1024){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(512);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                multiplicador = 32;
                                
                            }else if(valorAcumulado <2048){
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(1024);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                multiplicador = 64;
                                
                            }else{
                                
                                pelotaSeleccionada[pelotasVan-1].setValor(2048);
                                pelotaSeleccionada[pelotasVan-1].cambiarImagen();
                                multiplicador = 64;
                                
                            }
                            
                            if(valorAcumulado > 2 && pelotasVan > 1){
                                String puntaje = puntos.getText();
                                int puntajeint = Integer.parseInt(puntaje);
                                puntajeint += valorAcumulado;
                                puntaje = String.valueOf(puntajeint);
                                puntos.setText(puntaje);
                            }
                            
                            if(pelotasVan > 1)activarSonido(2);
                            
                            valorAcumulado=0;
                            pelotasVan=0;
                            clicPresionado = false;
                            
                        
                        }
                    
                    }
                
                }
                
            }
            
        }
        
    }
    
    
    /**
    *Método que activa un sonido específico.
    *@param i Número de sonido a reproducir.
    */
    private void activarSonido(int i){ //Método para activar el sonido.
        
        try {
            
            String ruta = "sonido/";
            ruta += String.valueOf(i);
            ruta+=".wav"; //Se obtiene la ruta del archivo a reproducir.
            
            File file = new File(ruta); //Se crea el nuevo objeto tipo file.
            
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}


