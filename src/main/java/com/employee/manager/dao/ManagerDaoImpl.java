package com.employee.manager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.manager.model.Manager;

/**
 * @author Apurva Kute
 *
 */
@Service
@Transactional
public class ManagerDaoImpl extends AbstractDao {

	public void saveManager(Manager manager) {
		persist(manager);
	}

	@SuppressWarnings("unchecked")
	public List<Manager> findAllManagers() {
		Criteria criteria = getSession().createCriteria(Manager.class);
		return (List<Manager>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Manager findManagerByEmailId(String userName) {
		Criteria criteria = getSession().createCriteria(Manager.class).add(
				Restrictions.eq("email", userName));
		return (Manager) criteria.uniqueResult();
	}

	public void deleteEmployeeById(String id) {
		Query query = getSession().createSQLQuery(
				"delete from Manager where id = :id");
		query.setString("id", id);
		query.executeUpdate();
	}

	public Manager findById(String id) {
		Criteria criteria = getSession().createCriteria(Manager.class);
		criteria.add(Restrictions.eq("id", id));
		return (Manager) criteria.uniqueResult();
	}

	public void updateManager(Manager manager) {
		getSession().update(manager);
	}

}
