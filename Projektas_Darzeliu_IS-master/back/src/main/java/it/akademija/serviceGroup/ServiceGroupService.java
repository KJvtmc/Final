package it.akademija.serviceGroup;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.akademija.order.OrderDAO;

@Service
public class ServiceGroupService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiceGroupService.class);
	@Autowired
	private ServiceGroupDAO gartenDao;
//	@Autowired
//	private OrderDAO applicationDao;

	/**
	 * Save new ServiceGroup to database, 
	 * returns ServiceGroup unique ID
	 *
	 * @param FavListDTO
	 * @return ID
	 */
	@Transactional
	public Long createNewServiceGroup(ServiceGroupDTO group) {

		ServiceGroup garten = new ServiceGroup( 
				group.getId(),
				group.getName(), 
				group.getBooks()

				);
		gartenDao.save(garten);
		return garten.getId();
	}


	public ServiceGroupDAO getGartenDao() {
		return gartenDao;
	}


	public void setGartenDao(ServiceGroupDAO gartenDao) {
		this.gartenDao = gartenDao;
	}

	/**
	 *
	 * Returns a page of group with specified page number and page size
	 *
	 * @param pageable
	 * @return page from group database
	 */
	@Transactional(readOnly = true)
	public Page<ServiceGroupDTO> getServiceGroupPage(Pageable pageable, String search) {
		if (search == null) {
			return gartenDao.findAllServiceGroup(pageable).map(ServiceGroupDTO::from);
		}
		return gartenDao.findByNameContainingIgnoreCase(search, pageable).map(ServiceGroupDTO::from);
	}
	
	/**
	 *
	 * Returns a page of ServiceGroup with specified page number and page size that
	 * matches the search query of name and address
	 * 
	 * @param pageable, search
	 * @return page from ServiceGroup database
	 */
	@Transactional(readOnly = true)
	public Page<ServiceGroupDTO> getServiceGroupPageByNameAndDescription(Pageable pageable, String search) {

		return gartenDao.findByNameContainingIgnoreCase(search, pageable).map(ServiceGroupDTO::from);
	}

	/**
	 * Find group by id. Read only
	 *
	 * @param id
	 * @return group or null if not found
	 */
	@Transactional(readOnly = true)
	public ServiceGroup findById(Long id) {

		return gartenDao.findById(id).orElse(null);
	}
	
	
	/**
	 *
	 * Delete ServiceGroup with specified id. 
	 *
	 * @param id
	 */
	@Transactional
	public ResponseEntity<String> deleteServiceGroup(Long id) {

		ServiceGroup garten = gartenDao.findById(id).orElse(null);

		if (garten != null) {

			gartenDao.deleteById(id);

			LOG.info("** UserService: trinama grup?? ID [{}] **", id);

			return new ResponseEntity<>("Grup?? i??trinta s??kmingai", HttpStatus.OK);

		} else {

			return new ResponseEntity<>("Grup?? su tokiu  kodu nerasta", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update ServiceGroup
	 *
	 * @param currentInfo
	 * @param FavList
	 */
	@Transactional
	public void updateServiceGroup(Long id, ServiceGroupDTO updatedInfo) {

		ServiceGroup current = gartenDao.findById(id).orElse(null);

		current.setName(updatedInfo.getName());
		current.setBooks(updatedInfo.getBooks());

		gartenDao.save(current);
	}


	public List<ServiceGroupDTO> getService() {

		return gartenDao.findAll().stream().map(ServiceGroupDTO::from).collect(Collectors.toList());
	}
}
