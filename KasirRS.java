import java.util.Scanner;
public class KasirRS{
    public static void main(String[] args){
        boolean berhasil;
        berhasil = false;
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|           Selamat Datang di Kasir           |");
        System.out.println("|            Rumah Sakit Cina Java            |");
        System.out.println("|                Silahkan Login               |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"); 

        while (berhasil == false){
            // untuk login password 
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan Username: ");
        String username= input.nextLine();
        System.out.print("Masukan Password: ");
        String password= input.nextLine();
     
        //Deklarasi Password
        if ((username.equals("alek")) && (password.equals("alek"))){
        berhasil = true;
            int kamar;
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|           Selamat Datang di Kasir           |");
            System.out.println("|           Rumah Sakit Cina Sejati           |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");  
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|          Silahkan Pilih Kelas Kamar         |");
            System.out.println("|             1. VIP                          |");
            System.out.println("|             2. Reguler                      |");
            System.out.println("|             3. BPJS                         |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("Masukan  Pilihan: ");
            kamar = input.nextInt();

            int lamaInapVIP, lamaInapReg, lamaInapBPJS, obat, konsumsi, total, bayar, kembalian; 
            
            switch (kamar){
            case 1:
                System.out.print("Lama Menginap (Hari)     : ");
                lamaInapVIP = input.nextInt();
                System.out.print("Layanan Obat per Perhari : ");
                obat = input.nextInt();
                System.out.print("Layanan Konsumsi Perhari : ");
                konsumsi = input.nextInt();
                
                total = (1000000*lamaInapVIP) + (obat*100000*lamaInapVIP) + (konsumsi*50000*lamaInapVIP);

                System.out.println("Total Tagihan            : " + total);
                System.out.print("Bayar Sekarang           : ");
                bayar = input.nextInt();
                kembalian = bayar - total;
                System.out.print("Kembalian                : " + kembalian);
            break;

            case 2:
                System.out.print("Lama Menginap (Hari)     : ");
                lamaInapReg = input.nextInt();
                System.out.print("Layanan Obat per Perhari : ");
                obat = input.nextInt();
                System.out.print("Layanan Konsumsi Perhari : ");
                konsumsi = input.nextInt();
                
                total = (700000*lamaInapReg) + (obat*100000*lamaInapReg) + (konsumsi*50000*lamaInapReg);

                System.out.println("Total Tagihan            : " + total);
                System.out.print("Bayar Sekarang           : ");
                bayar = input.nextInt();
                kembalian = bayar - total;
                System.out.print("Kembalian                : " + kembalian);
            break;

            case 3:
                System.out.print("Lama Menginap (Hari)     : ");
                lamaInapBPJS = input.nextInt();
                System.out.print("Layanan Obat per Perhari : ");
                obat = input.nextInt();
                System.out.print("Layanan Konsumsi Perhari : ");
                konsumsi = input.nextInt();
                
                total = (500000*lamaInapBPJS) + (obat*100000*lamaInapBPJS) + (konsumsi*50000*lamaInapBPJS);

                System.out.println("Total Tagihan            : " + total);  
                System.out.print("Bayar Sekarang           : ");
                bayar = input.nextInt();
                kembalian = bayar - total;
                System.out.print("Kembalian                : " + kembalian);          
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
    }
}
