package com.clayfin.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.model.AttendanceDetails;
import com.clayfin.repository.AttendenceDetailsRepo;

@Service
public class AttendenceDetailsService {
	
	@Autowired
	AttendenceDetailsRepo attendenceDetailsRepo;
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<AttendanceDetails> findByUserId(Integer id) {
        return  entityManager.createQuery("SELECT p FROM AttendanceDetails p WHERE p.user.id = :id ORDER BY p.id DESC",
        		AttendanceDetails.class).setParameter("id", id).setMaxResults(1).getResultList();
    }

	
	
	@Autowired
    private SessionFactory sessionFactory;

	public AttendanceDetails getLastOutput(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<AttendanceDetails> query = session.createQuery(
            "SELECT o FROM OutputEntity o  WHERE o.user.id = :id ORDER BY o.createdAt DESC",
            
            AttendanceDetails.class
        );
        query.setParameter("id", id);
        query.setMaxResults(1);
        List<AttendanceDetails> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null; // or throw an exception, depending on your use case
        }

        return resultList.get(0);
    }
	
	
	
	
	
	public AttendanceDetails createAttendence(AttendanceDetails attendanceDetails) {
		attendenceDetailsRepo.save(attendanceDetails);
		return attendanceDetails;
	}
	
	

}
