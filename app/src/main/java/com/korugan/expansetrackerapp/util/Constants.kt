package com.korugan.expansetrackerapp.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

object Constants {
    const val CRYPTO_BASE_URL = "https://api.coinpaprika.com/v1/"
    const val BASE_STOCK_URL = "https://finnhub.io/api/v1/"
    const val STOCK_SYMBOL_GRAPH_URL = "https://financialmodelingprep.com/api/v3/"
    const val STOCK_API_KEY = ""
    const val STOCK_SYMBOL_GRAPH_API_KEY = ""

    const val DISCLAIMER_TEXT = """
    Bu sitede yer alan tüm içerikler yalnızca bilgilendirme amacıyla sunulmuştur. 
    
    Sunulan bilgiler, profesyonel tavsiye niteliği taşımaz.
    
    Herhangi bir karar almadan önce alanında uzman kişilerle görüşmeniz tavsiye edilir.
    
    İçeriklerin doğruluğu ve güncelliği konusunda azami özen gösterilse de oluşabilecek hatalardan dolayı sorumluluk kabul edilmez. 
    
    Kullanıcılar, bu sitedeki bilgileri kendi sorumluluklarında kullanmayı kabul eder.
    """

    const val SSS_TEXT = """
     Sıkça Sorulan Sorular (SSS) 
        
    1. Bu platform ne işe yarar?

    "Platformumuz, kullanıcıların ihtiyaç duyduğu bilgilere hızlı ve güvenilir bir şekilde ulaşmasını sağlamak amacıyla oluşturulmuştur. Hizmetlerimiz; içerik sağlama, rehberlik etme ve kullanıcı dostu çözümler sunma üzerine kuruludur 
             
    "2. Üye olmak zorunlu mu?

    "Hayır. Çoğu içeriğe üye olmadan erişebilirsiniz. Ancak bazı özellikleri kullanabilmeniz için ücretsiz bir üyelik oluşturmanız gerekebilir
         
    "3. Üyelik ücreti var mı?

    "Hayır. Üyelik tamamen ücretsizdir ve dilediğiniz zaman hesabınızı kapatma hakkına sahipsiniz.
             
    "4. Kişisel verilerim güvende mi?

    "Evet. Gizliliğinize büyük önem veriyoruz. Verileriniz yalnızca hizmet kalitesini artırmak amacıyla kullanılır ve üçüncü şahıslarla paylaşılmaz 
           
    "5. Destek ekibine nasıl ulaşabilirim?

    "Destek ekibimize destek@example.com adresinden ulaşabilir veya iletişim sayfamızdaki formu doldurabilirsiniz.
            
    "6. İçerikler ne sıklıkla güncelleniyor?

    "İçeriklerimiz düzenli olarak güncellenir. Geri bildirimleriniz doğrultusunda da yeni bilgiler eklenir veya mevcut içerikler revize edilir.
            
    "7. Hatalı veya eksik içerikle karşılaşırsam ne yapmalıyım?" 

    "Bize ulaşarak durumu bildirmeniz yeterli. En kısa sürede gerekli düzeltmeleri yaparız"""
    const val ABOUT_TEXT = """
    HESAPKİTAPP HAKKINDA

    HesapKitapp, kişisel gelir ve giderlerinizi kolayca takip etmenizi sağlayan, sade ve anlaşılır bir finans yönetimi uygulamasıdır.
    
    Amacımız, kullanıcıların günlük harcamalarını ve gelirlerini kaydederek bütçelerini daha verimli bir şekilde yönetmelerine yardımcı olmaktır.
    
    HesapKitapp ile finansal alışkanlıklarınızı analiz edebilir, gereksiz harcamaları tespit edebilir ve tasarruf etmeye başlayabilirsiniz.
    
    Basit arayüzü ve kullanıcı dostu tasarımı sayesinde herkesin rahatlıkla kullanabileceği pratik bir çözümdür.
"""
    const val PRIVACY_TEXT = """
    KULLANICI SÖZLEŞMESİ

    1. Taraflar
    Bu kullanıcı sözleşmesi, HesapKitapp ile kullanıcı arasında akdedilmiştir. Kullanıcı, HesapKitapp hizmetlerinden yararlanarak bu sözleşme koşullarını kabul etmiş sayılır.

    2. Hizmet Tanımı
    HesapKitapp, kullanıcılara finansal kayıt tutma, gelir-gider analizi ve temel bütçe yönetimi gibi hizmetler sunar.

    3. Kullanım Koşulları
    - Kullanıcı, HesapKitapp’i yalnızca yasal amaçlarla kullanacağını kabul eder.
    - Hizmetin güvenliğini tehlikeye atacak herhangi bir girişimde bulunamaz.
    - Kullanıcı, HesapKitapp'e sağladığı bilgilerin doğru ve güncel olduğunu taahhüt eder.

    4. Sorumluluk Reddi
    HesapKitapp, sunulan bilgilerin eksiksiz ve hatasız olduğunu garanti etmez. Uygulama üzerinden yapılan tüm finansal değerlendirmeler kullanıcı sorumluluğundadır.

    5. Fikri Mülkiyet
    HesapKitapp’e ait tüm içerik, tasarım ve yazılımlar fikri mülkiyet hakları kapsamında korunmaktadır. İzinsiz kopyalanamaz, dağıtılamaz.

    6. Sözleşme Değişiklikleri
    HesapKitapp, bu sözleşmeyi dilediği zaman güncelleyebilir. Güncel metin, kullanıcıya uygulama veya site üzerinden bildirilir.""" + """
    GİZLİLİK POLİTİKASI

    1. Kapsam
    Bu gizlilik politikası, HesapKitapp kullanıcılarının verilerinin nasıl toplandığını, işlendiğini ve korunduğunu açıklar.

    2. Toplanan Veriler
    - Ad, e-posta adresi, işlem geçmişi gibi kullanıcı tarafından sağlanan bilgiler
    - Uygulama kullanımı sırasında oluşan teknik veriler (cihaz bilgisi, kullanım süresi vb.)

    3. Verilerin Kullanım Amaçları
    - Hizmet kalitesini artırmak
    - Kullanıcı deneyimini geliştirmek
    - Yasal yükümlülükleri yerine getirmek

    4. Veri Paylaşımı
    HesapKitapp, kullanıcı verilerini üçüncü şahıslarla izinsiz paylaşmaz. Yalnızca yasal zorunluluklar halinde resmi mercilerle paylaşılabilir.

    5. Veri Güvenliği
    Kullanıcı verileri, güvenli sunucularda saklanmakta ve kötüye kullanıma karşı koruma altındadır.

    6. Haklarınız
    Kullanıcılar, kendileriyle ilgili verilere erişme, düzeltme veya silme hakkına sahiptir. Bu talepler için destek birimine ulaşabilirsiniz.
    """

    @Composable
    fun screenHeight(): Int = LocalConfiguration.current.screenHeightDp

    @Composable
    fun screenWidth(): Int = LocalConfiguration.current.screenWidthDp
}