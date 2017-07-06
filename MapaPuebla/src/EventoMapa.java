//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.List;
//import java.awt.event.ContainerEvent;
//import java.awt.event.ContainerListener;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.swing.SwingUtilities;

import com.esri.client.local.ArcGISLocalTiledLayer;
//import javax.swing.JLayeredPane;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
//
//import com.esri.client.toolkit.overlays.NavigatorOverlay;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Feature;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.map.ArcGISDynamicMapServiceLayer;
import com.esri.map.ArcGISFeatureLayer;
//import com.esri.core.symbol.SimpleMarkerSymbol;
//import com.esri.core.symbol.SimpleMarkerSymbol.Style;
//import com.esri.map.ArcGISDynamicMapServiceLayer;
//import com.esri.map.ArcGISFeatureLayer;
import com.esri.map.ArcGISTiledMapServiceLayer;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
import com.esri.map.Layer;
import com.esri.map.Layer.LayerStatus;
import com.esri.map.LayerInfo;
import com.esri.map.LayerInitializeCompleteEvent;
import com.esri.map.LayerInitializeCompleteListener;
//import com.esri.map.Layer;
//import com.esri.map.LayerInitializeCompleteEvent;
//import com.esri.map.LayerInitializeCompleteListener;
import com.esri.map.LayerList;
import com.esri.map.MapEvent;
import com.esri.map.MapEventListener;
import com.esri.map.MapEventListenerAdapter;
import com.esri.toolkit.overlays.HitTestEvent;
import com.esri.toolkit.overlays.HitTestListener;
//import com.esri.toolkit.JLayerTree;
//import com.esri.toolkit.overlays.InfoPopupOverlay;
import com.esri.toolkit.overlays.HitTestOverlay;

public class EventoMapa {
	String urlMapOnline = "http://192.168.116.124:6080/arcgis/rest/services/chignahuapanSDE/MapServer";
	String urlMapLocal = "http://192.168.116.124:6080/arcgis/rest/services/chignahuapanSDE/FeatureServer";
	HashMap<String, LayerInfo> listaLayers = new HashMap<String, LayerInfo>();
	
	  public void eventoMapa (JMap map) {
		map.addMapEventListener(new MapEventListener() {
			@Override
			public void mapReady(MapEvent event) {
				SpatialReference mapSR = event.getMap().getSpatialReference();
			    System.out.println("The map spatial reference is wkid=" + mapSR.getID());
			}
			@Override 
			public void mapExtentChanged(MapEvent event) {
				event.getMap().getGraphics();
			}
			@Override
			public void mapDispose(MapEvent event) {
				
			}
		});
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
	}

	 public JMap createMap() throws Exception {
		 final JMap map = new JMap();
		final ArcGISTiledMapServiceLayer baseLayer = new ArcGISTiledMapServiceLayer(
				"http://services.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer");
		 map.setExtent(new Envelope(-10943738.01, 2152726.77, -10912321.29, 2170862.64));
		 LayerList layers = map.getLayers();
		 baseLayer.setName("Mapa Base");
		 layers.add(baseLayer);
		 return map;
	 }

	/**
	 * Descripcion: se limpa layer por nombre de layer SIS_PC
	 * 
	 * @param nameLayer
	 * @param map
	 * @return
	 */
	public int limpiarLayers(String nameLayer, JMap map) {
		int remover = 0;
		if (map.getLayers() != null) {
			for (Layer x : map.getLayers()) {
				nameLayer = nameLayer.replace(" ", "").toLowerCase();
				String nameLayerList = x.getName().replace(" ", "").toLowerCase();
				if (nameLayer.equals(nameLayerList)) {
					map.getLayers().remove(x);
					remover = 1;
				}
			}
		}
		return remover;
	}

