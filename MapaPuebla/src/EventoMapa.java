//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Dimension;
//import java.awt.List;
//import java.awt.event.ContainerEvent;
//import java.awt.event.ContainerListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.ToolTipManager;

//import javax.swing.JLayeredPane;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
//
//import com.esri.client.toolkit.overlays.NavigatorOverlay;
import com.esri.core.geometry.Envelope;
//import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
//import com.esri.core.symbol.SimpleMarkerSymbol;
//import com.esri.core.symbol.SimpleMarkerSymbol.Style;
//import com.esri.map.ArcGISDynamicMapServiceLayer;
//import com.esri.map.ArcGISFeatureLayer;
import com.esri.map.ArcGISTiledMapServiceLayer;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
//import com.esri.map.Layer;
//import com.esri.map.LayerInitializeCompleteEvent;
//import com.esri.map.LayerInitializeCompleteListener;
import com.esri.map.LayerList;
import com.esri.map.MapEvent;
import com.esri.map.MapEventListener;
import com.esri.map.MapEventListenerAdapter;
//import com.esri.toolkit.JLayerTree;
//import com.esri.toolkit.overlays.InfoPopupOverlay;

public class EventoMapa {
	
	  public void eventoMapa (JMap map) {
		map.addMapEventListener(new MapEventListener() {
			
			@Override
			public void mapReady(MapEvent event) {
				SpatialReference mapSR = event.getMap().getSpatialReference();
			    System.out.println("The map spatial reference is wkid=" + mapSR.getID());
			}
			@Override 
			public void mapExtentChanged(MapEvent event) {
				//System.out.println(" evento "+event.getMap().getGraphics());
				event.getMap().getGraphics();
			}
			@Override
			public void mapDispose(MapEvent event) {
				
			}
		});
		
	}

	/*public void crearGrafico (JMap map) { 
		try {
			SimpleMarkerSymbol simpleMarker = new SimpleMarkerSymbol(java.awt.Color.BLUE,12,Style.CIRCLE);
			Point pointGeometry = new Point(-10882400, 2156094);
			Graphic pointGraphic = new Graphic(pointGeometry, simpleMarker);
			GraphicsLayer myGraphicsLayer = new GraphicsLayer();
			myGraphicsLayer.addGraphic(pointGraphic);
			map.getLayers().add(myGraphicsLayer);
		} catch (Exception e) {
		}
		
	}*/
	
	
	public void crearLinea (JMap map) {
//		SimpleLineSymbol lineSymbol = new SimpleLineSymbol( Color.GREEN, 3, com.esri.core.symbol.SimpleLineSymbol.Style.DASH);
//		
//			//create the line geometry
//			Polyline lineGeometry = new Polyline();
//			lineGeometry.startPath(-902557,7570663);
//			lineGeometry.lineTo(-902959,7570868);
//			lineGeometry.lineTo(-903042,7571220);
//			lineGeometry.lineTo(-902700,7571803);
//			lineGeometry.lineTo(-904043,7576654);
//			lineGeometry.lineTo(-900544,7585289);
//			lineGeometry.lineTo(-794365,7592435);
//			lineGeometry.lineTo(-790122,7594445);
//			lineGeometry.lineTo(-785283,7595488);
			 
//			Graphic lineGraphic = new Graphic(lineGeometry, lineSymbol);
//			GraphicsLayer myGraphicsLayer = new GraphicsLayer();
//			myGraphicsLayer.addGraphic(lineGraphic);
//			map.getLayers().add(myGraphicsLayer);
			
	}
	
	public void agregarNavegador (JMap map) {
		
//		SimpleMarkerSymbol simpleMarker = new SimpleMarkerSymbol(java.awt.Color.BLUE,12,Style.CIRCLE);
//		Point pointGeometry = new Point(-20042400, 856094);
//		Graphic pointGraphic = new Graphic(pointGeometry, simpleMarker);
//		
//		NavigatorOverlay navigator = new NavigatorOverlay();
//		map.addMapOverlay(navigator);
//		Envelope initialExtent = new Envelope(-20042400, 856094, -2783530, 11716267);
//		double BUFFER_DISTANCE = 500000; // 500 km
		
		
//		Geometry geometryForZoom = GeometryEngine.buffer(
//				pointGraphic.getGeometry(), 
//		   map.getSpatialReference(), 
//		   BUFFER_DISTANCE, 
//		   map.getSpatialReference().getUnit());
		
//		map.zoomTo(geometryForZoom);
//		map.setExtent(initialExtent);
	}
	
	public void crearGeometria (JMap map) {
		String NEWLINE = System.getProperty("line.separator");
		DecimalFormat decimalFormat = new DecimalFormat("##.##");
	    map.addMapEventListener(new MapEventListenerAdapter() {

	        @Override
	        public void mapExtentChanged(MapEvent arg0) {
	          if (map.isReady()) {
	            StringBuilder str = new StringBuilder();
	            str.append("MinX: " + decimalFormat.format(map.getExtent().getXMin()) + NEWLINE);
	            str.append("MinY: " + decimalFormat.format(map.getExtent().getYMin()) + NEWLINE);
	            str.append("MaxX: " + decimalFormat.format(map.getExtent().getXMax()) + NEWLINE);
	            str.append("MaxY: " + decimalFormat.format(map.getExtent().getYMax()) + NEWLINE);
	            System.out.println("str: " + str.toString());
	            System.out.println("map " + map.getScale());
	          }  
	        }
	      });
	}

	
	public void addLayers(JMap map, int numLayer){
		GraphicsLayer newLayer= new GraphicsLayer();
		String nameLayer= "Capa "+numLayer;
		newLayer.setName(nameLayer);
		map.getLayers().add(newLayer);
		
		System.out.println("Layers List: "+ map.getLayers());
	}

	 public JMap createMap() throws Exception {
		 final JMap map = new JMap();
		 final ArcGISTiledMapServiceLayer baseLayer = new ArcGISTiledMapServiceLayer("http://services.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer");
		 map.setExtent(new Envelope(-10943738.01, 2152726.77, -10912321.29, 2170862.64));
		 LayerList layers = map.getLayers();
		 baseLayer.setName("Mapa Base");
		 layers.add(baseLayer);
		 
		 return map;
	 }
}

