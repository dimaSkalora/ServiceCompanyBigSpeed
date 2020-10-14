package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WFProcessStatusRowMapper implements RowMapper<WFProcessStatus> {
    @Override
    public WFProcessStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        WFProcessStatus wfProcessStatus = new WFProcessStatus();
        wfProcessStatus.setId(rs.getInt("wfpstatus_id"));
        wfProcessStatus.setName(rs.getString("wfpstatus_name"));

        return wfProcessStatus;
    }
}
