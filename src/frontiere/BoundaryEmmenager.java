package frontiere;

import controleur.ControlEmmenager;

public class BoundaryEmmenager {
	private ControlEmmenager controlEmmenager;

	public BoundaryEmmenager(ControlEmmenager controlEmmenager) {
		this.controlEmmenager = controlEmmenager;
	}

	public void emmenager(String nomVisiteur) {
		if (controlEmmenager.isHabitant(nomVisiteur)) {
			System.out.println(
					"Mais vous êtes déjà un habitant du village !");
		} else {
			StringBuilder question = new StringBuilder();
			question.append("Êtes-vous :\n");
			question.append("1 - un druide.\n");
			question.append("2 - un gaulois.\n");
			int choixUtilisateur = -1;
			do {
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				switch (choixUtilisateur) {
				case 1:
					emmenagerDruide(nomVisiteur);
					break;

				case 2:
					emmenagerGaulois(nomVisiteur);
					break;

				default:
					System.out
							.println("Vous devez choisir le chiffre 1 ou 2 !");
					break;
				}
			} while (choixUtilisateur != 1 && choixUtilisateur != 2);
		}
	}

	private void emmenagerDruide(String nomVisiteur) {
		 souhaiterBienvenue(nomVisiteur);
		
		int force = demanderForce();
		int effetPotionMinimum = demanderEffetPotionMinimum();
		int effetPotionMaximum = demanderEffetPotionMaximum();
		
		controlEmmenager.ajouterDruide(nomVisiteur, force, effetPotionMinimum, effetPotionMaximum);
	}
	
	private void emmenagerGaulois(String nomVisiteur) {		
		this.souhaiterBienvenue(nomVisiteur);
		
		int force = demanderForce();
		
		controlEmmenager.ajouterGaulois(nomVisiteur, force);
	}
	
	
	private void souhaiterBienvenue(String nom) {
		System.out.println("Bienvenue villageois " + nom + ".");
	}
	
	
	private int demanderForce() {
		return Clavier.entrerEntier("Quelle est votre force ? : ");
	}
	
	private int demanderEffetPotionMinimum() {
		return Clavier.entrerEntier("Quelle est la force de potion la plus faible que vous produisez ?");
	}
	
	private int demanderEffetPotionMaximum() {
		return Clavier.entrerEntier("Quelle est la force de potion la plus forte que vous produisez ?");
	}
	
}

