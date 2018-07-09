package com.rest.domain.device.control;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.ws.rs.core.MultivaluedMap;

import com.rest.domain.device.entity.CardEntity;
import com.rest.domain.device.entity.CardType;
import com.rest.domain.device.entity.DeviceEntity;
import com.rest.infrastructure.LogInterceptor;

public class DeviceRepository {

	private static final String LIKE_WILDCARD = "%";
	
	@PersistenceContext(unitName = "NetworkManagement")
	private EntityManager entityManager;

	public DeviceEntity getDeviceById(int id) {
		return entityManager.find(DeviceEntity.class,id);
	}

	@Interceptors(LogInterceptor.class)
	public int save(DeviceEntity deviceEntity) {
		entityManager.persist(deviceEntity);
		return deviceEntity.getId();
	}

	public void deleteDevice(int id) {
		DeviceEntity device = getDeviceById(id);
		entityManager.remove(device);
	}
	
	
	public int getIdentifier(String type) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = criteriaBuilder.createQuery(Integer.class);
		Root<CardEntity> cRoot = cq.from(CardEntity.class);
		cq.select( cRoot.get("nrType") ) . where( cRoot.get("cardType").in(type) );
		
		Set<Integer> integers = new HashSet<>(entityManager.createQuery(cq).getResultList());
		
		int[] x = integers.toArray().;
		return getMinPositiveNumberNotInList(integers);
	}


	public List<DeviceEntity> queryDeviceByQueryParams(MultivaluedMap<String, String> queryParameters) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeviceEntity> criteria = criteriaBuilder.createQuery(DeviceEntity.class);
		Root<DeviceEntity> deviceRoot = criteria.from(DeviceEntity.class);
		
		criteria.select(deviceRoot);
		for (Entry<String, List<String>> values : queryParameters.entrySet()) {
			Predicate equalPredicate = createLikePredicate(criteriaBuilder, deviceRoot, values);
			criteria.where(equalPredicate);
		}

		return entityManager.createQuery(criteria).getResultList();
	}

	private Predicate createLikePredicate(CriteriaBuilder criteriaBuilder, Root<DeviceEntity> deviceRoot,
			Entry<String, List<String>> likeQueryParam) {
		return criteriaBuilder.like(deviceRoot.get(likeQueryParam.getKey()), createLikePattern(likeQueryParam.getValue().get(0)));
	}

	private String createLikePattern(String attributeValue) {
		return LIKE_WILDCARD + attributeValue + LIKE_WILDCARD;
	}
	
	
	private int getMinPositiveNumberNotInList(Set<Integer> numberSet) {
		int next = 1;
		while(numberSet.contains(next)) {
			next++;
		}
		return next;
	}

}
