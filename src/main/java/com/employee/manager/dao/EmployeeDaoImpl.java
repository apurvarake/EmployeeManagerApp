package com.employee.manager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.manager.model.Employee;

/**
 * @author Apurva Kute
 *
 */
@Service
@Transactional
public class EmployeeDaoImpl extends AbstractDao {

	public void saveEmployee(Employee employee) {
		persist(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		Criteria criteria = getSession().createCriteria(Employee.class);
		return (List<Employee>) criteria.list();
	}

	public void deleteEmployeeById(int id) {
		Query query = getSession().createSQLQuery(
				"delete from Employee where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public Employee findById(int id) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		return (Employee) criteria.uniqueResult();
	}

	public void updateEmployee(Employee employee) {
		getSession().update(employee);
	}

}
