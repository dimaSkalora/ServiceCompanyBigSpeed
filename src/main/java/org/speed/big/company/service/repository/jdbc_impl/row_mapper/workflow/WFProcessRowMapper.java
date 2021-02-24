package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.workflow.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WFProcessRowMapper implements RowMapper<WFProcess> {
    @Override
    public WFProcess mapRow(ResultSet rs, int rowNum) throws SQLException {

        WFPackage wfPackage = new WFPackage();
        wfPackage.setId(rs.getInt("wfpack_id"));
        wfPackage.setName(rs.getString("wfpack_name"));
        wfPackage.setDateRegistration(rs.getDate("wfpack_date_registration").toLocalDate());
        wfPackage.setCustomerName(rs.getString("wfpack_customer_name"));
        wfPackage.setCustomerAddress(rs.getString("wfpack_customer_address"));
        wfPackage.setCustomerAddressJur(rs.getString("wfpack_customer_address_jur"));
        wfPackage.setCustomerPhone(rs.getString("wfpack_customer_phone"));
        wfPackage.setCustomerEmail(rs.getString("wfpack_customer_email"));
        wfPackage.setContractNumber(rs.getString("wfpack_contract_number"));
        wfPackage.setDescription(rs.getString("wfpack_description"));
        wfPackage.setUserAdd(rs.getString("wfpack_user_add"));
        wfPackage.setDateAdd(rs.getTimestamp("wfpack_date_add").toLocalDateTime());
        wfPackage.setUserEdit(rs.getString("wfpack_user_edit"));
        wfPackage.setDateEdit(rs.getTimestamp("wfpack_date_edit").toLocalDateTime());

        WFService wfService = new WFService();
        wfService.setId(rs.getInt("wfs_id"));
        wfService.setName(rs.getString("wfs_name"));

        WFBaseProcess wfBaseProcess = new WFBaseProcess();
        wfBaseProcess.setId(rs.getInt("wfbp_id"));
        wfBaseProcess.setName(rs.getString("wfbp_name"));
        wfBaseProcess.setDescription(rs.getString("wfbp_description"));
        wfBaseProcess.setWfServiceId(wfService);

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
