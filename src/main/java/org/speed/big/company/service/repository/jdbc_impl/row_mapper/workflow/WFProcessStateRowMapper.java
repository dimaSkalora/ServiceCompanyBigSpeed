package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.RoleType;
import org.speed.big.company.service.model.workflow.WFGroup;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class WFProcessStateRowMapper implements RowMapper<WFProcessState> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public WFProcessState mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        RoleType roleType = new RoleType();
        roleType.setId(rs.getInt("r_role_type_id"));

        Role role = new Role();
        role.setId(rs.getInt("r_id"));
        role.setName(rs.getString("r_name"));
        role.setDescription(rs.getString("r_description"));
        role.setRoleTypeId(roleType);

        WFGroup wfGroup = new WFGroup();
        wfGroup.setId(rs.getInt("wfg_id"));
        wfGroup.setName(rs.getString("wfg_name"));
        wfGroup.setDescription(rs.getString("wfg_description"));

        WFProcessState wfProcessState = new WFProcessState();
        wfProcessState.setId(rs.getInt("wfps_id"));
        wfProcessState.setName(rs.getString("wfps_name"));
        wfProcessState.setRoleId(role);
        wfProcessState.setWfGroupId(wfGroup);
        wfProcessState.setDescription(rs.getString("wfps_description"));

        return wfProcessState;
    }
}