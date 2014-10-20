Projet Javanaise
=======


-Equipe:

  * Alexandre MORJARET: alexandre.morjaret@gmail.com
  
  * KaÃ¯s CHAABOUNI: kais.chaabouni@e.ujf-grenoble.fr


-Avancement:

  Nous avons finalisé le projet Javanaise version 1 et la version 2 avec le proxy dynamique et les annotations.
  
  
-Installation et Tests:

  * Récupérer les sources
  * Lancer le coordinateur, en exécutant JvnCoordImpl
  * Lancer les clients:
  * Tests de chat: 
      - Lancer Irc pour tester JVN version 1(objet IRC)
      - Lancer IrcAvecProxy pour tester JVN version 2 (objet IRC)
      - Lancer OtherAvecProxy pour tester JVN version 2 (pareil que IRC mais avec un autre nom)
      - Lancer IrcSleep pour tester l'accès conncurrent  (sleep pendant 6s pour le write)
      