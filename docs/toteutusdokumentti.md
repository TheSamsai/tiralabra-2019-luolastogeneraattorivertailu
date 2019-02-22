# Toteutusdokumentti

## Ohjelman rakenne

Ohjelma koostuu 6 erillisestä moduulista, jotka on jaettu niiden tehtävän perusteella.
Kyseiset moduulit voidaan jakaa sovelluslogiikkaan, sovelluskohtaisiin tietorakenteisiin,
ja yleiskäyttöisiin tietorakenteisiin.

### Sovelluslogiikka

Sovelluksen pääohjelma sijaitsee "app" moduulissa, ja sen tehtävänä on toteuttaa suoritusaikana
eri generaattorien suorituskykytestaus ja esitellä generaattorien tuottamat luolastot.

Keskeiseen sovelluslogiikkaan kuuluvat myös BSP-generaattori (moduulissa "bspgenerator") ja 
soluautomaattigeneraattori (moduulissa "cellautomatagenerator"). Lisäksi BSP-generaattorin kanssa
samassa moduulissa on generaattorin oma binääripuutoteutus.

### Sovelluskohtaiset tietorakenteet

Sovelluksen oman tiedon tallentamiseen ja prosessointiin tarkoitetut luokat sijaitsevat moduulissa "domain".
Näitä ovat esimerkiksi Dungeon.java, joka enkapsuloi kartan hallitsemiseen tarvittavat tiedot ja metodit.
Lisäksi moduulissa on luokka Room.java, jonka avulla kartalle voidaan hahmotella suorakulmiomaisia huoneita.
Moduuli sisältää myös BFS-toteutuksen Dungeon-olioiden läpikäymiseen luokassa DungeonBFS.java.

### Yleiskäyttöiset tietorakenteet

Yleiskäyttöiset tietorakenteet ovat tietorakennetoteutuksia, jotka eivät ole suoraan sidonnaisia
sovelluksen tietotyyppeihin ja malleihin, vaan ne on luotu niin, että niitä voitaisiin tarvittaessa
käyttää muissakin sovelluksissa. Tietorakenteet on sijoitettu moduuliin "util".

Näitä tietorakenteita ovat dynaaminen taulukkototeutus ArrayList.java ja linkitettyyn listaan perustuva
jonototeutus tiedostoissa Queue.java ja QueueNode.java. Lisäksi aputietorakenteet sisältävät luokan Pair.java,
joka on yksinkertaisesti sitoo kaksi arvoa toisiinsa.

Näiden lisäksi sovelluksen yhteydessä on toteutettu pseudosatunnaislukugeneraattori, joka perustuu LCG-algoritmiin.

## Tietorakenteiden aika- ja tilavaativuudet

### Jono

#### Lisäys, O(1)

Jono on toteutettu linkitettynä listana, jossa on viittaus ensimmäiseen ja viimeiseen listan solmuun.
Tästä syystä lisäysoperaatio on vakioaikainen.

Pseudokoodi:
```python
    def enqueue(A):
        node = Node(A)

        if jono-on-tyhjä:
            first = node
            last = node
        else:
            last.next = node
            last = node

        size++
```

#### Poisto, O(1)

Koska jono sisältää viittauksen jonon ensimmäiseen elementtiin, tarvitsee vain päivittää jonon ensimmäinen
elementti ja jono pysyy yhtenäisenä.

Pseudokoodi:
```python
    def dequeue():
        node = first
        
        first = first.next
        size--;

        retuun node.data
```

#### Läpikäynti (haku), O(n)

Jos täytyy tutkia, onko jokin elementti listalla, täytyy pahimmassa tapauksessa käydä läpi koko jono, mikäli elementti on jonon päässä.

### ArrayList

#### Lisäys, O(n)*

ArrayList tukee lisäyksiä listan loppuun. Toteutus ei salli elementtien lisäämistä listan alkuun tai keskelle.
ArrayList pitää sisäisesti luvut tallessa staattisessa taulukossa, mutta mikäli taulukko on liian lyhyt,
joudutaan taulukko korvaamaan uudella, mikä tarkoittaa kaikkien taulukon elementtien kopioimista.

* Koska taulukon kokoa kasvatetaan aina ylivuodon tapahtuessa kaksinkertaiseksi, lisäys on keskimäärin O(1) operaatio.

