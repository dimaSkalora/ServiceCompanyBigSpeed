package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFBaseProcessType;
import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class WFBaseProcessRowMapper implements RowMapper<WFBaseProcess> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public WFBaseProcess mapRow(ResultSet rs, int rowNum) throws SQLException {
        WFService wfService = new WFService();
        wfService.setId(rs.getInt("wfs_id"));
        wfService.setName(rs.getString("wfs_name"));

        WFBaseProcessType wfBaseProcessType = new WFBaseProcessType();
        wfBaseProcessType.setId(rs.getInt("wfbpt_id"));
        wfBaseProcessType.setName(rs.getString("wfbpt_name"));

        WFBaseProcess wfBaseProcess = new WFBaseProcess();
        wfBaseProcess.setId(rs.getInt(""));
        wfBaseProcess.setName(rs.getString(""));
        wfBaseProcess.setDescription(rs.getString(""));
        wfBaseProcess.setWfServiceId(wfService);
        wfBaseProcess.setWfBaseProcessTypeId(wfBaseProcessType);

        return wfBaseProcess;
    }
}
