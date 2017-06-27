import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import com.esri.runtime.ArcGISRuntime;

import javafx.scene.layout.BorderPane;

import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.map.ArcGISTiledMapServiceLayer;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
import com.esri.map.MapEvent;
import com.esri.map.MapEventListenerAdapter;
import com.esri.map.MapOptions;
import com.esri.map.MapOptions.MapType;

public class Principal {

  private JFrame window;
  private JMap map;
  
  public Principal() throws Exception {
	  JPanel panelPrincipal =new JPanel(new BorderLayout()); 
  	  JPanel panelMenu = new JPanel(new BorderLayout());
  	  JPanel panelMenuCapas = new JPanel(new BorderLayout());
  	  JPanel panelMapa = new JPanel(new BorderLayout());
  	  Menu menu = new Menu();
	EventoMapa eventoMapa = new EventoMapa();
	
	 for(int i=0; i<=5; i++){
	    	System.out.println("Realizo la iteración número "+i);
	    	map = eventoMapa.createMap(i, panelMenuCapas);
	    }
	
    window = new JFrame();
    window.setSize(1430, 850);
    window.setLocationRelativeTo(null); // center on screen
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setLayout(new BorderLayout(0, 0));
    
    
    panelMenu.setBackground(Color.white);
    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    panelMenu.setBorder(border);
  	panelMenu.setPreferredSize(new Dimension(1200, 80));
  	
  	panelMenuCapas.setPreferredSize(new Dimension(300, 660));
  	Border borderCapas = BorderFactory.createLineBorder(Color.BLACK, 1);
    panelMenuCapas.setBorder(borderCapas); 
    panelMenuCapas.setBackground(Color.white);
    
   
    Border borderMapa = BorderFactory.createLineBorder(Color.BLACK, 1);
    panelMapa.setBorder(borderMapa); 
    panelMapa.setPreferredSize(new Dimension(1114, 200));
    panelMapa.setBackground(Color.WHITE); 
    
    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
        map.dispose();
      }
    });

    
//    MapOptions mapOptions = new MapOptions(MapType.TOPO);
//    map = new JMap(mapOptions);
//    map.getLayers().add(tiledLayer);
//    map.setExtent(new Envelope(-10943738.01, 2152726.77, -10912321.29, 2170862.64));
//    GraphicsLayer graphicsLayer = new GraphicsLayer();
//    graphicsLayer.setName("Marker graphics");
//    map.getLayers().add(graphicsLayer);
//    System.out.println("Antes del for MAp= "+map);
//    for(int i=0; i<=5; i++){
//    	System.out.println("Realizo la iteración número "+i);
//    	map =eventoMapa.addLayers(map, i+1, panelMenuCapas);
//    }
//    System.out.println("MAp= "+map);
    menu.crearMenuCapas(map, panelMenuCapas);
    menu.crearMenu(panelMenu, map); 
//    eventoMapa.crearLayers(map, panelMenuCapas);  
    panelMapa.add(map, -1);
    panelPrincipal.add(panelMenu, BorderLayout.NORTH); 
    panelPrincipal.add(panelMenuCapas, BorderLayout.LINE_START);
    panelPrincipal.add(panelMapa,BorderLayout.EAST);
    window.getContentPane().add(panelPrincipal);
    
    
    
  }

  /**
   * Starting point of this application.
   * @param args
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {

      @Override
      public void run() {
        try {
          Principal application = new Principal();
          application.window.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}