package org.speed.big.company.service.repository.jdbc_impl.row_mapper;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class RoleRowMapper implements RowMapper<Role> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        RoleType roleType = new RoleType();
        roleType.setId(rs.getInt("r_role_type_id"));

        Role role = new Role();
        role.setId(rs.getInt("r_id"));
        role.setName(rs.getString("r_name"));
        role.setDescription(rs.getString("r_description"));
        role.setRoleTypeId(roleType);

        return role;
    }
}
