import java.util.Scanner;
import java.util.ArrayList; // Menambahkan List (Alex)
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
        ArrayList<String> daftarObat = new ArrayList<>(); //masukan Vari array (Alex)
        ArrayList<Integer> hargaObat = new ArrayList<>(); //masukan vari array (Alex)
        ArrayList<Integer> jumlahPembelian = new ArrayList<>();//masukan vari array (Alex) 
        
        //Deklarasi Password
        if ((username.equals("alek")) && (password.equals("admin"))){
        berhasil = true;

            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|           Selamat Datang di Kasir           |");
            System.out.println("|           Rumah Sakit Cina Sejati           |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");    
        }

        else{
            System.out.println(" ");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|               Passwordmu Salah              |");
            System.out.println("|              Masukan yang Benar             |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");       
        }
        // tampilan Pilihan (Alex) 
         while (true) {
              System.out.println("\nMenu:");
            System.out.println("1. Tambahkan Obat");
            System.out.println("2. Cetak Pembelian Obat");
            System.out.println("3. Keluar");
            System.out.print("Pilihan Anda: ");
            
            int pilihan = input.nextInt();
            // memilih pilihan kita 
            switch (pilihan) {
                case 1:
                    input.nextLine(); // Membuang karakter newline
                    System.out.print("Masukkan nama obat: ");
                    String namaObat = input.nextLine();
                    System.out.print("Masukkan harga obat: ");
                    int harga = input.nextInt();
                    System.out.print("Masukkan jumlah obat yang dibeli: ");
                    int jumlah = input.nextInt();

                    daftarObat.add(namaObat);
                    hargaObat.add(harga);
                    jumlahPembelian.add(jumlah);

                    System.out.println("Obat berhasil ditambahkan!");
                    break;
                case 2:
                    System.out.println("Daftar Obat yang Dibeli:");

                    int totalHarga = 0;
                    for (int i = 0; i < daftarObat.size(); i++) {
                        int subtotal = hargaObat.get(i) * jumlahPembelian.get(i);
                        System.out.println(daftarObat.get(i) + " - Harga: " + hargaObat.get(i) + " - Jumlah: " + jumlahPembelian.get(i) + " - Subtotal: " + subtotal);
                        totalHarga += subtotal;
                    }
                    System.out.println("Total Harga: " + totalHarga);
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan program ini!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
         
    }
    }
}