Pseudokoodi:
```python
    def insert(A):
        if array-is-full:
            new_array = A[array.size * 2]
            copy_from_to(array, new_array) // O(n)
            new_array[size] = A
            capacity = array.size * 2
        else:
            array[size] = A
            size++;
```

#### Poisto, O(n)

Jos listalta poistetaan elementti jostain indeksistä, kaikki elementit sen jälkeen joudutaan siirtämään taaksepäin.

#### Indeksointi, O(1)

ArrayList indeksoi lukuja ja kirjoituksia sisäisen taulukon perusteella, joten jonkin yksittäisen indeksin luku- tai kirjoitus
on O(1) operaatio.

#### Läpikäynti, O(n)

### HashSet (hajautustaulu)

#### Tilavaativuus, O(n)

HashSet alustetaan 1000:n alkion kokoisella taululla, jonka jälkeen taulun täyttösuhde pidetään aina alle 75% kasvattamalla
taulukon kokoa nelinkertaiseksi aikaisemmasta. Näin ollen taulukko on aina vähintään 25% suurempi, kuin talletettujen
alkioiden määrä.

#### Lisäys, O(1) keskimäärin, O(log n) pahin tapaus

Mikäli hajautustaulussa ei tapahdu yhteentörmäyksiä, lisäys on O(1) operaatio. Yhteentörmäysten käsittely on toteutettu
avoimella hajautuksella neliöisellä kokeilulla, joten pahimmassa tapauksessa algoritmi joutuu tekemään log n hyppyä
ennen kuin se löytää vapaan indeksin.

#### Haku, O(1) keskimäärin, O(log n) pahin tapaus

Haussa ajetaan sama algoritmi kuin lisäyksessä. Mikäli yhteentörmäyksiä ei tapahdu, haettu arvo on samassa indeksissä
kuin laskettu hajautusarvo, jolloin operaation aikavaativuus on O(1).

#### Taulukon uudelleenhajautus, O(n)

Jos taulukon täyttösuhde kasvaa yli 75%, tietorakenteen käyttämä taulukko korvaataan neljä kertaa suuremmalla taulukolla, ja
jokainen alkuperäisen taulukon alkio hajautetaan uudelleen.

## BSP-generaattorin aikavaativuus

BSP-generaattori koostuu kolmesta eri vaiheesta.

### Puurakenteen generointi, O(n), missä n: rekursioiden lukumäärä

Puurakenteen generoinnin pseudokoodi on seuraava:

```python
    def generate_tree(tree, n):
        if (n == 0):
            return
        else:
            tree.partition()
            generate_tree(tree.left, n - 1)
            generate_tree(tree.right, n - 1)
```

Algoritmille annettu rekursioiden määrä määrittää, kuinka syvälle algoritmia ajetaan, ja kuinka
monimutkainen huoneiden ja käytävien verkko halutaan luoda.

### Huoneiden luominen, O(n), missä n: binääripuun lehtisolmujen määrä

Kun puurakenne kartasta on saatu valmiiksi, sen perusteella luodaan puun lehtisolmujen avulla "luolaston" huoneet.
Pseudokoodi algoritmille on seuraava:

```python
    def carve_rooms(tree):
        if is-leaf(tree):
            draw_room(tree.room)
        else:
            carve_rooms(tree.left)
            carve_rooms(tree.right)

    def draw_room(room): // O(n * k)
        for y = room.y to room.y + room.height:
            for x = room.x to room.x + room.width:
                create_floor(x, y)
```

Algoritmi on käytännössä vain syvyyshaku binääripuulle, ja jokaista lehtisolmua kohti ajetaan O(w * h)
aikavaativuuden piirtofunktio. Koska huoneille on ennalta määritelty keskimääräinen koko, on huoneiden
lukumäärä kriittisin tekijä algoritmin aikavaativuudessa.

### Käytävien luominen, O(n), missä n: binääripuun lehtisolmujen lukumäärä

Lopuksi generaattori luo jokaisen luolaston alipuun välille käytävät, siten että kaikista huoneista on pääsy kaikkiin muihin.

Pseudokoodi:

