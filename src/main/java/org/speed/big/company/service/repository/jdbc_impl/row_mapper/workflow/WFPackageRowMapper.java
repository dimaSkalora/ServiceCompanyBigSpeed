package org.speed.big.company.service.repository.jdbc_impl.row_mapper.workflow;

import org.speed.big.company.service.model.workflow.WFPackage;
import org.speed.big.company.service.model.workflow.WFPackageStatus;
import org.speed.big.company.service.model.workflow.WFService;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WFPackageRowMapper implements RowMapper<WFPackage> {
    //Класс ResultSet представляет результирующий набор данных и обеспечивает приложению построчный доступ
    // к результатам запросов. При обработке запроса ResultSet поддерживает указатель на текущую обрабатываемую строку.
    @Override
    public WFPackage mapRow(ResultSet rs, int rowNum) throws SQLException {
        //Методы ResultSet.getXXX предоставляют доступ к значениям в колонках в текущей строке.
        // В пределах одной строки значения могут быть считаны в любом порядке
        WFPackageStatus wfPackageStatus = new WFPackageStatus();
        wfPackageStatus.setId(rs.getInt("wfps_id"));
        wfPackageStatus.setName(rs.getString("wfps_name"));

        WFService wfService = new WFService();
        wfService.setId(rs.getInt("wfs_id"));
        wfService.setName(rs.getString("wfs_name"));

        WFPackage wfPackage = new WFPackage();
        wfPackage.setId(rs.getInt("wfp_id"));
        wfPackage.setName(rs.getString("wpf_name"));
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
        wfPackage.setWfPackageStatusId(wfPackageStatus);
        wfPackage.setWfServiceId(wfService);

        return wfPackage;
    }
}
