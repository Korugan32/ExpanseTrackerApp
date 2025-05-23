## HesapKitapp
HesapKitapp, Android cihazlar için geliştirilmiş modern bir kişisel finans yönetimi uygulamasıdır. Kullanıcılar bu uygulama ile günlük gelir ve giderlerini farklı kategoriler altında kaydedebilir, bütçelerini belirleyebilir ve finansal durumlarını grafiklerle görselleştirebilir. Amacı, teknik bilgiye sahip olmayan kullanıcılar için bile finansal takibi kolaylaştırmak ve tasarruf hedeflerine ulaşmayı desteklemektir. Jetpack Compose kullanılarak tasarlanan sade ve sezgisel arayüzü sayesinde, kullanıcılar kolayca veri girişi yapabilir ve finansal durumlarını grafiklerle takip edebilir.
## Teknolojiler
* Jetpack Compose
* MVVM
* Dagger-Hilt
* Retrofit
* OkHTTP
* Room
* WorkManager
* DataStore
* Accompanist
* Navigation
* Clean Architecture
* Compose Chart(https://github.com/ehsannarmani/ComposeCharts)
## Özellikler
* Harcamaları Ekleme ve Takip
* Abonelik Sistemi(Kira,Maaş vs.)
* Grafiksel İstatistikler
* Amerikan Borsasını Ekleme ve Takip
* Cripto Piyasası Ekleme ve Takip
* Hedef Belirleme Ve Yönetme
* Borsa Haberlerini Görüntüleme
* Onboarding Sayfaları ile Uygulamayı Tanıtma
* Cripto ve Amerikan Kağıtlarını Grafikle Görüntüleme
## Uygulama Görüntüleri
<img src="https://github.com/user-attachments/assets/6ac3770e-c582-4466-8321-778a0d7edfb5" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/51cb1a44-b078-4372-acee-757ec402647f" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/8d68c15a-f2be-498b-9e37-4faaaaf52d9b" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/a2acca03-fd35-4382-a91c-bef1c7c339bc" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/d92c6643-b4bb-4ea9-ae99-b394c015ff3c" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/d9ba7a20-9f4d-4d95-9586-43151449fc0e" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/b746d999-4fa5-4f97-96a1-c816af490f63" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/4cd04dea-a629-4469-af77-acc9a4ec42e9" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/92808476-bd8a-458a-a476-abd747e24af3" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/b95c8f62-23b2-49f3-ab1f-4a04a2f793bc" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/e1e74073-7281-4173-b57c-58019faa0284" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/ed66d947-c2cd-4bcd-90e3-f60b2d2377fb" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/b5670d78-865a-42a7-8161-f66c33b65590" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/f3f625e1-a69b-4c3d-872e-93e1fa26c8f5" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/15ee0275-5a23-43a9-8f92-5de3ffc333a5" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/c1ef5d9b-9be7-44b0-bced-60f604265ec4" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/0f976682-9dc1-4fc3-9534-82a633a96a93" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/913e81c5-b20a-4dce-8201-17da68b15dad" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/09f5fb16-c6a4-4422-99f4-a6f5f0bc1765" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/603e8fb6-e64a-4c06-be82-2c643f1f947f" width="250" height="500"/>
<img src="https://github.com/user-attachments/assets/a3419673-b582-458c-b056-2d4b4854b514" width="250" height="500"/>

## Api 

Aşağıdan alınan api keyler proje içerisinde bulunan Constant içindeki 

STOCK_API_KEY 

STOCK_SYMBOL_GRAPH_API_KEY

kısımlarına yazılmalıdır.

``` kotlin
object Constants {
    const val CRYPTO_BASE_URL = "https://api.coinpaprika.com/v1/"
    const val BASE_STOCK_URL = "https://finnhub.io/api/v1/"
    const val STOCK_SYMBOL_GRAPH_URL = "https://financialmodelingprep.com/api/v3/"
    const val STOCK_API_KEY = 
    const val STOCK_SYMBOL_GRAPH_API_KEY =
...
```

STOCK_API_KEY : https://finnhub.io/ Sitesinden alınabilir.

STOCK_SYMBOL_GRAPH_API_KEY : https://financialmodelingprep.com Sitesinden alınabilir.

## Katkıda Bulunma
Projeye katkı sağlamak isterseniz aşağıdaki adımları izleyebilirsiniz:

    Repositori'yi fork'layın ve kendi kopyanızı oluşturun.

    Yeni bir branch açın (feature/yeni-ozellik veya bugfix/sorun-adi gibi).

    Geliştirmelerinizi yapın ve temiz commit mesajları ile kaydedin.

    Değişikliklerinizi kendi fork'unuza push edin.

    Bu repository'e bir Pull Request (PR) gönderin; yaptığınız değişiklikleri ve eklediğiniz özellikleri açıklayın.

Katkılarınız ve geri bildirimleriniz için teşekkür ederiz!
