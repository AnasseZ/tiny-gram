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
	public ArrayList<String> firstName;
	public ArrayList<String> profilImagesUrl;
	
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
		
		firstName = new ArrayList<String>();
		firstName.add("Julien");
		firstName.add("Thomas");
		firstName.add("Simon");
		firstName.add("Martin");
		firstName.add("Lucas");
		firstName.add("Anthony");
		firstName.add("Yassine");
		firstName.add("Karim");
		firstName.add("Samy");
		firstName.add("Hicham");
		firstName.add("Mehdi");
		
		profilImagesUrl = new ArrayList<>();
		profilImagesUrl.add("https://vignette.wikia.nocookie.net/pelea-versus/images/8/84/16Gatsu-Guts-Berserk-Manga.jpg/revision/latest/scale-to-width-down/439?cb=20141103194702&path-prefix=es");
		profilImagesUrl.add("http://img.filmsactu.net/datas/seriestv/b/r/breaking-bad/xl/breaking-bad-5a6a108381a4a.jpg");
		profilImagesUrl.add("https://vignette.wikia.nocookie.net/dragonball/images/d/dd/GogetaFusionRebornDVD.png/revision/latest?cb=20130331131615&path-prefix=fr");
		profilImagesUrl.add("https://vl-media.fr/wp-content/uploads/2017/12/gomorra_marcodamore.jpg");
		profilImagesUrl.add("https://img.cinemablend.com/filter:scale/quill/1/2/e/a/8/7/12ea87f85a55016c28cf7ecec89904b447bd7672.jpg?mw=600");
		profilImagesUrl.add("https://i0.wp.com/www.jdbn.fr/wp-content/uploads/2015/06/willsmith__120111165106.jpg?fit=600%2C400");
		
	};
}
