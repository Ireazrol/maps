import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.esri.map.JMap;

public class Botones {
	private int numLayer=1;
	
	public void BtnAgregarCapa(JButton btnAgregarData, JMap map){
		btnAgregarData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	EventoMapa eventoMapa = new EventoMapa();
            	eventoMapa.addLayers(map, numLayer);
            	numLayer++;
            }
        });
	}
}
