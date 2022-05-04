/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Service.Ipifx;
import Util.MyDB;
import entities.Articles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MOUEFEK
 */
public class articles_service implements Ipifx<Articles> {
    Connection connection ; 
    private Statement stm;
    public articles_service(){
        connection=MyDB.getInstance().getConnection();    
    }

    @Override
    public void ajouter(Articles t) {
        try {
            String req1="insert into Articles (titre,description,date,image,nom_jeux_id) values(?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(req1);
        ps.setString(1, t.getTitre());
        ps.setString(2, t.getDescription());
        ps.setString(4, t.getImage());
        ps.setDate(3, t.getDate());
        ps.setInt(5,t.getNom_jeux_id());
        ps.executeUpdate();
        
        }catch(SQLException ex){
            System.out.println(ex.getMessage());       
        }
    }

    @Override
    public void modifier(Articles t, int id) {
        try{
        String req = "UPDATE Articles set titre = '" +t.getTitre()+ "' , description = '" +t.getDescription()+"' , date = '" +t.getDate()+"' , image = '" +t.getImage()+"' , nom_jeux_id = '" +t.getNom_jeux_id()+"' where `id`='"+id+"' ";
        stm = connection.createStatement();
        stm.executeUpdate(req);
        }catch(SQLException ex ){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try{
        String req = "delete from Articles where `id`='"+id+"' ";
        stm = connection.createStatement();
        stm.executeUpdate(req);
        }catch(SQLException ex ){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Articles>recuperer() {
        List<Articles> list = new ArrayList<Articles>();
        try{
            String req = "select * from Articles";
            Statement st = connection.createStatement();
    
        ResultSet rs=st.executeQuery(req);
        while(rs.next())
        {
            Articles j = new Articles();
            j.setId(rs.getInt("id"));
            j.setTitre(rs.getString("titre"));
            j.setDescription(rs.getString("description"));
            j.setDate(rs.getDate("date"));
            j.setImage(rs.getString("image"));
            j.setNom_jeux_id(rs.getInt("nom_jeux_id"));
            list.add(j);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        return list;
    } 
}
