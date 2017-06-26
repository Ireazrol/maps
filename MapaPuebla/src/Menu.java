import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;
import javax.swing.border.LineBorder;

import com.esri.client.toolkit.overlays.DrawingOverlay.DrawingMode;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
import com.esri.map.JMap;
import com.esri.toolkit.legend.JLegend;
import com.esri.toolkit.overlays.DrawingCompleteEvent;
import com.esri.toolkit.overlays.DrawingCompleteListener;
import com.esri.toolkit.overlays.DrawingOverlay;

public class Menu {
	
	public void crearMenu (JPanel panelMenu){
		JPanel jpanelHerramientas = new JPanel(new BorderLayout());
		jpanelHerramientas.setBackground(Color.white);
		jpanelHerramientas.setPreferredSize(new Dimension(1200, 20));
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		
		JMenu subMenuArchivo = new JMenu("");
		
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic(KeyEvent.VK_F);
        menuArchivo.getAccessibleContext().setAccessibleDescription("Dealing with Files");
        menuBar.add(menuArchivo);
        
        JMenuItem menuItemArchivo = new JMenuItem();
        menuItemArchivo.setBackground(Color.WHITE);
        menuItemArchivo = new JMenuItem("Guardar mapa", new ImageIcon(""));
        menuItemArchivo.getAccessibleContext().setAccessibleDescription("Guardar mapa");
        menuArchivo.add(menuItemArchivo);
		
        
        final DrawingOverlay drawingOverlay = new DrawingOverlay();
        //map.addMapOverlay(drawingOverlay);
        drawingOverlay.setActive(true);
        
        drawingOverlay.addDrawingCompleteListener(new DrawingCompleteListener() {

            @Override
            public void drawingCompleted(DrawingCompleteEvent arg0) {
              // gets the graphic and clears it from the overlay
             // graphicsLayer.addGraphic((Graphic) drawingOverlay.getAndClearFeature());
            }
          });
        
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        ToolTipManager.sharedInstance().setInitialDelay(100);
        
        JButton rectangleButton = new JButton(new ImageIcon(getClass().getResource(
                "/com/esri/client/toolkit/images/EditingRectangleTool16.png")));
            rectangleButton.setToolTipText("Rectangle tool");
            rectangleButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
//                drawingOverlay.setUp(
//                    DrawingMode.POLYGON_RECTANGLE,
//                    new SimpleFillSymbol(new Color(200, 0, 0, 120), new SimpleLineSymbol(new Color(200, 0, 0), 3)),
//                    null);
              }
            });
            toolBar.add(rectangleButton);
        
        JButton polylineButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingLineTool16.png")));
        polylineButton.setToolTipText("Polyline tool");
        polylineButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
//            drawingOverlay.setUp(
//                DrawingMode.POLYLINE,
//                new SimpleLineSymbol(Color.BLUE, 3),
//                null);
          }
        });
        toolBar.add(polylineButton);
        
        JButton freehandLineButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingFreehandTool16.png")));
        freehandLineButton.setToolTipText("Freehand line tool");
        freehandLineButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
//            drawingOverlay.setUp(
//                DrawingMode.POLYLINE_FREEHAND,
//                new SimpleLineSymbol(Color.RED, 2),
//                null);
          }
        });
        toolBar.add(freehandLineButton);
        
        JButton pointButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingPointTool16.png")));
        pointButton.setToolTipText("Point tool");
        pointButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
//            drawingOverlay.setUp(
//                DrawingMode.POINT,
//                new SimpleMarkerSymbol(Color.GREEN, 20, Style.CIRCLE),
//                null);
          }
        });
        toolBar.add(pointButton);
        
        JButton multipointButton = new JButton(new ImageIcon(getClass().getResource(
            "/com/esri/client/toolkit/images/EditingMultiPointTool16.png")));
        multipointButton.setToolTipText("Multipoint tool");
        multipointButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
//            drawingOverlay.setUp(
//                DrawingMode.MULTIPOINT,
//                new SimpleMarkerSymbol(Color.DARK_GRAY, 20, Style.CIRCLE),
//                null);
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
//            drawingOverlay.setUp(
//                DrawingMode.POLYGON,
//                new SimpleFillSymbol(new Color(0, 0, 0, 80), dottedLine),
//                null);
          }
        });
        toolBar.add(polygonButton);
        
        jpanelHerramientas.add(menuBar);
        panelMenu.add(jpanelHerramientas, BorderLayout.NORTH);
		//panelMenu.add(menuBar); 
	}
	
	public void crearMenuCapas (JMap map, JPanel panelMenuCapas) {
		JLegend legend = new JLegend(map);
	    legend.setPreferredSize(new Dimension(250, 700));
	    legend.setBorder(new LineBorder(new Color(205, 205, 255), 3));
	    panelMenuCapas.add(legend);
	}
}

