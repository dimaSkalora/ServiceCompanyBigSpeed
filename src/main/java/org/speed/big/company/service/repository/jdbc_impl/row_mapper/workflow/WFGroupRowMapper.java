package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.workflow.WFGroup;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class WFGroupRowMapper implements RowMapper<WFGroup> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public WFGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
    //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
    // В пределах одной строки значения могут быть считаны в любом порядке
        WFGroup wfGroup = new WFGroup();
        wfGroup.setId(rs.getInt("wfg_id"));
        wfGroup.setName(rs.getString("wfg_name"));
        wfGroup.setDescription(rs.getString("wfg_description"));

        return wfGroup;
    }
}
