package org.speed.big.company.service.repository.jdbc_impl.row_mapper;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.model.User;
import org.speed.big.company.service.model.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class UserRoleRowMapper implements RowMapper<UserRole> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке

        User user = new User();
        user.setId(rs.getInt("u_id"));
        user.setName(rs.getString("u_name"));
        user.setEmail(rs.getString("u_email"));
        user.setPassword(rs.getString("u_password"));
        user.setPhone(rs.getString("u_phone"));
        user.setRegistered(rs.getDate("u_registered").toLocalDate());
        user.setEnabled(rs.getBoolean("u_enabled"));

        Role role = new Role();
        role.setId(rs.getInt("r_id"));
        role.setName(rs.getString("r_name"));
        role.setDescription(rs.getString("r_description"));

        UserRole userRole = new UserRole();
        userRole.setId(rs.getInt("ur_id"));
        userRole.setUserId(user);
        userRole.setRoleId(role);
        userRole.setDateTime(rs.getTimestamp("ur_date_time").toLocalDateTime());

        return userRole;
    }
}
