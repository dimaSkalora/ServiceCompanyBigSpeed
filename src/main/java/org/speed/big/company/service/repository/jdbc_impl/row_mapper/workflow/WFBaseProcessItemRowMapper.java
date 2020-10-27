package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.Role;
import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFBaseProcessItem;
import org.speed.big.company.service.model.workflow.WFProcessState;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper <T> - используется JdbcTemplate для отображения строк ResultSet для каждой строки.
//Реализации этого интерфейса выполняют фактическую работу по отображению каждой строки в объект результата
public class WFBaseProcessItemRowMapper implements RowMapper<WFBaseProcessItem> {
    @Override
    public WFBaseProcessItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role roleFromId = new Role();
        roleFromId.setId(rs.getInt("fromR_id"));
        roleFromId.setName(rs.getString("fromR_name"));
        roleFromId.setDescription(rs.getString("fromR_description"));

        Role roleToId = new Role();
        roleToId.setId(rs.getInt("toR_id"));
        roleToId.setName(rs.getString("toR_name"));
        roleToId.setDescription(rs.getString("toR_description"));

        WFProcessState wfProcessStateFromId = new WFProcessState();
        wfProcessStateFromId.setId(rs.getInt("wfpsStateFromId_id"));
        wfProcessStateFromId.setName(rs.getString("wfpsStateFromId_name"));
        wfProcessStateFromId.setRoleId(roleFromId);
        wfProcessStateFromId.setDescription(rs.getString("wfpsStateFromId_description"));

        WFProcessState wfProcessStateToId = new WFProcessState();
        wfProcessStateToId.setId(rs.getInt("wfpsStateToId_id"));
        wfProcessStateToId.setName(rs.getString("wfpsStateToId_name"));
        wfProcessStateToId.setRoleId(roleToId);
        wfProcessStateToId.setDescription(rs.getString("wfpsStateToId_description"));

        WFBaseProcess wfBaseProcess = new WFBaseProcess();
        wfBaseProcess.setId(rs.getInt("wfbprocess_id"));
        wfBaseProcess.setName(rs.getString("wfbprocess_name"));
        wfBaseProcess.setDescription(rs.getString("wfbprocess_description"));

        WFBaseProcessItem wfBaseProcessItem = new WFBaseProcessItem();
        wfBaseProcessItem.setId(rs.getInt("wfbpi_id"));
        wfBaseProcessItem.setStateFromId(wfProcessStateFromId);
        wfBaseProcessItem.setStateToId(wfProcessStateToId);
        wfBaseProcessItem.setBaseProcessId(wfBaseProcess);

        return wfBaseProcessItem;
    }
}
