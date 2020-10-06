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
        wfPackage.setName(rs.getString("wfp_customer_name"));
        wfPackage.setName(rs.getString("wfp_customer_address"));
        wfPackage.setName(rs.getString(""));
        wfPackage.setName(rs.getString(""));
        wfPackage.setName(rs.getString(""));

        return wfPackage;
    }
}
