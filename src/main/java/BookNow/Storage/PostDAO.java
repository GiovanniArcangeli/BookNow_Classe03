package BookNow.Storage;

import BookNow.Entity.Post;

import java.sql.*;
public class PostDAO {

    public void doSave(Post p){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into post values (?,?,?)");
            ps.setString(1, p.getTesto());
            ps.setString(2, p.getTags());
            ps.setString(3, p.getCliente().getCf());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");

            PostDAO service = new PostDAO();
            service.addPosts(p);
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

            ClienteDAO service = new PostDAO();
            service.removePost(p);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
