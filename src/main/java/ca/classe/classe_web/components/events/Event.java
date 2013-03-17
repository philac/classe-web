package ca.classe.classe_web.components.events;

import ca.classe.classe_service.commun.Evenement;

public interface Event {
	Evenement<?> getEvent(Object itemId);
}
