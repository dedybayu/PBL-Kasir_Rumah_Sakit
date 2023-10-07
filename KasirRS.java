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
                            System.out.println("|          1. Masukan Biodata Pasien           |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Nama Pasien : ");
                            String namaPasien = inputAdm.nextLine();
                            System.out.print("=> Alamat      : ");
                            String alamatPasien = inputAdm.nextLine();
                            System.out.print("=> Nomor HP    : ");
                            String nomerhpPasien = inputAdm.nextLine();
                            System.out.print("=> Penyakit    : ");
                            String penyakitPasien = inputAdm.nextLine();


                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|          Silahkan Pilih Kelas Kamar         |");
                            System.out.println("|             1. VIP                          |");
                            System.out.println("|             2. Reguler                      |");
                            System.out.println("|             3. BPJS                         |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("Masukan  Pilihan: ");
                            int kamar = inputAdm.nextInt();

                            int lamaInapVIP, lamaInapReg, lamaInapBPJS, obat, konsumsi, total, bayar, kembalian; 

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

                                System.out.println("Nama Pasien " + namaPasien);
                                System.out.println("Nomer HP Pasien " + nomerhpPasien);
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
                                lamaInapBPJS = inputAdm.nextInt();
                                System.out.print("Layanan Obat per Perhari : ");
                                obat = inputAdm.nextInt();
                                System.out.print("Layanan Konsumsi Perhari : ");
                                konsumsi = inputAdm.nextInt();
                            
                                total = (500000*lamaInapBPJS) + (obat*100000*lamaInapBPJS) + (konsumsi*50000*lamaInapBPJS);

                                System.out.println("Total Tagihan            : " + total);  
                                System.out.print("Bayar Sekarang           : ");
                                bayar = inputAdm.nextInt();
                                kembalian = bayar - total;
                                System.out.println("Kembalian                : " + kembalian);          
                            break;
                        
                            default:
                                System.out.println("Errorrr");
                            }

                        }

                        else{
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

                    } else{
                        System.out.println(" ");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("|        Username Atau Passwordmu Salah       |");
                        System.out.println("|              Masukan yang Benar             |");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"); 
                    }


                }




            break;

            default:
            System.out.println("Masukan Angka 1 atau 2");
        }

        


    }
}



