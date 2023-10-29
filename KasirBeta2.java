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
                        System.exit(0);
                    }

                    // break;

                default:
                    System.out.println("Pilihan Tidak Tersedia");
            }
        }
    }

    // Deklarasi Variable Array untuk Menyimpan Data Pasien
    private static String[][] biodataPasien = new String[10][6];

    // Deklarasi Untuk Riwayat Pasien 
    private static String[][] riwayatTransaksi = new String[50][6];

    // Deklarasi Untuk Pasien yang menginab
    private static String[][] kamarVIP = new String[5][6];      // Ada 5 Kamar VIP
    private static String[][] kamarBiasa = new String[10][6];   // Ada 10 Kamar Reguler
    private static String[][] kamarBersama = new String[15][6]; // Ada 15 Kamar Bersama

    // Deklarasi untuk Transaksi
    private static int[] uangMasuk = new int[20];

    // Deklarasi untuk looping
    private static int idx = 0;
    private static int transaksi = 0;

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
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
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
                    while (idx < biodataPasien.length) {
                        if (biodataPasien[idx][0] == null) {

                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
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
                            System.out.print("=> Punya BPJS           : ");
                            biodataPasien[idx][4] = inputAdm.nextLine(); // 4 : Penyakit Pasien

                            biodataPasien[idx][5] = Integer.toString((idx + 1)); // 5 : Kode Pasien
                            System.out.println("=> Kode Pasien Adalah   : " + (biodataPasien[idx][4]));
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Daftarkan Pasien lagi: ");
                            char daftarLagi = inputAdm.nextLine().charAt(0);
                            if (daftarLagi == 'n' || daftarLagi == 'N') {
                                break;
                            }
                            idx++;
                        } 
                        else {
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
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|            Bayar Tagihan Pasien               |");
                            System.out.println("|-----------------------------------------------|");
                            System.out.println("| ? Ketik (cari) untuk mencari nomor urut pasien|");
                            System.out.println("| ? Ketik (kembali) untuk kembali ke menu       |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Masukan Kode Pasien : ");
                            String bayarPasien = inputAdm.nextLine();

                            if (bayarPasien.equalsIgnoreCase("cari")) {
                                System.out.print("=> Masukan Nama Pasien : ");
                                String cariPasien = inputAdm.nextLine();

                                boolean ditemukan = false;
                                for (int i = 0; i < biodataPasien.length; i++) {
                                    if (biodataPasien[i][0] != null
                                            && biodataPasien[i][0].equalsIgnoreCase(cariPasien)) {
                                        // System.out.printf("Nomor Pasien %s adalah %d\n", cariPasien, i);
                                        System.out.println("=====================================");
                                        System.out.println("|       Pasien Nomor " + biodataPasien[i][5]);
                                        System.out.println("|=> Nama Pasien     : " + biodataPasien[i][0]);
                                        System.out.println("|=> Alamat Pasien   : " + biodataPasien[i][1]);
                                        System.out.println("|=> Nomer HP        : " + biodataPasien[i][2]);
                                        System.out.println("|=> Penyakit Pasien : " + biodataPasien[i][3]);
                                        System.out.println("|=> Kode Pasien     : " + biodataPasien[i][5]);
                                        System.out.println("=====================================\n");
                                        ditemukan = true;
                                    }
                                }

                                if (!ditemukan) {
                                    System.out.println("Pasien Tidak ditemukan");
                                }

                            } else if (bayarPasien.equalsIgnoreCase("kembali")) {
                                break;

                            } else if (bayarPasien.matches("\\d+")) { // JIKA angka Maka kesini
                                int kodePasien = Integer.parseInt(bayarPasien); // Jika Kode ditemukan
                                if (kodePasien >= 1 && kodePasien <= biodataPasien.length
                                        && biodataPasien[kodePasien - 1] != null) {

                                    System.out.println("=====================================");
                                    System.out.println("| Bayar Tagihan Pasien Nomor " + kodePasien);
                                    System.out.println("|=> Nama Pasien     : " + biodataPasien[kodePasien - 1][0]);
                                    System.out.println("|=> Alamat Pasien   : " + biodataPasien[kodePasien - 1][1]);
                                    System.out.println("|=> Nomer HP        : " + biodataPasien[kodePasien - 1][2]);
                                    System.out.println("|=> Penyakit Pasien : " + biodataPasien[kodePasien - 1][3]);
                                    System.out.println("|=> Kode Pasien     : " + biodataPasien[kodePasien - 1][5]);
                                    System.out.println("=====================================\n");

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
                        System.out.println("=====================================");
                        System.out.println("|   Tidak Ada Pasien yang Aktif     |");
                        System.out.println("=====================================\n");

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
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|            Daftarkan Kamar Pasien             |");
                            System.out.println("|-----------------------------------------------|");
                            System.out.println("| ? Ketik (cari) untuk mencari nomor urut pasien|");
                            System.out.println("| ? Ketik (kembali) untuk kembali ke menu       |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Masukan Kode Pasien : ");
                            String bayarPasien = inputAdm.nextLine();

                            if (bayarPasien.equalsIgnoreCase("cari")) {
                                System.out.print("=> Masukan Nama Pasien : ");
                                String cariPasien = inputAdm.nextLine();

                                boolean ditemukan = false;
                                for (int i = 0; i < biodataPasien.length; i++) {
                                    if (biodataPasien[i][0] != null
                                            && biodataPasien[i][0].equalsIgnoreCase(cariPasien)) {
                                        // System.out.printf("Nomor Pasien %s adalah %d\n", cariPasien, i);
                                        System.out.println("=====================================");
                                        System.out.println("|       Pasien Nomor " + biodataPasien[i][5]);
                                        System.out.println("|=> Nama Pasien     : " + biodataPasien[i][0]);
                                        System.out.println("|=> Alamat Pasien   : " + biodataPasien[i][1]);
                                        System.out.println("|=> Nomer HP        : " + biodataPasien[i][2]);
                                        System.out.println("|=> Penyakit Pasien : " + biodataPasien[i][3]);
                                        System.out.println("|=> Kode Pasien     : " + biodataPasien[i][5]);
                                        System.out.println("=====================================\n");
                                        ditemukan = true;
                                    }
                                }

                                if (!ditemukan) {
                                    System.out.println("Pasien Tidak ditemukan");
                                }

                            } else if (bayarPasien.equalsIgnoreCase("kembali")) {
                                break;

                            } else if (bayarPasien.matches("\\d+")) { // JIKA angka Maka kesini
                                int kodePasien = Integer.parseInt(bayarPasien); // Jika Kode ditemukan
                                if (kodePasien >= 1 && kodePasien <= biodataPasien.length
                                        && biodataPasien[kodePasien - 1] != null) {

                                    System.out.println("=====================================");
                                    System.out.println("| Bayar Tagihan Pasien Nomor " + kodePasien);
                                    System.out.println("|=> Nama Pasien     : " + biodataPasien[kodePasien - 1][0]);
                                    System.out.println("|=> Alamat Pasien   : " + biodataPasien[kodePasien - 1][1]);
                                    System.out.println("|=> Nomer HP        : " + biodataPasien[kodePasien - 1][2]);
                                    System.out.println("|=> Penyakit Pasien : " + biodataPasien[kodePasien - 1][3]);
                                    System.out.println("|=> Kode Pasien     : " + biodataPasien[kodePasien - 1][5]);
                                    System.out.println("=====================================\n");

                                    // Engko Logika Pesan Kamar Pasien

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
                        System.out.println("=====================================");
                        System.out.println("|   Tidak Ada Pasien yang Aktif     |");
                        System.out.println("=====================================\n");

                    }

                    break;

                case 4:
                    // Menu Riwayat Pasien
                    boolean semuaKosong = true;
                    for (int i = 0; i < biodataPasien.length; i++) {
                        if (biodataPasien[i] != null) {
                            System.out.println("=====================================");
                            System.out.println("|       Pasien Nomor " + biodataPasien[i][5]);
                            System.out.println("|=> Nama Pasien     : " + biodataPasien[i][0]);
                            System.out.println("|=> Alamat Pasien   : " + biodataPasien[i][1]);
                            System.out.println("|=> Nomer HP        : " + biodataPasien[i][2]);
                            System.out.println("|=> Penyakit Pasien : " + biodataPasien[i][3]);
                            System.out.println("|=> Kode Pasien     : " + biodataPasien[i][5]);
                            System.out.println("=====================================\n");
                            semuaKosong = false;
                        }

                    }

                    if (semuaKosong) {
                        System.out.println("=====================================");
                        System.out.println("|   Tidak Ada Pasien yang Aktif     |");
                        System.out.println("=====================================\n");

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
                    System.out.println("Menu Data Pasien");
                    boolean semuaKosong = true;
                    for (int i = 0; i < biodataPasien.length; i++) {
                        if (biodataPasien[i] != null) {
                            System.out.println("=====================================");
                            System.out.println("|       Pasien Nomor " + biodataPasien[i][5]);
                            System.out.println("|=> Nama Pasien     : " + biodataPasien[i][0]);
                            System.out.println("|=> Alamat Pasien   : " + biodataPasien[i][1]);
                            System.out.println("|=> Nomer HP        : " + biodataPasien[i][2]);
                            System.out.println("|=> Penyakit Pasien : " + biodataPasien[i][3]);
                            System.out.println("|=> Kode Pasien     : " + biodataPasien[i][5]);
                            System.out.println("=====================================\n");
                            semuaKosong = false;
                        }

                    }

                    if (semuaKosong) {
                        System.out.println("=====================================");
                        System.out.println("|   Tidak Ada Pasien yang Aktif     |");
                        System.out.println("=====================================\n");

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
}
