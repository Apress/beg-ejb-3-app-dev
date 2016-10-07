package com.apress.ejb3.ch10.ejb21.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DepartmentLocalDTO
  implements Serializable
{
  private Long deptno;
  private String dname;
  private Collection<EmployeeLocalDTO> employeesDTO;
  private String loc;

  public DepartmentLocalDTO() {
  }

  public DepartmentLocalDTO(DepartmentLocal departmentLocal) {
    copyFromEntity(departmentLocal);
  }

  public void copyFromEntity(DepartmentLocal departmentLocal) {
    deptno = departmentLocal.getDeptno();
    dname = departmentLocal.getDname();
    // Uncomment the next line to instantiate a collection of DTOs for the objects referenced by the employees CMR field
    _loadEmployeesDTO(departmentLocal.getEmployees());
    loc = departmentLocal.getLoc();
  }

  public void copyToEntity(DepartmentLocal departmentLocal) {
    departmentLocal.setDname(dname);
    departmentLocal.setLoc(loc);
  }

  public Long getDeptno() {
    return deptno;
  }

  public void setDeptno(Long deptno) {
    this.deptno = deptno;
  }

  public String getDname() {
    return dname;
  }

  public void setDname(String dname) {
    this.dname = dname;
  }

  public Collection<EmployeeLocalDTO> getEmployeesDTO() {
    return employeesDTO;
  }

  private void _loadEmployeesDTO(Collection<EmployeeLocal> employees) {
    final int len = (employees == null ? 0 : employees.size());
    employeesDTO = new ArrayList(len);
    if (len > 0) {
      final Iterator iter = employees.iterator();
      while (iter.hasNext()) {
        employeesDTO.add(new EmployeeLocalDTO((EmployeeLocal)iter.next()));
      }
    }
  }

  public void addEmployeeLocalDTO(EmployeeLocalDTO employeeLocalDTO) {
    employeesDTO.add(employeeLocalDTO);
    employeeLocalDTO.setDepartmentDTO(this);
  }

  public void removeEmployeeLocalDTO(EmployeeLocalDTO employeeLocalDTO) {
    employeesDTO.remove(employeeLocalDTO);
    employeeLocalDTO.setDepartmentDTO(null);
  }

  public void setEmployeesDTO(Collection<EmployeeLocalDTO> employeesDTO) {
    this.employeesDTO = employeesDTO;
  }

  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }
}
