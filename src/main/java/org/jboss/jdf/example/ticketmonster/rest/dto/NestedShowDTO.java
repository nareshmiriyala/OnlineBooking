package org.jboss.jdf.example.ticketmonster.rest.dto;

import java.io.Serializable;
import org.jboss.jdf.example.ticketmonster.model.ShowData;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
public class NestedShowDTO implements Serializable {

	private Long id;
	private String displayTitle;

	public NestedShowDTO() {
	}

	public NestedShowDTO(final ShowData entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.displayTitle = entity.toString();
		}
	}

	public ShowData fromDTO(ShowData entity, EntityManager em) {
		if (entity == null) {
			entity = new ShowData();
		}
		if (this.id != null) {
			TypedQuery<ShowData> findByIdQuery = em.createQuery(
					"SELECT DISTINCT s FROM ShowData s WHERE s.id = :entityId",
					ShowData.class);
			findByIdQuery.setParameter("entityId", this.id);
			try {
				entity = findByIdQuery.getSingleResult();
			} catch (javax.persistence.NoResultException nre) {
				entity = null;
			}
			return entity;
		}
		entity = em.merge(entity);
		return entity;
	}
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	
    public String getDisplayTitle() {
       return this.displayTitle;
    }
}