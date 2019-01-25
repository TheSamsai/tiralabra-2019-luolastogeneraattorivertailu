# Viikkoraportti 2

## Projektin eteneminen

Tällä viikolla toteutin alustavan toteutuksen BSP-generaattorin käyttämälle binääripuulle ja
kirjoitin sen avulla yksinkertaisen, osittain toimivan luolastogeneraattorin, joka suurinpiirtein
osaa sijoittaa kartalle huoneita. Kartan tallentamista varten loin myös yksinkertaiset luokat
yksittäiselle huoneelle ja koko kartalle. Generaattori vaatii kuitenkin vielä jonkin verran hienosäätöä,
sillä sen luomat huoneet eivät ole vielä täysin järkevän näköisiä.

Lisäksi projektin testaus on aloitettu, ja Travis CI otettu käyttöön jatkuvan integroinnin toteuttamiseksi. 

## Mitä opin

Opin että rekursiivisien funktioiden kanssa voi hyvin helposti tehdä ikäviä virheitä, jotka nopeasti
johtavat ohjelman kaatumiseen. Niiden käyttö on tosin erityisesti puiden käsittelyssä hyvin käytännöllistä.

## Vaikeudet viikon aikana

Alun ongelmana oli ohjelman toiminnan tutkiminen. Koska generaattori käyttää rekursiota vahvasti,
on joskus hankala saada hyvä käsitys siitä, miten ohjelma käyttäytyy ajon aikana, ja siten debuggaus voi olla
haasteellista. Tämän vuoksi ajatuksellisesti yksinkertaisenkin algoritmin luominen oli hitaampaa kuin olin
odottanut. Lisäksi, koska ohjelma on melko graafisesti orientoitunut, on järkevien yksikkötestien keksiminen
ohjelman isommille osille hankalaa.

## Mitä teen seuraavaksi

Seuraavaksi tarkoituksenani on parannella generaattorin toimintaa siten, että sen luomat huoneet ovat hiukan
järkevämmän muotoisia ja kokoisia, joten käytännössä luolaston alueiden jakamiseen on keksittävä parempaa
heurestiikkaa. 

Tämän jälkeen pyrin luomaan ainakin alustavan tavan rakentaa luolaston huoneiden välille käytävät, jotta kartta on 
yhtenäinen ja sen jokaiseen huoneeseen on pääsy mistä tahansa muusta huoneesta. Tähän tarkoitukseen luon myös BFS 
algoritmin, jolla luolaston käytävät ja huoneet voidaan tutkia testejä varten.

Mikäli kaikki tämä valmistuu, aloitan myös soluautomaatin luomisen, jotta alustavat luolastot sen avulla voidaan
generoida.

## Ajankäyttö

Projektiin käytetty aika: 6 tuntia.
