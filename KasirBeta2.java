import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class KasirBeta2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|           Selamat Datang di Kasir           |");
            System.out.println("|           Rumah Sakit Cinta Java            |");
            System.out.println("|                Silahkan Login               |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|           1. Login Sebagai Admin            |");
            System.out.println("|           2. Login Sebagai Manager          |");
            System.out.println("|           3. Keluar                         |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            int loginSbg;

            do { // Looping jika input tidak sesuai
                System.out.print("=> Login Sebagai : ");
                if (input.hasNextInt()) {
                    loginSbg = input.nextInt();
                    if (loginSbg >= 1 && loginSbg <= 3) {
                        break; // Keluar dari perulangan jika masukan sesuai
                    } else {
                        System.out.println("Tidak Tersedia. Masukan Angka 1, 2 atau 3 Sesuai Menu");
                    }
                } else {
                    input.next(); // Membersihkan masukan yang tidak valid
                    System.out.println("Input Invalid. Harap masukkan angka.");
                }
            } while (true);
            input.nextLine();

            switch (loginSbg) {
                case 1:
                    // Login sebagai Admin
                    loginAdmin(input);
                    break;

                case 2:
                    // Login sebagai Manager
                    loginManager(input);
                    break;

                case 3:
                    System.out.print("Konfirmasi Keluar y/n : ");
                    char exit = input.nextLine().charAt(0);

                    if ((exit == 'n') || (exit == 'N')) {
                        break;
                    } else if ((exit == 'y') || (exit == 'Y')) {
                        System.out.println("Terimakasih Telah Menggunakan Aplikasi Ini");
                        System.exit(0);
                    }

                    // break;

                default:
                    System.out.println("Pilihan Tidak Tersedia");
            }
        }
    }

    // Deklarasi Format Tanggal
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Deklarasi Variable Array untuk Menyimpan Data Pasien
    private static String[][] biodataPasien = new String[10][10];

    // Deklarasi Untuk Riwayat Pasien
    private static String[][] riwayatTransaksi = new String[50][6];

    // Deklarasi Untuk Pasien yang menginab
    private static String[][] kamarVIP = new String[5][6]; // Ada 5 Kamar VIP
    private static String[][] kamarBiasa = new String[10][6]; // Ada 10 Kamar Reguler
    private static String[][] kamarBersama = new String[15][6]; // Ada 15 Kamar Bersama

    // Deklarasi untuk Riwayat Transaksi
    private static int[] uangMasuk = new int[20];
    private static int penghasilan = 0;

    // Deklarasi untuk looping
    private static int riwayat = 0; // untuk loping smua riwayat riwayat
    private static int iGlobal, kodePasien;

    // Method untuk login sebagai Admin
    private static void loginAdmin(Scanner input) {

        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|        Anda akan login sebagai Admin        |");
        System.out.println("|    Silahkan masukan Username dan Password   |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        Scanner inputAdm = new Scanner(System.in); // Variable Scanner untuk Admin

        String userAdm, passAdm;
        int attemptAdmin = 0;
        do {
            System.out.print("=> Masukan Username : ");
            userAdm = inputAdm.nextLine();
            System.out.print("=> Masukan Password : ");
            passAdm = inputAdm.nextLine();

            if (!((userAdm.equals("alek")) && (passAdm.equals("alek")))) {
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

        while (true) {
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|            Selamat Datang Admin             |");
            System.out.println("|           Rumah Sakit Cinta Java            |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|          1. Daftarkan Pasien                |");
            System.out.println("|          2. Bayar Tagihan Pasien            |");
            System.out.println("|          3. Pesankan Kamar Pasien           |");
            System.out.println("|          4. Data Pasien                     |");
            System.out.println("|          5. Logout                          |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            int menuAdmin;

            do { // Looping jika input tidak sesuai
                System.out.print("=> Pilih Menu : ");
                if (inputAdm.hasNextInt()) {
                    menuAdmin = inputAdm.nextInt();
                    if (menuAdmin >= 1 && menuAdmin <= 5) {
                        break; // Keluar dari perulangan jika masukan sesuai
                    } else {
                        System.out.println("Tidak Tersedia. Masukan Angka 1 - 5 Sesuai Menu");
                    }
                } else {
                    inputAdm.next(); // Membersihkan masukan yang tidak valid
                    System.out.println("Input Invalid. Harap masukkan angka.");
                }
            } while (true);
            inputAdm.nextLine();

            switch (menuAdmin) {

                case 1:
                    // Menu Daftarkan Pasien
                    int idx = 0;
                    while (idx < biodataPasien.length) {
                        if (biodataPasien[idx][0] == null) {

                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|               Daftarkan Pasien              |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Nama Pasien          : ");
                            biodataPasien[idx][0] = inputAdm.nextLine(); // 0 : Nama
                            System.out.print("=> Alamat               : ");
                            biodataPasien[idx][1] = inputAdm.nextLine(); // 1 : Alamat
                            System.out.print("=> Nomor HP             : ");
                            biodataPasien[idx][2] = inputAdm.nextLine(); // 2 : Nomor HP
                            System.out.print("=> Penyakit Pasien      : ");
                            biodataPasien[idx][3] = inputAdm.nextLine(); // 3 : Penyakit Pasien

                            while (true) { // Perulangan jika input bukan y/n
                                System.out.print("=> Punya BPJS (y/n)     : ");
                                biodataPasien[idx][4] = inputAdm.nextLine(); // 4 : BPJS Pasien

                                if (biodataPasien[idx][4].equalsIgnoreCase("y")
                                        || biodataPasien[idx][4].equalsIgnoreCase("n")) {
                                    break;
                                } else {
                                    System.out.println("Masukan y atau n");
                                }
                            }

                            biodataPasien[idx][7] = Integer.toString((idx + 1)); // menrubah int menjadi String
                            System.out.println("=> Kode Pasien Adalah   : " + (biodataPasien[idx][7])); // 6 : Kode
                                                                                                        // Pasien
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

                            char daftarLagi;
                            while (true) { // Perulangan jika input bukan y/n
                                System.out.print("=> Daftarkan Lagi (y/n): ");
                                daftarLagi = inputAdm.nextLine().charAt(0);
                                if ((daftarLagi == 'y' || daftarLagi == 'Y')
                                        || (daftarLagi == 'n' || daftarLagi == 'N')) { // Apakah ingin mendaftarkan lagi
                                    break;
                                } else {
                                    System.out.println("input invalid Masukan y/n");
                                }
                            }

                            if (daftarLagi == 'n' || daftarLagi == 'N') {
                                break;
                            }

                            idx++;

                        } else {
                            System.out.println("Pasien Penuh");
                        }

                    }

                    break;

                case 2: // Menu Bayar Tagihan Pasien

                    boolean adaPasien = false;
                    for (int i = 0; i < biodataPasien.length; i++) {
                        if (biodataPasien[i][0] != null) {
                            adaPasien = true;
                        }
                    }

                    if (adaPasien == true) {
                        do {
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|            Bayar Tagihan Pasien             |");
                            System.out.println("|---------------------------------------------|");
                            System.out.println("| (?) Ketik (cari) untuk mencari Kode Pasien  |");
                            System.out.println("| (?) Ketik (kembali) untuk kembali ke menu   |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Masukan Kode Pasien : ");
                            String bayarPasien = inputAdm.nextLine();

                            if (bayarPasien.equalsIgnoreCase("cari")) {
                                System.out.print("=> Masukan Nama Pasien : ");
                                String cariPasien = inputAdm.nextLine();

                                boolean ditemukan = false;
                                for (iGlobal = 0; iGlobal < biodataPasien.length; iGlobal++) {
                                    if (biodataPasien[iGlobal][0] != null
                                            && biodataPasien[iGlobal][0].contains(cariPasien)) { // Diganti contains

                                        printBiodataPasien();

                                        ditemukan = true;
                                    }
                                }

                                if (!ditemukan) {
                                    System.out.println("Pasien Tidak ditemukan");
                                }

                            } else if (bayarPasien.equalsIgnoreCase("kembali")) {
                                break;

                            } else if (bayarPasien.matches("\\d+")) { // JIKA angka Maka kesini
                                kodePasien = Integer.parseInt(bayarPasien); // Jika Kode ditemukan
                                if (kodePasien >= 1 && kodePasien <= biodataPasien.length
                                        && biodataPasien[kodePasien - 1][0] != null) {
                                    System.out.println("=====================================");
                                    System.out.println("|  Bayar Tagihan Pasien Nomor "+kodePasien);
                                    bioKodePasien();

                                    // Engko Ditambahi Tagian Pasien

                                } else {
                                    System.out.println("Kode Pasien tidak valid"); // JIKA kode tidak ditemukan
                                }

                            }

                            else {
                                System.out.println("Input Invalid\n");

                            }

                        } while (true);

                    }

                    else if (adaPasien == false) {
                        noActivePasien();

                    }

                    break;

                case 3:
                    // Pesankan Kamar Pasien
                    System.out.println("Menu 3");
                    boolean adaPasien3 = false;
                    for (int i = 0; i < biodataPasien.length; i++) {
                        if (biodataPasien[i][0] != null) {
                            adaPasien3 = true;
                        }
                    }

                    if (adaPasien3 == true) {
                        do {
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|            Daftarkan Kamar Pasien           |");
                            System.out.println("|---------------------------------------------|");
                            System.out.println("| (?) Ketik (cari) untuk mencari Kode Pasien  |");
                            System.out.println("| (?) Ketik (kembali) untuk kembali ke menu   |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Masukan Kode Pasien : ");
                            String bayarPasien = inputAdm.nextLine();

                            if (bayarPasien.equalsIgnoreCase("cari")) {
                                System.out.print("=> Masukan Nama Pasien : ");
                                String cariPasien = inputAdm.nextLine();

                                boolean ditemukan = false;
                                for (iGlobal = 0; iGlobal < biodataPasien.length; iGlobal++) {
                                    if (biodataPasien[iGlobal][0] != null
                                            && biodataPasien[iGlobal][0].equalsIgnoreCase(cariPasien)) {

                                        printBiodataPasien();
                                        ditemukan = true;
                                    }
                                }

                                if (!ditemukan) {
                                    System.out.println("Pasien Tidak ditemukan");
                                }

                            } else if (bayarPasien.equalsIgnoreCase("kembali")) {
                                break;

                            } else if (bayarPasien.matches("\\d+")) { // JIKA angka Maka kesini
                                kodePasien = Integer.parseInt(bayarPasien); // Jika Kode ditemukan
                                if (kodePasien >= 1 && kodePasien <= biodataPasien.length
                                        && biodataPasien[kodePasien - 1][0] != null) {

                                    System.out.println("=====================================");
                                    System.out.println("|  Pesankan Kamar Pasien Nomor "+kodePasien);
                                    
                                    bioKodePasien();

                                    System.out.println("=====================================");
                                    System.out.println("|           1. VIP                  |");
                                    System.out.println("|           2. Reguler              |");
                                    System.out.println("|           3. Bersama              |");
                                    System.out.println("|           4. Informasi Kamar      |");
                                    System.out.println("=====================================");
                                    // Engko Logika Pesan Kamar Pasien
                                    System.out.println("|=> Masukan Kamar Pasien : ");

                                } else {
                                    System.out.println("Kode Pasien tidak valid"); // JIKA kode tidak ditemukan
                                }

                            }

                            else {
                                System.out.println("Input Invalid\n");

                            }

                        } while (true);

                    }

                    else if (adaPasien3 == false) {
                        noActivePasien();

                    }

                    break;

                case 4:
                    // Menu Riwayat Pasien
                    boolean semuaKosong = true;
                    for (iGlobal = 0; iGlobal < biodataPasien.length; iGlobal++) {
                        if (biodataPasien[iGlobal][0] != null) {
                            printBiodataPasien();
                            semuaKosong = false;
                        }

                    }

                    if (semuaKosong) {
                        noActivePasien();
                    }
                    break;

                case 5:
                    // Logout dan kembali ke menu login
                    System.out.print("Konfirmasi Logout y/n : ");
                    char logout = inputAdm.nextLine().charAt(0);

                    if ((logout == 'n') || (logout == 'N')) {
                        break;
                    } else if ((logout == 'y') || (logout == 'Y')) {
                        return;
                    }

                default:
                    System.out.println("Pilihan Tidak Tersedia");

            }
        }
    }

    // Method untuk login sebagai Manager
    private static void loginManager(Scanner input) {

        Scanner inputMnjr = new Scanner(System.in);
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|       Anda akan login sebagai Manager       |");
        System.out.println("|    Silahkan masukan Username dan Password   |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        String userMan, passMan;

        int attemptManager = 0;
        do {
            System.out.print("=> Masukan Username : ");
            userMan = inputMnjr.nextLine();
            System.out.print("=> Masukan Password : ");
            passMan = inputMnjr.nextLine();

            if (!((userMan.equals("manager")) && (passMan.equals("manager")))) {
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

        while (true) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|           Selamat Datang Manager            |");
            System.out.println("|           Rumah Sakit Cinta Java            |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|              1. Laporan Keuangan            |");
            System.out.println("|              2. Riwayat Transaksi           |");
            System.out.println("|              3. Logout                      |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            int menuManager;

            do { // Looping jika input tidak sesuai
                System.out.print("=> Pilih Menu : ");
                if (inputMnjr.hasNextInt()) {
                    menuManager = inputMnjr.nextInt();
                    if (menuManager >= 1 && menuManager <= 3) {
                        break; // Keluar dari perulangan jika masukan sesuai
                    } else {
                        System.out.println("Tidak Tersedia. Masukan Angka 1 - 3 Sesuai Menu");
                    }
                } else {
                    inputMnjr.next(); // Membersihkan masukan yang tidak valid
                    System.out.println("Input Invalid. Harap masukkan angka.");
                }
            } while (true);
            inputMnjr.nextLine();

            switch (menuManager) {
                case 1:
                    // Menu Laporan Keuangan
                    System.out.println("Menu Laporan Keuangan");
                    break;

                case 2:
                    // Menu Riwayat Transaksi
                    // System.out.println("Menu Data Pasien");
                    boolean semuaKosong = true;
                    for (iGlobal = 0; iGlobal < biodataPasien.length; iGlobal++) {
                        if (biodataPasien[iGlobal][0] != null) {
                            printBiodataPasien();
                            semuaKosong = false;
                        }

                    }

                    if (semuaKosong) {
                        noActivePasien();
                    }
                    break;

                case 3:
                    System.out.print("Konfirmasi Logout y/n : ");
                    char logout = inputMnjr.nextLine().charAt(0);

                    if ((logout == 'n') || (logout == 'N')) {
                        break;
                    } else if ((logout == 'y') || (logout == 'Y')) {
                        return;
                    }

                default:
                    System.out.println("Pilihan Tidak Tersedia");
            }
        }
    }

    static void printBiodataPasien() {
        System.out.println("=====================================");
        System.out.println("|       Pasien Nomor " + biodataPasien[iGlobal][7]);
        System.out.println("|=> Nama Pasien     : " + biodataPasien[iGlobal][0]);
        System.out.println("|=> Alamat Pasien   : " + biodataPasien[iGlobal][1]);
        System.out.println("|=> Nomer HP        : " + biodataPasien[iGlobal][2]);
        System.out.println("|=> Penyakit Pasien : " + biodataPasien[iGlobal][3]);
        System.out.println("|=> Kode Pasien     : " + biodataPasien[iGlobal][7]);
        System.out.println("=====================================\n");
    }

    static void noActivePasien() {
        System.out.println("=====================================");
        System.out.println("|   Tidak Ada Pasien yang Aktif     |");
        System.out.println("=====================================\n");
    }

    static void bioKodePasien() {
        System.out.println("|=> Nama Pasien     : " + biodataPasien[kodePasien - 1][0]);
        System.out.println("|=> Alamat Pasien   : " + biodataPasien[kodePasien - 1][1]);
        System.out.println("|=> Nomer HP        : " + biodataPasien[kodePasien - 1][2]);
        System.out.println("|=> Penyakit Pasien : " + biodataPasien[kodePasien - 1][3]);
        System.out.println("|=> Kode Pasien     : " + biodataPasien[kodePasien - 1][7]);
        System.out.println("=====================================\n");
    }
}
