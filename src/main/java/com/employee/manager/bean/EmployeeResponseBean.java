/**
 * 
 */
package com.employee.manager.bean;

/**
 * @author Apurva Kute
 *
 */
public class EmployeeResponseBean extends ResponseBean {

	private EmployeeBean employee;

	/**
	 * @return the employee
	 */
	public EmployeeBean getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

}
