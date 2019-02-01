# Viikkoraportti 3

## Projektin eteneminen

Tällä viikolla parantelin BSP-generaattorin tuloksia lisäämällä kartan partitiointiin heurestiikkaa, joka
vähentää pylväsmäisten huoneiden syntymistä. Käytännössä tämä toteutettiin lisäämällä generateTree() metodille
double-tyyppinen parametri "balance", jota lisätään tai vähennetään sitä mukaa tehdäänkö pystysuora leikkaus
vai poikittainen leikkaus. Tämä vähentää sitä todennäköisyyttä, että alue partitioidaan samalla tavalla monta
kertaa peräkkäin.

Lisäksi toteutin käytävien piirtämisen huoneiden välille. Tämä algoritmi etsii kartan eri aliosien väliset
huoneet ja piirtää niiden välille suurin piirtein L:n mallisen tunnelin. Koska huoneet sijaitsevat puun
lehtisolmuissa, lisäsin myös uuden findRoom() metodin, joka hakee satunnaisesti jonkin huoneen, joka sijaitsee
kyseisen alipuun jossain lehdessä.

Koska oli myös syytä tarkistaa, että kaikki huoneet ovat saavutettavissa, toteutin myös BFS-traversal algoritmin,
jonka avulla karttoja on helppo testata ruutujen saavuttavuuden osalta.

Lisäksi tein vähän alustavaa tietorakennetyötä ja suunnittelin yksinkertaisen Pair-luokan jolla X,Y-koordinaatteja
on helpompi syöttää ja palauttaa funktioista. Jostain syystä Java ei tätä yksinkertaista tietorakennetta itse
sisällä.

## Mitä opin

Opin, että joskus luottaminen pelkkään satunnaislukugeneraattorin jakaumaan ei sovellu omiin tarkoituksiin
läheskään yhtä hyvin, kuin toivoisi. Kuitenkin yksinkertaisesti vaikuttamalla dynaamisesti todennäköisyyksiin
voi satunnaislukugeneraattorin ja helpon tasoitusmenetelmän avulla saada melko hyviä tuloksia.

## Vaikeudet viikon aikana

Vaikeudet olivat pääasiassa samoja kuin aikaisemmin. Ongelmia, joita ei ole helppo nähdä tai visualisoida ovat
hankalia debugata. Tämä ilmeni esim. BFS-algoritmia luodessa jolloin Javan VM alkoi valittaa heap-muistin
loppumisesta. Ongelma oli lopulta pieni ohjelmointivirhe, jossa solmu saattoi joutua BFS:n to-do listalle
monta kertaa, mikä johti muistin kulumiseen loppuun.

## Mitä teen seuraavaksi

Koska BSP-generaattori on käytännössä valmis ja toimiva, seuraava tavoitteeni on luoda soluautomaattiin perustuvan
generaattorin perusteet ja aloittaa omien tietorakenteiden luominen. Ainakin BFS-algoritmin jonon korvaaminen
omalla tietorakenteella on omasta mielestäni mahdollista seuraavan viikon aikana. Pyrin myös korvaamaan ohjelman
ArrayListit omalla toteutuksella seuraavan viikon aikana. Näille kaikille on myös syytä toteuttaa järkevät
ja riittävän kattavat yksikkötestit

## Ajankäyttö

Projektiin käytetty aika: 5 tuntia.
