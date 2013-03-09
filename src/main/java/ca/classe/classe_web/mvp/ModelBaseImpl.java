package ca.classe.classe_web.mvp;

import java.lang.reflect.ParameterizedType;

import ca.classe.classe_service.ServiceBase;
import ca.classe.classe_service.commun.ContexteApplicationUtils;

public abstract class ModelBaseImpl<S extends ServiceBase> implements ModelBase {

	protected S service;
	
	@SuppressWarnings("unchecked")
	public ModelBaseImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        service = ContexteApplicationUtils.getService((Class<S>) type.getActualTypeArguments()[0]);
	}
	
}
