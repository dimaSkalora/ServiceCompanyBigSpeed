package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.workflow.WFBaseProcess;
import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFProcess;
import org.speed.big.company.service.model.workflow.WFProcessStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WFProcessRowMapper implements RowMapper<WFProcess> {
    @Override
    public WFProcess mapRow(ResultSet rs, int rowNum) throws SQLException {

        WFPackage wfPackage = new WFPackage();
        wfPackage.setId(rs.getInt("wfp_id"));
        wfPackage.setName(rs.getString("wfp_name"));
        wfPackage.setDateRegistration(rs.getDate("wfp_date_registration").toLocalDate());
        wfPackage.setCustomerName(rs.getString("wfp_customer_name"));
        wfPackage.setCustomerAddress(rs.getString("wfp_customer_address"));
        wfPackage.setCustomerAddressJur(rs.getString("wfp_customer_address_jur"));
        wfPackage.setCustomerPhone(rs.getString("wfp_customer_phone"));
        wfPackage.setCustomerEmail(rs.getString("wfp_customer_email"));
        wfPackage.setContractNumber(rs.getString("wfp_contract_number"));
        wfPackage.setDescription(rs.getString("wfp_description"));
        wfPackage.setUserAdd(rs.getString("wfp_user_add"));
        wfPackage.setDateAdd(rs.getTimestamp("wfp_date_add").toLocalDateTime());
        wfPackage.setUserEdit(rs.getString("wfp_user_edit"));
        wfPackage.setDateEdit(rs.getTimestamp("wfp_date_edit").toLocalDateTime());

        WFBaseProcess wfBaseProcess = new WFBaseProcess();
        wfBaseProcess.setId(rs.getInt("wfbp_id"));
        wfBaseProcess.setName(rs.getString("wfbp_name"));
        wfBaseProcess.setDescription(rs.getString("wfbp_description"));

        WFProcessStatus wfProcessStatus = new WFProcessStatus();
        wfProcessStatus.setId(rs.getInt("wfpstatus_id"));
        wfProcessStatus.setName(rs.getString("wfpstatus_name"));

        WFProcess wfProcess = new WFProcess();
        wfProcess.setId(rs.getInt("wfp_id"));
        wfProcess.setStartDate(rs.getTimestamp("wfp_start_date").toLocalDateTime());
        if (rs.getTimestamp("wfp_final_date").toLocalDateTime() != null)
            wfProcess.setFinalDate(rs.getTimestamp("wfp_final_date").toLocalDateTime());
        if (rs.getString("wfp_description") != null)
            wfProcess.setDescription(rs.getString("wfp_description"));
        wfProcess.setDateEdit(rs.getTimestamp("wfp_date_edit").toLocalDateTime());
        wfProcess.setUserEdit(rs.getString("wfp_user_edit"));
        wfProcess.setWfPackageId(wfPackage);
        wfProcess.setWfBaseProcessId(wfBaseProcess);
        wfProcess.setWfProcessStatusId(wfProcessStatus);

        return wfProcess;
    }
}
