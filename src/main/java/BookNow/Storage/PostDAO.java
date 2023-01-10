package BookNow.Storage;

import BookNow.Entity.Post;

import java.sql.*;
public class PostDAO {

    public void doSave(Post p, String CF){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into post values (?,?,?)");
            ps.setString(1, p.getTesto());
            ps.setString(2, p.getTags());
            ps.setString(3, CF);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doDelete(int idPost){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("delete from post where ID_Post = ?");
            ps.setInt(1, idPost);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
