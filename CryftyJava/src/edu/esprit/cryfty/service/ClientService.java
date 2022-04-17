package edu.esprit.cryfty.service;

import edu.esprit.cryfty.entity.Client;
import edu.esprit.cryfty.entity.User;
import edu.esprit.cryfty.utils.DataSource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientService {
    public ClientService() {
    }

    public Client getClientById(int id) {
        return new Client();
    }

    public void addClient(Client client){
        UserService usr = new UserService();
        usr.addUser(client.getUsername(),client.getRoles(),client.getPassword());
        User user = usr.showUser();
        String request = "insert into client(id,first_name,last_name,email,phone_number,age,address,avatar,couverture) " +
                "values(?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = DataSource.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1,user.getId());
            pst.setString(2,client.getFirstName());
            pst.setString(3,client.getLastName());
            pst.setString(4,client.getEmail());
            pst.setInt(5,client.getPhoneNumber());
            pst.setInt(6,client.getAge());
            pst.setString(7,client.getAddress());
            pst.setString(8,client.getAvatar());
            pst.setString(9,client.getCouverture());

            pst.executeUpdate();
            System.out.println("done");

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


}
