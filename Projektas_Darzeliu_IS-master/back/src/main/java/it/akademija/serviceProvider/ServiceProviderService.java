package it.akademija.serviceProvider;

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
public class ServiceProviderService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiceProviderService.class);
	@Autowired
	private ServiceProviderDAO gartenDao;
//	@Autowired
//	private OrderDAO applicationDao;

	/**
	 * Save new kindergarten to database, 
	 * returns PrivateKindergartens unique ID
	 *
	 * @param kindergarten
	 * @return ID
	 */
	@Transactional
	public String createNewPrivateKindergarten(ServiceProviderDTO kindergarten) {

		ServiceProvider garten = new ServiceProvider( 
				kindergarten.getCode(), 
				kindergarten.getName(), 
				kindergarten.getAddress(),
				kindergarten.getPhone(), 
				kindergarten.getEmail());
		gartenDao.save(garten);
		return garten.getCode();
	}


	public ServiceProviderDAO getGartenDao() {
		return gartenDao;
	}


	public void setGartenDao(ServiceProviderDAO gartenDao) {
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
	public Page<ServiceProviderDTO> getKindergartenPage(Pageable pageable, String search) {
		if (search == null) {
			return gartenDao.findAllKindergarten(pageable).map(ServiceProviderDTO::from);
		}
		return gartenDao.findByNameContainingIgnoreCase(search, pageable).map(ServiceProviderDTO::from);
	}
	
	/**
	 *
	 * Returns a page of Kindergarten with specified page number and page size that
	 * matches the search query of name and address
	 * 
	 * @param pageable, search
	 * @return page from kindergarten database
	 */
	@Transactional(readOnly = true)
	public Page<ServiceProviderDTO> getKindergartenPageByNameAndAddress(Pageable pageable, String search) {

		return gartenDao.findByNameAndAddressContainingIgnoreCase(search, pageable).map(ServiceProviderDTO::from);
	}

	/**
	 * Find kindergarten by id. Read only
	 *
	 * @param id
	 * @return kindergarten or null if not found
	 */
	@Transactional(readOnly = true)
	public ServiceProvider findById(String id) {

		return gartenDao.findById(id).orElse(null);
	}
	
	
//	/**
//	 * Find kindergarten by name. Read only
//	 *
//	 * @param name
//	 * @return kindergarten or null if not found
//	 */
//	@Transactional(readOnly = true)
//	public boolean nameAlreadyExists(String name, String id) {
//
//		PrivateKindergarten kindergarten = gartenDao.findByName(name);
//
//		if (kindergarten != null && kindergarten.getId() != id) {
//			return true;
//		}
//		return false;
//	}
	
	/**
	 *
	 * Delete kindergarten with specified id. Also deletes all related kindergarten
	 * choises
	 *
	 * @param id
	 */
	@Transactional
	public ResponseEntity<String> deleteKindergarten(String id) {

		ServiceProvider garten = gartenDao.findById(id).orElse(null);

		if (garten != null) {

			gartenDao.deleteById(id);

			LOG.info("** UserService: trinamas darželis ID [{}] **", id);

			return new ResponseEntity<>("Darželis ištrintas sėkmingai", HttpStatus.OK);

		} else {

			return new ResponseEntity<>("Darželis su tokiu įstaigos kodu nerastas", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update kindergarten
	 *
	 * @param currentInfo
	 * @param kindergarten
	 */
	@Transactional
	public void updateKindergarten(String id, ServiceProviderDTO updatedInfo) {

		ServiceProvider current = gartenDao.findById(id).orElse(null);

		current.setName(updatedInfo.getName());
		current.setAddress(updatedInfo.getAddress());
		current.setCode(updatedInfo.getCode());
		current.setServiceGroups(updatedInfo.getServiceGroups());
		current.setEmail(updatedInfo.getEmail());
		current.setPhone(updatedInfo.getPhone());

		gartenDao.save(current);
	}
}
