package it.akademija.serviceGroup;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ServiceGroupDAO extends JpaRepository<ServiceGroup, Long>{
	@Query("SELECT new ServiceGroup( k.id, k.name, k.description) FROM ServiceGroup k")
	Page<ServiceGroup> findAllServiceGroup(Pageable pageable);

	@Query("SELECT new ServiceGroup(   k.id, k.name, k.description) FROM ServiceGroup k WHERE LOWER(k.name) LIKE LOWER(concat('%', ?1,'%'))")
	Page<ServiceGroup> findByNameContainingIgnoreCase(String name, Pageable pageable);

	@Query("SELECT new ServiceGroup( k.id, k.name, k.description) FROM ServiceGroup k WHERE LOWER(k.name||' '||k.description) LIKE LOWER(concat('%', ?1,'%'))")
	Page<ServiceGroup> findByNameAndDescriptionContainingIgnoreCase(String name, Pageable pageable);

	@Query("SELECT new ServiceGroup( k.id, k.name, k.description) FROM ServiceGroup k")
	List<ServiceGroup> findAll();
}
