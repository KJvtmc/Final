package it.akademija.order;


import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

public interface OrderDAO extends JpaRepository<OrderEntity, Long> {

	boolean existsCompensationApplicationByChildPersonalCode(String childPersonalCode);

	@Query("SELECT new it.akademija.order.OrderInfoUser(a.id, a.childName, a.childSurname, a.submitedAt, a.serviceProvider.name, a.serviceProvider.code, a.status) FROM OrderEntity a WHERE a.mainGuardian.username=?1")
	Set<OrderInfoUser> findAllUserCompensationApplications(String currentUsername);

	@Query("SELECT c FROM OrderEntity c ORDER BY c.id DESC")
	Page<OrderEntity> orderdByDocumentId(Pageable pageable);

	@Query("SELECT c FROM OrderEntity c WHERE c.submitedAt=?1 ORDER BY c.id DESC")
	Page<OrderEntity> findBySubmitedAt(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate submitedAt,
			Pageable pageable);

}
