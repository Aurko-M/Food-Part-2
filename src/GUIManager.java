

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import java.util.regex.*;

import javax.swing.JFrame;

public class GUIManager extends JFrame implements ActionListener{
	
	protected Mediator mediator;
	
	public GUIManager(Mediator m){
		mediator = m;
		try {
			DietManagerGUI frame = new DietManagerGUI(m);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GUIManager(){
		
	}
	
	private boolean isValid(String field){
		if(!field.equals("")){
			return true; 
		}
		return false;
	}
	
	protected boolean isValidated(String[] fields){
		for(int i = 0; i < fields.length; i++){
			if(!isValid(fields[i])){
				return false;
			}
		}
		return true;
	}
	
	public void actionPerformed(ActionEvent ae) {
		
	}
}
