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
        System.out.print("Login Sebagai : ");
        int loginSbg = input.nextInt();

        //Pilihan Login Sebagai Admin atau Manager
        switch (loginSbg){
            case 1:
                System.out.println("\nAnda Admin");
                Scanner inputAdm = new Scanner(System.in);  // Variable Scanner untuk Admin
                boolean berhasilAdm;
                berhasilAdm = false;
                while (berhasilAdm == false){
                    // untuk login password 
                    System.out.print("Masukan Username : ");
                    String userAdm = inputAdm.nextLine();
                    System.out.print("Masukan Password : ");
                    String passAdm = inputAdm.nextLine();
            
                    //Deklarasi Password
                        if ((userAdm.equals("alek")) && (passAdm.equals("alek"))){
                            berhasilAdm = true;

                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|            Selamat Datang Admin             |");
                            System.out.println("|           Rumah Sakit Cina Sejati           |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|          1. Masukan Biodata Pasien          |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Nama Pasien          : ");
                            String namaPasien = inputAdm.nextLine();
                            System.out.print("=> Alamat               : ");
                            String alamatPasien = inputAdm.nextLine();
                            System.out.print("=> Nomor HP             : ");
                            String nomerhpPasien = inputAdm.nextLine();
                            System.out.print("=> Penyakit             : ");
                            String penyakitPasien = inputAdm.nextLine();
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Perlu Rawat Inap y/n :");
                            String perluInap = inputAdm.nextLine();

                            //Jika Perlu Rawat Inap
                            if (perluInap.equalsIgnoreCase("y")){

                                System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("|          Silahkan Pilih Kelas Kamar         |");
                                System.out.println("|             1. VIP                          |");
                                System.out.println("|             2. Reguler                      |");
                                System.out.println("|             3. Bersama                      |");
                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.print("Masukan  Pilihan: ");
                                int kamar = inputAdm.nextInt();

                                int lamaInapVIP, lamaInapReg, lamaInapBersama, obat, konsumsi, total, bayar, kembalian;
                                int donasi=0, kembalianAkir=0; 
                                // String apaDonasi;

                                switch (kamar){
                                case 1:
                                    System.out.print("Lama Menginap (Hari)     : ");
                                    lamaInapVIP = inputAdm.nextInt();
                                    System.out.print("Layanan Obat per Perhari : ");
                                    obat = inputAdm.nextInt();
                                    System.out.print("Layanan Konsumsi Perhari : ");
                                    konsumsi = inputAdm.nextInt();
                                
                                    total = (1000000*lamaInapVIP) + (obat*100000*lamaInapVIP) + (konsumsi*50000*lamaInapVIP);

                                    System.out.println("Total Tagihan            : " + total);
                                    System.out.print("Bayar Sekarang           : ");
                                    bayar = inputAdm.nextInt();
                                    kembalian = bayar - total;
                                    System.out.println("Kembalian                : " + kembalian);
                                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                                    String bug = inputAdm.nextLine();

                                    //Donasi Kemanusiaan
                                    System.out.print("Donasikan Kembalian  y/n : ");
                                    String apaDonasi = inputAdm.nextLine();

                                    if (apaDonasi.equalsIgnoreCase("y")){
                                        System.out.print("Donasikan Semuanya? y/n  : ");
                                        String apaDonasiSemua = inputAdm.nextLine();

                                        if (apaDonasiSemua.equalsIgnoreCase("y")){
                                            donasi = kembalian;
                                        } 
                                        else if (apaDonasiSemua.equalsIgnoreCase("n")){
                                            System.out.print("Masukan Besar Donasi     : ");
                                            donasi = inputAdm.nextInt();                                        
                                        }
                                            kembalianAkir = kembalian - donasi;

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

                                    } 
                                    else if (apaDonasi.equalsIgnoreCase("n")) {
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
                                            System.out.println("|              Bukti Pembayaran               |");
                                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                                    }
                                                                    
                                break;

                                case 2:
                                    System.out.print("Lama Menginap (Hari)     : ");
                                    lamaInapReg = inputAdm.nextInt();
                                    System.out.print("Layanan Obat per Perhari : ");
                                    obat = inputAdm.nextInt();
                                    System.out.print("Layanan Konsumsi Perhari : ");
                                    konsumsi = inputAdm.nextInt();
                                
                                    total = (700000*lamaInapReg) + (obat*100000*lamaInapReg) + (konsumsi*50000*lamaInapReg);

                                    System.out.println("Total Tagihan            : " + total);
                                    System.out.print("Bayar Sekarang           : ");
                                    bayar = inputAdm.nextInt();
                                    kembalian = bayar - total;
                                    System.out.println("Kembalian                : " + kembalian);
                                break;

                                case 3:
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
                                break;
                            
                                default:
                                    System.out.println("Input tidak sesuai");
                                }
                                

                            //Tidak Rawat Inap
                            } else if (perluInap.equalsIgnoreCase("n")){
                                System.out.println("Tidak Rawat Inap");

                            } else {  //Jika Input "Apakah Rawat Inap" Salah
                                System.out.println("Input salah");
                            }

   
                        }

                        else{ //Jika Pssword Admin Salah
                            System.out.println(" ");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|        Username Atau Passwordmu Salah       |");
                            System.out.println("|              Masukan yang Benar             |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");       
                        }
                    }

            break;

            case 2:  //Login Sebagai Manager
                Scanner inputMnjr = new Scanner(System.in);
                System.out.println("Anda Manager");
                String userMan, passMan;
                boolean berhasilMnjr;
                berhasilMnjr = false;

                while (berhasilMnjr == false){
                System.out.print("Masukan Username : ");
                userMan = inputMnjr.nextLine();
                System.out.print("Masukan Password : ");
                passMan = inputMnjr.nextLine();
                
                    if ((userMan.equals("manager")) && (passMan.equals("manager"))){
                        berhasilMnjr = true;
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("|           Selamat Datang Manager            |");
                        System.out.println("|           Rumah Sakit Cina Sejati           |");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

                    } else{  //Jika Password Manager Salah
                        System.out.println(" ");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("|        Username Atau Passwordmu Salah       |");
                        System.out.println("|              Masukan yang Benar             |"); 
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"); 
                    }


                }




            break;

            default:
            System.out.println("Masukan Angka 1 atau 2"); // Input harus angka 1 atau 2
        }

        


    }
}



