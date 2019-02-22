# Viikkoraportti 6

## Projektin eteneminen

Tämän viikon tärkein tuotettu toiminallisuus oli pseudosatunnaislukugeneraattori, joka perustuu [LCG-algoritmiin](https://en.wikipedia.org/wiki/Linear_congruential_generator).
Lisäksi täytin toteutus- ja testausdokumentaatiota siten, että luolastogeneraattorien algoritmit on nyt
dokumentoitu. Lisäksi onnistuin saamaan HashSetissä olevan bugin kiinni, joka aiheutti taulukon ylivuodon.
Nyt HashSet automaattisesti rehashaa taulukon, mikäli ylivuoto uhkaa tapahtua. Tämä kuluttaa aikaa ja tilaa, mutta
sallii sen, että taulukon lopppuun ei tarvitse tehdä ylivuotolistaa, tai muokata HashSetin toiminnallisuutta
jotta tämä tilanne estettäisiin. Samalla onnistuin optimoimaan soluautomaattigeneraattorin segmenttien yhdistystä
korvaamalla käytettyjä ArrayListeja HashSetillä. Tämä pudotti merkittävän osan algoritmin suoritusajasta.

## Mitä opin

Opin kuinka LCG-algorimit toimii, ja sitä kautta kuinka monet ohjelmointikielet toteuttavat niiden standardikirjaston
satunnaislukugeneraattorit. 

## Vaikeudet viikon aikana

Suurimmat vaikeudet viikolla olivat satunnaislukugeneraattorin lukuarvojen konfiguroiminen niin, että tuotetut
satunnaisluvut olivat riittävän satunnaisia ja HashSetin bugien viilaaminen pois. Aluksi satunnaislukugeneraattori
tuotti toisteisia tai joissain määrin arvattavia arvoja, mutta onneksi esimerkiksi glibc:n käyttämät arvot
ovat hyvin dokumentoituja, joten onnistuin lopulta luomaan implementaation, joka täyttää projektin vaatimukset.

HashSetin bugi oli mielenkiintoinen, sillä se ei ilmennyt testeissä kuin satunnaisesti, mutta optimoidessani
soluautomaattigeneraattoria löysin keinon, jolla ongelma ilmeni toistuvasti. Bugi oli hajautustaulun ylivuoto,
joka aiheutui ilmeisesti hajautustaulun täyttymisestä. Korjasin ongelman uudelleenhajauttamalla taulun, jos
tutkittu indeksi kasvaa kapasiteetin yli, mikä tuntuu estävän ongelman, joskin muistin ja suoritusajan kustannuksella.
Tästä huolimatta HashSet-toteutus on edelleen hyvin nopea.

## Mitä teen seuraavaksi

Jäljellä ei ole kuin vain viimeisiä algoritmien viilaamisia ja dokumentoinnin viimeistelyä. Ohjelma toimii nyt
niin kuin suunnittelin ja kaikki ohjelman käyttämät tietorakenteet on korvattu omatekoisilla versioilla.

## Ajankäyttö

Projektiin käytetty aika: 6 tuntia.
