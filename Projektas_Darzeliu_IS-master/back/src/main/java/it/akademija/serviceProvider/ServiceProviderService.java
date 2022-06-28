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
	private ServiceProviderDAO bookDao;
//	@Autowired
//	private OrderDAO applicationDao;

	/**
	 * Save new book to database, 
	 * returns book unique ID
	 *
	 * @param book
	 * @return ID
	 */
	@Transactional
	public String createNewBook(ServiceProviderDTO book) {

		ServiceProvider bookNew = new ServiceProvider( 
				book.getCode(), 
				book.getName(), 
				book.getDescription(),
				book.getPages(), 
				book.getImage());
		
		bookDao.save(bookNew);
		return bookNew.getCode();
	}


	public ServiceProviderDAO getGartenDao() {
		return bookDao;
	}


	public void setGartenDao(ServiceProviderDAO gartenDao) {
		this.bookDao = gartenDao;
	}
	
	
//////////////////////////////////
	/**
	 *
	 * Returns a page of book with specified page number and page size
	 *
	 * @param pageable
	 * @return page from book database
	 */
	@Transactional(readOnly = true)
	public Page<ServiceProviderDTO> getBookPage(Pageable pageable, String search) {
		if (search == null) {
			return bookDao.findAllBooks(pageable).map(ServiceProviderDTO::from);
		}
		return bookDao.findByNameContainingIgnoreCase(search, pageable).map(ServiceProviderDTO::from);
	}
	
	/**
	 *
	 * Returns a page of Book with specified page number and page size that
	 * matches the search query of name and address
	 * 
	 * @param pageable, search
	 * @return page from Book database
	 */
	@Transactional(readOnly = true)
	public Page<ServiceProviderDTO> getBookPageByNameAndAddress(Pageable pageable, String search) {

		return bookDao.findByNameAndDescriptionContainingIgnoreCase(search, pageable).map(ServiceProviderDTO::from);
	}

	/**
	 * Find Book by id. Read only
	 *
	 * @param id
	 * @return Book or null if not found
	 */
	@Transactional(readOnly = true)
	public ServiceProvider findById(String id) {

		return bookDao.findById(id).orElse(null);
	}
	
	
//	/**
//	 * Find Book by name. Read only
//	 *
//	 * @param name
//	 * @return Book or null if not found
//	 */
//	@Transactional(readOnly = true)
//	public boolean nameAlreadyExists(String name, String id) {
//
//		PrivateBook Book = gartenDao.findByName(name);
//
//		if (Book != null && Book.getId() != id) {
//			return true;
//		}
//		return false;
//	}
	
	/**
	 *
	 * Delete Book with specified id. Also deletes all related Book
	 * choises
	 *
	 * @param id
	 */
	@Transactional
	public ResponseEntity<String> deleteBook(String id) {

		ServiceProvider garten = bookDao.findById(id).orElse(null);

		if (garten != null) {

			bookDao.deleteById(id);

			LOG.info("** UserService: trinamas darželis ID [{}] **", id);

			return new ResponseEntity<>("Darželis ištrintas sėkmingai", HttpStatus.OK);

		} else {

			return new ResponseEntity<>("Darželis su tokiu įstaigos kodu nerastas", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Update Book
	 *
	 * @param currentInfo
	 * @param Book
	 */
	@Transactional
	public void updateBook(String id, ServiceProviderDTO updatedInfo) {

		ServiceProvider current = bookDao.findById(id).orElse(null);

		current.setName(updatedInfo.getName());
		current.setDescription(updatedInfo.getDescription());
		current.setPages(updatedInfo.getPages());
		current.setImage(updatedInfo.getImage());
		current.setServiceGroup(updatedInfo.getServiceGroup());
		current.setOrder(updatedInfo.getOrder());
		bookDao.save(current);
	}
}
