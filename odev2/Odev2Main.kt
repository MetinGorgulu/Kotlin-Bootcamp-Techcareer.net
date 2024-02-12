package com.example.kotlinders1.nesne_tabanli_programlama.odev2

fun main() {

    val fonk = Odev2();
    
    //Soru 1
    println("--------------------------------")
    val km  = 55.0
    val mile = fonk.soru1(km)
    println("1.Soru : $mile")
    println("--------------------------------")




    //Soru 2
    val kenar1 = 8
    val kenar2 = 6
    val area = fonk.soru2(kenar1 , kenar2 )
    println("2.Soru : $area")
    println("--------------------------------")



    //Soru 3

    val faktoriyel = 5
    val sonucFaktoriyel = fonk.soru3(faktoriyel)
    if(sonucFaktoriyel != -1){
        println("3.Soru : $sonucFaktoriyel")
    }else{
        println("Negatif Sayıların Faktöriyeli Olmaz")
    }
    println("--------------------------------")

    //Soru 4
    val mesaj = "Evde oturmayı çok severim."
    val eSayisi = fonk.soru4(mesaj)
    println("4.Soru : $eSayisi")
    println("--------------------------------")

    //Soru 5
    val kenarSayisi = 3
    val toplamAci= fonk.soru5(kenarSayisi)
    println("5.Soru : $toplamAci")
    println("--------------------------------")

    //Soru 6
    val calismaSaati = 20
    val ucret = fonk.soru6(calismaSaati)
    println("6.Soru : $ucret")
    println("--------------------------------")

    //Soru 7
    val sure = 4
    val otoparkUcret = fonk.soru7(sure)
    println("7.Soru : $otoparkUcret")
}
