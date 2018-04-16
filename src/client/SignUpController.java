package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import database.datalog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.ServerInterface;

public class SignUpController {
	private String remoteObjectName = "MainFrame";
	@FXML
	private TextField user;
	
	@FXML
	private TextField password;
	
	@FXML
	private TextField check;
	
	@FXML
	private Label label;
	
	datalog d = null;
	
	public void Sign(ActionEvent ev) throws Exception {
		System.out.println("pota");
		if (d== null) { // cos� creo tutto solo una volta
			
		d = new datalog();
		
		//mi connetto al server per potermi registrare
		Registry registry = LocateRegistry.getRegistry();
		ServerInterface MainFrame = (ServerInterface) registry.lookup(remoteObjectName);
		}
		if(!user.getText().isEmpty() && user.getText().equals(user.getText().replaceAll(" ", ""))) {
			
		if(d.Repeat(user.getText()))
			//nome gi� presente
			label.setText("Nome utente gi� in uso");
		else {
			if(password.getText().equals(check.getText())  ) {
				if((password.getText().isEmpty())) {
					label.setText("La password non pu� essere vuota");
				}
				else {
				d.Signup(user.getText(),password.getText());
				((Node) ev.getSource()).getScene().getWindow().hide();
				LoginController C = new LoginController();
				Stage stage = new Stage();
				C.initialize(stage);
				}
				
			}
			else
				//le due password non coincidono
				label.setText("le Password non coincidono");
			
		}
		}
		else
			label.setText("Username non pu� essere vuoto/ o contenere spazi");
			
	}
	public void Back(ActionEvent ev) throws Exception {
		((Node) ev.getSource()).getScene().getWindow().hide();
		LoginController C = new LoginController();
		Stage stage = new Stage();
		C.initialize(stage);
	}

}
