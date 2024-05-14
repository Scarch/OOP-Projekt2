# Küsimuste mäng: JavaFX edition

Antud repo sisaldab objektorienteeritud programmeerimise 2. rühmatöö faile.

https://courses.cs.ut.ee/2024/OOP/spring/Main/Ruhm2 - Read Me jääb enamasti samaks.

## Autorid

- Sten-Egert Märtson
- Mirko Martjak

### Panused, ajakulu

Märkus: 2. rühmatöösse ei ole lisatud 1. rühmatöö tunde. 

- Sten-Egert Märtson (ajakulu: ~...h)
	- Vajalikud osad 1.rühmatööst või kirjuta oma sõnadega juurde
  -
- Mirko Martjak (ajakulu: ~...h)
  - Readme fail ja selle vormistuse kontroll
  - Kõik eelnev 1.rühmatööst
	- Küsimused
	- Küsimuste vorming txt failis
 	- Readme fail ja selle vormistuse kontroll

### Mured

- Sten-Egert Märtson
 
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
- Peaklass: Klass, milles mäng käivitatakse

### Meetodid (Muuta vajadusel või lisada)

#### Peaklass
- tutvustus: Küsib mängijalt, et kas ta soovib läbi lugeda mängu juhised.
- alustaMängu: Pärast tutvustuse käivitatud, juureks muudele mängu meetoditele
- leiaTeemaFail: Tagastab teemale vastava küsimuste .txt faili
- mituKüsimust: Küsitakse mängijalt, mitu küsimust soovib ühe teema kohta mängu jooksul saada.
- kasValikvastused: Küsib enne küsimuste küsimist mängijalt, kas ta soovib valikvastuseid või mitte.
- teemaValikud: Küsitakse mängijalt, mitu teemat mängija soovib võtta ning antakse valik teemade vahel.
- loeFail: Meetod, mis loeb ära txt faili sisu ning paneb sisu põhjal küsimused massiivi.
- vastusteÜlevaade: pakub mängijale võimaluse kuvada küsimusi, millele anti vale vastuse
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

