package it.akademija.serviceItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ServiceItemDAO extends JpaRepository<ServiceItem, Long>{
	@Query("SELECT new ServiceItem( k.itemId, k.name, k.description) FROM ServiceItem k")
	Page<ServiceItem> findAllServiceItem(Pageable pageable);

	@Query("SELECT new ServiceItem(   k.itemId, k.name, k.description) FROM ServiceItem k WHERE LOWER(k.name) LIKE LOWER(concat('%', ?1,'%'))")
	Page<ServiceItem> findByNameContainingIgnoreCase(String name, Pageable pageable);

	@Query("SELECT new ServiceItem( k.itemId, k.name, k.description) FROM ServiceItem k WHERE LOWER(k.name||' '||k.description) LIKE LOWER(concat('%', ?1,'%'))")
	Page<ServiceItem> findByNameAndDescriptionContainingIgnoreCase(String name, Pageable pageable);

}
