package org.speed.big.company.service.model.workflow;

import org.speed.big.company.service.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = WFPackage.DELETE, query = "delete from WFPackage wfp where wfp.id=:id "),
        @NamedQuery(name = WFPackage.GET, query = "select wfp from WFPackage wfp join fetch wfp.wfServiceId " +
                " join fetch wfp.wfPackageStatusId where wfp.id=:id "),
        @NamedQuery(name = WFPackage.ALL_SORTED, query = "select wfp from WFPackage wfp join fetch wfp.wfServiceId " +
                " join fetch wfp.wfPackageStatusId order by wfp.dateRegistration, wfp.name")
})
@Entity
@Table(name = "wf_package", indexes = {
        @Index(name = "wfpack_idx_wfsserid", columnList = "wf_service_id"),
        @Index(name = "wfpack_idx_wfpackstatid", columnList = "wf_package_status_id")
})
public class WFPackage extends WFAbstractBaseEntity {

    public static final String DELETE = "WFPackage.delete";
    public static final String GET = "WFPackage.get";
    public static final String ALL_SORTED = "WFPackage.allSorted";

    @NotBlank
    private String name;
    @NotNull
    @Column(name = "date_registration", nullable = false)
    private LocalDate dateRegistration;
    @NotBlank
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @NotBlank
    @Column(name = "customer_address", nullable = false)
    private String customerAddress;
    @NotBlank
    @Column(name = "customer_address_jur")
    private String customerAddressJur;
    @NotBlank
    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;
    @NotBlank
    @Column(name = "customer_email")
    private String customerEmail;
    @NotBlank
    @Column(name = "contract_number", nullable = false)
    private String contractNumber;
    @NotBlank
    private String description;
    @NotBlank
    @Column(name = "user_add", nullable = false)
    private String userAdd;
    @NotNull
    @Column(name = "date_add", nullable = false)
    private LocalDateTime dateAdd;
    @NotBlank
    @Column(name = "user_edit", nullable = false)
    private String userEdit;
    @NotNull
    @Column(name = "date_edit", nullable = false)
    private LocalDateTime dateEdit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_service_id", nullable = false)
    private WFService wfServiceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wf_package_status_id", nullable = false)
    private WFPackageStatus wfPackageStatusId;

    public WFPackage() {
    }

    public WFPackage(Integer id, String name, LocalDate dateRegistration, String customerName, String customerAddress, String customerAddressJur, String customerPhone, String customerEmail, String contractNumber, String description, String userAdd, LocalDateTime dateAdd, String userEdit, LocalDateTime dateEdit, WFService wfServiceId, WFPackageStatus wfPackageStatusId) {
        super(id);
        this.name = name;
        this.dateRegistration = dateRegistration;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerAddressJur = customerAddressJur;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.contractNumber = contractNumber;
        this.description = description;
        this.userAdd = userAdd;
        this.dateAdd = dateAdd;
        this.userEdit = userEdit;
        this.dateEdit = dateEdit;
        this.wfServiceId = wfServiceId;
        this.wfPackageStatusId = wfPackageStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(LocalDate dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddressJur() {
        return customerAddressJur;
    }

    public void setCustomerAddressJur(String customerAddressJur) {
        this.customerAddressJur = customerAddressJur;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserAdd() {
        return userAdd;
    }

    public void setUserAdd(String userAdd) {
        this.userAdd = userAdd;
    }

    public LocalDateTime getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(LocalDateTime dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getUserEdit() {
        return userEdit;
    }

    public void setUserEdit(String userEdit) {
        this.userEdit = userEdit;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }

    public WFService getWfServiceId() {
        return wfServiceId;
    }

    public void setWfServiceId(WFService wfServiceId) {
        this.wfServiceId = wfServiceId;
    }

    public WFPackageStatus getWfPackageStatusId() {
        return wfPackageStatusId;
    }

    public void setWfPackageStatusId(WFPackageStatus wfPackageStatusId) {
        this.wfPackageStatusId = wfPackageStatusId;
    }

    @Override
    public String toString() {
        return "WFPackage{" +
                "name='" + name + '\'' +
                ", dateRegistration=" + dateRegistration +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerAddressJur='" + customerAddressJur + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", contractNumber='" + contractNumber + '\'' +
                ", description='" + description + '\'' +
                ", userAdd='" + userAdd + '\'' +
                ", dateAdd=" + dateAdd +
                ", userEdit='" + userEdit + '\'' +
                ", dateEdit=" + dateEdit +
                ", wfServiceId=" + wfServiceId +
                ", wfPackageStatusId=" + wfPackageStatusId +
                ", id=" + id +
                '}';
    }
}