```python
    def carve_tunnels(tree):
        if is-leaf(tree):
            return
        else:
            carve_tunnels(tree.left)
            carve_tunnels(tree.right)

            room1 = find_room(tree.left)
            room2 = find_room(tree.right)

            draw_tunnel(room1, room2)

    def find_room(tree):
        if is-leaf(tree):
            return tree;
        else:
            return find_room(tree.left OR tree.right) // tarvitaan huone vain toiselta puolelta

    def draw_tunnel(room1, room2):
        x,y = room1.center

        for x = x to room2.center.x:
            draw_floor(x, y)

        for y = y to room2.center.y:
            draw_floor(x, y)
```

### Kokonaisuus, O(n)

Kaikkiaan BSP-algoritmin aikavaativuus on O(n), missä n on binääripuun solmujen lukumäärä.
Tilavaativuus samoin on verrannollinen puurakenteen syvyyteen algoritmin funktioiden rekursiivisuuden takia.

## Soluautomaattigeneraattorin aikavaativuus

Soluautomaatti koostuu myös kolmesta erillisestä vaiheesta.

### Alustus, O(n * k), missä n on luolaston leveys ja k luolaston korkeus

Soluautomaatin luolasto alustetaan satunnaisilla arvoilla, joten jokaiselle luolaston ruudulle on
määriteltävä yksitellen onko se seinää vai lattiaa.

Pseudokoodi:

```python
    def init():
        for y = 0 to height:
            for x = 0 to width:
                tile(x, y) = Wall OR Floor
```

### Simulointi, O(n * k), missä n on luolaston leveys ja k luolaston korkeus

Luolaston eri ruutuja käsitellään seuraavaksi soluautomaattina ja jokaisessa simulaation vaiheessa jokaisen
ruudun kohdalla päätetään muiden ruutujen perusteella, onko ruutu seinää vai lattiaa. Simulaatiota ajetaan vain
neljä kertaa, joten simulaatioiden lukumäärä ei vaikuta algoritmin aikavaativuuteen.

Pseudokoodi:

```python
    def simulate():
        for y = 0 to height:
            for x = 0 to width:
                if is-wall(x, y):
                    if wall-neighbours(x, y) < 4:
                        tile(x, y) = floor
                else:
                    if wall-neighbours(x, y) >= 5 or walls-nearby <= 2:
                        tile(x, y) = wall
```

### Luolaston segmenttien yhdistäminen, O(n * w * h), missä n: segmenttien lukumäärä, w: leveys, h: korkeus

Koska algoritmi voi tuottaa luolastoja, jotka koostuvat erillisistä osista, tulee kaikki luolaston eri osat
yhdistää käytävillä, jotta voidaan taata, että kaikista luolaston osista on pääsy muihin osiin. Tämä toteutetaan
etsimällä BFS-haulla kaikki luolaston segmentit, ja tämän jälkeen segmentit yhdistetään toisiinsa yksi kerrallaan.

Pseudokoodi:

```python
    def connect-segments():
        segments = []

        for y = 0 to height:
            for x = 0 to width:
                for segment in segments:
                    if (x,y) not in segment:
                        new_segment = BFS(x, y) // O(v) operaatio
                        segments.add(new_segment)
        
        previous = segments.get(0)

        for segment in segments:
            carve-hallway(segment.get-first(), previous.get-first())
            previous = segment
```

Tämä algoritmin osa on todettu suorituskykytestauksessa algoritmin kaikista aikaavievimmäksi osaksi. Segmenttien
yhdistämiselle on olemassa tehokkaampia vaihtoehtoisia menetelmiä, kuten irrallisten segmenttien täyttö tai
simulaation alustamisen muuttaminen sellaiseksi, että tietylle alueelle on taattu, luolan yhdistävä käytävä.
Tein kuitenkin sen päätöksen, että halusin ennemmin isompia, satunnaisia luolastoja, kuin pienempiä,
vähemmän satunnaisia luolastoja. Luolastot voisi myös generoida ilman segmenttien yhdistämistä, jolloin
luolasto eri olisi yhtenäinen.

### Kokonaisuus, O(n * w * h)

Soluautomaatin suorituskyky perustuu suurelta osin segmenttien yhdistelyn suorituskykyyn, sillä tämä vaihe on
algoritmin kaikista intensiivisin. Tämän voi myös todeta suorituskykytestien perusteella, jotka osoittavat,
että yli 50% algoritmin suoritusajasta kuluu segmenttien yhdistämiseen. 
