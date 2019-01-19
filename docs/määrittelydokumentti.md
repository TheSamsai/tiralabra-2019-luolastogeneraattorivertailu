# Määrittelydokumentti

## Projektin kuvaus

Projektin tavoite on toteuttaa ja vertailla alustavasti kahta erilaista tapaa luoda karttoja tai 
ns. "luolia" roguelike-tyylistä peliä varten. Toteutettavat algoritmit ovat BSP:tä (binary space partitioning) käyttävä generaattori
ja soluautomaatin avulla toimiva luonnollisemman oloisia luolia luova generaattori.

Tarkoituksena on verrata näiden algoritmien ajallista tehokkuutta ja generoitujen karttojen visuaalista
ulkonäköä, ja tämän pohjalta arvioida algoritmien soveltuvuutta roguelike-pelien kehittämiseen.

## Projektin tietorakenteet ja algoritmit

### BSP-pohjainen generaattori

[Lähde](http://www.roguebasin.com/index.php?title=Basic_BSP_Dungeon_generation)

Binääripuihin perustuva generaattori on suhteellisen tehokas tapa luoda melko kiinnostavia "luolastoja" jotka koostuvat
huonemaisista, suorakulmaisista kammioista. Luolastot eivät kuitenkaan ole kovin luonnollisen oloisia.

#### Binääripuu

BSP-karttageneraattori pohjautuu vahvasti binääripuun toimintaan, joten sen pohjalle on toteutettava binääripuu
tietorakenteena. Binääripuulle on myös toteuttava lisäysoperaatio, jolla puuhun voidaan lisätä uusia lehtisolmuja. Puuta
on voitava myös käydä läpi [syvyyshaulla (DFS)](https://en.wikipedia.org/wiki/Depth-first_search), jotta luolaston osa-alueet 
voidaan generoida ja liittää toisiinsa rekursiivisesti.

### Soluautomaattiin perustuva generaattori

[Lähde](http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels)

Soluautomaattiin perustuva ratkaisu pyrkii matkimaan luonnollisia luolastoja simuloimalla luolaston ruutuja soluina. Algoritmina
se on kuitenkin melkein BSP-algoritmin vastakohta, sillä se on melko hidas, mutta sen tulokset ovat melko luonnollisia.

#### Leveyssuuntainen haku

Koska soluautomaatilla generoitu luolasto ei välttämättä ole yhtenäinen, on yhtenäisyyden tarkistamiseksi syytä toteuttaa
kartan ruutujen tasolla toimiva [leveyshaku (BFS)](https://en.wikipedia.org/wiki/Breadth-first_search).

### Sekalaiset aputietorakenteet ja algoritmit

#### Joukko ([Set](https://en.wikipedia.org/wiki/Set_(abstract_data_type)#Implementations))

Erityisesti BFS- ja DFS-hakujen käytössä tulee kyetä selvittämään onko jossain solmussa jo käyty. Hajautettu joukko (HashSet),
tarjoaa tähän nopean ratkaisun, jonka lisäysoperaatio ja hakuoperaatio ovat keskimäärin O(1) operaatioita. Joukko-opillisia
operaatioita, kuten unionia ja erotusta ei näiden algoritmien kohdalla kuitenkaan tarvita.

#### Pseudosatunnaislukugeneraattori

Jotta generaattorit eivät aina loisi tismalleen samanlaisia karttoja, tarvitaan algoritmeille myös satunnaislukugeneraattori,
joka voidaan alustaa jollain arvolla, ja tämän jälkeen generaattori tuottaa satunnaisen kaltaisia uusia lukuja. Koska algoritmien
ei tarvitse olla kryptografisesti satunnaisia, riittää yksinkertainen satunnaislukugeneraattori, joka palauttaa arvon annetulta
väliltä jakojäännöksen avulla ja päivittää sisäisen tilansa generoidun arvon perusteella. Näin generaattori voidaan alustaa
ajassa O(1) ja se myös luo uuden arvon vakioajassa.

## Ohjelman syötteet

Ohjelma saa syötteenään vain jonkinlaisen alustusarvon satunnaislukugeneraattorille. Tähän tarkoitukseen kelpaa käyttöjärjestelmän
kellonaika. Syötteen perusteella voidaan sen jälkeen generoida halutut luolastot.


