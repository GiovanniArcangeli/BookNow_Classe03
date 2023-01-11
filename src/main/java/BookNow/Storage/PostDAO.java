package BookNow.Storage;

import BookNow.Entity.Cliente;
import BookNow.Entity.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public List<Post> doRetrieveByCf(Cliente cliente){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Post, testo, tags from post where CF = ?");
            ps.setString(1, cliente.getCf());

            ResultSet rs = ps.executeQuery();
            List<Post> posts = new ArrayList<>();
            while(rs.next()){
                int idPost = rs.getInt(1);
                String testo = rs.getString(2);
                String tags = rs.getString(3);
                posts.add(new Post(idPost, testo, tags, cliente));
            }
            return posts;
        }
        catch (SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
    public void doSave(Post p){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into post values (?,?,?)");
            ps.setString(1, p.getTesto());
            ps.setString(2, p.getTags());
            ps.setString(3, p.getCliente().getCf());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");

            ps = con.prepareStatement("select ID_Post from post" +
                    " where testo = ?, tags = ?, CF = ?");
            ps.setString(1, p.getTesto());
            ps.setString(2, p.getTags());
            ps.setString(3, p.getCliente().getCf());

            ResultSet rs = ps.executeQuery();
            rs.next();
            int idPost = rs.getInt(1);

            p.setID_Post(idPost);
            ClienteDAO service = new ClienteDAO();
            service.addPost(p);
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

            ps = con.prepareStatement("select ID_Post from post" +
                    " where testo = ?, tags = ?, CF = ?");
            ps.setString(1, p.getTesto());
            ps.setString(2, p.getTags());
            ps.setString(3, p.getCliente().getCf());

            ResultSet rs = ps.executeQuery();
            rs.next();
            int idPost = rs.getInt(1);

            p.setID_Post(idPost);
            ClienteDAO service = new ClienteDAO();
            service.addPost(p);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
