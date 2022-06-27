package it.akademija.serviceItem;

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
public class ServiceItemService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiceItemService.class);
	@Autowired
	private ServiceItemDAO gartenDao;


	/**
	 * Save new ServiceItem to database, 
	 * returns ServiceItem unique ID
	 *
	 * @param ServiceItemDTO
	 * @return ID
	 */
	@Transactional
	public Long createNewServiceItem(ServiceItemDTO kindergarten) {

		ServiceItem garten = new ServiceItem( 
				kindergarten.getItemId(), 
				kindergarten.getName(), 
				kindergarten.getDescription(),
				kindergarten.getServiceGroups()
				);
		gartenDao.save(garten);
		return garten.getId();
	}


	public ServiceItemDAO getGartenDao() {
		return gartenDao;
	}


	public void setGartenDao(ServiceItemDAO gartenDao) {
		this.gartenDao = gartenDao;
	}

	/**
	 *
	 * Returns a page of Kindergarten with specified page number and page size
	 *
	 * @param pageable
	 * @return page from kindergarten database
	 */
	@Transactional(readOnly = true)
	public Page<ServiceItemDTO> getServiceItemPage(Pageable pageable, String search) {
		if (search == null) {
			return gartenDao.findAllServiceItem(pageable).map(ServiceItemDTO::from);
		}
		return gartenDao.findByNameContainingIgnoreCase(search, pageable).map(ServiceItemDTO::from);
	}
	
	/**
	 *
	 * Returns a page of ServiceItem with specified page number and page size that
	 * matches the search query of name and address
	 * 
	 * @param pageable, search
	 * @return page from ServiceItem database
	 */
	@Transactional(readOnly = true)
	public Page<ServiceItemDTO> getServiceItemPageByNameAndDescription(Pageable pageable, String search) {

		return gartenDao.findByNameAndDescriptionContainingIgnoreCase(search, pageable).map(ServiceItemDTO::from);
	}

	/**
	 * Find kindergarten by id. Read only
	 *
	 * @param id
	 * @return kindergarten or null if not found
	 */
	@Transactional(readOnly = true)
	public ServiceItem findById(Long id) {

		return gartenDao.findById(id).orElse(null);
	}
	
	
	/**
	 *
	 * Delete ServiceItem with specified id. 
	 *
	 * @param id
	 */
	@Transactional
	public ResponseEntity<String> deleteServiceItem(Long id) {

		ServiceItem garten = gartenDao.findById(id).orElse(null);

		if (garten != null) {

			gartenDao.deleteById(id);

			LOG.info("** UserService: trinama grupė ID [{}] **", id);

			return new ResponseEntity<>("Grupė ištrinta sėkmingai", HttpStatus.OK);

		} else {

			return new ResponseEntity<>("Grupė su tokiu  kodu nerasta", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update ServiceItem
	 *
	 * @param currentInfo
	 * @param ServiceItem
	 */
	@Transactional
	public void updateServiceItem(Long id, ServiceItemDTO updatedInfo) {

		ServiceItem current = gartenDao.findById(id).orElse(null);

		current.setName(updatedInfo.getName());
		current.setDescription(updatedInfo.getDescription());

		gartenDao.save(current);
	}
}
