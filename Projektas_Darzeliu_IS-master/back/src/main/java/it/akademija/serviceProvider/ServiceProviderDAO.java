package it.akademija.serviceProvider;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ServiceProviderDAO extends JpaRepository<ServiceProvider, String>{
	@Query("SELECT new ServiceProvider( k.code , k.name, k.description, k.pages, k.image) FROM ServiceProvider k")
	Page<ServiceProvider> findAllBooks(Pageable pageable);

	@Query("SELECT new ServiceProvider(  k.code , k.name, k.description, k.pages, k.image) FROM ServiceProvider k WHERE LOWER(k.name) LIKE LOWER(concat('%', ?1,'%'))")
	Page<ServiceProvider> findByNameContainingIgnoreCase(String name, Pageable pageable);

	@Query("SELECT new ServiceProvider( k.code , k.name, k.description, k.pages, k.image) FROM ServiceProvider k WHERE LOWER(k.name||' '||k.description) LIKE LOWER(concat('%', ?1,'%'))")
	Page<ServiceProvider> findByNameAndDescriptionContainingIgnoreCase(String name, Pageable pageable);

}
