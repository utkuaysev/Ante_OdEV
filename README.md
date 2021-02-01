# Ante_OdEV
Ante Grup icin verilen odev reposudur

## Odev
Anasayfada **localhost:8080** üzerinden korona grafiğine erişebilirsiniz.

Grafik üzerinde taburcu vaka ve vefat sayıları farklı renklerle gösterilmiştir.Grafiğin altında bulunan legend'e tıklayarak kümülatif ya da ayrı ayrı sayı bilgilerinin grafiğine ulaşabilirsiniz.

Grafiğin altındaki dropdown üzerinden de bütün şehirlere ya da spesifik şehire bağlı olarak grafiklere ulaşmanız mümkündür.

Navigation bar'dan Kayıt sekmesine tıklayarak yeni haber girebilirsiniz. Haberde il date vaka vefat taburcu bilgilerinin hepsi olmalıdır.Yoksa sistem format hatası döner.

Haber girilirken sayıdan sonra .(nokta) olmaması önemlidir. [<sayı> <boşluk> <nokta>] dogru formattır.
Ornek: vaka sayisi 14.vefat sayısı 12  
yerine
vaka sayisi 14 . vefat sayısı 12

## Odev run bilgileri
Projeyi çalıştırmak için proje dizininde kullanılması gereken komut:

``` java -Dfile.encoding=UTF-8 -jar coronaEntity-0.0.1-SNAPSHOT.jar ```

React
``` npm run build ```
komutu kullanılarak build edilmiş, build dosyası src/main/resources/static dizinine atılmıştır.

Veritabanı dump'u dump dosyasında olup örnek veri içermektedir.
