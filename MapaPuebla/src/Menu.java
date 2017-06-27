import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;
import javax.swing.border.LineBorder;

import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
import com.esri.toolkit.legend.JLegend;
import com.esri.toolkit.overlays.DrawingCompleteEvent;
import com.esri.toolkit.overlays.DrawingCompleteListener;
import com.esri.toolkit.overlays.DrawingOverlay;
import com.esri.toolkit.overlays.DrawingOverlay.DrawingMode;

import javafx.scene.layout.Border;

public class Menu {
	
	public void crearMenu (JPanel panelMenu, JMap map) {
		JPanel jpanelHerramientas = new JPanel(new BorderLayout());
		jpanelHerramientas.setBackground(Color.white);
		jpanelHerramientas.setPreferredSize(new Dimension(1200, 20));
		
		JPanel jpanelIconos = new JPanel(new BorderLayout()); 
		//jpanelIconos.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); 
		jpanelIconos.setBackground(Color.white);
		jpanelIconos.setPreferredSize(new Dimension(1200, 10));
		
		JPanel jpanelIconosD = new JPanel(new BorderLayout()); 
		//jpanelIconosD.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); 
		jpanelIconosD.setBackground(Color.white);
		jpanelIconosD.setPreferredSize(new Dimension(1200, 10));
		
		 GraphicsLayer graphicsLayer = new GraphicsLayer();
	     map.getLayers().add(graphicsLayer);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		
		JMenu subMenuArchivo = new JMenu("");
		
        JMenu menuArchivo = new JMenu("Archivo");
        agregarCaracteristicasMenu(menuArchivo, "Archivo");
        menuBar.add(menuArchivo);
        
        JMenuItem menuItemArchivo = new JMenuItem();
        menuArchivo.add(agregarCaracteristicasItem(menuItemArchivo, "", "Guardar mapa", "Guardar mapa"));
        menuArchivo.addSeparator();
        menuArchivo.add(agregarCaracteristicasItem(menuItemArchivo, "", "Configurar impresión", "Configurar impresión"));
        menuArchivo.add(agregarCaracteristicasItem(menuItemArchivo, "", "Imprimir mapa", "Imprimir mapa"));
        menuArchivo.add(agregarCaracteristicasItem(menuItemArchivo, "", "Exportar mapa", "Exportar mapa"));
        menuArchivo.addSeparator();
        menuArchivo.add(agregarCaracteristicasItem(menuItemArchivo, "", "Salir del sistema", "Salir sistema"));
        
        /*Inicia menu de vista*/
        JMenu menuVista = new JMenu("Vista");
        agregarCaracteristicasMenu(menuVista, "Vista");
        menuBar.add(menuVista);
        
        JMenuItem menuItemVista = new JMenuItem();
        menuVista.add(agregarCaracteristicasItem(menuItemVista, "", "Vista grafíca", "Vista grafíca"));
        menuVista.add(agregarCaracteristicasItem(menuItemVista, "", "Vista diseño", "Vista diseño"));
        menuVista.addSeparator();
        menuVista.add(agregarCaracteristicasItem(menuItemVista, "", "Rota la vista del documento actual", "Rota vista"));
        menuVista.add(agregarCaracteristicasItem(menuItemVista, "", "Termina Herramienta Rotar", "Termina Rotar"));
        
        /* inicia menu de Edición */
        JMenu menuEdicion = new JMenu("Edición");
        agregarCaracteristicasMenu(menuEdicion, "Edición");
        menuBar.add(menuEdicion);
        
        JMenuItem menuItemEdicion = new JMenuItem();
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Deshacer", "Deshacer"));
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Rehacer", "Rehacer"));
        menuEdicion.addSeparator();
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Copiar", "Copiar"));
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Pegar", "Pegar"));
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Cortar", "Cortar"));
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Eliminar", "Eliminar"));
        menuEdicion.addSeparator();
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Herramienta Avanzada de edición", "Herramienta Avanzada"));
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Barra de versiones", "Barra versiones"));
        menuEdicion.addSeparator();
        menuEdicion.add(agregarCaracteristicasItem(menuItemEdicion, "", "Edición activa", "Edicion activa"));
        
        
        /*Inicia menu de Insertar*/
        JMenu menuInsertar = new JMenu("Insertar");
        agregarCaracteristicasMenu(menuInsertar, "Insertar");
        menuBar.add(menuInsertar);
        
        JMenuItem menuItemInsertar = new JMenuItem();
        menuInsertar.add(agregarCaracteristicasItem(menuItemInsertar, "", "Insertar titúlo", "Insertar titulo"));
        menuInsertar.add(agregarCaracteristicasItem(menuItemInsertar, "", "Insertar Texto", "Insertar Texto"));
        menuInsertar.addSeparator();
        menuInsertar.add(agregarCaracteristicasItem(menuItemInsertar, "", "Insertar leyenda", "Insertar leyenda"));
        menuInsertar.add(agregarCaracteristicasItem(menuItemInsertar, "", "Norte en el mapa", "Norte mapa"));
        menuInsertar.add(agregarCaracteristicasItem(menuItemInsertar, "", "Escala en el mapa", "Escala mapa"));
        menuInsertar.add(agregarCaracteristicasItem(menuItemInsertar, "", "Texto de escala en el mapa", "Texto escala"));
        menuInsertar.addSeparator();
        menuInsertar.add(agregarCaracteristicasItem(menuItemInsertar, "", "Imagen en el mapa", "Imagen mapa"));
        
        /*Inicia menu de seleccion*/
        JMenu menuSeleccion = new JMenu("Selección");
        agregarCaracteristicasMenu(menuSeleccion, "Seleccion");
        menuBar.add(menuSeleccion);
        
        JMenuItem menuItemSeleccion = new JMenuItem();
        menuSeleccion.add(agregarCaracteristicasItem(menuItemSeleccion, "", "Selección normal", "Selección normal"));
        menuSeleccion.addSeparator();
        menuSeleccion.add(agregarCaracteristicasItem(menuItemSeleccion, "", "Selección por ubicación", "Seleccion ubicacion")); 
        menuSeleccion.add(agregarCaracteristicasItem(menuItemSeleccion, "", "Selección por atributos", "Seleccion atributos"));
        
        /*Inicia menu de herramientas*/
        JMenu menuHerramientas = new JMenu("Herramientas");
        agregarCaracteristicasMenu(menuHerramientas, "Herramientas");
        menuBar.add(menuHerramientas);
         
        JMenuItem menuItemHerramientas = new JMenuItem();
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Herramientas estándar", "Herramientas estandar"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Herramientas Vista Layout", "Herramientas vista"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Herramientas Vista Layout", "Herramientas vista"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Herramientas de dibujo en vista Layout", "Herramientas dibujo"));
        menuHerramientas.addSeparator();
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Croquis de localización", "Croquis de localización"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Localizar sector", "Localizar sector"));
        menuHerramientas.addSeparator();
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Street View", "Street View"));
        menuHerramientas.addSeparator();
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Creación features", "Creación features"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Cálculo de fondo y frente", "Calculo de fondo y frente"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Validaciones anexas", "Validaciones anexas"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Centroides", "Centroides"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Colindantes", "Colindantes"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Herramientas Auxiliar", "Herramientas Auxiliar"));
        menuHerramientas.add(agregarCaracteristicasItem(menuItemHerramientas, "", "Números exteriores", "Números exteriores"));
        
        /*Inicia menu de versiones */
        JMenu menuVersiones = new JMenu("Versiones");
        agregarCaracteristicasMenu(menuVersiones, "Versiones");
        menuBar.add(menuVersiones);
        
        JMenuItem menuItemVersiones = new JMenuItem();
        menuVersiones.add(agregarCaracteristicasItem(menuItemVersiones, "", "Cambia Versión", "Cambia Versión"));
        menuVersiones.add(agregarCaracteristicasItem(menuItemVersiones, "", "Conciliación y posteo", "Conciliación y posteo"));
                
        /*Inicia menu de proceso catastral*/
        JMenu menuProcesoC = new JMenu("Proceso Catastral");
        agregarCaracteristicasMenu(menuProcesoC, "ProcesoC");
        menuBar.add(menuProcesoC);
        
        JMenuItem menuItemProcesoC = new JMenuItem();
        menuProcesoC.add(agregarCaracteristicasItem(menuItemProcesoC, "", "Predios privativos", "Predios privativos"));
        menuProcesoC.add(agregarCaracteristicasItem(menuItemProcesoC, "", "Predios Condominios", "Predios Condominios"));
        
        /*Inicia menu de tramite Catastral*/
        JMenu menuTramiteC = new JMenu("Trámite Catasral");
        agregarCaracteristicasMenu(menuTramiteC, "TramiteC"); 
        menuBar.add(menuTramiteC);
        
        JMenuItem menuItemTramiteC = new JMenuItem();
        menuTramiteC.add(agregarCaracteristicasItem(menuItemTramiteC, "", "Bandeja de tramites", "Bandeja de tramites"));
        menuTramiteC.addSeparator();
        menuTramiteC.add(agregarCaracteristicasItem(menuItemTramiteC, "", "Fusión de predios", "Fusión de predios"));
        menuTramiteC.add(agregarCaracteristicasItem(menuItemTramiteC, "", "División de predios", "División de predios"));
        menuTramiteC.addSeparator();
        menuTramiteC.add(agregarCaracteristicasItem(menuItemTramiteC, "", "Tramite de fraccionamientos y condominios", "Tramite de fraccionamientos y condominios"));
        
        /*Inicia menu de validacion cartografia*/
        JMenu menuValidacionC = new JMenu("Validación Cartografíca");
        agregarCaracteristicasMenu(menuValidacionC, "ValidacionC");
        menuBar.add(menuValidacionC);
        
        JMenuItem menuItemValidacionC = new JMenuItem();
        menuValidacionC.add(agregarCaracteristicasItem(menuItemValidacionC, "", "Fraccionamientos y condominios", "Fraccionamientos y condominios"));
        
        /*Inicia menu de Consulta informacion*/
        JMenu menuConsultaI = new JMenu("Consulta información");
        agregarCaracteristicasMenu(menuConsultaI, "ConsultaI");
        menuBar.add(menuConsultaI);
        
        JMenuItem menuItemConsultaI = new JMenuItem();
        menuConsultaI.add(agregarCaracteristicasItem(menuItemConsultaI, "", "Consulta de información", "Consulta de información"));
                
        /*Inicia Menu de Historial*/
        JMenu menuHistorial = new JMenu("Historial");
        agregarCaracteristicasMenu(menuHistorial, "Historial");
        menuBar.add(menuHistorial);
        
        JMenuItem menuItemHistorial = new JMenuItem();
        menuHistorial.add(agregarCaracteristicasItem(menuItemHistorial, "", "Histórico de predios", "Histórico de predios"));
        
        /*Inicia menu de Actualiza Cartografia*/
        JMenu menuActualizaC = new JMenu("Actualiza Cartografía");
        agregarCaracteristicasMenu(menuActualizaC, "ActualizaC");
        menuBar.add(menuActualizaC);
        
        JMenuItem menuItemActualizaC = new JMenuItem();
        menuActualizaC.add(agregarCaracteristicasItem(menuItemActualizaC, "", "Región Catastral", "Región Catastral"));
        menuActualizaC.add(agregarCaracteristicasItem(menuItemActualizaC, "", "Zona catastral", "Zona catastral"));
        menuActualizaC.add(agregarCaracteristicasItem(menuItemActualizaC, "", "Sector catastral", "Sector catastral"));
        menuActualizaC.add(agregarCaracteristicasItem(menuItemActualizaC, "", "Asentamientos", "Asentamientos"));
        menuActualizaC.add(agregarCaracteristicasItem(menuItemActualizaC, "", "Manzanas", "Manzanas"));
        menuActualizaC.add(agregarCaracteristicasItem(menuItemActualizaC, "", "Vialidades", "Vialidades"));
        menuActualizaC.add(agregarCaracteristicasItem(menuItemActualizaC, "", "Zonas Homogéneas", "Zonas Homogéneas"));
        menuActualizaC.add(agregarCaracteristicasItem(menuItemActualizaC, "", "Bandas de valor", "Bandas de valor"));
        
        /*Inicia menu de Planos*/
        JMenu menuPlanos = new JMenu("Planos");
        agregarCaracteristicasMenu(menuPlanos, "Planos");
        menuBar.add(menuPlanos);
        
        JMenuItem menuItemPlanos = new JMenuItem();
        menuPlanos.add(agregarCaracteristicasItem(menuItemPlanos, "", "Planos catastrales", "Planos catastrales"));
        
        /*Inicia menu de ventanas*/
        JMenu menuVentanas = new JMenu("Ventanas");
        agregarCaracteristicasMenu(menuVentanas, "Ventanas");
        menuBar.add(menuVentanas);
        
        JMenuItem menuItemVentanas = new JMenuItem();
        menuVentanas.add(agregarCaracteristicasItem(menuItemVentanas, "", "Ventana contenedora", "Ventana contenedora"));
        menuVentanas.addSeparator();
        menuVentanas.add(agregarCaracteristicasItem(menuItemVentanas, "", "Visor de mapas", "Visor de mapas"));
        menuVentanas.add(agregarCaracteristicasItem(menuItemVentanas, "", "Tabla de contenido", "Tabla de contenido"));
        menuVentanas.add(agregarCaracteristicasItem(menuItemVentanas, "", "Catálogo", "Catálogo"));
        menuVentanas.add(agregarCaracteristicasItem(menuItemVentanas, "", "Buscar", "Buscar"));
        
        final DrawingOverlay drawingOverlay = new DrawingOverlay();
        map.addMapOverlay(drawingOverlay);
        drawingOverlay.setActive(true);
        
        drawingOverlay.addDrawingCompleteListener(new DrawingCompleteListener() {

            @Override
            public void drawingCompleted(DrawingCompleteEvent arg0) {
              // gets the graphic and clears it from the overlay
              graphicsLayer.addGraphic((Graphic) drawingOverlay.getAndClearFeature());
            }
          });
                
        JToolBar toolBar = createToolBar(drawingOverlay);
        toolBar.setBackground(Color.white);
        toolBar.setPreferredSize(new Dimension(300, 20));
        
        JToolBar toolBarGuardar = crearToolBarGuardar(drawingOverlay);
        toolBarGuardar.setBackground(Color.white);
        toolBarGuardar.setPreferredSize(new Dimension(800, 20));
         
        JToolBar toolBarZoom = crearToolBarZoom(drawingOverlay);
        toolBarZoom.setBackground(Color.white);
        toolBarZoom.setPreferredSize(new Dimension(200, 20));
        
        jpanelHerramientas.add(menuBar);
        panelMenu.add(jpanelHerramientas, BorderLayout.NORTH);
        
        jpanelIconos.add(toolBarGuardar, BorderLayout.LINE_START);
        jpanelIconos.add(toolBar, BorderLayout.CENTER);
        jpanelIconos.add(toolBarZoom, BorderLayout.LINE_END);
        panelMenu.add(jpanelIconos, BorderLayout.CENTER);
        
        panelMenu.add(jpanelIconosD, BorderLayout.SOUTH);

	}
	
	public JToolBar crearToolBarZoom (DrawingOverlay drawingOverlay) {
		JToolBar toolBar = new JToolBar();  	
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        ToolTipManager.sharedInstance().setInitialDelay(100); 

        
        return toolBar;
	}
	
	public JToolBar crearToolBarGuardar (DrawingOverlay drawingOverlay) {
		JToolBar toolBar = new JToolBar();  
//		javax.swing.border.Border border= BorderFactory.createLineBorder(Color.RED, 1);
//		toolBar.setBorder(border);		
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        ToolTipManager.sharedInstance().setInitialDelay(100); 
        
        JButton btnNuevo = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/new.png")));
        btnNuevo.setToolTipText("Nuevo");
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnNuevo);
        
        JButton btnAbrir = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/open.png")));
        btnAbrir.setToolTipText("Abrir");
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnAbrir);
        
        JButton btnGuardar = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/save.png")));
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnGuardar);
        
        JButton btnImprimir = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/print.png")));
        btnImprimir.setToolTipText("Imprimir");
        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnImprimir);
        
        JButton btnCortar = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/cut.png")));
        btnCortar.setToolTipText("Cortar");
        btnCortar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnCortar);
        
        JButton btnCopiar = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/copy.png")));
        btnCopiar.setToolTipText("Copiar");
        btnCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnCopiar);
        
        JButton btnPegar = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/paste.png")));
        btnPegar.setToolTipText("Pegar");
        btnPegar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnPegar);
        
        JButton btnEliminar = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/delete.png")));
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnEliminar);
        
        JButton btnDeshacer = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/undo.png")));
        btnDeshacer.setToolTipText("Deshacer");
        btnDeshacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnDeshacer);
        
        JButton btnSiguiente = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/redo.png")));
        btnSiguiente.setToolTipText("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnSiguiente);
        
        JButton btnAgregarData = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/add_Data.png")));
        btnAgregarData.setToolTipText("Add Data");
        btnAgregarData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnAgregarData);
        
        JComboBox cmbScala = new JComboBox();
        cmbScala.setBounds(10,10,150,20);
        cmbScala.addItem("1:1.000");
        cmbScala.addItem("1:10.000");
        cmbScala.addItem("1:24.000");
        cmbScala.addItem("1:100.000");
        cmbScala.addItem("1:250.000");
        cmbScala.addItem("1:500.000");
        cmbScala.addItem("1:750.000");
        cmbScala.addItem("1:1.000.000");
        cmbScala.addItem("1:3.000.000");
        cmbScala.addItem("1:10.000.000");
        toolBar.add(cmbScala);
        
        JButton btnEditor = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/edit.png")));
        btnEditor.setToolTipText("Editor");
        btnEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnEditor);
        
        JButton btnTable = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/table.png")));
        btnTable.setToolTipText("Tabla de contenido");
        btnTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnTable);
        
        JButton btnCatalogo = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/catalogo.png")));
        btnCatalogo.setToolTipText("Editor");
        btnCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnCatalogo);
        
        JButton btnBuscar = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/search.png")));
        btnBuscar.setToolTipText("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnBuscar);
        
        JButton btnHerramientas = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/tools.png")));
        btnHerramientas.setToolTipText("Herramientas");
        btnHerramientas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnHerramientas);
        
        JButton btnBuilder = new JButton(new ImageIcon(getClass().getResource("/imagenes/img/grafico.png")));
        btnBuilder.setToolTipText("Builder");
        btnBuilder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          	  	Map<String, Object> attributes = new HashMap<String, Object>();
