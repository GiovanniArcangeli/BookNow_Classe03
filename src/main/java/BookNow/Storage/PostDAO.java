package BookNow.Storage;

import BookNow.Entity.Cliente;
import BookNow.Entity.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

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
                String CF = rs.getString("CF");
                Cliente cliente = (Cliente) new ClienteDAO().getClienteByUsername(CF);
                posts.add(new Post(idPost, titolo, testo, tags, cliente));
            }
            return posts;
        }
        catch (SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public List<Post> doRetrieveByCliente(Cliente cliente){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Post, titolo, testo, tags from post where CF = ?");
            ps.setString(1, cliente.getCf());

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
    public int doSave(Post p){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into post values (?,?,?,?)");
            ps.setString(1, p.getTitolo());
            ps.setString(2, p.getTesto());
            ps.setString(3, p.getTags());
            ps.setString(4, p.getCliente().getCf());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");

            int idPost = -1;   //Serve per fare i controlli nei metodi chiamanti

            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    idPost = rs.getInt(1);
                    p.setID_Post(idPost);
                    ClienteDAO sc = new ClienteDAO();
                    sc.addPost(p);
                }
            }
            return idPost;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doDelete(Post p){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("delete from post where ID_Post = ?");
            ps.setInt(1, p.getID_Post());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");

            ClienteDAO service = new ClienteDAO();
            service.removePost(p);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
