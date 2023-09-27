import java.util.Scanner;
public class Kasir_RS{
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
        if ((username.equals("rscintajava")) && (password.equals("javasejati"))){
        berhasil = true;
            String kamar;
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
            kamar = input.nextLine();

            int lamaInapVIP, lamaInapReg, lamaInapBPJS, obat, konsumsi, total; 

            if (kamar.equals ("1")){
                System.out.print("Lama Menginap (Hari)     : ");
                lamaInapVIP = input.nextInt();
                System.out.print("Layanan Obat per Perhari : ");
                obat = input.nextInt();
                System.out.print("Layanan Konsumsi Perhari : ");
                konsumsi = input.nextInt();
                
                total = (1000000*lamaInapVIP) + (obat*100000*lamaInapVIP) + (konsumsi*50000*lamaInapVIP);

                System.out.println("Total Tagihan            : " + total);
            }
            else if (kamar.equals ("2")){
                System.out.print("Lama Menginap (Hari)     : ");
                lamaInapReg = input.nextInt();
                System.out.print("Layanan Obat per Perhari : ");
                obat = input.nextInt();
                System.out.print("Layanan Konsumsi Perhari : ");
                konsumsi = input.nextInt();
                
                total = (700000*lamaInapReg) + (obat*100000*lamaInapReg) + (konsumsi*50000*lamaInapReg);

                System.out.println("Total Tagihan            : " + total);
            }
            else if (kamar.equals ("3")){
                System.out.print("Lama Menginap (Hari)     : ");
                lamaInapBPJS = input.nextInt();
                System.out.print("Layanan Obat per Perhari : ");
                obat = input.nextInt();
                System.out.print("Layanan Konsumsi Perhari : ");
                konsumsi = input.nextInt();
                
                total = (500000*lamaInapBPJS) + (obat*100000*lamaInapBPJS) + (konsumsi*50000*lamaInapBPJS);

                System.out.println("Total Tagihan            : " + total);            
            }
            else{
                System.out.println("eror");
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
