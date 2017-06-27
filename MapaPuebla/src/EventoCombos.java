import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.esri.map.JMap;

public class EventoCombos {
	
	public void eventoCmbScala (JComboBox cmbScala, JMap map) {
		cmbScala.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) { 
	             	
	            }
	        });
	}

}
