# latinska_slovicka

někde ve složce res/raw<br>
funguje to na principu databázový tabulky<br>
category.txt = soubor pro ukládání jmen kategorií a jejich slovíček <br>
{ID_KATEGORIE} {JMÉNO_KATEGORIE}|{POLE SLOVÍČEK podle mýho systému} <br>
0 Zvířátka|0-37,38,40,50,80-120<br>
<br>
slovicka.txt = soubor pro ukládání slovíček a id pro nastavení obrázku<br>
{ID_SLOVICKA} {CZ}|{LA}<br>
0 pes|canis<br>
1 had|hadvlatině<br>
<br>
soubory pro obrázky slovíček<br>
a{ID_SLOVICKA}.png - nejlépe velikost tak 300x300, nesmí být větší než 1400x1400<br>
a0.png = obrázek se psem<br>
a1.png = obrázek s hadem<br>
<br>
soubory pro nadhledy kategorií<br>
s{ID_KATEGORIE}.png - pravidla o velikosti stejná!<br>
