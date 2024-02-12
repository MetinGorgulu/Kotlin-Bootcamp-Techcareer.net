package com.example.kotlinders1.nesne_tabanli_programlama.odev2

class Odev2 {
    //Soru1
    fun soru1 ( km : Double) : Double{
        return km * 0.621
    }

    //Soru2
    fun soru2 (kenar1 :Int , kenar2:Int) : Int{
        return kenar1 * kenar2
    }

    //Soru3
    fun soru3(sayi:Int) :Int{

        if(sayi >1){
            return soru3( sayi-1) * sayi
        }else if(sayi<0){
            return -1
        }
        else{

            return 1
        }


    }

    //Soru4
    fun soru4(kelime:String) :Int{

        var eSayisi= 0;

        for( char in kelime ){
            if(char.lowercase() =="e" ){
                eSayisi++
            }
        }

        return eSayisi;
    }

    //Soru 5
    fun soru5(kenar: Int): Int {

        return (kenar - 2) * 180
    }

    //Soru 6
    fun soru6 (gunSayisi:Int) :Int {

        val calismaSaati = 8
        val calismaSaatUcret = 40
        val mesaiSaatUcret = 80

        val toplamCalismaSaati = gunSayisi * calismaSaati
        var mesaiCalismaSaati = 0
        if(toplamCalismaSaati > 150){
             mesaiCalismaSaati = toplamCalismaSaati - 150
        }

        return ((toplamCalismaSaati - mesaiCalismaSaati) * calismaSaatUcret) + (mesaiCalismaSaati * mesaiSaatUcret)
    }

    //Soru 7
    fun soru7(sure:Int) : Int{

        var ucret = 0
        val saatUcret = 50
        val saatAsimiUcretArtisi = 10

        for (i in 1..sure ){
            if(i ==1){
                ucret += saatUcret
            }else{
                ucret += saatUcret+((i-1)*10)
            }

        }

        return ucret

    }

}