//          	    drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
            }
        });
        toolBar.add(btnBuilder);
        
        
        return toolBar;
	}
	
	public JToolBar createToolBar(DrawingOverlay drawingOverlay) {
		JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        ToolTipManager.sharedInstance().setInitialDelay(100); 
        JButton rectangleButton = new JButton(new ImageIcon(getClass().getResource("/com/esri/client/toolkit/images/EditingRectangleTool16.png")));
        rectangleButton.setToolTipText("Rectangle tool");
        rectangleButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
        	  Map<String, Object> attributes = new HashMap<String, Object>();
        	  drawingOverlay.setUp(DrawingMode.POLYGON_RECTANGLE, new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)), attributes);
          }
        });
        toolBar.add(rectangleButton);
        
        JButton polylineButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingLineTool16.png")));
        polylineButton.setToolTipText("Polyline tool");
        polylineButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            drawingOverlay.setUp(
                DrawingMode.POLYLINE,
                new SimpleLineSymbol(Color.BLUE, 3),
                null);
          }
        });
        toolBar.add(polylineButton);
        
        JButton freehandLineButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingFreehandTool16.png")));
        freehandLineButton.setToolTipText("Freehand line tool");
        freehandLineButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            drawingOverlay.setUp(
                DrawingMode.POLYLINE_FREEHAND,
                new SimpleLineSymbol(Color.GREEN, 2),
                null);
          }
        });
        toolBar.add(freehandLineButton);
        
        JButton pointButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingPointTool16.png")));
        pointButton.setToolTipText("Point tool");
        pointButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            drawingOverlay.setUp(
                DrawingMode.POINT,
                new SimpleMarkerSymbol(Color.cyan, 20, Style.CIRCLE),
                null);
          }
        });
        toolBar.add(pointButton);
        
        JButton multipointButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingMultiPointTool16.png")));
        multipointButton.setToolTipText("Multipoint tool");
        multipointButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            drawingOverlay.setUp(
                DrawingMode.MULTIPOINT,
                new SimpleMarkerSymbol(Color.DARK_GRAY, 20, Style.CIRCLE),
                null);
          }
        });
        toolBar.add(multipointButton);
        
        final SimpleLineSymbol dottedLine = new SimpleLineSymbol(Color.BLACK, 2);
        dottedLine.setStyle(SimpleLineSymbol.Style.DASH);
        JButton polygonButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingPolygonTool16.png")));
        polygonButton.setToolTipText("Polygon tool");
        polygonButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            drawingOverlay.setUp(
                DrawingMode.POLYGON,
                new SimpleFillSymbol(new Color(0, 0, 0, 80), dottedLine),
                null);
          }
        });
        toolBar.add(polygonButton);
		
		return toolBar;
	}
	
	public void crearMenuCapas (JMap map, JPanel panelMenuCapas) {
		JLegend legend = new JLegend(map);
		
	    legend.setPreferredSize(new Dimension(250, 700));
	    legend.setBorder(new LineBorder(new Color(205, 205, 255), 3));
	    panelMenuCapas.add(legend);
	}
	
	
	public JMenuItem agregarCaracteristicasItem (JMenuItem menuItem, String Imagen, String nombreItem, String decripcion) {
		menuItem = new JMenuItem(nombreItem, new ImageIcon(Imagen));
		menuItem.setBackground(Color.WHITE);
		menuItem.getAccessibleContext().setAccessibleDescription(decripcion);
		return menuItem;
	}
	
	public void agregarCaracteristicasMenu (JMenu menu, String decripcion) {
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(decripcion);
	}
	
}

