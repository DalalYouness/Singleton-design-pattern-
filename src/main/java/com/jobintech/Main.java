package com.jobintech;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//On a cette classe qui va utiliser l'objet partagé
public class Main {

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        /*cette operation de comparasion va nous donné true parce que c'est la meme address memoire
        * On a deux variables dans le stack qui contient une meme address memoire donc il point vers le meme objets
        * */
        System.out.println(s1 == s2);

        /*donc si je veux faire l'operation des deux pointeurs les deux vont impacter l'objet*/
        s1.increment(); // 1
        s2.increment(); // 2

        System.out.println(s1.getCounter());
        System.out.println(s2.getCounter());
        // les deux vont donné la meme resultats
        /*hadchi li drna db ghay7al lina 3aynina ela whd le probleme
        * li howa db 7na khdamin la programation synchrone donc ghankhdmo b s1 nakhdo result
        * man b3d s2 nakhdo result c'est d'une maniere sequentiel
        *
        * problematique :
        * si on a deux thread qui veut acceder les deux au memes temps a l'objet ,
        * donc les deux vont utiliser la meme adresse memoire , si les deux veut faire une
        * operation au meme temps a l'etat de l'objet? dans ce cas on va tomber dans
        * le probleme c'est que l'objet singleton devient non thread safe , donc
        * on va utiliser la synchronisation
        * */
    }
}