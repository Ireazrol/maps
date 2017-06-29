import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
import com.esri.toolkit.overlays.DrawingCompleteEvent;
import com.esri.toolkit.overlays.DrawingCompleteListener;
import com.esri.toolkit.overlays.DrawingOverlay;
import com.esri.toolkit.overlays.DrawingOverlay.DrawingMode;

public class Botones {
	private int numLayer=7;    
	
	public void BtnAddLayer(JButton btnAgregarData, JMap map){
		btnAgregarData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	EventoMapa eventoMapa = new EventoMapa();
            	eventoMapa.addLayers(map, numLayer);
            	numLayer++;
            }
        });
	}
	
	public void BtnRectangle(DrawingOverlay drawingOverlayu,JButton rectangleButton){
		System.out.println("Entré al método para pintar un rectangulo");
		rectangleButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("Pintaré un rectangulo");
	        	  drawingOverlayu.setUp(DrawingMode.POLYGON_RECTANGLE, 
	        			  new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)),
	        			  null);
	          }
	    });
	}
	
	public void BtnPolyline(DrawingOverlay drawingOverlaya, JButton polylineButton){
		System.out.println("Entré al método para pintar una polilínea");
		polylineButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("Pintaré una polilínea");
	        	  drawingOverlaya.setUp(
	                DrawingMode.POLYLINE,
	                new SimpleLineSymbol(Color.BLUE, 3),
	                null);
	          }
	    });
	}
	
	public void BtnFreehandLine(DrawingOverlay drawingOverFreehand, JButton freehandLineButton){
		freehandLineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingOverFreehand.setUp(DrawingMode.POLYLINE_FREEHAND,new SimpleLineSymbol(Color.GREEN, 2), null);
			}
		});
	}
	
	public void BtnPoint(DrawingOverlay drawingOverPoint,JButton pointButton){
		pointButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingOverPoint.setUp(DrawingMode.POINT,new SimpleMarkerSymbol(Color.cyan, 20, Style.CIRCLE), null);
			}
		});
	}
	
	public void BtnMultipoint(DrawingOverlay drawingOverMultipoint, JButton multipointButton) {
		multipointButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingOverMultipoint.setUp(DrawingMode.MULTIPOINT, new SimpleMarkerSymbol(Color.DARK_GRAY, 20, Style.CIRCLE), null);
			}
		});
	}
	
	public void BtnPolygon(DrawingOverlay drawingOverPolygon, JButton polygonButton,SimpleLineSymbol dottedLine){
		polygonButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingOverPolygon.setUp(DrawingMode.POLYGON, new SimpleFillSymbol(new Color(0, 0, 0, 80), dottedLine), null);
			}
		});
	}
	
	public void graphicLayer(DrawingOverlay drawingOverlay, GraphicsLayer graphicsLayer){
		drawingOverlay.addDrawingCompleteListener(new DrawingCompleteListener() {
			 @Override
			 public void drawingCompleted(DrawingCompleteEvent arg0) {
		        graphicsLayer.addGraphic((Graphic) drawingOverlay.getAndClearFeature());
		      }
		 });
	}
	/**********DrawingOverlay************/
	public DrawingOverlay DORectangle(JMap map){
		final DrawingOverlay drawingOverlayu = new DrawingOverlay();
	    map.addMapOverlay(drawingOverlayu);
	    drawingOverlayu.setActive(true);
	    return drawingOverlayu;
	}
}