# Küsimuste mäng: JavaFX edition

Antud repo sisaldab objektorienteeritud programmeerimise 2. rühmatöö faile.

https://courses.cs.ut.ee/2024/OOP/spring/Main/Ruhm2 - Read Me jääb enamasti samaks.

## Autorid

- Sten-Egert Märtson
- Mirko Martjak

### Panused, ajakulu

Märkus: 2. rühmatöösse ei ole lisatud 1. rühmatöö tunde. 

- Sten-Egert Märtson (ajakulu: ~10h)
  - Kõik eelnev 1.rühmatööst
  - Klasside loomine
  - JavaFX disain
  - Punktide edetabel 
  - 
- Mirko Martjak (ajakulu: ~...h)
  - Kõik eelnev 1.rühmatööst
  - Readme fail ja selle vormistuse kontroll
  - Mängu bugide otsimine
  - JavaFX bugi parandamine

### Mured

- Sten-Egert Märtson
  - Algul tahtsin koostada küsimuste küsimimise meetodi for-tsükkliga, kuid tuli välja, et see vist ei ole võimalik
    - For-tsükkli kasutamiseks oleks pidanud tsükklis rakendama mingi vastuse ootamise funktsiooni, et for-tsükkel koheselt ei läheks lõppu ning lõpetaks tsükkli
    - Lahenduseks oli klassiväljade kasutamine, et jälgida nt mitmenda küsimuse juures on kasutaja, mitu küsimust kasutaja on õigesti vastanud, mis küsimustele valesti vastanud jne
  - Edetabelisse tulemuste kirjutamisel pidin looma EdetabelTulemus klassi
    - Algul tahtsin kasutada `Map<String, Integer>` (String: kasutaja nimi, Integer: kasutaja punktiskoor), et salvestada eelnevad tulemused ja uue kasutaja tulemuse, kuid raskus seisneb sorteerimises: tahtsin, et see map oleks sorteeritud punktiskooride poolest, mis on tegelikult üllatavalt raske. Seega otsustasin lihtsalt luua uue klassi, kus kasutasin `implements Comparable`, et lihtsalt sorteerida alates suuremast tulemusest
 
- Mirko Martjak

## Projekti kirjeldus

### Programmi eesmärk (Muuta vajadusel või lisada)

- Programmi kaustas on valmis tehtud küsimused igale teemale. Neid saab omal soovil muuta.
- Küsimuste liike on kokku kaks: valikvastustega ja avatud küsimused.
- Programm algab mängu lühikese tutvustusega, mida kasutaja võib ka vahele jätta.
- Kasutaja saab mängule öelda, et kas soovib valikvastusega küsimusi või mitte.
- Kasutaja saab mängule öelda, mitu teemat ta soovib võtta ja millised need oleks.
- Pärast teemade valimist mäng algab.
- Mäng toimub kiiruse peale: kui vastad 15 sekundi jooksul, siis saad rohkem punkte.
- Punktid sõltuvad õige vastuse ning sisestamis kiirusest.
- Kui mäng ära lõpeb, siis tagastakse punkti summa ja küsitakse, et kas soovid üle vaadata küsimused, mis läksid valesti.

### Protsess (Muuta vajadusel või lisada)

- Otsustasime projekti jätkata (mõlemad)
	

### Klassid (Muuta vajadusel või lisada)

- AvatudKüsimus: peaklassis loeme teema küsimuste failist saadud sisu, milles read muudame antud klassi isendteks, mis sisaldavad küsimust ja vastust.
- ValikvastustegaKüsimused: peaklassis loeme failist saadud sisu, milles read muudame antud klassi isendteks, mis sisaldavad küsimust, õiget vastust ja valikvastuseid.
- KüsimusteTeema: Klass, mille kaudu teeme isendid ning hoiustame erinevate teemade küsimusi: nii avatud kui ka valikutega küsimuste hoidmine eraldi massiividena
- EdetabeliTulemus: Lihtsustab edetabelis tulemuste sorteerimist ning üldist käideldamist
- Peaklass: Klass, milles mäng käivitatakse

### Meetodid (Muuta vajadusel või lisada)

#### Peaklass

- `alustaKüsimist`: Muudab pealava stseeni, et kuvataks küsimusi ning pakutaks võimalust neile vastata.
- `alustaMäng`: Tegelikult on see mängu algse seadistuse määramiseks. Muudab pealava nii, et kasutaja saaks valida, milliste teemade küsimusi kuvatakse, millist sorti küsimusi (valik või avatud küsimused) küsitakse ja mitu küsimust esitatakse.
- `leiaTeemaFail`: Tagastab teemale vastava küsimuste .txt faili
- `kirjutaEdetabetabeliFaili`: Võtab argumendiks kasutaja nime ning lisab tema edetabeli faili (`edetabel.txt`). Punktisumma saab klassimuutujast.
- `koostaKüsimuseStseen`: Koostab vastavalt parameetritele sobiva küsimuse stseeni. Paneb paika valikvastused loetaval moel.
- `kuvaTulemused`: Kuvab pealavale mängu lõppemisel tulemuste stseeni, kust saab uut mängu alustada, mängu sulgeda või edetabelit kuvada.
- `loeFail`: Meetod, mis loeb ära txt faili sisu ning paneb sisu põhjal küsimused massiivi.
- `lisatekstVBoxi`: Abiks mängu kirjelduse kuvamisel. Muudab sõne tükkideks ning lisab sõne tükid eraldi ridadele.
- `loeEdetabelFail`: Loeb edetabelist kõik tulemused ning tagastab need listis EdetabeliTulemus isenditena.
- `oota3Sekundit`: Kuvab pealavale ooteekraani soovitud tekstiga, mis kestab 3 sekundit.

- `alustaMänguNupp`: Vajutusel käitab `alustaMäng` meetodi.
- `kuvaEdetabel`: Vajutusel kuvab uue akna, kus on kuvatud edetabel.
- `suleMäng`: Paneb mängu kõik aknad kinni.

#### AvatudKüsimus

- Peamine küsimuste klass, mille põhjal nt tegime küsimuste massiivid
- Isendiväljad küsimuse ja vastuse jaoks
- Meetodid isendiväljadele juurde pääsemiseks

#### ValikvastusegaKüsimus

- Alamklass, mis põhineb AvatudKüsimus klassil
- Arvestab valikvastuste olemasoluga (uus isendiväli)

#### KüsimusteTeema

- tagastaSuvalineAvatudKüsimus meetod: tagastab suvalise avatud küsimuse ning eemaldab selle avatud küsimuste massiivist
- tagastaSuvalineValikKüsimus: tagastab suvalise valikvastustega küsimuse ning eemaldab selle valikvastusega küsimuste massiivist

### Testimine (Muuta vajadusel või lisada)

- Otsisime mängu buge, mida võib JavaFX tekidada.
- Proovisime mängu loomulikult mängida.

## Hinnang (Lisada)

### Läks Hästi:

- Kood töötab (vist)
### Vajab arendamist:

### Kokkuvõtte:

