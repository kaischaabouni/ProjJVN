Projet Javanaise
=======


-Equipe:

  * Alexandre MORJARET: alexandre.morjaret@gmail.com
  
  * Kaïs CHAABOUNI: kais.chaabouni@e.ujf-grenoble.fr


-Avancement:

  Nous avons finalis� le projet Javanaise version 1 et la version 2 avec le proxy dynamique et les annotations.
  
  
-Installation et Tests:

  * R�cup�rer les sources
  * Lancer le coordinateur, en ex�cutant JvnCoordImpl
  * Lancer les clients:
  * Tests de chat: 
      - Lancer Irc pour tester JVN version 1(objet IRC)
      - Lancer IrcAvecProxy pour tester JVN version 2 (objet IRC)
      - Lancer OtherAvecProxy pour tester JVN version 2 (pareil que IRC mais avec un autre nom)
      - Lancer IrcSleep pour tester l'acc�s conncurrent  (sleep pendant 6s pour le write)
      