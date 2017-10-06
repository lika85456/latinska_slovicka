# latinska_slovicka

někde ve složce res/raw
funguje to na principu databázový tabulky
category.txt = soubor pro ukládání jmen kategorií a jejich slovíček
{ID_KATEGORIE} {JMÉNO_KATEGORIE}|{POLE SLOVÍČEK podle mýho systému}
0 Zvířátka|0-37,38,40,50,80-120

slovicka.txt = soubor pro ukládání slovíček a id pro nastavení obrázku
{ID_SLOVICKA} {CZ}|{LA}
0 pes|canis
1 had|hadvlatině

soubory pro obrázky slovíček
a{ID_SLOVICKA}.png - nejlépe velikost tak 300x300, nesmí být větší než 1400x1400
a0.png = obrázek se psem
a1.png = obrázek s hadem

soubory pro nadhledy kategorií
s{ID_KATEGORIE}.png - pravidla o velikosti stejná!
