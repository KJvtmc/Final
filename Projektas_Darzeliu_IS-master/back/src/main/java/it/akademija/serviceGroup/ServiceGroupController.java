package it.akademija.serviceGroup;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.akademija.journal.JournalService;
import it.akademija.journal.ObjectType;
import it.akademija.journal.OperationType;

@RestController
@Api(value = "ServiceGroup")
@RequestMapping(path = "/api/ServiceGroup")
public class ServiceGroupController {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceGroupController.class);

	@Autowired
	private ServiceGroupService service;

	@Autowired
	private JournalService journalService;

	/**
	 * Get specified ServiceGroup information page
	 *
	 * @return page of ServiceGroup information
	 */
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/manager/page")
	@ApiOperation(value = "Get ServiceGroup information pages")
	public ResponseEntity<Page<ServiceGroupDTO>> getKindergartenPage(

			@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam(value = "search", required = false) String search) {

		Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name").ignoreCase();
		String decodedName = "";

		Pageable pageable = PageRequest.of(page, size, Sort.by(order));
		if (search != null) {
			try {
				search = search.replaceAll("%", "%25");
				decodedName = URLDecoder.decode(search, "UTF-8");
				decodedName = decodedName.replaceAll("%", "%75[%]%");
				decodedName = decodedName.replaceAll("_", "[_]");

				return new ResponseEntity<>(service.getServiceGroupPage(pageable, decodedName),
						HttpStatus.OK);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(service.getServiceGroupPage(pageable, null), HttpStatus.OK);

	}
	
	/**
	 * Get specified ServiceGroup information page
	 *
	 * @return page of ServiceGroup information
	 */
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/manager/groups")
	@ApiOperation(value = "Get ServiceGroup information pages")
	public ResponseEntity<List<ServiceGroupDTO>> getAll() {

		Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name").ignoreCase();
		String decodedName = "";

		return new ResponseEntity<List<ServiceGroupDTO>>(service.getService(), HttpStatus.OK);

	}

	/**
	 * Get specified ServiceGroup information page
	 *
	 * @return page of ServiceGroup information
	 */
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/manager/search")
	@ApiOperation(value = "Get ServiceGroup information pages")
	public ResponseEntity<Page<ServiceGroupDTO>> getProviderPageByNameAndAddress(

			@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("search") String search) {

		Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name").ignoreCase();
		String decodedSearch = "";

		Pageable pageable = PageRequest.of(page, size, Sort.by(order));

			try {
				search = search.replaceAll("%", "%25");
				decodedSearch = URLDecoder.decode(search, "UTF-8");
				decodedSearch = decodedSearch.replaceAll("%", "%75[%]%");
				decodedSearch = decodedSearch.replaceAll("_", "[_]");

				return new ResponseEntity<>(service.getServiceGroupPageByNameAndDescription(pageable, decodedSearch),
						HttpStatus.OK);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		
		return new ResponseEntity<>(service.getServiceGroupPage(pageable, null), HttpStatus.OK);

	}

	/**
	 * Create new ServiceGroup entity
	 *
	 * @param ServiceGroup entity
	 * @return message
	 */
	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/manager/createServiceGroup")
	@ApiOperation(value = "Create new ServiceGroup")
	public ResponseEntity<String> createNewServiceGroup(
			@ApiParam(value = "ServiceGroup", required = true) @Valid @RequestBody ServiceGroupDTO ServiceGroup) {

		Long id = ServiceGroup.getId();

		if (service.findById(id) != null) {

			LOG.warn("Kuriamas tiekėjas su jau egzistuojančiu įstaigos kodu [{}]", id);

			return new ResponseEntity<>("tiekėjas su tokiu įstaigos kodu jau yra", HttpStatus.CONFLICT);

		} 
//		else if (kindergartenService.nameAlreadyExists(kindergarten.getKindergartenName().trim(), id)) {
//
//			LOG.warn("Kuriamas darželis su jau egzistuojančiu įstaigos pavadinimu [{}]", kindergarten.getKindergartenName().trim());
//
//			return new ResponseEntity<>("Darželis su tokiu įstaigos pavadinimu jau yra", HttpStatus.CONFLICT);
//
//		}
		else {

			service.createNewServiceGroup(ServiceGroup);

			LOG.info("**KindergartenController: kuriamas darzelis pavadinimu [{}] **", ServiceGroup.getName());

			journalService.newJournalEntry(OperationType.KINDERGARTEN_CREATED, id,
					ObjectType.KINDERGARTEN, "Sukurtas naujas darželis");

			return new ResponseEntity<>("tiekėjas sukurtas sėkmingai", HttpStatus.OK);
		}

	}

	/**
	 *
	 * Delete kindergarten entity with specified id
	 *
	 * @param id
	 * @return message if entity was deleted or if it does not exist in the database
	 */
	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/manager/delete/{id}")
	@ApiOperation(value = "Delete kindergarten by ID")
	public ResponseEntity<String> deleteKindergarten(
			@ApiParam(value = "Kindergarten id", required = true) @PathVariable Long id) {

		journalService.newJournalEntry(OperationType.KINDERGARTEN_DELETED, id, ObjectType.KINDERGARTEN,
				"Ištrintas tiekėjas");

		return service.deleteServiceGroup(id);
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/manager/update/{id}")
	@ApiOperation(value = "Update kindergarten by ID")
	public ResponseEntity<String> updateKindergarten(
			@ApiParam(value = "Kindergarten", required = true) @Valid @RequestBody ServiceGroupDTO updated,
			@PathVariable Long id) {

		if (service.findById(id) == null) {

			LOG.warn("tiekėjas įstaigos kodu [{}] nėra", id);

			return new ResponseEntity<>("tiekėjas su tokiu įstaigos kodu nerastas", HttpStatus.NOT_FOUND);

		}
//		else if (kindergartenService.nameAlreadyExists(updated.getKindergartenName().trim(), id)) {
//
//			LOG.warn("Darželis pavadinimu [{}] jau egzituoja", updated.getKindergartenName().trim());
//
//			return new ResponseEntity<>("Darželis su tokiu įstaigos pavadinimu jau yra", HttpStatus.CONFLICT);
//
//		} 
		else {

			service.updateServiceGroup(id, updated);

			LOG.info("** Usercontroller: atnaujinamas tiekėjas ID [{}] **", id);

			journalService.newJournalEntry(OperationType.KINDERGARTEN_UPDATED, id,
					ObjectType.KINDERGARTEN, "Atnaujinti darželio duomenys");

			return new ResponseEntity<>("tiekėjas duomenys atnaujinti sėkmingai", HttpStatus.OK);
		}

	}
	

	public ServiceGroupService getService() {
		return service;
	}

	public void setGartenService(ServiceGroupService service) {
		this.service = service;
	}

}
