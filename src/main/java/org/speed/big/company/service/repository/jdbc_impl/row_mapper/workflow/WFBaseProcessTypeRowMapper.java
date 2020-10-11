package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WFBaseProcessTypeRowMapper implements RowMapper<WFBaseProcessType> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public WFBaseProcessType mapRow(ResultSet rs, int rowNum) throws SQLException {
        WFBaseProcessType wfBaseProcessType = new WFBaseProcessType();
        wfBaseProcessType.setId(rs.getInt("wfbpt_id"));
        wfBaseProcessType.setName(rs.getString("wfbpt_name"));

        return wfBaseProcessType;
    }
}
