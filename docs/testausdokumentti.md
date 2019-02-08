# Testausdokumentti

## Toiminnallisuustestaus

Ohjelman osien oikeellisuus on toteutettu JUnit-yksikkötesteillä. Jokaista ei-triviaalia luokkaa kohti on tehty
testausluokka, joka sisältää yksikkötestejä luokan yleisiin käyttötilanteisiin ja vaatimuksiin perustuen.

Kaikki JUnit-testit on ajettavissa komennolla "./gradlew test". Testikattavuusraportin voi tarvittaessa
generoida komennolla "./gradlew jacocoTestReport".

### Generaattorien yksikkötestit

Molemmat luolastogeneraattorit sisältävät yksikkötestit, joilla tarkistetaan että generaattorit kykenevät
generoimaan erikokoisia karttoja ilman että ne päätyvät virhetilanteeseen ja lisäksi generaattorien tuottamat
luolastot tarkistetaan yhtenäisiksi, eli että kaikista luolan osista päästään muihin luolan osiin. Tämä testaus
on tehty leveyshaulla (BFS)

### Tietorakenteiden yksikkötestit

Tietorakenteille on toteutettu testit niiden alustusarvoille ja niiden yleisesti korrektille käyttäytymiselle.
Jonolle ja ArrayListille on toteutettu testit lisäysten ja poistojen toimivuudelle, sekä sille, että niiden
koot raportoidaan oikein, ja että niiden sisällöt ovat oikeanlaiset eri käyttötilanteiden jälkeen.

### Sovelluskohtaisten tietorakenteiden ja algoritmien testaus

Kartan tallentamiseen ja yksittäisten huoneiden mallintamiseen, sekä näiden läpikäyntiin tarkoitetuille algoritmeille
on tehty yksinkertaisia toiminnallisuuden varmistavia testejä. BFS-algoritmilla on esimerkiksi testit, joilla
tarkistetaan että BFS-haku tulee suoritetuksi kokonaan, eikä esimerkiksi kaadu liiallisen muistinkäytön tai
loputtoman silmukan seurauksena. Lisäksi sille on tarkistettu, että se löytää esimerkkiluolan kaikki saavutettavat
ruudut.

## Suorituskykytestaus

Sovelluksen pääohjelma ajaa suorituskykytestit sovelluksen molemmille generaattorityypeille. Generaattoreita
testataan pyytämällä ne generoimaan 50x50 kokoiset kartat kymmenen kertaa ja mittaamalla jokaiseen generaatioon
kulutettu aika. Tämän jälkeen näiden kymmenen testin tulosten keskiarvo raportoidaan käyttäjälle ja viimeiseksi
generoidut luolat tulostetaan.

Generointi käyttää syötteenä pseudosatunnaislukugeneraattoria. Tällä hetkellä käytössä on Javan standardikirjaston
satunnaislukugeneraattori, mutta se tullaan korvaamaan omalla korvaavalla pseudosatunnaislukugeneraattorilla.
