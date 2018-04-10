package com.anasse.tinygram;

import java.util.ArrayList;

/***
 * Fake informations for our data fixtures
 * @author AnasseZ
 *
 */
public class DataConstant {

	
	public ArrayList<String> imageUrls;
	public ArrayList<String> contents;
	public ArrayList<String> hashtags;
	
	public DataConstant() {
		imageUrls = new ArrayList<String>();
		imageUrls.add("http://insanityraiburari.e-monsite.com/medias/images/berserk-3-.jpg");
		imageUrls.add("http://www.yozone.fr/IMG/jpg/berserk_39_centre.jpg");
		imageUrls.add("http://www.clique.tv/wp-content/uploads/2017/04/will_smith.jpg");
		imageUrls.add("http://i.imgur.com/9UN7kP6.jpg");
		imageUrls.add("https://www.jvfrance.com/wp-content/uploads/2017/09/fortnite-890x501.jpg");
		imageUrls.add("https://www.ecranlarge.com/uploads/image/001/009/berserk-photo-berserk-1009624.jpg");
		
		contents = new ArrayList<String>();
		contents.add("Un mec qui casse tout.");
		contents.add("Quel coup d'épee !");
		contents.add("Quel bon acteur celui là !");
		contents.add("Ce projet vaut 20/20.");
		contents.add("Il n'en restera qu'un.");
		contents.add("Super jeu !");
		
		
		hashtags = new ArrayList<String>();
		hashtags.add("#berserk");
		hashtags.add("#guts");
		hashtags.add("#VraimentBon");
		hashtags.add("#casseTout");
		hashtags.add("#AuTop");
		hashtags.add("#HappyDay");
		hashtags.add("#Super");
		hashtags.add("#Oklm");
	};
}
