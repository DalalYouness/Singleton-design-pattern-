package com.jobintech;

/*
 * Cette classe s'appelle Singleton uniquement dans un but pédagogique.
 * L'objectif est de regrouper toutes les manières possibles d'implémenter
 * le pattern Singleton, ainsi que les problématiques associées,
 * notamment le multi-threading et la concurrence.
 */
public class Singleton {

    /*
     * ================================
     * 1 - EAGER INITIALIZATION
     * ================================
     *
     * Première façon d'initialiser l'objet :
     * l'instance est créée directement au niveau du champ.
     *
     * ➜ L'instanciation se fait dès que la classe est chargée en mémoire
     *   par la JVM.
     *
     * Avantages :
     * - Thread-safe par nature (class loading garanti par la JVM)
     *
     * Inconvénients :
     * - L'objet est créé même s'il n'est jamais utilisé
     *   (pas de Lazy Initialization)
     */
    private static final Singleton INSTANCE = new Singleton();

    /*
     * État interne partagé de l'objet Singleton.
     * Cet état sera commun à tous les threads et à toutes les références.
     */
    private int counter;

    /*
     * ================================
     * 2 - STATIC BLOCK INITIALIZATION
     * ================================
     *
     * Deuxième façon possible :
     * instancier l'objet dans un bloc static.
     *
     * Cette approche est équivalente à l'Eager Initialization,
     * car l'instanciation se fait également lors du chargement de la classe.
     *
     * Exemple :
     *
     * static {
     *     INSTANCE = new Singleton();
     * }
     *
     * ➜ Thread-safe
     * ➜ Pas Lazy
     */

    /*
     * Constructeur privé :
     * - Empêche l'instanciation depuis l'extérieur de la classe
     * - Garantit que l'objet ne sera créé que depuis cette classe
     */
    private Singleton() {
    }

    /*
     * ================================
     * 3 - LAZY INITIALIZATION
     * ================================
     *
     * Méthode permettant d'obtenir l'unique instance du Singleton.
     *
     * Principe de la Lazy Initialization :
     * - L'objet n'est créé que lors du premier appel à cette méthode
     * - Tant que personne n'appelle getInstance(), l'objet n'existe pas
     *
     * Problème :
     * - Cette implémentation n'est PAS thread-safe
     * - Si plusieurs threads appellent getInstance() en même temps :
     *   ➜ chacun peut voir INSTANCE == null
     *   ➜ plusieurs instances peuvent être créées
     *   ➜ violation du principe du Singleton
     */
//    public static Singleton getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new Singleton();
//        }
//        return INSTANCE;
//    }

    /*
     * ================================
     * 4 - SYNCHRONIZATION
     * ================================
     *
     * Pour résoudre le problème de concurrence, on peut synchroniser
     * l'accès à la section critique (l'instanciation).
     *
     * Comme la méthode est static, on ne peut pas synchroniser sur "this".
     * On synchronise donc sur la classe Singleton.
     *
     * Inconvénient :
     * - Tous les threads sont bloqués même après la création de l'instance
     * - Impact négatif sur les performances
     */
//    public static Singleton getInstance() {
//        synchronized (Singleton.class) {
//            if (INSTANCE == null) {
//                INSTANCE = new Singleton();
//            }
//        }
//        return INSTANCE;
//    }

    /*
     * ================================
     * 5 - DOUBLE CHECKED LOCKING
     * ================================
     *
     * Objectif :
     * - Synchroniser uniquement lors de la première instanciation
     * - Éviter le coût du synchronized à chaque appel
     *
     * Fonctionnement :
     * 1. Premier test sans synchronisation
     * 2. Synchronisation uniquement si INSTANCE est null
     * 3. Deuxième vérification à l'intérieur du bloc synchronized
     *
     * Cette approche améliore les performances
     * mais nécessite l'utilisation du mot-clé volatile.
     *
     * https://chatgpt.com/c/696a09b9-ec80-8333-a5dd-40fee1c40717 voila pourquoi on a besoin de volatoile dans ce cas
     * dans la partie finale de chatgpt
     */
//    public static Singleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (Singleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Singleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }

    /*
     * Dans cette classe de démonstration,
     * nous retournons simplement l'instance EAGER
     * afin de rester cohérent avec l'exemple principal.
     */
    public static Singleton getInstance() {
        return INSTANCE;
    }

    /*
     * ================================
     * PROBLÉMATIQUE DE L'ÉTAT PARTAGÉ
     * ================================
     *
     * Même si le Singleton est thread-safe au niveau de son instanciation,
     * son état interne ne l'est pas forcément.
     *
     * La méthode increment() n'est PAS thread-safe :
     * plusieurs threads peuvent modifier "counter" en même temps.
     */
    public int increment() {
        counter++;
        return counter;
    }

    public int getCounter() {
        return counter;
    }

    /*
    *  il reste deux methode akhrin
    * 1 - classe dans laquelle on met le singleton
    * 2 - enum singleton
    *
    * */
}
