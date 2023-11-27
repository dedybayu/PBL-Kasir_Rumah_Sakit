import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class KasirBeta2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
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
    private static String[][] biodataPasien = new String[100][10];

    // Deklarasi Untuk Riwayat Pasien
    private static String[][] detailRiwayatTransaksi = new String[100][15];
    // [0]nama [1]alamat [2]nohp [3]pnykt [4]bpjs [5]tglmsk [6]tglkluar [7]lmainap
    // [8]obat
    // [9]konsum [10]diskonbpjs [11]tagihan [12]donasi

    // Deklarasi Untuk Pasien yang menginab
    private static String[][] kamarVIP = new String[5][10]; // Ada 5 Kamar VIP
    private static String[][] kamarReguler = new String[10][10]; // Ada 10 Kamar Reguler
    private static String[][][] kamarBersama = new String[10][2][10]; // Ada 10 Kamar Bersama 1kmr 2orng
    // Penjelasan Array [kamar][biodata pasien] untuk 2D
    // Penjelasan Array[kamar][pasien 1,2][biodata pasien] untuk 3D

    // Deklarasi untuk Pembayaran
    private static int[] uangMasuk = new int[20];
    private static int penghasilan = 0;

    // Deklarasi Untuk Total Transaksi
    private static int[][] transactions = new int[biodataPasien.length][4];
    // [][0] = total tagihan, [][1] = jumlah yang dibayar, [][2] = kembalian, [][3]
    // = donasi

    // Deklarasi untuk looping
    private static int riwayat = 0; // untuk loping smua riwayat riwayat
    private static int iGlobal, kodePasien;
    // private static Char

    // Deklarasi Untuk Pembayaran
    // Deklarasi variabel buat logika Pembayaran
    private static int obat = 0, hargaObat = 0, hargaKatPenyakit = 0, kembalian = 0, bayar = 0;
    private static double keringanan = 0, tagihan = 0;
    private static int donasi = 0, kembalianAkhir = 0;
    private static String penyakit, apaDonasi, apaDonasiSemua;

    // Method untuk login sebagai Admin
    private static void loginAdmin(Scanner input) {

        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|        Anda akan login sebagai Admin        |");
        System.out.println("|    Silahkan masukan Username dan Password   |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        // Scanner input = new Scanner(System.in); // Variable Scanner untuk Admin

        String userAdm, passAdm;
        int attemptAdmin = 0;
        do {
            System.out.print("=> Masukan Username : ");
            userAdm = input.nextLine();
            System.out.print("=> Masukan Password : ");
            passAdm = input.nextLine();

            if (!((userAdm.equals("alek")) && (passAdm.equals("alek")))) {
                attemptAdmin++;
                userPwSalah();
            } else {
                break;
            }

        } while (attemptAdmin < 3);
        if (attemptAdmin == 3) {
            System.out.println("Anda gagal login sebanyak 3 kali. Program keluar.");
            System.exit(0);
        }

        // Pemberian Pasien Awal Buat Uji Coba
        biodataPasien[0][0] = "alek";
        biodataPasien[0][1] = "Banyuwangi";
        biodataPasien[0][2] = "08212432";
        biodataPasien[0][3] = "batuk";
        biodataPasien[0][4] = "y";
        biodataPasien[0][5] = "11-12-2023";
        biodataPasien[0][7] = "1";

        while (true) {
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|            Selamat Datang Admin             |");
            System.out.println("|           Rumah Sakit Cinta Java            |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|          1. Daftarkan Pasien                |");
            System.out.println("|          2. Bayar Tagihan Pasien            |");
            System.out.println("|          3. Pesankan Kamar Pasien           |");
            System.out.println("|          4. Data Pasien                     |");
            System.out.println("|          5. Cek Ketersediaan Kamar          |");
            System.out.println("|          6. Logout                          |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            int menuAdmin;

            do { // Looping jika input tidak sesuai
                System.out.print("=> Pilih Menu : ");
                if (input.hasNextInt()) {
                    menuAdmin = input.nextInt();
                    if (menuAdmin >= 1 && menuAdmin <= 6) {
                        break; // Keluar dari perulangan jika masukan sesuai
                    } else {
                        System.out.println("Tidak Tersedia. Masukan Angka 1 - 6 Sesuai Menu");
                    }
                } else {
                    input.next(); // Membersihkan masukan yang tidak valid
                    System.out.println("Input Invalid. Harap masukkan angka.");
                }
            } while (true);
            input.nextLine();

            switch (menuAdmin) {

                case 1:
                    // Menu Daftarkan Pasien
                    int idx = 0;
                    do {
                        if (biodataPasien[idx][0] == null) {

                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|               Daftarkan Pasien              |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Nama Pasien          : ");
                            biodataPasien[idx][0] = input.nextLine(); // 0 : Nama
                            System.out.print("=> Alamat               : ");
                            biodataPasien[idx][1] = input.nextLine(); // 1 : Alamat
                            System.out.print("=> Nomor HP             : ");
                            biodataPasien[idx][2] = input.nextLine(); // 2 : Nomor HP
                            System.out.print("=> Penyakit Pasien      : ");
                            biodataPasien[idx][3] = input.nextLine(); // 3 : Penyakit Pasien

                            while (true) { // Perulangan jika input bukan y/n
                                System.out.print("=> Punya BPJS (y/n)     : ");
                                biodataPasien[idx][4] = input.nextLine(); // 4 : BPJS Pasien

                                if (biodataPasien[idx][4].equalsIgnoreCase("y")
                                        || biodataPasien[idx][4].equalsIgnoreCase("n")) {
                                    break;
                                } else {
                                    System.out.println("Masukan y atau n");
                                }
                            }
                            // Mengambil tanggal sistem saat ini
                            // System.out.println("=>");
                            LocalDate tanggalMasuk = LocalDate.now();

                            // LocalDate tanggalMasuk = inputTanggal("Tanggal Masuk : ", formatter, input);
                            biodataPasien[idx][5] = tanggalMasuk.format(formatter); // 5 Tanggal Masuk 6 Tanggal
                                                                                    // Keluar
                            // System.out.print("=> Masukan Tanggal Masuk : ");
                            // biodataPasien[idx][5] = input.nextLine();

                            biodataPasien[idx][7] = Integer.toString((idx + 1)); // menrubah int menjadi String
                            System.out.println("=> Kode Pasien Adalah   : " + (biodataPasien[idx][7])); // 7 : Kode
                                                                                                        // Pasien
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

                            char daftarLagi;
                            while (true) { // Perulangan jika input bukan y/n
                                System.out.print("=> Daftarkan Lagi (y/n): ");
                                daftarLagi = input.nextLine().charAt(0);
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

                        }
                        idx++;
                    } while (idx < biodataPasien.length);

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
                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|            Bayar Tagihan Pasien             |");
                            System.out.println("|---------------------------------------------|");
                            System.out.println("| (?) Ketik (cari) untuk mencari Kode Pasien  |");
                            System.out.println("| (?) Ketik (kembali) untuk kembali ke menu   |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Masukan Kode Pasien : ");
                            String bayarPasien = input.nextLine();

                            if (bayarPasien.equalsIgnoreCase("cari")) {
                                System.out.print("=> Masukan Nama Pasien : ");
                                String cariPasien = input.nextLine();

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
                                    System.out.println("========================================");
                                    System.out.println("|  Bayar Tagihan Pasien Nomor " + kodePasien);
                                    printBioKodePasien();

                                    // Blok Jika pasien Rawat Inap
                                    if (biodataPasien[kodePasien - 1][8] != null) {
                                        System.out.println("|=> Pasien Kamar "+ biodataPasien[kodePasien-1][8]+" "+biodataPasien[kodePasien-1][9]);
                                        System.out.println("========================================");

                                        LocalDate tanggalKeluar = inputTanggal("Tanggal Keluar: ", formatter, input);
                                        biodataPasien[kodePasien - 1][6] = tanggalKeluar.format(formatter);

                                        LocalDate tanggalCheskIn = LocalDate.parse(biodataPasien[kodePasien - 1][5],
                                                formatter);
                                        LocalDate tanggalCheckOut = LocalDate.parse(biodataPasien[kodePasien - 1][6],
                                                formatter);

                                        long selisihHari = hitungSelisihHari(tanggalCheskIn, tanggalCheckOut);

                                        System.out.println("Selisih Hari = " + selisihHari);

                                        // Untuk Tagihan Kamar
                                        int tagihanKamar = tagihanKamarPasien((kodePasien - 1), selisihHari);

                                        System.out.println(tagihanKamar);

                                        do {
                                            System.out.print("Kategori Penyakit   : ");
                                            penyakit = input.nextLine();
                                            if (penyakit.equalsIgnoreCase("sedang")
                                                    || penyakit.equalsIgnoreCase("berat")
                                                    || penyakit.equalsIgnoreCase("kronis")) {
                                                break;
                                            } else {
                                                System.out.println("Kategori Penyakit Salah (sedang/berat/kronis)");
                                            }

                                        } while (true);

                                        do {
                                            System.out.print("=> Jumlah Layanan Obat  : ");
                                            if (input.hasNextInt()) {
                                                obat = input.nextInt();
                                                break;

                                            } else {
                                                input.next(); // Membersihkan masukan yang tidak valid
                                                System.out.println("Input Invalid. Harap masukkan angka.");
                                            }
                                        } while (true);

                                        if (biodataPasien[kodePasien - 1][4].equalsIgnoreCase("y")) {
                                            keringanan = 0.7;
                                        } else if (biodataPasien[kodePasien - 1][4].equalsIgnoreCase("n")) {
                                            keringanan = 1;
                                        }

                                        if (penyakit.equalsIgnoreCase("sedang")) {
                                            hargaKatPenyakit = 100000;
                                            hargaObat = 50000;
                                        } else if (penyakit.equalsIgnoreCase("berat")) {
                                            hargaKatPenyakit = 400000;
                                            hargaObat = 200000;
                                        } else if (penyakit.equalsIgnoreCase("kronis")) {
                                            hargaKatPenyakit = 1000000;
                                            hargaObat = 500000;
                                        }

                                        // Rumus Tagihan
                                        tagihan = (((obat * hargaObat) + hargaKatPenyakit) + tagihanKamar) * keringanan;

                                        TagihanDanApakahDonasi(input);
                                        // Menyimpan transaksi
                                        menyimpanTransaksi();

                                    }

                                    // Blok Jika Pasien Tidak Rawat Inap
                                    else if (biodataPasien[kodePasien - 1][8] == null) {

                                        do {
                                            System.out.print("Kategori Penyakit   : ");
                                            penyakit = input.nextLine();
                                            if (penyakit.equalsIgnoreCase("biasa")
                                                    || penyakit.equalsIgnoreCase("sedang")
                                                    || penyakit.equalsIgnoreCase("berat")) {
                                                break;
                                            } else {
                                                System.out.println("Kategori Penyakit Salah (biasa/sedang/berat)");
                                            }

                                        } while (true);

                                        do {
                                            System.out.print("=> Jumlah Layanan Obat  : ");
                                            if (input.hasNextInt()) {
                                                obat = input.nextInt();
                                                break;

                                            } else {
                                                input.next(); // Membersihkan masukan yang tidak valid
                                                System.out.println("Input Invalid. Harap masukkan angka.");
                                            }
                                        } while (true);

                                        if (biodataPasien[kodePasien - 1][4].equalsIgnoreCase("y")) {
                                            keringanan = 0.7;
                                        } else if (biodataPasien[kodePasien - 1][4].equalsIgnoreCase("n")) {
                                            keringanan = 1;
                                        }

                                        if (penyakit.equalsIgnoreCase("biasa")) {
                                            hargaKatPenyakit = 50000;
                                            hargaObat = 30000;
                                        } else if (penyakit.equalsIgnoreCase("sedang")) {
                                            hargaKatPenyakit = 100000;
                                            hargaObat = 50000;
                                        } else if (penyakit.equalsIgnoreCase("berat")) {
                                            hargaKatPenyakit = 200000;
                                            hargaObat = 100000;
                                        }

                                        // Logika Tagihan
                                        tagihan = ((obat * hargaObat) + hargaKatPenyakit) * keringanan;

                                        TagihanDanApakahDonasi(input);
                                        // Menyimpan transaksi
                                        menyimpanTransaksi();

                                    }

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
                    // System.out.println("Menu 3");
                    boolean adaPasien3 = false;
                    for (int i = 0; i < biodataPasien.length; i++) {
                        if (biodataPasien[i][0] != null) {
                            adaPasien3 = true;
                        }
                    }

                    if (adaPasien3 == true) {
                        do {
                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("|            Daftarkan Kamar Pasien           |");
                            System.out.println("|---------------------------------------------|");
                            System.out.println("| (?) Ketik (cari) untuk mencari Kode Pasien  |");
                            System.out.println("| (?) Ketik (kembali) untuk kembali ke menu   |");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("=> Masukan Kode Pasien : ");
                            String bayarPasien = input.nextLine();

                            if (bayarPasien.equalsIgnoreCase("cari")) {
                                System.out.print("=> Masukan Nama Pasien : ");
                                String cariPasien = input.nextLine();

                                boolean ditemukan = false;
                                for (iGlobal = 0; iGlobal < biodataPasien.length; iGlobal++) {
                                    if (biodataPasien[iGlobal][0] != null
                                            && biodataPasien[iGlobal][0].contains(cariPasien)) {

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

                                    System.out.println("========================================");
                                    System.out.println("|          Pasien Nomor " + kodePasien);

                                    printBioKodePasien();

                                    if (biodataPasien[kodePasien - 1][8] != null) {
                                        System.out.println(
                                                "| Sudah Terdaftar di Kamar " + biodataPasien[kodePasien - 1][8]);
                                        System.out.println("========================================");
                                    }

                                    while (biodataPasien[kodePasien - 1][8] == null) {
                                        System.out.println("\n========================================");
                                        System.out.println("|       1. VIP     (1.500.000,00/malam)|");
                                        System.out.println("|       2. Reguler (800.000,00/malam  )|");
                                        System.out.println("|       3. Bersama (400.000,00/malam  )|");
                                        System.out.println("========================================");
                                        // Engko Logika Pesan Kamar Pasien array ke 8 Kamar Pasien
                                        int pilihKamar;
                                        boolean adaKamarVip = false, adaKamarReg = false, adaKamarBersm = false;

                                        do { // Looping jika input tidak sesuai
                                            System.out.print("=> Pilih Menu : ");
                                            if (input.hasNextInt()) {
                                                pilihKamar = input.nextInt();
                                                if (pilihKamar >= 1 && pilihKamar <= 4) {
                                                    input.nextLine(); // Membersihkan masukan yang tidak valid
                                                    break; // Keluar dari perulangan jika masukan sesuai
                                                } else {
                                                    System.out
                                                            .println("Tidak Tersedia. Masukan Angka 1 - 4 Sesuai Menu");
                                                }

                                            } else {
                                                input.nextLine(); // Membersihkan masukan yang tidak valid
                                                System.out.println("Input Invalid. Harap masukkan angka.");
                                            }

                                        } while (true);

                                        if (pilihKamar == 1) {
                                            System.out.println("Kamar VIP");

                                            for (int i = 0; i < kamarVIP.length; i++) {
                                                if (kamarVIP[i][0] == null) {
                                                    adaKamarVip = true;
                                                    biodataPasien[kodePasien - 1][8] = "VIP";
                                                    for (int j = 0; j < kamarVIP[i].length; j++) {
                                                        kamarVIP[i][j] = biodataPasien[kodePasien - 1][j];
                                                    }
                                                    System.out.println("Pasien didaftarkan di KamarVIP " + (i + 1));
                                                    biodataPasien[kodePasien-1][9] = Integer.toString((i + 1));
                                                    break;
                                                } else if (kamarVIP[i][0] != null) {
                                                    continue;
                                                }

                                            }

                                            if (adaKamarVip == false) {
                                                System.out.println("Kamar VIP Penuh");
                                            } else if (adaKamarVip == true) {
                                                break;
                                            }

                                        } else if (pilihKamar == 2) {

                                            for (int i = 0; i < kamarReguler.length; i++) {
                                                if (kamarReguler[i][0] == null) {
                                                    adaKamarReg = true;
                                                    biodataPasien[kodePasien - 1][8] = "Reguler";
                                                    for (int j = 0; j < kamarReguler[i].length; j++) {
                                                        kamarReguler[i][j] = biodataPasien[kodePasien - 1][j];
                                                    }
                                                    System.out.println("Pasien didaftarkan di KamarReguler " + (i + 1));
                                                    biodataPasien[kodePasien-1][9] = Integer.toString((i + 1));
                                                    break;
                                                } else if (kamarReguler[i][0] != null) {
                                                    continue;
                                                }

                                            }

                                            if (adaKamarReg == false) {
                                                System.out.println("Kamar Reguler Penuh");
                                            } else if (adaKamarReg == true) {
                                                break;
                                            }

                                        } else if (pilihKamar == 3) {

                                            for (int i = 0; i < kamarBersama.length; i++) {
                                                for (int j = 0; j < kamarBersama[i].length; j++) {
                                                    if (kamarBersama[i][j][0] == null) {
                                                        adaKamarBersm = true;
                                                        biodataPasien[kodePasien - 1][8] = "Bersama";
                                                        for (int k = 0; k < kamarBersama[i][j].length; k++) {
                                                            kamarBersama[i][j][k] = biodataPasien[kodePasien - 1][k];
                                                        }
                                                        System.out.println(
                                                                "Pasien didaftarkan di KamarBersama " + (i + 1));
                                                        biodataPasien[kodePasien-1][9] = Integer.toString((i + 1));

                                                        break;
                                                    }
                                                }
                                                if (adaKamarBersm == true) {
                                                    break;
                                                }
                                            }

                                            if (adaKamarBersm == false) {
                                                System.out.println("Kamar Bersama Penuh");
                                            } else if (adaKamarBersm == true) {
                                                break;
                                            }

                                        }
                                    }

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
                            System.out.println(biodataPasien[iGlobal][8]);
                            semuaKosong = false;
                        }

                    }

                    if (semuaKosong) {
                        noActivePasien();
                    }
                    break;

                case 5:
                    System.out.println("Cek Kamar");
                    boolean backToMenu = false;
                    do {
                        System.out.println("\n========================================");
                        System.out.println("|      Cek Ketersediaan Kamar Pasien   |");
                        System.out.println("|--------------------------------------|");
                        System.out.println("|        1. Kamar VIP                  |");
                        System.out.println("|        2. Kamar Reguler              |");
                        System.out.println("|        3. Kamar Bersama              |");
                        System.out.println("|        4. Kembali                    |");
                        System.out.println("========================================");
                        // System.out.print("=> Masukan Pilihan : ");
                        int cekKamar; // = input.nextInt();

                        do { // Looping jika input tidak sesuai
                            System.out.print("=> Masukan Pilihan : ");
                            if (input.hasNextInt()) {
                                cekKamar = input.nextInt();
                                if (cekKamar >= 1 && cekKamar <= 4) {
                                    break; // Keluar dari perulangan jika masukan sesuai
                                } else {
                                    System.out.println("Tidak Tersedia. Masukan Angka 1 - 4 Sesuai Menu");
                                }
                            } else {
                                input.next(); // Membersihkan masukan yang tidak valid
                                System.out.println("Input Invalid. Harap masukkan angka.");
                            }
                        } while (true);
                        input.nextLine();

                        boolean adaYangInap = false;
                        switch (cekKamar) {
                            case 1:
                                for (int i = 0; i < kamarVIP.length; i++) {
                                    if (kamarVIP[i][0] != null) {
                                        System.out.println("Nama Pasien : " + kamarVIP[i][0]);
                                        adaYangInap = true;
                                    }
                                }

                                if (adaYangInap == false) {
                                    System.out.println("Belum Ada Pasien Yang Menginab");
                                }
                                break;

                            case 2:
                                for (int i = 0; i < kamarReguler.length; i++) {
                                    if (kamarReguler[i][0] != null) {
                                        System.out.println("Nama Pasien : " + kamarReguler[i][0]);
                                        adaYangInap = true;
                                    }
                                }

                                if (adaYangInap == false) {
                                    System.out.println("Belum Ada Pasien Yang Menginab");
                                }
                                break;

                            case 3:
                                for (int i = 0; i < kamarBersama.length; i++) {
                                    for (int j = 0; j < kamarBersama[i].length; j++) {
                                        if (kamarBersama[i][j][0] != null) {
                                            System.out.println("Nama Pasien : " + kamarBersama[i][j][0]);
                                            adaYangInap = true;
                                        }
                                    }
                                }

                                if (adaYangInap == false) {
                                    System.out.println("Belum Ada Pasien Yang Menginab");
                                }
                                break;

                            case 4:
                                // input.nextLine();
                                char kembali;
                                while (true) { // Perulangan jika input bukan y/n
                                    System.out.print("=> Konfirmasi Kembali (y/n): ");
                                    kembali = input.nextLine().charAt(0);
                                    if ((kembali == 'y' || kembali == 'Y')
                                            || (kembali == 'n' || kembali == 'N')) { // Apakah ingin mendaftarkan lagi
                                        break;
                                    } else {
                                        System.out.println("input invalid Masukan y/n");
                                    }
                                }

                                if (kembali == 'y' || kembali == 'Y') {
                                    backToMenu = true;
                                }
                                break;
                        }
                    } while (backToMenu == false);

                    break;

                case 6:
                    // Logout dan kembali ke menu login
                    do {
                        System.out.print("Konfirmasi Logout y/n : ");
                        char logout = input.nextLine().charAt(0);

                        if ((logout == 'n') || (logout == 'N')) {
                            break;
                        } else if ((logout == 'y') || (logout == 'Y')) {
                            return;
                        } else {
                            System.out.println("Input invalid masukan y/n");
                        }
                    } while (true);

                    // default:
                    // System.out.println("Pilihan Tidak Tersedia");

            }
        }
    }

    // Method untuk login sebagai Manager
    private static void loginManager(Scanner input) {

        // Scanner input = new Scanner(System.in);
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|       Anda akan login sebagai Manager       |");
        System.out.println("|    Silahkan masukan Username dan Password   |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        String userMan, passMan;

        int attemptManager = 0;
        do {
            System.out.print("=> Masukan Username : ");
            userMan = input.nextLine();
            System.out.print("=> Masukan Password : ");
            passMan = input.nextLine();

            if (!((userMan.equals("manager")) && (passMan.equals("manager")))) {
                attemptManager++;
                userPwSalah();
            } else {
                break;
            }
        } while (attemptManager < 3);
        if (attemptManager == 3) {
            System.out.println("Anda gagal login sebanyak 3 kali. Program keluar.");
            System.exit(0);
        }

        while (true) {
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
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
                if (input.hasNextInt()) {
                    menuManager = input.nextInt();
                    if (menuManager >= 1 && menuManager <= 3) {
                        break; // Keluar dari perulangan jika masukan sesuai
                    } else {
                        System.out.println("Tidak Tersedia. Masukan Angka 1 - 3 Sesuai Menu");
                    }
                } else {
                    input.next(); // Membersihkan masukan yang tidak valid
                    System.out.println("Input Invalid. Harap masukkan angka.");
                }
            } while (true);
            input.nextLine();

            switch (menuManager) {
                case 1:
                    // Menu Laporan Keuangan
                    System.out.println("Menu Laporan Keuangan");
                    System.out.println("========================================");
                    System.out.println("Masukkan kode pasien: ");
                    System.out.println("========================================");
                    String kodePasien = input.nextLine();
                    System.out.println("========================================");

                    // mencari kode pasien
                    int indexPasien = -1;
                    for (int i = 0; i < biodataPasien.length; i++) {
                        if (biodataPasien[i][7].equals(kodePasien)) {
                            indexPasien = i;
                            break; // Correct usage of break inside the loop
                        }
                    }

                    if (indexPasien != -1) {
                        System.out.println("=========================================");
                        System.out.println("|Laporan Keuangan Pasien Rumah Sakit cinta Java");
                        System.out.println("|       Pasien Nomor " + biodataPasien[indexPasien][7]);
                        System.out.println("|=> Nama Pasien     : " + biodataPasien[indexPasien][0]);
                        System.out.println("|=> Alamat Pasien   : " + biodataPasien[indexPasien][1]);
                        System.out.println("|=> Nomer HP        : " + biodataPasien[indexPasien][2]);
                        System.out.println("|=> Penyakit Pasien : " + biodataPasien[indexPasien][3]);
                        System.out.println("|=> Kode Pasien     : " + biodataPasien[indexPasien][7]);
                        System.out.println("========================================");
                        // info detail Laporan keuangan 
                       System.out.println("================================================");
                       System.out.println("Laporan Keuangan Secara Detail ");
                       System.out.println("================================================");
                       System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
        
                        System.out.println("| => Nama Pasien     : ");
                        System.out.println("| => Alamat Pasien   : ");
                        System.out.println("| => Nomer HP Pasien : ");
                        System.out.println("| => Penyakit Pasien : ");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("| => Total Tagihan   : ");
                        System.out.println("| => Total Bayar     : ");
                        System.out.println("| => Total Kembalian : ");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("| => Total Donasi    : ");
                        System.out.println("| => Kembalian Akhir : ");
        
                       System.out.println("================================================");

                    } else {
                        System.out.println("Pasien dengan kode " + kodePasien + " tidak ditemukan.");
                    }

                    break;

                case 2:
                    // Menu Riwayat Transaksi
                    System.out.println("Menu Riwayat Transaksi");

                    for (iGlobal = 0; iGlobal < biodataPasien.length; iGlobal++) {
                        if (transactions[iGlobal][0] != 0) {
                            System.out.println("Detail Transaksi untuk Pasien " + (iGlobal + 1));
                            System.out.println("Total Tagihan: " + transactions[iGlobal][0]);
                            System.out.println("Jumlah Pembayaran: " + transactions[iGlobal][1]);
                            System.out.println("Kembalian: " + transactions[iGlobal][2]);
                            System.out.println("Donasi: " + transactions[iGlobal][3]);
                        } else {
                            System.out.println("Tidak ada transaksi untuk pasien " + (iGlobal + 1));
                        }

                    }

                    break;

                case 3:
                    // Logout dan kembali ke menu login
                    do {
                        System.out.print("Konfirmasi Logout y/n : ");
                        char logout = input.nextLine().charAt(0);

                        if ((logout == 'n') || (logout == 'N')) {
                            break;
                        } else if ((logout == 'y') || (logout == 'Y')) {
                            return;
                        } else {
                            System.out.println("Input invalid masukan y/n");
                        }
                    } while (true);

                    // default:
                    // System.out.println("Pilihan Tidak Tersedia");
            }
        }
    }

    static int tagihanKamarPasien(int kode, long hari) {
        int tagihanKamar = 0;
        if (biodataPasien[kodePasien - 1][8] == "VIP") {
            tagihanKamar = 1500000 * (int) hari;
        } else if (biodataPasien[kodePasien - 1][8] == "Reguler") {
            tagihanKamar = 800000 * (int) hari;
        } else if (biodataPasien[kodePasien - 1][8] == "Bersama") {
            tagihanKamar = 800000 * (int) hari;
        }
        return tagihanKamar;
    }

    static void TagihanDanApakahDonasi(Scanner input) {
        System.out.println("=> Total Tagihan            : " + (int) tagihan);
        System.out.print("=> Bayar Sekarang           : ");
        bayar = input.nextInt();
        kembalian = bayar - (int) tagihan;
        System.out.println("== Kembalian                : " + kembalian);
        input.nextLine();

        do {
            System.out.print("=> Donasikan Kembalian  y/n : ");
            apaDonasi = input.nextLine();
            if (apaDonasi.equalsIgnoreCase("y") || apaDonasi.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Input Invalid");
            }

        } while (true);

        if (apaDonasi.equalsIgnoreCase("y")) {
            System.out.print("=> Donasikan Semuanya? y/n  : ");
            apaDonasiSemua = input.nextLine();

            if (apaDonasiSemua.equalsIgnoreCase("y")) {
                donasi = kembalian;
                // transactions[kodePasien - 1][3] = donasi;
            } else if (apaDonasiSemua.equalsIgnoreCase("n")) {
                System.out.print("=> Masukan Besar Donasi     : ");
                donasi = input.nextInt();
                // transactions[kodePasien - 1][3] = donasi;
                input.nextLine();
            }
            kembalianAkhir = kembalian - donasi;

        }

        else if (apaDonasi.equalsIgnoreCase("n")) {
            donasi = 0;

            kembalianAkhir = kembalian;
        }

        System.out.println(donasi);

    }

    static void menyimpanTransaksi() {
        transactions[kodePasien - 1][0] = (int)tagihan;
        transactions[kodePasien - 1][1] = bayar;
        transactions[kodePasien - 1][2] = kembalian;
        transactions[kodePasien - 1][3] = donasi;
    }

    static void cetakTransaksiDanHapusBiodata() {
        // Nota/Bukti Pembayaran
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|              Bukti Pembayaran               |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| => Nama Pasien     : ");
        System.out.println("| => Alamat Pasien   : ");
        System.out.println("| => Nomer HP Pasien : ");
        System.out.println("| => Penyakit Pasien : ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| => Total Tagihan   : ");
        System.out.println("| => Total Bayar     : ");
        System.out.println("| => Total Kembalian : ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| => Total Donasi    : ");
        System.out.println("| => Kembalian Akhir : ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|              Bukti Pembayaran               |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

    }

    static void userPwSalah() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|        Username Atau Passwordmu Salah       |");
        System.out.println("|              Masukan yang Benar             |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
    }

    static void printBiodataPasien() {
        System.out.println("========================================");
        System.out.println("|       Pasien Nomor " + biodataPasien[iGlobal][7]);
        System.out.println("|=> Nama Pasien     : " + biodataPasien[iGlobal][0]);
        System.out.println("|=> Alamat Pasien   : " + biodataPasien[iGlobal][1]);
        System.out.println("|=> Nomer HP        : " + biodataPasien[iGlobal][2]);
        System.out.println("|=> Penyakit Pasien : " + biodataPasien[iGlobal][3]);
        System.out.println("|=> Kode Pasien     : " + biodataPasien[iGlobal][7]);
        System.out.println("========================================\n");
    }

    static void noActivePasien() {
        System.out.println("========================================");
        System.out.println("|     Tidak Ada Pasien yang Aktif      |");
        System.out.println("========================================\n");
    }

    static void printBioKodePasien() {
        System.out.println("|=> Nama Pasien     : " + biodataPasien[kodePasien - 1][0]);
        System.out.println("|=> Alamat Pasien   : " + biodataPasien[kodePasien - 1][1]);
        System.out.println("|=> Nomer HP        : " + biodataPasien[kodePasien - 1][2]);
        System.out.println("|=> Penyakit Pasien : " + biodataPasien[kodePasien - 1][3]);
        System.out.println("|=> Kode Pasien     : " + biodataPasien[kodePasien - 1][7]);
        System.out.println("========================================");
    }

    private static long hitungSelisihHari(LocalDate tanggalCheskIn, LocalDate tanggalCheckOut) {
        return ChronoUnit.DAYS.between(tanggalCheskIn, tanggalCheckOut);
    }

    private static LocalDate inputTanggal(String prompt, DateTimeFormatter formatter, Scanner scanner) {
        LocalDate tanggal = null;
        boolean formatValid = false;

        do {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                tanggal = LocalDate.parse(input, formatter);
                formatValid = true;
            } catch (Exception e) {
                System.out.println("Format tanggal tidak valid. Silakan coba lagi.");
            }

        } while (!formatValid);

        return tanggal;
    }
}
