package ca.classe.classe_service.entite;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.classe.classe_service.ServiceBaseImpl;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServiceClassImpl extends ServiceBaseImpl implements ServiceClass{

	private DaoClass daoClass;
	
	@Inject
	public ServiceClassImpl(DaoClass daoClass) {
		this.daoClass = daoClass;
	}
}