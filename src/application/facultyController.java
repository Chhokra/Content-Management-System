package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class facultyController {
	public Button book;
	public TextField bookRoom;
	public TableView<Room> availTable, bookedTable;
	public TableColumn<Room, String> availRoomNum, availSlot, bookedRoom, bookedSlot;
	public TableColumn<Room, Integer>availCap, bookedCap;
	/*public TableColumn<Room,Button> bookedCancel;*/
	Room[] list = Main.list;
	Faculty user = (Faculty) loginController.user;
	ObservableList<Room> avail_data = FXCollections.observableArrayList();
	ObservableList<Room> booked_data = FXCollections.observableArrayList();





	    @FXML
	    public void initialize() {


			for(int i=0; i<list.length; i++){
				if (!(list[i].isBooked())){
					avail_data.add(list[i]);
				}
				if(list[i].BookedBy().isEmpty()==false){
					booked_data.add(list[i]);
				}
			}

	        availRoomNum.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        availCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        availSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));
	        availTable.setItems(avail_data);

	        bookedRoom.setCellValueFactory(new PropertyValueFactory<Room, String>("name"));
	        bookedCap.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
	        bookedSlot.setCellValueFactory(new PropertyValueFactory<Room, String>("slot"));
	        bookedTable.setItems(booked_data);



	        book.setOnAction(arg0 -> {
				handleBookingAction(arg0);
			});


	}





		private void handleBookingAction(ActionEvent arg0) {
			String roomName=bookRoom.getText();
			for(int i=0; i<list.length; i++){
				if (roomName.equals(list[i].getName())){
					user.bookRoom(list[i]);
					avail_data.remove(list[i]);
					booked_data.add(list[i]);
					break;
				}
			}


		}

}
