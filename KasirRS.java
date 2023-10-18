import java.util.Scanner;
public class KasirRS{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);  // Variabel Scaner Global

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|           Selamat Datang di Kasir           |");
        System.out.println("|            Rumah Sakit Cina Java            |");
        System.out.println("|                Silahkan Login               |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|           1. Login Sebagai Admin            |"); 
        System.out.println("|           2. Login Sebagai Manager          |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        int loginSbg;
        
        do {
            System.out.print("=> Login Sebagai : ");
            loginSbg = input.nextInt();
            if (!((loginSbg == 1)||(loginSbg ==2))){
                System.out.println("Input Invalid, Masukan Lagi");
            }
        } while (!((loginSbg == 1)||(loginSbg ==2)));
        
        //Pilihan Login Sebagai Admin atau Manager
        switch (loginSbg){
            case 1:
                System.out.println("\nAnda Admin");
                Scanner inputAdm = new Scanner(System.in);  // Variable Scanner untuk Admin
                String userAdm;
                String passAdm;
                int attemptAdmin = 0;
                    do {
                        System.out.print("=> Masukan Username : ");
                        userAdm = inputAdm.nextLine();
                        System.out.print("=> Masukan Password : ");
                        passAdm = inputAdm.nextLine();
                        
                        if(!((userAdm.equals("alek")) && (passAdm.equals("alek")))){
                            attemptAdmin++;
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|        Username Atau Passwordmu Salah       |");
                            System.out.println("|              Masukan yang Benar             |"); 
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"); 
                        } else {
                            break;
                        }

                    } while (attemptAdmin < 3);
                    if (attemptAdmin == 3) {
                        System.out.println("Anda gagal login sebanyak 3 kali. Program keluar.");
                        System.exit(0);
                    }
  
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("|            Selamat Datang Admin             |");
                    System.out.println("|           Rumah Sakit Cina Sejati           |");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("|          1. Daftarkan Pasien                |");
                    System.out.println("|          2. Bayar Tagihan Pasien            |");            
                    System.out.println("|          3. Cek Ketersediaan Kamar          |");
                    System.out.println("|          4. Status Pembayaran Pasien        |");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                    int menuAdmin=0;

                    do { //Perulangan Jika Mwnu Salah
                        System.out.print("=> Pilih Menu : ");
                        menuAdmin = inputAdm.nextInt();

                        if(!((menuAdmin == 1) || (menuAdmin ==2 ) || (menuAdmin == 3 ) || (menuAdmin == 4 ))){
                            System.out.printf("Pillihan %d Tidak Tersedia, Masukan Angka 1, 2, 3, atau 4 Sesuai Menu\n", menuAdmin);
                        }
                    } while (!((menuAdmin == 1) || (menuAdmin ==2 ) || (menuAdmin == 3 ) || (menuAdmin == 3 )));

                    if (menuAdmin == 1){ // Menu Daftarkan Pasien
                        inputAdm.nextLine();
                        System.out.print("=> Nama Pasien          : ");
                        String namaPasien = inputAdm.nextLine();
                        System.out.print("=> Alamat               : ");
                        String alamatPasien = inputAdm.nextLine();
                        System.out.print("=> Nomor HP             : ");
                        String nomerhpPasien = inputAdm.nextLine();
                        System.out.print("=> Penyakit             : ");
                        String penyakitPasien = inputAdm.nextLine();
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.print("=> Perlu Rawat Inap y/n : ");
                        String perluInap = inputAdm.nextLine();

                        //Jika Perlu Rawat Inap
                        if (perluInap.equalsIgnoreCase("y")){
                            
                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|          Silahkan Pilih Kelas Kamar         |");
                            System.out.println("|             1. VIP                          |");
                            System.out.println("|             2. Reguler                      |");
                            System.out.println("|             3. Bersama                      |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            int kamar;

                            do {
                                System.out.print("=> Masukan  Pilihan: ");
                                kamar = inputAdm.nextInt();
                                if (!((kamar >= 1)&&(kamar <= 4))) {
                                    System.out.printf("Pilihan %d Tidak tersedia Masukan Lagi!\n", kamar);
                                }
                            } while (!((kamar >= 1)&&(kamar <= 4)));

                            int lamaInapVIP, lamaInapReg, lamaInapBersama, obat, konsumsi, total=0, bayar=0, kembalian=0;
                            int donasi=0, kembalianAkir=0; 
                            String apaDonasi, apaDonasiSemua;
                            
                            if (kamar == 1){                           
                                System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("|                Informasi Biaya              |");
                                System.out.println("|          => Rp. 1.000.000,00 /Hari          |");
                                System.out.println("|          => Rp. 100.000,00   /Obat          |");
                                System.out.println("|          => Rp. 50.000,00    /Konsumsi      |");
                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                                    System.out.print("=> Lama Menginap (Hari)     : ");
                                    lamaInapVIP = inputAdm.nextInt();
                                    System.out.print("=> Layanan Obat per Perhari : ");
                                    obat = inputAdm.nextInt();
                                    System.out.print("=> Layanan Konsumsi Perhari : ");
                                    konsumsi = inputAdm.nextInt();
                                
                                    total = (1000000*lamaInapVIP) + (obat*100000*lamaInapVIP) + (konsumsi*50000*lamaInapVIP);

                                    System.out.println("== Total Tagihan            : " + total);
                                    System.out.print("=> Bayar Sekarang           : ");
                                    bayar = inputAdm.nextInt();
                                    kembalian = bayar - total;
                                    System.out.println("== Kembalian                : " + kembalian);
                                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                                    inputAdm.nextLine(); // TAMBAL BUGGGGGG

                                    //Donasi Kemanusiaan
                                    System.out.print("=> Donasikan Kembalian  y/n : ");
                                    apaDonasi = inputAdm.nextLine();

                                    if (apaDonasi.equalsIgnoreCase("y")){
                                        System.out.print("=> Donasikan Semuanya? y/n  : ");
                                        apaDonasiSemua = inputAdm.nextLine();

                                        if (apaDonasiSemua.equalsIgnoreCase("y")){
                                            donasi = kembalian;
                                        } 
                                        else if (apaDonasiSemua.equalsIgnoreCase("n")){
                                            System.out.print("=> Masukan Besar Donasi     : ");
                                            donasi = inputAdm.nextInt();                                        
                                        }
                                            kembalianAkir = kembalian - donasi;
                                    } 

                                    else if (apaDonasi.equalsIgnoreCase("n")) {
                                        donasi = 0;
                                    }

                            } else if (kamar == 2){
                                System.out.print("=> Lama Menginap (Hari)     : ");
                                lamaInapReg = inputAdm.nextInt();
                                System.out.print("=> Layanan Obat per Perhari : ");
                                obat = inputAdm.nextInt();
                                System.out.print("=> Layanan Konsumsi Perhari : ");
                                konsumsi = inputAdm.nextInt();
                            
                                total = (700000*lamaInapReg) + (obat*100000*lamaInapReg) + (konsumsi*50000*lamaInapReg);

                                System.out.println("== Total Tagihan            : " + total);
                                System.out.print("=> Bayar Sekarang           : ");
                                bayar = inputAdm.nextInt();
                                kembalian = bayar - total;
                                System.out.println("== Kembalian                : " + kembalian);
                                inputAdm.nextLine(); // TAMBAL BUGGGGGG

                                //Donasi Kemanusiaan
                                System.out.print("=> Donasikan Kembalian  y/n : ");
                                apaDonasi = inputAdm.nextLine();

                                if (apaDonasi.equalsIgnoreCase("y")){
                                    System.out.print("=> Donasikan Semuanya? y/n  : ");
                                    apaDonasiSemua = inputAdm.nextLine();

                                    if (apaDonasiSemua.equalsIgnoreCase("y")){
                                        donasi = kembalian;
                                    } 
                                    else if (apaDonasiSemua.equalsIgnoreCase("n")){
                                        System.out.print("=> Masukan Besar Donasi     : ");
                                        donasi = inputAdm.nextInt();                                        
                                    }
                                        kembalianAkir = kembalian - donasi;
                                } 

                                else if (apaDonasi.equalsIgnoreCase("n")) {
                                    donasi = 0;
                                }


                            } else if(kamar ==3){
                                System.out.print("Lama Menginap (Hari)     : ");
                                lamaInapBersama = inputAdm.nextInt();
                                System.out.print("Layanan Obat per Perhari : ");
                                obat = inputAdm.nextInt();
                                System.out.print("Layanan Konsumsi Perhari : ");
                                konsumsi = inputAdm.nextInt();
                            
                                total = (500000*lamaInapBersama) + (obat*100000*lamaInapBersama) + (konsumsi*50000*lamaInapBersama);

                                System.out.println("Total Tagihan            : " + total);  
                                System.out.print("Bayar Sekarang           : ");
                                bayar = inputAdm.nextInt();
                                kembalian = bayar - total;
                                System.out.println("Kembalian                : " + kembalian);
                                inputAdm.nextLine(); // TAMBAL BUGGGGGG

                                //Donasi Kemanusiaan
                                System.out.print("Donasikan Kembalian  y/n : ");
                                apaDonasi = inputAdm.nextLine();

                                if (apaDonasi.equalsIgnoreCase("y")){
                                    System.out.print("Donasikan Semuanya? y/n  : ");
                                    apaDonasiSemua = inputAdm.nextLine();

                                    if (apaDonasiSemua.equalsIgnoreCase("y")){
                                        donasi = kembalian;
                                    } 
                                    else if (apaDonasiSemua.equalsIgnoreCase("n")){
                                        System.out.print("Masukan Besar Donasi     : ");
                                        donasi = inputAdm.nextInt();                                        
                                    }
                                        kembalianAkir = kembalian - donasi;
                                } 

                                else if (apaDonasi.equalsIgnoreCase("n")) {
                                    donasi = 0;
                                }
                            }
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|              Bukti Pembayaran               |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("| => Nama Pasien     : " + namaPasien);
                            System.out.println("| => Alamat Pasien   : " + alamatPasien);
                            System.out.println("| => Nomer HP Pasien : " + nomerhpPasien);
                            System.out.println("| => Penyakit Pasien : " + penyakitPasien);
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("| => Total Tagihan   : " + total);
                            System.out.println("| => Total Bayar     : " + bayar);
                            System.out.println("| => Total Kembalian : " + kembalian);
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("| => Total Donasi    : " + donasi);
                            System.out.println("| => Kembalian Akhir : " + kembalianAkir);
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|              Bukti Pembayaran               |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

                            

                        //Tidak Rawat Inap
                        } else if (perluInap.equalsIgnoreCase("n")){
                            System.out.println("Tidak Rawat Inap");

                        } else {  //Jika Input "Apakah Rawat Inap" Salah
                            System.out.println("Input salah");
                        }


                    }
                    else if (menuAdmin == 2){ //Menu 2
                        System.out.println("Menu 2 Bayar tagihan");
                    }
                    else if (menuAdmin == 3){
                        System.out.println("Menu 3");
                    }
                    else if (menuAdmin == 4){
                        System.out.println("Menu 4");
                    }
            break;

            case 2:  //Login Sebagai Manager
                Scanner inputMnjr = new Scanner(System.in);
                System.out.println("Anda Manager");
                String userMan, passMan;

                int attemptManager = 0;
                    do {
                        System.out.print("=> Masukan Username : ");
                        userMan = inputMnjr.nextLine();
                        System.out.print("=> Masukan Password : ");
                        passMan = inputMnjr.nextLine();

                        if(!((userMan.equals("manager")) && (passMan.equals("manager")))){
                            attemptManager++;
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|        Username Atau Passwordmu Salah       |");
                            System.out.println("|              Masukan yang Benar             |"); 
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"); 
                        } else {
                            break;
                        }

                    } while (attemptManager < 3);
                    if (attemptManager == 3) {
                        System.out.println("Anda gagal login sebanyak 3 kali. Program keluar.");
                        System.exit(0);
                    }
                
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("|           Selamat Datang Manager            |");
                    System.out.println("|           Rumah Sakit Cina Sejati           |");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("|                  Pilih Menu                 |");
                    System.out.println("|              1. Laporan Keuangan            |");
                    System.out.println("|              2. Riwayat Transaksi           |");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                    int menuMnjr;
                    do {
                        System.out.print("=> Pilih Menu : ");
                        menuMnjr = inputMnjr.nextInt();
                        
                        if (!((menuMnjr == 1)||(menuMnjr==2))){
                            System.out.println("Input Salah, Masukan Lagi");
                        }
                    } while (!((menuMnjr == 1)||(menuMnjr==2)));


                    switch (menuMnjr) {
                        case 1:
                            System.out.println("Ini Nanti ada Laporan Keuangan");
                            break;
                        case 2:
                            System.out.println("Ini Nanti ada Menu Riwayat Transaksi");
                            break;
                        default:
                            System.out.println("Input Salah");
                            break;
                    }
                        
            break;

            default:
            System.out.println("Masukan Angka 1 atau 2"); // Input harus angka 1 atau 2
        }

        


    }
}



