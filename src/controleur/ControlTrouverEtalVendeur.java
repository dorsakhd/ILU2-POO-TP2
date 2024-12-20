package controleur;

import villagegaulois.Etal;
import personnages.Gaulois;
import villagegaulois.Village;

public class ControlTrouverEtalVendeur {
	private Village village;

	public ControlTrouverEtalVendeur(Village village) {
		this.village = village;
	}

	public Etal trouverEtalVendeur(String nomVendeur) {
		Gaulois vendeur = this.village.trouverHabitant(nomVendeur);
		return vendeur == null ? null : this.village.rechercherEtal(vendeur);
	}
}
