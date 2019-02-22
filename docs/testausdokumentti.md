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

Sovelluksen pääohjelma ajaa suorituskykytestit sovelluksen molemmille generaattorityypeille. Ohjelmä pyytää
käyttäjältä halutun luolaston leveyden ja korkeuden. Tämän jälkeen generaattoreita testataan pyytämällä ne 
generoimaan käyttäjän pyytämän kokoiset luolastot kymmenen kertaa ja mittaamalla jokaiseen generaatioon
kulutettu aika. Tämän jälkeen näiden kymmenen testin tulosten keskiarvo raportoidaan käyttäjälle ja viimeiseksi
generoidut luolat tulostetaan.

Generointi käyttää syötteenä pseudosatunnaislukugeneraattoria, joka alustetaan järjestelmän kellon mukaan.

Aputietorakenteilla on myös suorituskykytestejä, jotka vertaavat tietorakenteiden suorituskykyä Javan standardikirjaston
tietorakenteisiin. Nämä testit on toteutettu JUnit testeinä ja ne ajetaan muiden yksikkötestien kanssa komennolla
"./gradlew test".

Dokumentoinnin ohessa on kaaviot generaattoreiden suorituskyvystä suhteessa pyydetyn luolaston kokoon. Tämä
testaus toteutettiin omalla pöytäkoneella, joka on varustettu Ryzen 7 1700 prosessorilla (3.7 GHz) ja 2133 MHz 
keskusmuistilla.

BSP-generaattori on testauksen perusteella huomattavasti nopeampi kuin soluautomaattiin perustuva generaattori,
kuten määrittelydokumentaatiossa ennustin. Järkevän kokoisilla syötteillä (< 200 x 200) generaattorin suorituskyky
pysyi melkein muuttumattomana, ja valtaosa algoritmin ajankäytöstä oli käytävien piirtämistä.

Soluautomaatti puolestaan hidastui nopeasti luolaston koon kasvaessa, sillä algoritmin eri osien suorituskyky
perustuu vahvasti luolaston kokoon. Soluautomaatin aikaavievin osa on luolaston segmenttien yhdistäminen, joka
on vastuussa yli 50% suoritusajasta. Kuitenkin, vaikka segmenttejä ei yhdisteltäisiin, yksinään luolaston
generointiin vaadittava soluautomaatin simulointi käyttää enemmän aikaa kuin BSP-algoritmi yhteensä samankokoiselle
luolastolle. Siispä vaikka segmenttien yhdistäminen jätettäisiin pois, olisi soluautomaatti kuitenkin huomattavasti
hitaampi, joten sen hinta ei ole vertailumielessä niin merkittävä.
