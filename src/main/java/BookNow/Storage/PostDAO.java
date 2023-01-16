package BookNow.Storage;

import BookNow.Entity.Cliente;
import BookNow.Entity.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    /**
     * Restituisce tutti i post all'interno del forum
     * @return La lista dei post pubblicati
     */
    public List<Post> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select * from post");

            ResultSet rs = ps.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()){
                int idPost = rs.getInt("ID_Post");
                String titolo = rs.getString("titolo");
                String testo = rs.getString("testo");
                String tags = rs.getString("tags");
                String username = rs.getString("username");
                Cliente cliente = (Cliente) new ClienteDAO().getClienteByUsername(username);
                posts.add(new Post(idPost, titolo, testo, tags, cliente));
            }
            return posts;
        }
        catch (SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
    /**
     * Restituisce i post pubblicati da un Cliente specifico
     * @param cliente Il cliente
     * @return la lista con i post pubblicati dal cliente
     * @pre cliente!=null
     * @pre cliente.getIsAlbergatore()==false
     */
    public List<Post> doRetrieveByCliente(Cliente cliente){
        if(cliente==null || cliente.getIsAlbergatore()!=false) throw new IllegalArgumentException("Cliente non valido");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Post, titolo, testo, tags from post where username = ?");
            ps.setString(1, cliente.getUsername());

            ResultSet rs = ps.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()){
                int idPost = rs.getInt(1);
                String titolo = rs.getString(2);
                String testo = rs.getString(3);
                String tags = rs.getString(4);
                posts.add(new Post(idPost, titolo, testo, tags, cliente));
            }
            return posts;
        }
        catch (SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
    /**
     * Pubblica un nuovo post
     * @param p Il nuovo post
     * @pre p!=null
     */
    public void doSave(Post p){
        if(p==null) throw new IllegalArgumentException("Post non valido");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into post(titolo, testo, tags, username) values (?,?,?,?)");
            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getTesto());
            ps.setString(3, p.getTags());
            ps.setString(4, p.getAutore().getUsername());

            if(ps.executeUpdate() != 1)
                throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");

        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
