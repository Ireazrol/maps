import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SegmentIterator;
import com.esri.core.map.Feature;
import com.esri.core.map.FeatureResult;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
import com.esri.core.tasks.query.QueryParameters;
import com.esri.core.tasks.query.QueryTask;
import com.esri.map.ArcGISFeatureLayer.EditCapabilities;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
import com.esri.map.LayerList;
import com.esri.map.MapEvent;
import com.esri.map.MapEventListenerAdapter;
import com.esri.toolkit.overlays.DrawingCompleteEvent;
import com.esri.toolkit.overlays.DrawingCompleteListener;
import com.esri.toolkit.overlays.DrawingOverlay;
import com.esri.toolkit.overlays.HitTestEvent;
import com.esri.toolkit.overlays.HitTestListener;
import com.esri.toolkit.overlays.HitTestOverlay;
import com.esri.toolkit.overlays.DrawingOverlay.DrawingMode;


public class PintarPredios {
	SimpleLineSymbol SYM_PARCEL_BORDER = new SimpleLineSymbol(Color.RED, 5);
	SimpleFillSymbol SYM_PARCEL =new SimpleFillSymbol(new Color(0, 0, 255, 80), SYM_PARCEL_BORDER);
	
	 Envelope initialExtent = new Envelope(-10943738.01, 2152726.77, -10912321.29, 2170862.64);
	 //Envelope initialExtent = new Envelope(-83.3188,42.6142,-83.3129,42.6167);
	 //Envelope initialExtent = new Envelope(13390581.4885455, 364539.496419515, 13448983.1843548, 422941.192228768);
	 final String URL_TAX_PARCELS ="http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/TaxParcel/TaxParcelQuery/MapServer/0";
	 GraphicsLayer graphicsLayer = new GraphicsLayer();
	 
	 public void pintarPoligonos (JMap map) { 
		System.out.println("map " + map.getLayers());
		LayerList layers = map.getLayers();
		graphicsLayer.setName("PREDIOS"); 
		layers.add(graphicsLayer);

		map.addMapEventListener(new MapEventListenerAdapter() {
			@Override
			public void mapReady(MapEvent mapEvent) {
				System.out.println("mapEvent " + initialExtent); 
				mapEvent.getMap().setExtent(initialExtent);
				System.out.println("xxxxxxx " + graphicsLayer);
				initializeParcels(graphicsLayer);
				
			}
		});
		 
		//earPredios(map, graphicsLayer);
		System.out.println("graphicsLayer " + graphicsLayer);
		HitTestListener listener = evenToTest();
		final HitTestOverlay selectionOverlay = new HitTestOverlay(graphicsLayer, listener);
		map.addMapOverlay(selectionOverlay);
		 
	 }
	 
	 public void crearPredios (JMap map, GraphicsLayer graphicsLayer) {
		 final DrawingOverlay drawingOverlaya = new DrawingOverlay();
	     map.addMapOverlay(drawingOverlaya);
	     drawingOverlaya.setActive(true);
	     drawingOverlaya.setUp(
	                DrawingMode.POLYLINE,
	                new SimpleLineSymbol(Color.red, 2),
	                null);
	     drawingOverlaya.addDrawingCompleteListener(new DrawingCompleteListener() {
			@Override
			public void drawingCompleted(DrawingCompleteEvent event) {
				SimpleLineSymbol lineSymbol = new SimpleLineSymbol(Color.blue, 3, SimpleLineSymbol.Style.SOLID);
				Graphic dispGraphic = new Graphic(drawingOverlaya.getFeature().getGeometry(),lineSymbol);
				graphicsLayer.addGraphic(dispGraphic);
				for (int i:graphicsLayer.getGraphicIDs()) { 
					Graphic graphicTest =graphicsLayer.getGraphic(i);
					Polyline lineGeometry = new Polyline();
					lineGeometry = (Polyline) graphicTest.getGeometry();
					System.out.println("Polyline polyline"+i+" = new Polyline();");  
					
					for (int x=0; x<lineGeometry.getPointCount(); x++) {   
						if (x==0) { 
							System.out.println("polyline"+i+".startPath("+ lineGeometry.getPoint(x).getX()+" , "+lineGeometry.getPoint(x).getY()+");" );
						} else {
							System.out.println("polyline"+i+".lineTo("+ lineGeometry.getPoint(x).getX()+" , "+lineGeometry.getPoint(x).getY()+");" );
						}
						//System.out.println("Point geometry"+x+" = new Point( " + lineGeometry.getPoint(x).getX()+" , "+lineGeometry.getPoint(x).getY()+");");
					}
					
					System.out.println("listaPolyline.add(polyline"+i+");"); 
				}
				
			}
		}); 
	     
	     
	 }
	 
