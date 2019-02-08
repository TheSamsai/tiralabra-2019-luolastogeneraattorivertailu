# Viikkoraportti 4

## Projektin eteneminen

Tällä viikolla ohjelman ydinosat ovat käytännössä lähes valmiit ja olen tyytyväinen niiden nykyiseen
toimivuuteen, tosin mikäli aika sallii, parantelen niiden toimintaa vielä. Samalla aloitin yleiskäyttöisten
tietorakenteiden toteuttamisen ArrayList- ja Jono-toteutuksilla, jotka ovat nyt valmiit vaaditulla tasolla ja
niille on olemassa järkevät testit.

## Mitä opin

Tällä viikolla suurin oppimani asia käsitteli tyyppien käsittelyä Javassa. Tekemäni ArrayList- ja Queue-toteutukset
on koodattu olemaan tyyppiriippumattomia, mikä erityisesti ArrayListin kanssa johti siihen, että täytyi lukea
dokumentaatio aiheesta tyyppiriippumattomat taulukot. Parin yrityksen jälkeen tämä kuitenkin onnistui, ja
nykyisten toteutusten tulisi kyetä turvallisesti tallettamaan mitä tahansa Javan olioita.

## Vaikeudet viikon aikana

Isoin ongelma oli soluautomaatin sääntöjen määrittäminen siten, että tuloksena oli suhteellisen järkevän näköisiä
luolastoja. Lisäksi luolaston yhtenäisyyden määrittäminen vaati suhteellisen monimutkaisen algoritmin, eikä ollut
läheskään yhtä yksinkertaista kuin toivoin. Onneksi [RogueBasin](http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels)
tarjosi hyvät esimerkit ja tiedot, joilla toteutuksen sai valmiiksi melko nopeasti.

## Mitä teen seuraavaksi

Ohjelman ydinosat vaativat enää vain pientä hienosäätöä, mutta ovat muuten valmiit, joten seuraavan viikon
tavoitteena on tuottaa HashSet-toteutus, testata se, ja ottaa se käyttöön. Lisäksi itse vertailutyötä on dokumentoitava,
sekä parantaa muuta dokumentointia, kuten toteutus- ja testausdokumentteja. Lisäksi, mikäli vain ehdin, yritän suunnitella
graafisen ohjelman, joka piirtäisi ohjelman kartat ja esittelisi tulokset hieman paremmin.

## Ajankäyttö

Projektiin käytetty aika: 6 tuntia.
