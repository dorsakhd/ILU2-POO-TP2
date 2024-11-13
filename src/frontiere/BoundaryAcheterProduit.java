package frontiere;

import java.util.Scanner;


import controleur.ControlAcheterProduit;
import personnages.Gaulois;
import villagegaulois.Etal;


public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
	    if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
	        System.out.println("Je suis désolé " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
	        return;
	    }

	    String produit = demanderProduit();
	    Gaulois[] vendeurs = controlAcheterProduit.trouverVendeurProduit(produit);

	    if (vendeurs == null) {
	        System.out.println("Désolé, personne ne vend ce produit au marché.");
	        return;
	    }

	    String nomVendeur = choisirVendeur(vendeurs);
	    Etal etalVendeur = controlAcheterProduit.trouverEtalVendeur(nomVendeur);
	    System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur + "\n");

	    int quantiteProduit = demanderQuantite(produit, nomAcheteur);
	    traiterAchat(etalVendeur, quantiteProduit, nomAcheteur, produit, nomVendeur);
	}

	private String demanderProduit() {
	    System.out.println("Quel produit voulez-vous acheter?");
	    return scan.next();
	}

	private String choisirVendeur(Gaulois[] vendeurs) {
	    StringBuilder question = new StringBuilder("Chez quel commerçant voulez-vous acheter ?\n");
	    for (int i = 0; i < vendeurs.length; i++) {
	        question.append((i + 1) + " - " + vendeurs[i].getNom() + "\n");
	    }
	    int choixUtilisateur = 0;
	    do {
	        choixUtilisateur = Clavier.entrerEntier(question.toString());
	    } while (choixUtilisateur <= 0 || choixUtilisateur > vendeurs.length);

	    return vendeurs[choixUtilisateur - 1].getNom();
	}

	private int demanderQuantite(String produit, String nomAcheteur) {
	    StringBuilder question = new StringBuilder("Bonjour " + nomAcheteur + "\n");
	    question.append("Combien de " + produit + " voulez-vous acheter ?\n");
	    return Clavier.entrerEntier(question.toString());
	}

	private void traiterAchat(Etal etalVendeur, int quantiteProduit, String nomAcheteur, String produit, String nomVendeur) {
	    int qteDisponible = etalVendeur.getQuantite();
	    if (qteDisponible == 0) {
	        System.out.println(nomAcheteur + " veut acheter " + quantiteProduit + " " + produit + ", malheureusement il n'y en a plus !");
	    } else if (quantiteProduit > qteDisponible) {
	        controlAcheterProduit.acheterProduit(etalVendeur, qteDisponible);
	        System.out.println(nomAcheteur + " veut acheter " + quantiteProduit + " " + produit + ", malheureusement " + nomVendeur + " n'en a plus que " + qteDisponible + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur);
	    } else {
	        controlAcheterProduit.acheterProduit(etalVendeur, quantiteProduit);
	        System.out.println(nomAcheteur + " achète " + quantiteProduit + " de " + produit + " à " + nomVendeur);
	    }
	}

}