	/**
	 * Descripción: se crea mapa base
	 * 
	 * @return
	 */
	public JMap crearMapaPuebla() {
		final JMap jMap = new JMap();
		jMap.setShowingEsriLogo(true);
		jMap.setWrapAroundEnabled(false);
		ArcGISTiledMapServiceLayer baseLayer = new ArcGISTiledMapServiceLayer("https://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
		LayerList layers = jMap.getLayers();
		baseLayer.setName("Mapa Base");
		layers.add(baseLayer);
		cargarMapasLayer(jMap, 0);
		// eleccionarPredio(jMap);
		return jMap;
	}

	/**
	 * Descrpción: se crea el mapa local u online de acuerdo al parametro que
	 * reciba en la variable url.
	 * 
	 * @param map
	 * @param url
	 */
	public void cargarMapasLayer(JMap map, int url) {
		 String urlMapa = urlMapOnline;
		if (url == 1) {
			urlMapa = urlMapLocal;
			final ArcGISLocalTiledLayer tiledLayer = new ArcGISLocalTiledLayer("C:\\Program Files (x86)\\ArcGIS SDKs\\java10.2.4\\sdk\\samples\\data\\tpks\\chignahuapanSDE.tpk");
			tiledLayer.setName("Local");
			map.getLayers().add(tiledLayer);
	        tiledLayer.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
	          @Override
	          public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
	            if (e.getID() == LayerInitializeCompleteEvent.LOCALLAYERCREATE_ERROR) {
	              System.out.println("no se pudo inicializar tiled " ); 
	            }
	          }
	        });
	        map.setExtent(new Envelope(-1.0914563461473859E7, 2251658.6830414305, -1.0909068825257503E7, 2255260.8421374366));
		} else {
			map.setExtent(new Envelope(-1.0914563461473859E7, 2251658.6830414305, -1.0909068825257503E7, 2255260.8421374366));
			final ArcGISDynamicMapServiceLayer onlineDynamicLayer = new ArcGISDynamicMapServiceLayer(urlMapa);
			onlineDynamicLayer.setName("Online");
			map.getLayers().add(onlineDynamicLayer);
			onlineDynamicLayer.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
				@Override
				public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
					System.out.println(" status " + onlineDynamicLayer.getStatus());
					if (onlineDynamicLayer.getStatus() == LayerStatus.INITIALIZED) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								// seleccionarPredio(map, onlineDynamicLayer);
								listaLayers = onlineDynamicLayer.getLayers();
								if (listaLayers != null) {
									for (int i = 0; i < listaLayers.size(); i++) {
										LayerInfo layerInfo = (LayerInfo) listaLayers.values().toArray()[i];
										ArcGISFeatureLayer arcGISFeatureLayer = new ArcGISFeatureLayer( urlMapOnline + "/" + layerInfo.getId());
										map.getLayers().add(arcGISFeatureLayer);
										arcGISFeatureLayer.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
											@Override
											public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
												if (arcGISFeatureLayer.getStatus() == LayerStatus.INITIALIZED) {
															System.out.println("estatus " + arcGISFeatureLayer.getStatus());
															//arcGISFeatureLayer.
															seleccionarPredio(map, arcGISFeatureLayer);
												}
											}
										});
									}
								}
								map.getLayers().remove(onlineDynamicLayer);
							}
						});
					}
				}
			});
		}
	}

	public void seleccionarPredio(JMap map, ArcGISFeatureLayer arcGISFeatureLayer) {
		LayerList layerList = map.getLayers();
		map.addMapEventListener(new MapEventListenerAdapter() {
			@Override
			public void mapReady(MapEvent mapEvent) {
				unionPredio(map, arcGISFeatureLayer);
				arcGISFeatureLayer.setSelectionColor(Color.YELLOW);
			}
		});
		System.out.println("layerListddddd " + layerList);
		HitTestListener listener = SeleccionarPredio();
		final HitTestOverlay selectionOverlay = new HitTestOverlay(arcGISFeatureLayer, listener);
		map.addMapOverlay(selectionOverlay);

	}
	
	public void unionPredio (JMap map, ArcGISFeatureLayer arcGISFeatureLayer) {
		int[] selectedIDs = arcGISFeatureLayer.getSelectionIDs();
	    // if there are less than 2 selected graphics, there is no need to perform a union
	    if (selectedIDs.length < 2) { 
	      return;
	    }
	    Geometry[] selectedGeometries = new Geometry[selectedIDs.length];
	    // get a list of selected geometries
	    int i = 0;
	    for (int id : selectedIDs) {
	      // add it to the selected collection 
	      selectedGeometries[i] = arcGISFeatureLayer.getGraphic(id).getGeometry();
	      i++;
	    }
	    Geometry unionGeometry = GeometryEngine.union(selectedGeometries, map.getSpatialReference());
	    SimpleLineSymbol lineSymbol = new SimpleLineSymbol(Color.red, 3, SimpleLineSymbol.Style.SOLID);
	    Graphic unionGraphic = new Graphic(unionGeometry, lineSymbol);
	    arcGISFeatureLayer.addGraphic(unionGraphic);
	    for (int id : selectedIDs) { 
	    	arcGISFeatureLayer.removeGraphic(id);
	    }
	}

	public HitTestListener SeleccionarPredio() {
		System.out.println("entro al evento eventTest ");
		HitTestListener listener = new HitTestListener() {
			@Override
			public void featureHit(HitTestEvent event) {
				HitTestOverlay overlay = (HitTestOverlay) event.getSource();
				List<Feature> hitFeatures = overlay.getHitFeatures();
				System.out.println(" overlay.getLayer() " + overlay.getLayer());
				ArcGISFeatureLayer arcGISFeatureLayer = (ArcGISFeatureLayer) overlay.getLayer();
				// GraphicsLayer graphicsLayer = (GraphicsLayer)  overlay.getLayer();
				// select or de-select each hit graphic
				for (Feature feature : hitFeatures) {
					
					if (arcGISFeatureLayer.isGraphicSelected((int) feature.getId())) {
						arcGISFeatureLayer.unselect((int) feature.getId());
					} else {
						arcGISFeatureLayer.select((int) feature.getId());
					}
				}
			}
		};
		return listener;
	}

}