	private GraphicsLayer initializeParcels(GraphicsLayer graphicsLayer) {
		graphicsLayer.setSelectionColor(Color.YELLOW);
		QueryParameters queryParams = new QueryParameters();
		queryParams.setGeometry(initialExtent);
		queryParams.setSpatialRelationship(com.esri.core.tasks.SpatialRelationship.INTERSECTS);
		try {
			List<Polygon> listaPolyline = new ArrayList<Polygon>();
			Polygon polyline12 = new Polygon();
			polyline12.startPath(-1.0930978293139592E7 , 2160387.5617778567);
			polyline12.lineTo(-1.0930996603242967E7 , 2160397.355554081);
			polyline12.lineTo(-1.0931014700438164E7 , 2160361.5869800444);
			polyline12.lineTo(-1.093100671638146E7 , 2160357.0094542005);
			polyline12.lineTo(-1.0930998412962487E7 , 2160374.4679248612);
			polyline12.lineTo(-1.0930988619186264E7 , 2160368.825858123);
			polyline12.lineTo(-1.0930978399593681E7 , 2160387.7746860352);
			polyline12.lineTo(-1.0930978399593681E7 , 2160387.7746860352);
			polyline12.lineTo(-1.093097850604777E7 , 2160387.881140125);
			listaPolyline.add(polyline12);
			Polygon polyline4 = new Polygon();
			polyline4.startPath(-1.0930958386224918E7 , 2160364.7806027257);
			polyline4.lineTo(-1.093096328311303E7 , 2160354.986826502);
			polyline4.lineTo(-1.0930955405510416E7 , 2160350.196392479);
			polyline4.lineTo(-1.0930950082805946E7 , 2160360.203076882);
			polyline4.lineTo(-1.0930958492679007E7 , 2160364.6741486364);
			listaPolyline.add(polyline4);
			Polygon polyline5 = new Polygon();
			polyline5.startPath(-1.0930988938548531E7 , 2160368.719404034);
			polyline5.lineTo(-1.0930995645156162E7 , 2160372.87111352);
			polyline5.lineTo(-1.0931004161483314E7 , 2160355.3061887706);
			polyline5.lineTo(-1.0930998200054308E7 , 2160352.006111999);
			polyline5.lineTo(-1.0930988938548531E7 , 2160368.932312213);
			listaPolyline.add(polyline5);
			Polygon polyline6 = new Polygon();
			polyline6.startPath(-1.0931055472354373E7 , 2160429.39823499);
			polyline6.lineTo(-1.0931066650033759E7 , 2160410.0235907207);
			polyline6.lineTo(-1.0931051214190798E7 , 2160401.187901301);
			polyline6.lineTo(-1.0931051107736709E7 , 2160400.655630854);
			polyline6.lineTo(-1.093105611107891E7 , 2160391.287670987);
			polyline6.lineTo(-1.093105579171664E7 , 2160390.8618546296);
			polyline6.lineTo(-1.0931053130364407E7 , 2160388.8392269313);
			polyline6.lineTo(-1.093105792079843E7 , 2160380.0035375115);
			polyline6.lineTo(-1.0931044933399523E7 , 2160372.9775676113);
			polyline6.lineTo(-1.0931025452301165E7 , 2160412.684942955);
			polyline6.lineTo(-1.0931055578808462E7 , 2160429.2917809007);
			listaPolyline.add(polyline6);
			Polygon polyline7 = new Polygon();
			polyline7.startPath(-1.0930958918495322E7 , 2160377.3421852747);
			polyline7.lineTo(-1.0930978399593681E7 , 2160387.668231946);
			polyline7.lineTo(-1.0930999264595201E7 , 2160349.7705761218);
			polyline7.lineTo(-1.0930993409620285E7 , 2160346.789861619);
			polyline7.lineTo(-1.0930989364364889E7 , 2160354.2416478763);
			polyline7.lineTo(-1.0930980954491826E7 , 2160349.344759764);
			polyline7.lineTo(-1.0930985106201313E7 , 2160341.999427596);
			polyline7.lineTo(-1.0930979570588665E7 , 2160338.699350825);
			polyline7.lineTo(-1.0930977228598697E7 , 2160343.06396849);
			polyline7.lineTo(-1.0930958918495322E7 , 2160377.4486393644);
			listaPolyline.add(polyline7);
			Polygon polyline8 = new Polygon();
			polyline8.startPath(-1.0931014806892226E7 , 2160406.936422128);
			polyline8.lineTo(-1.0931025558755254E7 , 2160412.684942955);
			polyline8.lineTo(-1.0931035565439656E7 , 2160391.819941434);
			polyline8.lineTo(-1.093103332990378E7 , 2160390.5424923613);
			polyline8.lineTo(-1.0931030881459724E7 , 2160395.4393804735);
			polyline8.lineTo(-1.0931027474928863E7 , 2160393.5232068645);
			polyline8.lineTo(-1.0931027474928863E7 , 2160393.203844596);
			polyline8.lineTo(-1.0931028752377935E7 , 2160391.0747628086);
			polyline8.lineTo(-1.0931022258678483E7 , 2160387.5617778585);
			polyline8.lineTo(-1.0931018426331265E7 , 2160395.1200182056);
			polyline8.lineTo(-1.0931020342504874E7 , 2160396.07810501);
			polyline8.lineTo(-1.0931014806892226E7 , 2160406.936422128);
			listaPolyline.add(polyline8);
			Polygon polyline9 = new Polygon();
			polyline9.startPath(-1.0930969244542036E7 , 2160346.683407529);
			polyline9.lineTo(-1.0930973822067881E7 , 2160349.3447597637);
			polyline9.lineTo(-1.0930977122144653E7 , 2160343.1704225787);
			polyline9.lineTo(-1.093097233171063E7 , 2160340.5090703443);
			polyline9.lineTo(-1.0930969138087947E7 , 2160346.683407529);
			listaPolyline.add(polyline9);
			Polygon polyline10 = new Polygon();
			polyline10.startPath(-1.093099639033476E7 , 2160397.249099993);
			polyline10.lineTo(-1.0931014913346315E7 , 2160406.8299680385);
			polyline10.lineTo(-1.0931020236050785E7 , 2160396.1845590994);
			polyline10.lineTo(-1.0931016190795386E7 , 2160394.0554773114);
			polyline10.lineTo(-1.0931021194137588E7 , 2160384.0487929084);
			polyline10.lineTo(-1.0931025558755254E7 , 2160385.9649665174);
			polyline10.lineTo(-1.093102790074522E7 , 2160380.7487161374);
			polyline10.lineTo(-1.0931023110311197E7 , 2160378.406726171);
			polyline10.lineTo(-1.0931025771663433E7 , 2160373.1904757903);
			polyline10.lineTo(-1.0931025558755254E7 , 2160372.7646594327);
			polyline10.lineTo(-1.0931012358448168E7 , 2160366.377414069);
			polyline10.lineTo(-1.0931007248651879E7 , 2160376.277644383);
			polyline10.lineTo(-1.0931012677810438E7 , 2160379.1519047963);
			polyline10.lineTo(-1.0931006609927341E7 , 2160390.3295841827);
			polyline10.lineTo(-1.093100150013105E7 , 2160387.0295074116);
			polyline10.lineTo(-1.093099660324294E7 , 2160397.3555540824);
			listaPolyline.add(polyline10);
			Polygon polyline11 = new Polygon();
			polyline11.startPath(-1.0930944653647387E7 , 2160370.1033071955);
			polyline11.lineTo(-1.0930959024949456E7 , 2160377.4486393635);
			polyline11.lineTo(-1.0930973822067881E7 , 2160349.3447597637);
			polyline11.lineTo(-1.093096519928664E7 , 2160344.4478716515);
			polyline11.lineTo(-1.0930970734899288E7 , 2160334.22827907);
			polyline11.lineTo(-1.093096509283255E7 , 2160331.034656388);
			polyline11.lineTo(-1.0930960408852616E7 , 2160340.189708076);
			polyline11.lineTo(-1.0930944653647387E7 , 2160370.2097612848);
			polyline11.lineTo(-1.0930944866555566E7 , 2160370.2097612848);
			listaPolyline.add(polyline11);
			Polygon polyline13 = new Polygon();
			polyline13.startPath(-1.0930924320916312E7 , 2160359.2449900773);
			polyline13.lineTo(-1.0930944973009655E7 , 2160370.2097612848);
			polyline13.lineTo(-1.0930960515306706E7 , 2160339.976799897);
			listaPolyline.add(polyline13);
			Polygon polyline14 = new Polygon();
			polyline14.startPath(-1.0930960515306706E7 , 2160340.189708076);
			polyline14.lineTo(-1.0930956044234952E7 , 2160337.6348099303);
			polyline14.lineTo(-1.0930953702244986E7 , 2160341.6800653273);
			polyline14.lineTo(-1.0930945505280102E7 , 2160337.421901752);
			polyline14.lineTo(-1.0930942418111509E7 , 2160343.5962389363);
			polyline14.lineTo(-1.0930935817957968E7 , 2160339.7638917184);
			polyline14.lineTo(-1.093093720186113E7 , 2160337.421901752);
			polyline14.lineTo(-1.0930941566478794E7 , 2160339.657437629);
			polyline14.lineTo(-1.0930943163290136E7 , 2160336.0379985897);
			polyline14.lineTo(-1.0930936350228414E7 , 2160331.9927431927);
			polyline14.lineTo(-1.0930935817957968E7 , 2160331.886289103);
			polyline14.lineTo(-1.0930932624335285E7 , 2160338.273534467);
			polyline14.lineTo(-1.0930933156605732E7 , 2160338.4864426455);
			polyline14.lineTo(-1.0930934114692537E7 , 2160339.2316212715);
			polyline14.lineTo(-1.0930924427370401E7 , 2160359.4578982564);
			listaPolyline.add(polyline14);
			Polygon polyline15 = new Polygon();
			polyline15.startPath(-1.0930931453340301E7 , 2160354.986826502);
			polyline15.lineTo(-1.093093517923343E7 , 2160356.7965460215);
			polyline15.lineTo(-1.0930939543851096E7 , 2160348.386672959);
			polyline15.lineTo(-1.0930935711503878E7 , 2160346.364045261);
			polyline15.lineTo(-1.0930931240432123E7 , 2160354.986826502);
			polyline15.lineTo(-1.093093155979439E7 , 2160355.1997346804);
			listaPolyline.add(polyline15);
			Polygon polyline16 = new Polygon();
			polyline16.startPath(-1.0930899836475752E7 , 2160346.364045261);
			polyline16.lineTo(-1.0930924640278582E7 , 2160359.3514441666);
			polyline16.lineTo(-1.0930934114692537E7 , 2160339.3380753607);
			polyline16.lineTo(-1.0930932624335285E7 , 2160338.3799885563);
			polyline16.lineTo(-1.0930926982268548E7 , 2160335.1863658745);
			polyline16.lineTo(-1.0930928898442157E7 , 2160331.247564567);
			polyline16.lineTo(-1.0930928685533978E7 , 2160330.821748209);
			polyline16.lineTo(-1.0930927088722637E7 , 2160329.970115494);
			polyline16.lineTo(-1.0930927727447173E7 , 2160328.692666421);
			polyline16.lineTo(-1.0930927195176726E7 , 2160328.3733041533);
			polyline16.lineTo(-1.0930913569053283E7 , 2160321.347334253);
			polyline16.lineTo(-1.0930912717420569E7 , 2160321.347334253);
			polyline16.lineTo(-1.0930899410659395E7 , 2160345.9382289033);
			polyline16.lineTo(-1.0930899942929842E7 , 2160346.47049935);
			polyline16.lineTo(-1.0930899942929842E7 , 2160346.47049935);
			listaPolyline.add(polyline16);
			Polygon polyline17 = new Polygon();
			polyline17.startPath(-1.0930914740048267E7 , 2160342.9575144);
			polyline17.lineTo(-1.0930920062752737E7 , 2160346.0446829926);
			polyline17.lineTo(-1.0930922511196792E7 , 2160340.828432612);
			polyline17.lineTo(-1.0930917188492322E7 , 2160337.6348099303);
			polyline17.lineTo(-1.0930914740048267E7 , 2160342.9575144);
			listaPolyline.add(polyline17);
			Polygon polyline18 = new Polygon();
			polyline18.startPath(-1.093104770120599E7 , 2160409.1719579985);
			polyline18.lineTo(-1.0931053981997263E7 , 2160413.110759306);
			polyline18.lineTo(-1.0931055791716782E7 , 2160409.7042284454);
			polyline18.lineTo(-1.0931049723833688E7 , 2160405.4460648694);
			polyline18.lineTo(-1.0931047807660079E7 , 2160409.278412088);
			listaPolyline.add(polyline18);
			Polygon polyline20 = new Polygon();
			polyline20.startPath(-1.0931077411651608E7 , 2160390.730173256);
			polyline20.lineTo(-1.0931058539245376E7 , 2160379.9874189394);
			polyline20.lineTo(-1.0931053022695862E7 , 2160389.2784496993);
			polyline20.lineTo(-1.0931056216487687E7 , 2160391.891552101);
			polyline20.lineTo(-1.0931050990282884E7 , 2160401.472927572);
			polyline20.lineTo(-1.0931066668897292E7 , 2160410.1832689103);
			polyline20.lineTo(-1.0931077121306898E7 , 2160390.730173256);
			listaPolyline.add(polyline20);
			Polygon polyline22 = new Polygon();
			polyline22.startPath(-1.0931039666839143E7 , 2160370.1156987553);
			polyline22.lineTo(-1.0931045038216302E7 , 2160373.019145868);
			polyline22.lineTo(-1.0931057958555954E7 , 2160380.4229360046);
			polyline22.lineTo(-1.0931058684417732E7 , 2160379.8422465823);
			polyline22.lineTo(-1.0931077266479252E7 , 2160390.7301732544);
			polyline22.lineTo(-1.0931085250958811E7 , 2160376.2129376917);
			polyline22.lineTo(-1.0931074508204496E7 , 2160370.551215822);
			polyline22.lineTo(-1.0931070153033827E7 , 2160380.2777636494);
			polyline22.lineTo(-1.0931041844424479E7 , 2160365.6153557305);
			polyline22.lineTo(-1.0931039666839143E7 , 2160370.1156987553);
			listaPolyline.add(polyline22);
			Polygon polyline24 = new Polygon();
			polyline24.startPath(-1.0931034876151407E7 , 2160376.358110047);
			polyline24.lineTo(-1.0931041554079767E7 , 2160379.8422465823);
			polyline24.lineTo(-1.0931035602013186E7 , 2160392.036724455);
			polyline24.lineTo(-1.0931033424427852E7 , 2160390.4398285435);
			polyline24.lineTo(-1.0931025439948292E7 , 2160385.9394855187);
			polyline24.lineTo(-1.093103385994492E7 , 2160367.2122516423);
			polyline24.lineTo(-1.0931038069943232E7 , 2160369.389836977);
			polyline24.lineTo(-1.0931034876151407E7 , 2160376.358110047);
			polyline24.lineTo(-1.0931034876151407E7 , 2160376.5032824026);
			listaPolyline.add(polyline24);
			Polygon polyline26 = new Polygon();
			polyline26.startPath(-1.0931052151661728E7 , 2160370.551215822);
			polyline26.lineTo(-1.093105374855764E7 , 2160366.3412175085);
			polyline26.lineTo(-1.0931047215801636E7 , 2160363.0022533294);
			polyline26.lineTo(-1.0931047215801636E7 , 2160362.5667362623);
			polyline26.lineTo(-1.0931048812697548E7 , 2160358.5019103047);
			polyline26.lineTo(-1.093106274924369E7 , 2160365.905700442);
			polyline26.lineTo(-1.0931058829590088E7 , 2160374.325697068);
			polyline26.lineTo(-1.0931051861317016E7 , 2160370.8415605333);
			polyline26.lineTo(-1.0931052296834083E7 , 2160370.2608711105);
			listaPolyline.add(polyline26);
			Polygon polyline28 = new Polygon();
			polyline28.startPath(-1.0931063765450178E7 , 2160364.599149241);
			polyline28.lineTo(-1.0931074798549207E7 , 2160370.6963881776);
			polyline28.lineTo(-1.093107000786147E7 , 2160380.1325912937);
			polyline28.lineTo(-1.0931058829590088E7 , 2160374.470869424);
			polyline28.lineTo(-1.0931063620277822E7 , 2160364.4539768854);
			listaPolyline.add(polyline28);
			Polygon polyline30 = new Polygon();
			polyline30.startPath(-1.093105607131533E7 , 2160354.872601414);
			polyline30.lineTo(-1.0931059845796576E7 , 2160356.469497326);
			polyline30.lineTo(-1.0931060426485999E7 , 2160354.1467396356);
			polyline30.lineTo(-1.093106274924369E7 , 2160355.3081184807);
			polyline30.lineTo(-1.09310643461396E7 , 2160351.0981201674);
			polyline30.lineTo(-1.0931068120620847E7 , 2160352.9853607905);
			polyline30.lineTo(-1.0931067539931424E7 , 2160354.7274290584);
			polyline30.lineTo(-1.0931090477163615E7 , 2160366.196045153);
			polyline30.lineTo(-1.0931085541303523E7 , 2160376.2129376917);
			polyline30.lineTo(-1.093107900854752E7 , 2160372.7288011564);
			polyline30.lineTo(-1.0931081766822277E7 , 2160367.6477687093);
			polyline30.lineTo(-1.0931065652690802E7 , 2160359.082599727);
			polyline30.lineTo(-1.093106274924369E7 , 2160365.760528086);
			polyline30.lineTo(-1.0931053167868217E7 , 2160360.679495639);
			polyline30.lineTo(-1.093105607131533E7 , 2160354.872601414);
			listaPolyline.add(polyline30);
			Polygon polyline32 = new Polygon();
			polyline32.startPath(-1.0931057523038886E7 , 2160352.114326658);
			polyline32.lineTo(-1.0931062894416045E7 , 2160355.3081184817);
			polyline32.lineTo(-1.09310643461396E7 , 2160351.098120169);
			polyline32.lineTo(-1.0931068556137914E7 , 2160353.1305331476);
			polyline32.lineTo(-1.093107668578983E7 , 2160356.905014394);
			polyline32.lineTo(-1.0931077847168675E7 , 2160353.4208778585);
			polyline32.lineTo(-1.0931078863375165E7 , 2160353.566050214);
			polyline32.lineTo(-1.0931087428544147E7 , 2160358.066393239);
			polyline32.lineTo(-1.0931085686475879E7 , 2160361.405357418);
			polyline32.lineTo(-1.093109163854246E7 , 2160364.7443215977);
			polyline32.lineTo(-1.0931097735781396E7 , 2160352.404671369);
			polyline32.lineTo(-1.0931070153033827E7 , 2160337.3067463837);
			polyline32.lineTo(-1.0931064636484312E7 , 2160349.6463966123);
			polyline32.lineTo(-1.0931059700624222E7 , 2160347.323638922);
			polyline32.lineTo(-1.0931057523038886E7 , 2160352.2594990134);
			listaPolyline.add(polyline32);
			Polygon polyline34 = new Polygon();
			polyline34.startPath(-1.0931054038902352E7 , 2160349.06570719);
			polyline34.lineTo(-1.093105810372831E7 , 2160350.952947813);
			polyline34.lineTo(-1.0931059845796578E7 , 2160347.033294211);
			polyline34.lineTo(-1.0931064781656668E7 , 2160349.791568968);
			polyline34.lineTo(-1.0931070153033827E7 , 2160337.3067463837);
			polyline34.lineTo(-1.0931059700624222E7 , 2160331.6450245143);
			polyline34.lineTo(-1.0931055635798264E7 , 2160340.6457105633);
			polyline34.lineTo(-1.0931059700624222E7 , 2160342.242606475);
			polyline34.lineTo(-1.093105839407302E7 , 2160345.0008812323);
			polyline34.lineTo(-1.0931055635798264E7 , 2160343.839502387);
			polyline34.lineTo(-1.0931053603385285E7 , 2160349.06570719);
			polyline34.lineTo(-1.0931054619591774E7 , 2160349.356051901);
			listaPolyline.add(polyline34);
			Polygon polyline36 = new Polygon();
			polyline36.startPath(-1.0931032988910774E7 , 2160350.0819136784);
			polyline36.lineTo(-1.0931040973390333E7 , 2160353.8563949247);
			polyline36.lineTo(-1.093103415028962E7 , 2160367.3574239984);
			polyline36.lineTo(-1.093103981201149E7 , 2160370.1156987553);
			polyline36.lineTo(-1.0931045909250425E7 , 2160357.7760485266);
			polyline36.lineTo(-1.0931047651318694E7 , 2160358.5019103047);
			polyline36.lineTo(-1.0931055780970609E7 , 2160340.790882918);
			polyline36.lineTo(-1.0931055490625897E7 , 2160340.6457105624);
			polyline36.lineTo(-1.0931059845796566E7 , 2160331.6450245134);
			polyline36.lineTo(-1.0931040828217978E7 , 2160321.4829596193);
			polyline36.lineTo(-1.0931020939605257E7 , 2160359.8084615055);
			polyline36.lineTo(-1.0931026456154771E7 , 2160363.0022533294);
			polyline36.lineTo(-1.093103313408313E7 , 2160350.227086034);
			listaPolyline.add(polyline36);
			Polygon polyline38 = new Polygon();
			polyline38.startPath(-1.0931038069943221E7 , 2160343.549157675);
			polyline38.lineTo(-1.0931045328561002E7 , 2160347.03329421);
			polyline38.lineTo(-1.0931048957869893E7 , 2160338.6132975835);
			polyline38.lineTo(-1.0931041263735045E7 , 2160334.6936439816);
			polyline38.lineTo(-1.0931037344081443E7 , 2160343.403985319);
			polyline38.lineTo(-1.0931038505460288E7 , 2160343.8395023863);
			listaPolyline.add(polyline38);
			Polygon polyline40 = new Polygon();
			polyline40.startPath(-1.0931012664780976E7 , 2160326.999509132);
			polyline40.lineTo(-1.0931009470989153E7 , 2160333.5322651356);
			polyline40.lineTo(-1.093101222926391E7 , 2160334.983988692);
			polyline40.lineTo(-1.093101121305742E7 , 2160337.8874358046);
			polyline40.lineTo(-1.093102384305236E7 , 2160344.5653641634);
			polyline40.lineTo(-1.0931025294775916E7 , 2160341.2263999837);
			polyline40.lineTo(-1.0931029649946585E7 , 2160343.4039853183);
			polyline40.lineTo(-1.0931040828217968E7 , 2160321.3377872626);
			polyline40.lineTo(-1.0931030085463652E7 , 2160315.8212377485);
			polyline40.lineTo(-1.0931027036844183E7 , 2160322.4991661077);
			polyline40.lineTo(-1.0931022246156448E7 , 2160320.031236062);
			polyline40.lineTo(-1.0931017600641068E7 , 2160329.031922111);
			polyline40.lineTo(-1.0931012664780976E7 , 2160326.1284749983);
			polyline40.lineTo(-1.0931012664780976E7 , 2160327.2898538434);
			listaPolyline.add(polyline40);
			Polygon polyline42 = new Polygon();
			polyline42.startPath(-1.0931016148917511E7 , 2160332.8064033575);
			polyline42.lineTo(-1.0931025585120628E7 , 2160338.0326081603);
			polyline42.lineTo(-1.0931031537187207E7 , 2160325.112268509);
			polyline42.lineTo(-1.0931022391328804E7 , 2160320.1764084175);
			polyline42.lineTo(-1.0931016003745155E7 , 2160332.8064033575);
			listaPolyline.add(polyline42);
			Polygon polyline44 = new Polygon();
			polyline44.startPath(-1.093098101720745E7 , 2160338.0326081603);
			polyline44.lineTo(-1.0930986098239897E7 , 2160340.936055273);
			polyline44.lineTo(-1.093098696927403E7 , 2160339.3391593606);
			polyline44.lineTo(-1.093100192202666E7 , 2160347.6139836316);
			polyline44.lineTo(-1.0931003373750215E7 , 2160343.984674741);
			polyline44.lineTo(-1.0931004099611994E7 , 2160344.275019452);
			polyline44.lineTo(-1.0931012374436265E7 , 2160348.9205348324);
			polyline44.lineTo(-1.0931009761333864E7 , 2160353.5660502124);
			polyline44.lineTo(-1.0931017165124001E7 , 2160357.340531459);
			polyline44.lineTo(-1.0931018036158135E7 , 2160355.598463191);
			polyline44.lineTo(-1.0931022100984093E7 , 2160357.7760485257);
			polyline44.lineTo(-1.093102485925885E7 , 2160351.6788095892);
			polyline44.lineTo(-1.0931019633054046E7 , 2160349.356051899);
			polyline44.lineTo(-1.0931022100984093E7 , 2160343.549157674);
			polyline44.lineTo(-1.0931008309610307E7 , 2160336.435712248);
			polyline44.lineTo(-1.0931007438576173E7 , 2160338.903642294);
			polyline44.lineTo(-1.093100526099084E7 , 2160337.8874358046);
			polyline44.lineTo(-1.0931006132024974E7 , 2160335.8550228258);
			polyline44.lineTo(-1.0930987695135808E7 , 2160326.1284749983);
			polyline44.lineTo(-1.093098101720745E7 , 2160338.0326081603);
			polyline44.lineTo(-1.093098101720745E7 , 2160338.0326081603);
			listaPolyline.add(polyline44);
			Polygon polyline46 = new Polygon();
			polyline46.startPath(-1.0930990308238197E7 , 2160321.77330433);
			polyline46.lineTo(-1.093100351892256E7 , 2160328.5964050447);
			polyline46.lineTo(-1.0931006277197316E7 , 2160322.7895108196);
			polyline46.lineTo(-1.0931012664780963E7 , 2160326.41881971);
			polyline46.lineTo(-1.0931017890985766E7 , 2160329.177094467);
			polyline46.lineTo(-1.093102210098408E7 , 2160320.4667531294);
			polyline46.lineTo(-1.0931027036844172E7 , 2160322.499166108);
			polyline46.lineTo(-1.0931029940291284E7 , 2160315.6760653937);
			polyline46.lineTo(-1.0931000615475446E7 , 2160299.85227863);
			polyline46.lineTo(-1.093098987272113E7 , 2160321.77330433);
			polyline46.lineTo(-1.0930990743755264E7 , 2160322.208821397);
			listaPolyline.add(polyline46);
			Polygon polyline48 = new Polygon();
			polyline48.startPath(-1.0930981597896859E7 , 2160324.967096154);
			polyline48.lineTo(-1.0930986678929307E7 , 2160327.725370911);
			polyline48.lineTo(-1.093098987272113E7 , 2160321.482959619);
			polyline48.lineTo(-1.0931000760647802E7 , 2160299.85227863);
			polyline48.lineTo(-1.0930980436518013E7 , 2160288.9643519577);
			polyline48.lineTo(-1.0930978984794458E7 , 2160292.1581437816);
			polyline48.lineTo(-1.093098087203508E7 , 2160293.3195226267);
			polyline48.lineTo(-1.09309762265197E7 , 2160302.4653810314);
			polyline48.lineTo(-1.0930974484451434E7 , 2160301.5943468977);
			polyline48.lineTo(-1.0930970564797832E7 , 2160309.723998813);
			polyline48.lineTo(-1.0930984791688683E7 , 2160316.837444239);
			polyline48.lineTo(-1.0930981017207436E7 , 2160325.1122685093);
			polyline48.lineTo(-1.0930982178586282E7 , 2160325.1122685093);
			listaPolyline.add(polyline48);
			Polygon polyline50 = new Polygon();
			polyline50.startPath(-1.0930967080661297E7 , 2160317.1277889498);
			polyline50.lineTo(-1.0930981162379792E7 , 2160324.6767514427);
			polyline50.lineTo(-1.0930984791688683E7 , 2160317.1277889498);
			polyline50.lineTo(-1.0930970709970187E7 , 2160309.723998813);
			polyline50.lineTo(-1.093096664514423E7 , 2160317.1277889498);
			polyline50.lineTo(-1.0930967661350718E7 , 2160317.418133661);
			listaPolyline.add(polyline50);
			Polygon polyline52 = new Polygon();
			polyline52.startPath(-1.0930958951009382E7 , 2160313.062962992);
			polyline52.lineTo(-1.0930966790316585E7 , 2160317.1277889498);
			polyline52.lineTo(-1.0930970709970187E7 , 2160309.869171168);
			polyline52.lineTo(-1.0930962435145916E7 , 2160305.0784834325);
			polyline52.lineTo(-1.0930958951009382E7 , 2160311.0305500133);
			polyline52.lineTo(-1.0930959822043514E7 , 2160311.901584147);
			polyline52.lineTo(-1.0930958805837026E7 , 2160313.208135348);
			listaPolyline.add(polyline52);
			Polygon polyline54 = new Polygon();
			polyline54.startPath(-1.0930962580318272E7 , 2160299.85227863);
			polyline54.lineTo(-1.0930967080661297E7 , 2160302.3202086757);
			polyline54.lineTo(-1.0930968677557208E7 , 2160298.4005550737);
			polyline54.lineTo(-1.09309762265197E7 , 2160302.3202086757);
			polyline54.lineTo(-1.093098087203508E7 , 2160293.4646949824);
			polyline54.lineTo(-1.0930978694449747E7 , 2160292.3033161373);
			polyline54.lineTo(-1.0930980436518013E7 , 2160288.819179602);
			polyline54.lineTo(-1.0930964903075961E7 , 2160280.979872398);
			polyline54.lineTo(-1.0930964032041827E7 , 2160283.1574577326);
			polyline54.lineTo(-1.0930944724118529E7 , 2160272.850220483);
			polyline54.lineTo(-1.0930940078603148E7 , 2160282.7219406655);
			polyline54.lineTo(-1.093095866066467E7 , 2160293.174350271);
			polyline54.lineTo(-1.0930956918596402E7 , 2160296.368142095);
			polyline54.lineTo(-1.0930962725490628E7 , 2160299.9974509855);
			listaPolyline.add(polyline54);
			Polygon polyline56 = new Polygon();
			polyline56.startPath(-1.0930939788258437E7 , 2160296.222969739);
			polyline56.lineTo(-1.0930948788944487E7 , 2160301.449174542);
			polyline56.lineTo(-1.0930951402046887E7 , 2160296.8036591616);
			polyline56.lineTo(-1.093094965997862E7 , 2160295.787452672);
			polyline56.lineTo(-1.0930952853770444E7 , 2160289.980558447);
			polyline56.lineTo(-1.093095866066467E7 , 2160293.174350271);
			polyline56.lineTo(-1.0930956773424046E7 , 2160296.658486806);
			polyline56.lineTo(-1.0930962725490628E7 , 2160300.142623341);
			polyline56.lineTo(-1.0930958370319959E7 , 2160308.7077923235);
			polyline56.lineTo(-1.0930951547219243E7 , 2160305.5140004996);
			polyline56.lineTo(-1.0930950531012755E7 , 2160307.4012411227);
			polyline56.lineTo(-1.0930958951009382E7 , 2160310.8853776576);
			polyline56.lineTo(-1.0930959822043514E7 , 2160312.1919288584);
			polyline56.lineTo(-1.0930958805837026E7 , 2160313.208135348);
			polyline56.lineTo(-1.0930956483079335E7 , 2160317.8536507282);
			polyline56.lineTo(-1.0930953434459867E7 , 2160316.4019271717);
			polyline56.lineTo(-1.0930946175842086E7 , 2160329.9029562455);
			polyline56.lineTo(-1.0930953724804578E7 , 2160333.6774374917);
			polyline56.lineTo(-1.0930960547905292E7 , 2160320.4667531294);
			polyline56.lineTo(-1.0930983775482193E7 , 2160332.9515757137);
			polyline56.lineTo(-1.093098087203508E7 , 2160338.0326081607);
			polyline56.lineTo(-1.0930972016521387E7 , 2160332.5160586466);
			polyline56.lineTo(-1.0930970709970187E7 , 2160334.4032992697);
			polyline56.lineTo(-1.0930960838250004E7 , 2160328.7415774004);
			polyline56.lineTo(-1.0930956192734623E7 , 2160337.5970910937);
			polyline56.lineTo(-1.0930953579632223E7 , 2160341.6619170513);
			polyline56.lineTo(-1.0930943272394972E7 , 2160336.000195182);
			polyline56.lineTo(-1.093093601377719E7 , 2160331.9353692243);
			polyline56.lineTo(-1.0930932819985367E7 , 2160338.4681252274);
			polyline56.lineTo(-1.0930926867918786E7 , 2160334.9839886925);
			polyline56.lineTo(-1.0930928900331765E7 , 2160331.209507446);
			polyline56.lineTo(-1.0930927158263497E7 , 2160330.1933009564);
			polyline56.lineTo(-1.093092773895292E7 , 2160328.886749756);
			polyline56.lineTo(-1.0930913947579136E7 , 2160321.482959619);
			polyline56.lineTo(-1.0930912641027935E7 , 2160321.482959619);
			polyline56.lineTo(-1.0930912641027935E7 , 2160320.9022701965);
			polyline56.lineTo(-1.0930933981364213E7 , 2160280.544355331);
			polyline56.lineTo(-1.0930944724118529E7 , 2160286.786766623);
			polyline56.lineTo(-1.0930939788258437E7 , 2160296.222969739);
			listaPolyline.add(polyline56);
			Polygon polyline58 = new Polygon();
			polyline58.startPath(-1.0930933981364213E7 , 2160306.8205517);
			polyline58.lineTo(-1.0930941530326705E7 , 2160310.7402053024);
			polyline58.lineTo(-1.0930934271708924E7 , 2160325.5477855764);
			polyline58.lineTo(-1.093092643240172E7 , 2160321.1926149074);
			polyline58.lineTo(-1.0930933981364213E7 , 2160306.6753793447);
			listaPolyline.add(polyline58);
			Polygon polyline60 = new Polygon();
			polyline60.startPath(-1.0930978984794458E7 , 2160307.981930545);
			polyline60.lineTo(-1.0930987114446374E7 , 2160312.48227357);
			polyline60.lineTo(-1.0930990017893486E7 , 2160306.8205517);
			polyline60.lineTo(-1.0930981743069215E7 , 2160302.17503632);
			polyline60.lineTo(-1.0930978984794458E7 , 2160307.981930545);
			listaPolyline.add(polyline60);
			Polygon polyline62 = new Polygon();
			polyline62.startPath(-1.0931057650898904E7 , 2160433.127749156);
			polyline62.lineTo(-1.0931102144948298E7 , 2160347.882140501);
			polyline62.lineTo(-1.0930937059550073E7 , 2160260.1415384216);
			polyline62.lineTo(-1.0930894644661864E7 , 2160345.8029793142);
			polyline62.lineTo(-1.0931057650898904E7 , 2160433.127749156);
			listaPolyline.add(polyline62);
			Polygon polyline64 = new Polygon();
			polyline64.startPath(-1.0931002553127443E7 , 2160319.189716124);
			polyline64.lineTo(-1.0931006295617579E7 , 2160311.9126519705);
			polyline64.lineTo(-1.0931001929379087E7 , 2160309.001826309);
			polyline64.lineTo(-1.0930998186888952E7 , 2160317.1105549373);
			polyline64.lineTo(-1.0931002553127443E7 , 2160319.189716124);
			listaPolyline.add(polyline64);



			SimpleLineSymbol lineSymbol = new SimpleLineSymbol(Color.red, 3, SimpleLineSymbol.Style.SOLID);
			
			for (Polygon x: listaPolyline) {
				Graphic disa = new Graphic(x, lineSymbol);
				graphicsLayer.addGraphic(disa);
			}
			

			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		System.out.println("despues " + graphicsLayer.getNumberOfGraphics());
		return graphicsLayer;
	}
	
	public HitTestListener evenToTest () {
		HitTestListener listener = new HitTestListener() {
			@Override
			public void featureHit(HitTestEvent event) {
				 HitTestOverlay overlay = (HitTestOverlay) event.getSource();
			      List<Feature> hitFeatures = overlay.getHitFeatures();
			      GraphicsLayer graphicsLayer = (GraphicsLayer) overlay.getLayer();
			      //select or de-select each hit graphic
			      for (Feature feature : hitFeatures) {
			        if (graphicsLayer.isGraphicSelected((int) feature.getId())) {
			          graphicsLayer.unselect((int) feature.getId());
			        }
			        else {
			          graphicsLayer.select((int) feature.getId());
			        }
			      }
			}
		};
		return listener;
	}
	
	public void unionPredio (JMap map) {
		int[] selectedIDs = graphicsLayer.getSelectionIDs();
	    // if there are less than 2 selected graphics, there is no need to perform a union
	    if (selectedIDs.length < 2) { 
	      return;
	    }
	    Geometry[] selectedGeometries = new Geometry[selectedIDs.length];
	    // get a list of selected geometries
	    int i = 0;
	    for (int id : selectedIDs) {
	      // add it to the selected collection 
	      selectedGeometries[i] = graphicsLayer.getGraphic(id).getGeometry();
	      i++;
	    }
	    // union the selected geometries
	    Geometry unionGeometry = GeometryEngine.union(selectedGeometries, map.getSpatialReference());
	    // add the union result to the graphics layer
	    SimpleLineSymbol lineSymbol = new SimpleLineSymbol(Color.red, 3, SimpleLineSymbol.Style.SOLID);
	    Graphic unionGraphic = new Graphic(unionGeometry, lineSymbol);
	    graphicsLayer.addGraphic(unionGraphic);
	    for (int id : selectedIDs) { 
	      graphicsLayer.removeGraphic(id);
	    }
	}
	
	public void editar (JMap map) {
		int[] selectedIDs = graphicsLayer.getSelectionIDs(); 
		System.out.println("ss " + selectedIDs.length);
		if (selectedIDs.length < 1) {
			return;
		}
		Geometry[] selectedGeometries = new Geometry[selectedIDs.length];
		int i = 0;
	    for (int id : selectedIDs) { 
	      // add it to the selected collection 
	    	System.out.println("editar " + graphicsLayer.getGraphic(id).getGeometry());
	    	Polygon polygon = new Polygon();
	    	polygon = (Polygon) graphicsLayer.getGraphic(id).getGeometry();
	    	
	      selectedGeometries[i] = graphicsLayer.getGraphic(id).getGeometry();
	      i++;
	    }
	    
	}

}
