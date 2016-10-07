package com.apress.ejb3.ch10.ejb21.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EmployeeLocalDTO
  implements Serializable
{
  private Double comm;
  private DepartmentLocalDTO departmentDTO;
  private Long deptno;
  private EmployeeLocalDTO managerDTO;
  private Collection<EmployeeLocalDTO> managedEmployeesDTO;
  private Long empno;
  private String ename;
  private Timestamp hiredate;
  private String job;
  private Double sal;

  public EmployeeLocalDTO() {
  }

  public EmployeeLocalDTO(EmployeeLocal employeeLocal) {
    copyFromEntity(employeeLocal);
  }

  public void copyFromEntity(EmployeeLocal employeeLocal) {
    comm = employeeLocal.getComm();
    // Uncomment the next line to instantiate a DTO for the object referenced by the department CMR field
    // departmentDTO = new DepartmentLocalDTO(employeeLocal.getDepartment());
    deptno = employeeLocal.getDeptno();
    // Uncomment the next line to instantiate a DTO for the object referenced by the manager CMR field
    // managerDTO = new EmployeeLocalDTO(employeeLocal.getManager());
    // Uncomment the next line to instantiate a collection of DTOs for the objects referenced by the managedEmployees CMR field
    // _loadManagedEmployeesDTO(employeeLocal.getManagedEmployees());
    empno = employeeLocal.getEmpno();
    ename = employeeLocal.getEname();
    hiredate = employeeLocal.getHiredate();
    job = employeeLocal.getJob();
    sal = employeeLocal.getSal();
  }

  public void copyToEntity(EmployeeLocal employeeLocal) {
    employeeLocal.setComm(comm);
    employeeLocal.setDeptno(deptno);
    employeeLocal.setEname(ename);
    employeeLocal.setHiredate(hiredate);
    employeeLocal.setJob(job);
    employeeLocal.setSal(sal);
  }

  public Double getComm() {
    return comm;
  }

  public void setComm(Double comm) {
    this.comm = comm;
  }

  public DepartmentLocalDTO getDepartmentDTO() {
    return departmentDTO;
  }

  public void setDepartmentDTO(DepartmentLocalDTO departmentDTO) {
    this.departmentDTO = departmentDTO;
  }

  public Long getDeptno() {
    return deptno;
  }

  public void setDeptno(Long deptno) {
    this.deptno = deptno;
  }

  public EmployeeLocalDTO getManagerDTO() {
    return managerDTO;
  }

  public void setManagerDTO(EmployeeLocalDTO managerDTO) {
    this.managerDTO = managerDTO;
  }

  public Collection<EmployeeLocalDTO> getManagedEmployeesDTO() {
    return managedEmployeesDTO;
  }

  private void _loadManagedEmployeesDTO(Collection<EmployeeLocal> managedEmployees) {
    final int len = (managedEmployees == null ? 0 : managedEmployees.size());
    managedEmployeesDTO = new ArrayList(len);
    if (len > 0) {
      final Iterator iter = managedEmployees.iterator();
      while (iter.hasNext()) {
        managedEmployeesDTO.add(new EmployeeLocalDTO((EmployeeLocal)iter.next()));
      }
    }
  }

  public void addEmployeeLocalDTO(EmployeeLocalDTO employeeLocalDTO) {
    managedEmployeesDTO.add(employeeLocalDTO);
    employeeLocalDTO.setManagerDTO(this);
  }

  public void removeEmployeeLocalDTO(EmployeeLocalDTO employeeLocalDTO) {
    managedEmployeesDTO.remove(employeeLocalDTO);
    employeeLocalDTO.setManagerDTO(null);
  }

  public void setManagedEmployeesDTO(Collection<EmployeeLocalDTO> managedEmployeesDTO) {
    this.managedEmployeesDTO = managedEmployeesDTO;
  }

  public Long getEmpno() {
    return empno;
  }

  public void setEmpno(Long empno) {
    this.empno = empno;
  }

  public String getEname() {
    return ename;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }

  public Timestamp getHiredate() {
    return hiredate;
  }

  public void setHiredate(Timestamp hiredate) {
    this.hiredate = hiredate;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public Double getSal() {
    return sal;
  }

  public void setSal(Double sal) {
    this.sal = sal;
  }
}
