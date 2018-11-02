package com.step.forum.dao;

import com.step.forum.constants.MessageConstants;
import com.step.forum.constants.UserConstants;
import com.step.forum.exceptions.DuplicateEmailException;
import com.step.forum.exceptions.InactiveAccountException;
import com.step.forum.exceptions.InvalidEmailException;
import com.step.forum.exceptions.InvalidPasswordException;
import com.step.forum.model.Role;
import com.step.forum.model.User;
import com.step.forum.util.DbUtil;

import javax.activity.InvalidActivityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDAO {

    private final String REGISTER_USER_SQL = "insert into user(email, password, token, status, id_role, first_name, last_name) values(?, ?, ?, ?, ?, ?, ?)";
    private final String GET_EMAIL_COUNT_SQL = "select count(*) as count from user where email=?";
    private final String GET_TOKEN_COUNT_SQL = "select count(*) as count from user where token=?";
    private final String GET_USER_BY_EMAIL = "select * from user where email = ? and password = ?";


    @Override
    public boolean register(User user) throws DuplicateEmailException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {

            if (!isValidEmail(user.getEmail())){
                throw new DuplicateEmailException(MessageConstants.ERROR_MESSAGE_DUPLICATE_EMAIL);
            }

            con = DbUtil.getConnection();
            ps = con.prepareStatement(REGISTER_USER_SQL);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getToken());
            ps.setInt(4, user.getStatus());
            ps.setInt(5, user.getRole().getId());
            ps.setString(6, user.getFirstName());
            ps.setString(7, user.getLastName());
            ps.executeUpdate();

            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeAll(con, ps);
        }
        return result;
    }

    @Override
    public User login(String email, String password) throws InvalidEmailException, InactiveAccountException, InvalidPasswordException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_USER_BY_EMAIL);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()){
                if (!rs.getString("password").equals(password)){
                    throw new InvalidPasswordException(MessageConstants.ERROR_MESSAGE_INVALID_PASSWORD);
                }
                if (rs.getInt("status") == UserConstants.USER_STATUS_INACTIVE){
                    throw new InactiveAccountException(MessageConstants.ERROR_MESSAGE_INACTIVE_ACCOUNT);
                }
                u = new User();
                u.setId(rs.getInt("id_user"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setToken(rs.getString("token"));
                u.setStatus(rs.getInt("status"));
                Role r = new Role();
                r.setId(rs.getInt("id_role"));
                u.setRole(r);
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
            }else{
                throw new InvalidEmailException(MessageConstants.ERROR_MESSAGE_INVALID_EMAIL);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con , ps, rs);
        }
        return u;
    }

    private boolean isValidEmail(String email){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_EMAIL_COUNT_SQL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()){
                if (rs.getInt("count") == 0){
                    result = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return result;
    }

    private boolean isValidToken(String token){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DbUtil.getConnection();
            ps = con.prepareStatement(GET_TOKEN_COUNT_SQL);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()){
                int count = rs.getInt("count");
                if (count == 1){
                    result = true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.closeAll(con, ps, rs);
        }
        return result;
    }
}
