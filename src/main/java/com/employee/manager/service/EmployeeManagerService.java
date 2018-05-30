/**
 * 
 */
package com.employee.manager.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.employee.manager.bean.EmployeeBean;
import com.employee.manager.bean.EmployeeResponseBean;
import com.employee.manager.bean.ManagerBean;
import com.employee.manager.bean.ResponseBean;
import com.employee.manager.bean.SignInRequestBean;
import com.employee.manager.dao.EmployeeDaoImpl;
import com.employee.manager.dao.ManagerDaoImpl;
import com.employee.manager.model.Employee;
import com.employee.manager.model.Manager;

/**
 * @author Apurva Kute
 *
 */
@Service
@Transactional
public class EmployeeManagerService {

	@Autowired
	private EmployeeDaoImpl employeeDaoImpl;

	@Autowired
	private ManagerDaoImpl managerDaoImpl;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	     
	/**
	 * @param employeeBean
	 * @return
	 */
	public ResponseBean addEmployee(EmployeeBean employeeBean) {
		
		LOGGER.info("START - Add Employee");
		Employee employee = employeeConverter(employeeBean);

		ResponseBean responseBean = new ResponseBean();
		try {
			employeeDaoImpl.saveEmployee(employee);
			responseBean.setMessage("Employee Added Successfully");
		} catch (Exception e) {
			responseBean.setCode(1);
			responseBean.setMessage("Error adding Employee ");
		}
		LOGGER.info("END - Add Employee");
		return responseBean;
	}

	/**
	 * @param id
	 * @return
	 */
	public EmployeeResponseBean populateEmployee(int id) {
		LOGGER.debug("START - Populate employee for id "+id);
		EmployeeResponseBean resp = new EmployeeResponseBean();
		try {
			resp.setEmployee(employeeConverter(employeeDaoImpl.findById(id)));
		} catch (Exception e) {
			resp.setCode(1);
			resp.setMessage("Error adding Employee");
		}
		LOGGER.debug("END - Populate employee for id "+id);
		return resp;
	}

	/**
	 * @param id
	 * @return
	 */
	public ResponseBean deleteEmployee(int id) {
		LOGGER.debug("START - Delete employee for id "+id);
		ResponseBean resp = new ResponseBean();
		try {
			employeeDaoImpl.deleteEmployeeById(id);
			resp.setMessage("Employee deleted Successfully");
		} catch (Exception e) {
			resp.setCode(1);
			resp.setMessage("Error in Deleting Employee");
		}
		LOGGER.debug("END - Delete employee for id "+id);
		return resp;
	}

	/**
	 * @return
	 */
	public List<EmployeeBean> populateEmployees() {
		LOGGER.info("START - Populate all employees");
		List<Employee> listEmployee = employeeDaoImpl.findAllEmployees();
		List<EmployeeBean> listEmployeeBean = new ArrayList<EmployeeBean>();
		for (Employee employee : listEmployee) {
			listEmployeeBean.add(employeeConverter(employee));
		}
		LOGGER.info("END - Populate all employees");
		return listEmployeeBean;
	}

	/**
	 * @param employeeBean
	 * @return
	 */
	public ResponseBean updateEmployee(EmployeeBean employeeBean) {
		LOGGER.debug("START - Update employee for id "+employeeBean.getId());
		Employee employee = employeeConverter(employeeBean);
		ResponseBean responseBean = new ResponseBean();
		try {
			employeeDaoImpl.updateEmployee(employee);
			responseBean.setMessage("Employee updated Successfully");
		} catch (Exception e) {
			responseBean.setCode(1);
			responseBean.setMessage("Error updating Employee ");
		}
		LOGGER.debug("END - Update employee for id "+employeeBean.getId());
		return responseBean;
	}

	/**
	 * @param managerBean
	 * @return
	 */
	public ResponseBean signUp(ManagerBean managerBean) {
		
		LOGGER.info("START - Sign up ne Manager");
		
		ResponseBean responseBean = new ResponseBean();
		try {
			managerDaoImpl.saveManager(managerConverter(managerBean));
			responseBean.setMessage("Manager Account Created Successfully");
		} catch (Exception e) {
			responseBean.setCode(1);
			responseBean.setMessage("Error creating Manager Account");
		}
		LOGGER.info("END - Sign up ne Manager");
		return responseBean;
	}

	/**
	 * @param signInRequestBean
	 * @return
	 */
	public ResponseBean signIn(SignInRequestBean signInRequestBean) {

		LOGGER.debug("START - Sign in for user Id "+signInRequestBean.getUserName());
		
		ResponseBean responseBean = new ResponseBean();
		try {
			Manager manager = managerDaoImpl
					.findManagerByEmailId(signInRequestBean.getUserName());
			if (!StringUtils.isEmpty(manager.getPassword())
					&& manager.getPassword().equals(
							signInRequestBean.getPassword())) {
				responseBean.setMessage("You have logged in Successfully");
				return responseBean;
			} else {
				responseBean.setCode(1);
				responseBean.setMessage("Login Error");
			}
		} catch (Exception e) {
			responseBean.setCode(1);
			responseBean.setMessage("Login Error");
		}
		LOGGER.debug("END - Sign in for user Id "+signInRequestBean.getUserName());
		return responseBean;
	}

	/**
	 * @param employee
	 * @return
	 */
	public EmployeeBean employeeConverter(Employee employee) {
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setId(employee.getId());
		employeeBean.setAddress(employee.getAddress());
		employeeBean.setCity(employee.getCity());
		employeeBean.setFirstName(employee.getFirstName());
		employeeBean.setLastName(employee.getLastName());
		employeeBean.setMobile(employee.getMobile());
		employeeBean.setDob(String.valueOf(employee.getDob().getYear()) + "-"
				+ String.valueOf(employee.getDob().getMonthOfYear()) + "-"
				+ String.valueOf(employee.getDob().getDayOfMonth()));
		return employeeBean;
	}

	/**
	 * @param employeebean
	 * @return
	 */
	public Employee employeeConverter(EmployeeBean employeebean) {
		Employee employee = new Employee();
		employee.setId(employeebean.getId());
		employee.setAddress(employeebean.getAddress());
		employee.setCity(employeebean.getCity());
		employee.setFirstName(employeebean.getFirstName());
		employee.setLastName(employeebean.getLastName());
		employee.setMobile(employeebean.getMobile());
		String[] dobArr = employeebean.getDob().split("-");
		employee.setDob(new LocalDate(Integer.valueOf(dobArr[0]), Integer
				.valueOf(dobArr[1]), Integer.valueOf(dobArr[2])));
		return employee;
	}

	/**
	 * @param managerBean
	 * @return
	 */
	public Manager managerConverter(ManagerBean managerBean) {
		Manager manager = new Manager();
		manager.setId(managerBean.getId());
		manager.setAddress(managerBean.getAddress());
		manager.setCompany(managerBean.getCompany());
		manager.setEmail(managerBean.getEmail());
		String[] dobArr = managerBean.getDob().split("-");
		manager.setDob(new LocalDate(Integer.valueOf(dobArr[0]), Integer
				.valueOf(dobArr[1]), Integer.valueOf(dobArr[2])));
		manager.setFirstName(managerBean.getFirstName());
		manager.setLastName(managerBean.getLastName());
		manager.setPassword(managerBean.getPassword());
		return manager;
	}

}
