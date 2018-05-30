package com.employee.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.employee.manager.bean.EmployeeBean;
import com.employee.manager.bean.EmployeeResponseBean;
import com.employee.manager.bean.ManagerBean;
import com.employee.manager.bean.ResponseBean;
import com.employee.manager.bean.SignInRequestBean;
import com.employee.manager.service.EmployeeManagerService;

/**
 * @author Apurva Kute
 *
 */
@Controller
public class EmployeeManagerController {

	@Autowired
	private EmployeeManagerService employeeManagerService;

	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<EmployeeBean> populateEmployees() {
		return employeeManagerService.populateEmployees();

	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody EmployeeResponseBean populateEmployee(
			@PathVariable int id) {
		return employeeManagerService.populateEmployee(id);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseBean addEmployee(
			@RequestBody EmployeeBean employee) {
		return employeeManagerService.addEmployee(employee);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseBean updateEmployee(
			@RequestBody EmployeeBean employee) {
		return employeeManagerService.updateEmployee(employee);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseBean signUp(
			@RequestBody ManagerBean managerBean) {
		return employeeManagerService.signUp(managerBean);
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseBean signIn(
			@RequestBody SignInRequestBean signInRequestBean) {
		return employeeManagerService.signIn(signInRequestBean);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseBean deleteEmployee(@PathVariable int id) {
		return employeeManagerService.deleteEmployee(id);
	}

	@RequestMapping("/login")
	public String login() {
		return "redirect:/static/login.html";
	}

}
