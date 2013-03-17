package ca.classe.classe_web.components.events;

import ca.classe.classe_service.commun.Evenement;

public interface DeleteEvent {

	Evenement<?> getDeleteEvent(Object itemId);

}
