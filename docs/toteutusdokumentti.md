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

### Indeksointi, O(1)

ArrayList indeksoi lukuja ja kirjoituksia sisäisen taulukon perusteella, joten jonkin yksittäisen indeksin luku- tai kirjoitus
on O(1) operaatio.

### Läpikäynti, O(n)


