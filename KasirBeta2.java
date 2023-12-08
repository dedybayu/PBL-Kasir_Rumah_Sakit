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
            int menuLogSbg = 3;
            int loginSbg = pilihMenu(menuLogSbg, input);

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
                    System.out.print("=> Konfirmasi Keluar y/n : ");
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

    // Username dan Password
    private static String[][] usernamePassword = { { "alek", "alek" }, { "manager", "manager" } };
    // Deklarasi Format Tanggal
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Deklarasi Variable Array untuk Menyimpan Data Pasien
    private static String[][] biodataPasien = new String[100][11];

    // Deklarasi Untuk Riwayat Pasien
    private static String[][] detailRiwayatTransaksi = new String[100][15];
    // [0]nama [1]alamat [2]nohp [3]pnykt [4]bpjs [5]tglmsk [6]tglkluar [7]lmainap
    // [8]obat [9]tagihan [10]bayar [11]kembalian [12]donasi [13]kembalianAKhir

    // Deklarasi Untuk Pasien yang menginab
    private static String[][] kamarVIP = new String[5][11]; // Ada 5 Kamar VIP
    private static String[][] kamarReguler = new String[10][11]; // Ada 10 Kamar Reguler
    private static String[][][] kamarBersama = new String[10][2][11]; // Ada 10 Kamar Bersama 1kmr 2orng
    // Penjelasan Array[kamar][biodata pasien] untuk 2D
    // Penjelasan Array[kamar][pasien 1,2][biodata pasien] untuk 3D

    // Deklarasi untuk Pembayaran
    private static int totalPendapatan = 0, totalDonasi = 0;

    // Deklarasi untuk looping
    private static int riwayat = 0; // untuk loping smua riwayat riwayat
    private static int iGlobal, kodePasien;
    // private static Char

    // Deklarasi variabel buat logika Pembayaran
    private static int obat = 0, hargaObat = 0, hargaKatPenyakit = 0, kembalian = 0, bayar = 0, tagihanKamar = 0;
    private static double keringanan = 0, tagihan = 0;
    private static int donasi = 0, kembalianAkhir = 0;
    private static String penyakit, apaDonasi, apaDonasiSemua;
    private static long selisihHari;

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

            if (!((userAdm.equals(usernamePassword[0][0])) && (passAdm.equals(usernamePassword[0][1])))) {
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
        LocalDate tanggalMasuk = LocalDate.now();
        // Pemberian Pasien Awal Buat Uji Coba
        biodataPasien[0][0] = "Alek";
        biodataPasien[0][1] = "Banyuwangi";
        biodataPasien[0][2] = "08212432";
        biodataPasien[0][3] = "batuk";
        biodataPasien[0][4] = "y";
        biodataPasien[0][5] = tanggalMasuk.format(formatter);
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
            System.out.println("|          5. Cek Kamar                       |");
            System.out.println("|          6. Riwayat Transaksi               |");
            System.out.println("|          7. Bantuan                         |");
            System.out.println("|          8. Ubah Password                   |");
            System.out.println("|          9. Logout                          |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            int menuAdminMaks = 9;
            int menuAdmin = pilihMenu(menuAdminMaks, input);

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

                            // LocalDate tanggalMasuk = inputTanggal("Tanggal Masuk : ", formatter, input);
                            biodataPasien[idx][5] = tanggalMasuk.format(formatter); // 5 Tanggal Masuk 6 Tanggal
                                                                                    // Keluar
                            System.out.println("=> Tanggal Masuk        : " + biodataPasien[idx][5]);

                            biodataPasien[idx][7] = Integer.toString((idx + 1)); // menrubah int menjadi String
                            System.out.println("=> Kode Pasien Adalah   : " + (biodataPasien[idx][7])); // 7 : Kode
                                                                                                        // Pasien
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

                            char daftarLagi;
                            while (true) { // Perulangan jika input bukan y/n
                                System.out.print("=> Daftarkan Lagi (y/n) : ");
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
                                            && biodataPasien[iGlobal][0].toLowerCase()
                                                    .contains(cariPasien.toLowerCase())) { // Diganti contains

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
                                        System.out.println("|=> Pasien Kamar " + biodataPasien[kodePasien - 1][8] + " "
                                                + biodataPasien[kodePasien - 1][9]);
                                        System.out.println("|=> Tanggal Masuk   : " + biodataPasien[kodePasien - 1][5]);
                                        System.out.println("========================================");

                                        LocalDate tanggalKeluar = inputTanggal("=> Tanggal Keluar      : ", formatter,
                                                input);
                                        biodataPasien[kodePasien - 1][6] = tanggalKeluar.format(formatter);

                                        LocalDate tanggalCheskIn = LocalDate.parse(biodataPasien[kodePasien - 1][5],
                                                formatter);
                                        LocalDate tanggalCheckOut = LocalDate.parse(biodataPasien[kodePasien - 1][6],
                                                formatter);

                                        selisihHari = hitungSelisihHari(tanggalCheskIn, tanggalCheckOut);

                                        // Untuk Tagihan Kamar
                                        tagihanKamar = tagihanKamarPasien((kodePasien - 1), selisihHari);

                                        do {
                                            System.out.println("Kategori (sedang/berat/kronis)");
                                            System.out.print("=> Kategori Penyakit   : ");
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
                                            System.out.print("=> Jumlah Layanan Obat : ");
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

                                        cetakTransaksiDanHapusBiodata(selisihHari);

                                        riwayat++;

                                    }

                                    // Blok Jika Pasien Tidak Rawat Inap
                                    else if (biodataPasien[kodePasien - 1][8] == null) {

                                        do {
                                            System.out.println("Kategori (biasa/sedang/berat)");
                                            System.out.print("=> Kategori Penyakit   : ");
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
                                            System.out.print("=> Jumlah Layanan Obat : ");
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

                                        biodataPasien[kodePasien - 1][6] = biodataPasien[kodePasien - 1][5];

                                        TagihanDanApakahDonasi(input);
                                        // Menyimpan transaksi
                                        menyimpanTransaksi();

                                        selisihHari = 0;

                                        // Mencetak Nota transaksi dan menghapus data pasien
                                        cetakTransaksiDanHapusBiodata(selisihHari);

                                        riwayat++;

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
                            break;
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
                                            && biodataPasien[iGlobal][0].toLowerCase()
                                                    .contains(cariPasien.toLowerCase())) {

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
                                        int menuMaks = 3;
                                        int pilihKamar = pilihMenu(menuMaks, input);

                                        boolean adaKamarVip = false, adaKamarReg = false, adaKamarBersm = false;

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
                                                    biodataPasien[kodePasien - 1][9] = Integer.toString((i + 1));
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
                                                    biodataPasien[kodePasien - 1][9] = Integer.toString((i + 1));
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
                                                        biodataPasien[kodePasien - 1][9] = Integer.toString((i + 1));
                                                        biodataPasien[kodePasien - 1][10] = Integer.toString((j + 1));

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
                    // Menu Data Pasien
                    boolean adaData = false;
                    for (int i = 0; i < biodataPasien.length; i++) {
                        if (biodataPasien[i][0] != null) {
                            adaData = true;
                        }
                    }
                    if (adaData == true) {
                        boolean kembaliDariData = false;
                        do {
                            System.out.println("\n========================================");
                            System.out.println("|           Cek Data Pasien            |");
                            System.out.println("|   1. Tampilkan Semua Data Pasien     |");
                            System.out.println("|   2. Cari Data Pasien                |");
                            System.out.println("|   3. Kembali                         |");
                            System.out.println("========================================");
                            int menuCekDataMaks = 3;
                            int menuCekData = pilihMenu(menuCekDataMaks, input);

                            switch (menuCekData) {
                                case 1:
                                    for (iGlobal = 0; iGlobal < biodataPasien.length; iGlobal++) {
                                        if (biodataPasien[iGlobal][0] != null) {
                                            printBiodataPasien();
                                            if (biodataPasien[iGlobal][8] != null) {
                                                System.out.println("|=> Kamar Pasien    : " + biodataPasien[iGlobal][8]
                                                        + " " + biodataPasien[iGlobal][9]);
                                                System.out.println("========================================");

                                            }
                                        }

                                    }
                                    input.nextLine();
                                    break;

                                case 2:
                                    String cariNama;
                                    boolean namaDitemukan = false;
                                    System.out.print("=> Masukan Nama Pasien : ");
                                    cariNama = input.nextLine();
                                    for (iGlobal = 0; iGlobal < biodataPasien.length; iGlobal++) {
                                        if (biodataPasien[iGlobal][0] != null
                                                && biodataPasien[iGlobal][0].toLowerCase()
                                                        .contains(cariNama.toLowerCase())) { // Diganti contains

                                            printBiodataPasien();
                                            if (biodataPasien[iGlobal][8] != null) {
                                                System.out.println("|=> Kamar Pasien    : " + biodataPasien[iGlobal][8]
                                                        + " " + biodataPasien[iGlobal][9]);
                                                System.out.println("========================================");

                                            }
                                            namaDitemukan = true;
                                        }
                                    }
                                    if (namaDitemukan == false) {
                                        System.out.println("Nama Pasien Tidak Ditemukan");
                                    }
                                    input.nextLine();
                                    break;

                                case 3:
                                    System.out.print("Kembali ke Menu");
                                    kembaliDariData = true;
                                    break;
                            }

                        } while (kembaliDariData == false);

                    } else {
                        noActivePasien();
                    }

                    break;

                case 5:
                    // System.out.println("Cek Kamar");
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
                        int menuCekKamrMaks = 4;
                        int kamarKosong = 0, kamarTerisi = 0;
                        int cekKamar = pilihMenu(menuCekKamrMaks, input);

                        boolean adaYangInap = false;
                        switch (cekKamar) {
                            case 1:
                                System.out.println("========================================");
                                System.out.println("|     Daftar Nama Pasien Kamar VIP     |");
                                System.out.println("========================================");
                                for (int i = 0; i < kamarVIP.length; i++) {
                                    if (kamarVIP[i][0] != null) {
                                        System.out.println("|=> VIP " + (i + 1) + ", Pasien " + kamarVIP[i][7] + " : "
                                                + kamarVIP[i][0]);
                                        adaYangInap = true;
                                        kamarTerisi++;
                                    } else {
                                        kamarKosong++;
                                    }
                                }

                                if (adaYangInap == false) {
                                    System.out.println("|    Belum Ada Yang Menginap di VIP    |");
                                }
                                System.out.println("========================================");
                                System.out.println(
                                        "|  Kamar Kosong: " + kamarKosong + "   Kamar Terisi: " + kamarTerisi);
                                System.out.println("========================================");
                                break;

                            case 2:
                                System.out.println("========================================");
                                System.out.println("|   Daftar Nama Pasien Kamar Reguler   |");
                                System.out.println("========================================");
                                for (int i = 0; i < kamarReguler.length; i++) {
                                    if (kamarReguler[i][0] != null) {
                                        System.out.println("|=> Reguler " + (i + 1) + ", Pasien " + kamarReguler[i][7]
                                                + " : " + kamarReguler[i][0]);
                                        adaYangInap = true;
                                        kamarTerisi++;
                                    } else {
                                        kamarKosong++;
                                    }
                                }

                                if (adaYangInap == false) {
                                    System.out.println("|  Belum Ada Yang Menginap di Reguler  |");
                                }
                                System.out.println("========================================");
                                System.out.println(
                                        "|  Kamar Kosong: " + kamarKosong + "   Kamar Terisi: " + kamarTerisi);
                                System.out.println("========================================");
                                break;

                            case 3:
                                System.out.println("========================================");
                                System.out.println("|   Daftar Nama Pasien Kamar Bersama   |");
                                System.out.println("========================================");
                                for (int i = 0; i < kamarBersama.length; i++) {
                                    for (int j = 0; j < kamarBersama[i].length; j++) {
                                        if (kamarBersama[i][j][0] != null) {
                                            System.out.println("=> Bersama " + (i + 1) + ", Pasien "
                                                    + kamarBersama[i][j][7] + " : " + kamarBersama[i][j][0]);
                                            adaYangInap = true;
                                            kamarTerisi++;
                                        } else {
                                            kamarKosong++;
                                        }
                                    }
                                }

                                if (adaYangInap == false) {
                                    System.out.println("|  Belum Ada Yang Menginab di Bersama  |");
                                }
                                System.out.println("========================================");
                                System.out.println("|  Bed Kosong: " + kamarKosong + "   Bed Terisi: " + kamarTerisi);
                                System.out.println("========================================");
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
                    if (detailRiwayatTransaksi[0][0] != null) {
                        System.out.println("========================================");
                        System.out.println("|  1. Tampilkan Semua Riwayat          |");
                        System.out.println("|  2. Cari Riwayat Transaksi (Nama)    |");
                        System.out.println("|  3. Cari Riwayat Transaksi (Tanggal) |");
                        System.out.println("========================================");
                        int menuRwytAdmMaks = 4;
                        int menuRwytAdm = pilihMenu(menuRwytAdmMaks, input);
                        switch (menuRwytAdm) {
                            case 1:
                                for (int i = 0; i < detailRiwayatTransaksi.length; i++) {
                                    if (detailRiwayatTransaksi[i][0] != null){
                                        riwayatTransaksiAdmin(i);
                                    } else {
                                        continue;
                                    }
                                }
                                break;

                            case 2:
                                char cariNamaLagi;
                                do {
                                    boolean adaRwytNama = false;
                                    System.out.println("=========================================");
                                    System.out.println("|    Cari Riwayat Berdasarkan Nama      |");
                                    System.out.println("=========================================");
                                    System.out.print("=> Masukan Nama Pasien : ");
                                    String cariRiwayat = input.nextLine();
                                    for (int i = 0; i < detailRiwayatTransaksi.length; i++) {
                                        if (detailRiwayatTransaksi[i][0] != null
                                                && detailRiwayatTransaksi[i][0]
                                                        .toLowerCase().contains(cariRiwayat.toLowerCase())) {

                                            riwayatTransaksiAdmin(i);
                                            adaRwytNama = true;
                                        }
                                    }
                                    if (adaRwytNama == false) {
                                        System.out.println("Transaksi Tidak Ditemukan\n");
                                    }
                                    System.out.print("Cari Lagi? y/n : ");
                                    cariNamaLagi = input.nextLine().charAt(0);
                                } while (!(cariNamaLagi == 'n' || cariNamaLagi == 'N'));
                                break;

                            case 3:
                                char cariTanggalLagi;
                                do {
                                    boolean adaRwytTanggal = false;
                                    System.out.println("=========================================");
                                    System.out.println("|   Cari Riwayat Berdasarkan Tanggal    |");
                                    System.out.println("=========================================");
                                    LocalDate tanggal = inputTanggal("=> Tanggal Keluar: ", formatter, input);
                                    String cariTanggal = tanggal.format(formatter);

                                    for (int i = 0; i < detailRiwayatTransaksi.length; i++) {
                                        if (detailRiwayatTransaksi[i][0] != null
                                                && detailRiwayatTransaksi[i][6].equals(cariTanggal)) {
                                            riwayatTransaksiAdmin(i);
                                            adaRwytTanggal = true;
                                        }
                                    }
                                    if (adaRwytTanggal == false) {
                                        System.out.println("Transaksi Tidak Ditemukan\n");
                                    }
                                    System.out.print("Cari Lagi? y/n : ");
                                    cariTanggalLagi = input.nextLine().charAt(0);
                                } while (!(cariTanggalLagi == 'n' || cariTanggalLagi == 'N'));
                                break;

                            case 4:
                                System.out.println("Kembali Ke Menu\n");
                                break;
                        }
                    } else {
                        System.out.println("========================================");
                        System.out.println("|     Belum Ada Riwayat Transaksi      |");
                        System.out.println("========================================");
                    }
                    break;

                case 7:
                    System.out.println("==========================================================");
                    System.out.println("|                     Menu Bantuan                       |");
                    System.out.println("==========================================================");
                    System.out.println("|               Keguanaan Menu di Manager                |");
                    System.out.println("==========================================================");
                    System.out.println("|1. Daftarkan Pasien                                     |");
                    System.out.println("|   Digunakan untuk mendaftarkan biodata pasien          |");
                    System.out.println("|2. Bayar Tagihan Pasien                                 |");
                    System.out.println("|   Berguna untuk membayar tagihan pasien yang terdaftar |");
                    System.out.println("|3. Pesankan Kamar Pasien                                |");
                    System.out.println("|   Digunakan untuk memesankan kamar bagi rawat inab     |");
                    System.out.println("|4. Data Pasien                                          |");
                    System.out.println("|   Digunakan untuk melihat daftar pasien yang terdaftar |");
                    System.out.println("|5. Cek Kamar                                            |");
                    System.out.println("|   Digunakan untuk melihat ketersediaan kamar pasien    |");
                    System.out.println("|   dan melihat data pasien yang menginab di kamar       |");
                    System.out.println("|6. Bantuan                                              |");
                    System.out.println("|   Digunakan untuk nampilkan bantuan pnggunaan aplikasi |");
                    System.out.println("|7. Ubah Password                                        |");
                    System.out.println("|   Digunakan untuk nampilkan bantuan pnggunaan aplikasi |");
                    System.out.println("|8. Logout                                               |");
                    System.out.println("|   Digunakan untuk keluar dari menu admin               |");
                    System.out.println("==========================================================");
                    break;

                case 8:
                    String usrLama, passLama, usrBaru, passBaru, confrmPass;
                    int attemptGanti = 0;
                    System.out.println("\n=========================================");
                    System.out.println("|          Ubah Password Admin          |");
                    System.out.println("=========================================");
                    do {
                        System.out.print("=> Masukan Username Lama : ");
                        usrLama = input.nextLine();
                        System.out.print("=> Masukan Password Lama : ");
                        passLama = input.nextLine();

                        if (!(usrLama.equals(usernamePassword[0][0]) && passLama.equals(usernamePassword[0][1]))) {
                            attemptGanti++;
                            userPwSalah();
                        } else {
                            System.out.println("=========================================");
                            System.out.print("=> Masukan Username Baru    : ");
                            usrBaru = input.nextLine();
                            System.out.print("=> Masukan Password Baru    : ");
                            passBaru = input.nextLine();
                            do {
                                System.out.print("=> Konfirmasi Password Baru : ");
                                confrmPass = input.nextLine();
                                if (!confrmPass.equals(passBaru)) {
                                    System.out.println("=========================================");
                                    System.out.println("|    Konfirmasi Password Tidak Sama     |");
                                    System.out.println("=========================================");
                                }
                            } while (!confrmPass.equals(passBaru));

                            usernamePassword[0][0] = usrBaru;
                            usernamePassword[0][1] = confrmPass;

                            System.out.println("=========================================");
                            System.out.println("| Username dan Password Berhasil Diubah |");
                            System.out.println("|        Silahkan Login Kembali         |");
                            System.out.println("=========================================");
                            return;
                        }
                    } while (attemptGanti < 3);
                    if (attemptGanti == 3) {
                        System.out.println("Anda gagal 3 kali, kembali ke menu");
                        break;
                    }

                case 9:
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

            if (!((userMan.equals(usernamePassword[1][0])) && (passMan.equals(usernamePassword[1][1])))) {
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
            // System.out.println(riwayat);
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|           Selamat Datang Manager            |");
            System.out.println("|           Rumah Sakit Cinta Java            |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|              1. Laporan Keuangan            |");
            System.out.println("|              2. Riwayat Transaksi           |");
            System.out.println("|              3. Bantuan                     |");
            System.out.println("|              4. Ubah Password               |");
            System.out.println("|              5. Logout                      |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            int menuMaks = 5;
            int menuManager = pilihMenu(menuMaks, input);

            switch (menuManager) {
                case 1:
                    // Menu Laporan Keuangan
                    boolean kmbliDriLaporan = false;
                    do {
                        System.out.println("\n=========================================");
                        System.out.println("|         Menu Laporan Keuangan         |");
                        System.out.println("=========================================");
                        System.out.println("|=> Total Pendapatan : " + totalPendapatan);
                        System.out.println("|=> Total Donasi     : " + totalDonasi);
                        System.out.println("=========================================");
                        System.out.println("|   1. Riwayat Pemasukan                |");
                        System.out.println("|   2. Riwayat Pemasukan Donasi         |");
                        System.out.println("|   3. Kembali                          |");
                        System.out.println("=========================================");
                        int menuLaporanMaks = 3;
                        int menuLapKeuangan = pilihMenu(menuLaporanMaks, input);
                        switch (menuLapKeuangan) {
                            case 1:
                                if (detailRiwayatTransaksi[0][9] != null) {
                                    for (int i = 0; i < detailRiwayatTransaksi.length; i++) {
                                        if (detailRiwayatTransaksi[i][9] != null) {
                                            System.out
                                                    .println("Tanggal " + detailRiwayatTransaksi[i][6] + " Pemasukan : "
                                                            + detailRiwayatTransaksi[i][9]);
                                        }
                                    }
                                } else {
                                    System.out.println("Belum Ada Pemasukan");
                                }
                                input.nextLine();
                                break;

                            case 2:
                                if (detailRiwayatTransaksi[0][12] != null && detailRiwayatTransaksi[0][12] != "0") {
                                    for (int i = 0; i < detailRiwayatTransaksi.length; i++) {
                                        if (detailRiwayatTransaksi[i][9] != null) {
                                            System.out
                                                    .println("Tanggal " + detailRiwayatTransaksi[i][6] + " Donasi : "
                                                            + detailRiwayatTransaksi[i][12]);
                                        }
                                    }
                                } else {
                                    System.out.println("Belum Ada Pemasukan");
                                }
                                input.nextLine();
                                break;

                            case 3:
                                kmbliDriLaporan = true;
                                break;
                        }
                    } while (kmbliDriLaporan == false);
                    break;

                case 2:
                    if (detailRiwayatTransaksi[0][0] != null) {
                        boolean kembaliMenu = false;
                        do {
                            System.out.println("\n=========================================");
                            System.out.println("|     Menu Riwayat Transaksi Pasien     |");
                            System.out.println("=========================================");
                            System.out.println("|   1. Seluruh Riwayat Transaksi        |");
                            System.out.println("|   2. Riwayat Pemasukan                |");
                            System.out.println("|   3. Riwayat Donasi                   |");
                            System.out.println("|   4. Cari Riwayat Transaksi (Nama)    |");
                            System.out.println("|   5. Cari Riwayat Transaksi (Tanggal) |");
                            System.out.println("|   6. kembali                          |");
                            System.out.println("=========================================");
                            int menuRiwayatMaks = 6;
                            int riwayatTransaksi = pilihMenu(menuRiwayatMaks, input);

                            switch (riwayatTransaksi) {

                                case 1:
                                    iGlobal = 0;
                                    while (detailRiwayatTransaksi[iGlobal][0] != null) {
                                        printRiwayatTransaksi();

                                        iGlobal++;
                                    }
                                    input.nextLine();
                                    break;

                                case 2:
                                    int j = 0;
                                    while (detailRiwayatTransaksi[j][0] != null) {
                                        System.out.println("=========================================");
                                        System.out.println("| Transaksi pada Tanggal " + detailRiwayatTransaksi[j][6]);
                                        System.out.println("=========================================");
                                        System.out.println("|=> Nama Pasien : " + detailRiwayatTransaksi[j][0]);
                                        System.out.println("|=> Pemasukan   : " + detailRiwayatTransaksi[j][9]);
                                        System.out.println("========================================\n");
                                        j++;
                                    }
                                    input.nextLine();
                                    break;

                                case 3:
                                    boolean adaDonasi = false;
                                    for (int i = 0; i < detailRiwayatTransaksi.length; i++) {
                                        if ((detailRiwayatTransaksi[i][0] != null)
                                                && !detailRiwayatTransaksi[i][12].equals("0")) {
                                            System.out.println("=========================================");
                                            System.out.println(
                                                    "| Transaksi pada Tanggal " + detailRiwayatTransaksi[i][6]);
                                            System.out.println("=========================================");
                                            System.out.println("|=> Nama Pasien : " + detailRiwayatTransaksi[i][0]);
                                            System.out.println("|=> Donasi      : " + detailRiwayatTransaksi[i][12]);
                                            System.out.println("========================================\n");
                                            adaDonasi = true;
                                        }

                                    }
                                    if (adaDonasi == false) {
                                        System.out.println("Belum Pemasukan Donasi");
                                    }

                                    input.nextLine();
                                    break;

                                case 4:

                                    char cariNamaLagi;
                                    do {
                                        boolean adaRwytNama = false;
                                        System.out.println("=========================================");
                                        System.out.println("|    Cari Riwayat Berdasarkan Nama      |");
                                        System.out.println("=========================================");
                                        System.out.print("=> Masukan Nama Pasien : ");
                                        String cariRiwayat = input.nextLine();
                                        for (iGlobal = 0; iGlobal < detailRiwayatTransaksi.length; iGlobal++) {
                                            if (detailRiwayatTransaksi[iGlobal][0] != null
                                                    && detailRiwayatTransaksi[iGlobal][0]
                                                            .toLowerCase().contains(cariRiwayat.toLowerCase())) {

                                                printRiwayatTransaksi();
                                                adaRwytNama = true;
                                            }
                                        }
                                        if (adaRwytNama == false) {
                                            System.out.println("Transaksi Tidak Ditemukan\n");
                                        }
                                        System.out.print("Cari Lagi? y/n : ");
                                        cariNamaLagi = input.nextLine().charAt(0);
                                    } while (!(cariNamaLagi == 'n' || cariNamaLagi == 'N'));
                                    break;

                                case 5:
                                    char cariTanggalLagi;
                                    do {
                                        boolean adaRwytTanggal = false;
                                        System.out.println("=========================================");
                                        System.out.println("|   Cari Riwayat Berdasarkan Tanggal    |");
                                        System.out.println("=========================================");
                                        LocalDate tanggal = inputTanggal("=> Tanggal Keluar: ", formatter, input);
                                        String cariTanggal = tanggal.format(formatter);

                                        for (iGlobal = 0; iGlobal < detailRiwayatTransaksi.length; iGlobal++) {
                                            if (detailRiwayatTransaksi[iGlobal][0] != null
                                                    && detailRiwayatTransaksi[iGlobal][6].equals(cariTanggal)) {
                                                printRiwayatTransaksi();
                                                adaRwytTanggal = true;
                                            }
                                        }
                                        if (adaRwytTanggal == false) {
                                            System.out.println("Transaksi Tidak Ditemukan\n");
                                        }
                                        System.out.print("Cari Lagi? y/n : ");
                                        cariTanggalLagi = input.nextLine().charAt(0);
                                    } while (!(cariTanggalLagi == 'n' || cariTanggalLagi == 'N'));
                                    break;

                                case 6:
                                    char kembali;
                                    while (true) { // Perulangan jika input bukan y/n
                                        System.out.print("=> Konfirmasi Kembali (y/n): ");
                                        kembali = input.nextLine().charAt(0);
                                        if ((kembali == 'y' || kembali == 'Y')
                                                || (kembali == 'n' || kembali == 'N')) { // Apakah ingin mendaftarkan
                                                                                         // lagi
                                            break;
                                        } else {
                                            System.out.println("input invalid Masukan y/n");
                                        }
                                    }

                                    if (kembali == 'y' || kembali == 'Y') {
                                        kembaliMenu = true;
                                    }
                                    break;
                            }
                        } while (kembaliMenu == false);
                    } else {
                        System.out.println("Belum Ada Transaksi");
                    }

                    break;

                case 3:
                    // Bantuan
                    System.out.println("==========================================================");
                    System.out.println("|                     Menu Bantuan                       |");
                    System.out.println("==========================================================");
                    System.out.println("|               Keguanaan Menu di Manager                |");
                    System.out.println("==========================================================");
                    System.out.println("|1. Menu Laporan Keuangan                                |");
                    System.out.println("|   Laporan keuangan untuk mengampilkan laporan keuangan |");
                    System.out.println("|2. Menu riwayat Tranksaksi                              |");
                    System.out.println("|   Digunakan untuk melihat riwayat transaksi selama ini |");
                    System.out.println("|3. Menu Bantuan                                         |");
                    System.out.println("|   Digunakan untuk melihat Bantuan                      |");
                    System.out.println("|4. Menu Ubah Password                                   |");
                    System.out.println("|   Digunakan untuk mengubah username atau password      |");
                    System.out.println("|5. Menu Logout                                          |");
                    System.out.println("|   Menu logout digunakan untuk keluar dari menu manager |");
                    System.out.println("==========================================================");
                    input.nextLine();
                    break;

                case 4:
                    String usrLama, passLama, usrBaru, passBaru, confrmPass;
                    int attemptGanti = 0;
                    System.out.println("\n=========================================");
                    System.out.println("|         Ubah Password Manager         |");
                    System.out.println("=========================================");
                    do {
                        System.out.print("=> Masukan Username Lama : ");
                        usrLama = input.nextLine();
                        System.out.print("=> Masukan Password Lama : ");
                        passLama = input.nextLine();

                        if (!(usrLama.equals(usernamePassword[1][0]) && passLama.equals(usernamePassword[1][1]))) {
                            attemptGanti++;
                            userPwSalah();
                        } else {
                            System.out.println("=========================================");
                            System.out.print("=> Masukan Username Baru    : ");
                            usrBaru = input.nextLine();
                            System.out.print("=> Masukan Password Baru    : ");
                            passBaru = input.nextLine();
                            do {
                                System.out.print("=> Konfirmasi Password Baru : ");
                                confrmPass = input.nextLine();
                                if (!confrmPass.equals(passBaru)) {
                                    System.out.println("=========================================");
                                    System.out.println("|    Konfirmasi Password Tidak Sama     |");
                                    System.out.println("=========================================");
                                }
                            } while (!confrmPass.equals(passBaru));

                            usernamePassword[1][0] = usrBaru;
                            usernamePassword[1][1] = confrmPass;

                            System.out.println("=========================================");
                            System.out.println("| Username dan Password Berhasil Diubah |");
                            System.out.println("|        Silahkan Login Kembali         |");
                            System.out.println("=========================================");
                            return;
                        }
                    } while (attemptGanti < 3);
                    if (attemptGanti == 3) {
                        System.out.println("Anda gagal 3 kali, kembali ke menu");
                        break;
                    }
                    break;
                case 5:
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
                    break;
            }
        }
    }

    static int pilihMenu(int menuMaks, Scanner input) {
        int menu;
        do { // Looping jika input tidak sesuai
            System.out.print("=> Pilih Menu : ");
            if (input.hasNextInt()) {
                menu = input.nextInt();
                if (menu >= 1 && menu <= menuMaks) {
                    break; // Keluar dari perulangan jika masukan sesuai
                } else {
                    System.out.println("Tidak Tersedia. Masukan Angka 1 - " + menuMaks + " Sesuai Menu");
                }
            } else {
                input.next(); // Membersihkan masukan yang tidak valid
                System.out.println("Input Invalid. Harap masukkan angka.");
            }
        } while (true);
        input.nextLine();
        return menu;
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
        System.out.println("=> Kembalian                : " + kembalian);
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
    }

    static void menyimpanTransaksi() {
        totalPendapatan += (int) tagihan;
        totalDonasi += donasi;
    }

    static void cetakTransaksiDanHapusBiodata(long selisihHari) {
        // Menghapus data di Kamar Pasien Jika inap
        if (biodataPasien[kodePasien - 1][8] != null) {
            if (biodataPasien[kodePasien - 1][8] == "VIP") {
                for (int i = 0; i < kamarVIP[0].length; i++) {
                    kamarVIP[(Integer.parseInt(biodataPasien[kodePasien - 1][9]) - 1)][i] = null;
                }
            } else if (biodataPasien[kodePasien - 1][8] == "Reguler") {
                for (int i = 0; i < kamarReguler.length; i++) {
                    kamarReguler[(Integer.parseInt(biodataPasien[kodePasien - 1][9]) - 1)][i] = null;
                }
            } else if (biodataPasien[kodePasien - 1][8] == "Bersama") {
                for (int i = 0; i < kamarBersama[0][0].length; i++) {
                    kamarBersama[(Integer.parseInt(biodataPasien[kodePasien - 1][9]) - 1)][(Integer
                            .parseInt(biodataPasien[kodePasien - 1][10]) - 1)][i] = null;
                }
            }
        }

        for (int i = 0; i <= 6; i++) {
            detailRiwayatTransaksi[riwayat][i] = biodataPasien[kodePasien - 1][i];
        }
        detailRiwayatTransaksi[riwayat][7] = String.valueOf(selisihHari);
        detailRiwayatTransaksi[riwayat][8] = Integer.toString(obat);
        detailRiwayatTransaksi[riwayat][9] = Integer.toString((int) tagihan);
        detailRiwayatTransaksi[riwayat][10] = Integer.toString(bayar);
        detailRiwayatTransaksi[riwayat][11] = Integer.toString(kembalian);
        detailRiwayatTransaksi[riwayat][12] = Integer.toString(donasi);
        detailRiwayatTransaksi[riwayat][13] = Integer.toString(kembalianAkhir);

        // Nota/Bukti Pembayaran
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|        Bukti Pembayaran RS CintaJava        |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| => Nama Pasien     : " + detailRiwayatTransaksi[riwayat][0]);
        System.out.println("| => Alamat Pasien   : " + detailRiwayatTransaksi[riwayat][1]);
        System.out.println("| => Nomer HP Pasien : " + detailRiwayatTransaksi[riwayat][2]);
        System.out.println("| => Penyakit Pasien : " + detailRiwayatTransaksi[riwayat][3]);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        if (!detailRiwayatTransaksi[riwayat][7].equalsIgnoreCase("0")) {
            System.out.println("| => Tagihan Kamar   : " + tagihanKamar);
        }
        System.out.println("| => Harga Jasa      : " + hargaKatPenyakit);
        System.out.println("| => Harga Obat      : " + hargaObat * obat);
        System.out.println("| => Keringanan      : "
                + (((obat * hargaObat) + hargaKatPenyakit) - (((obat * hargaObat) + hargaKatPenyakit) * keringanan)));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| => Total Tagihan   : " + detailRiwayatTransaksi[riwayat][9]);
        System.out.println("| => Total Bayar     : " + detailRiwayatTransaksi[riwayat][10]);
        System.out.println("| => Total Kembalian : " + detailRiwayatTransaksi[riwayat][11]);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| => Total Donasi    : " + detailRiwayatTransaksi[riwayat][12]);
        System.out.println("| => Kembalian Akhir : " + detailRiwayatTransaksi[riwayat][13]);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|        Bukti Pembayaran RS CintaJava        |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

        for (int i = 0; i < biodataPasien[kodePasien - 1].length; i++) {
            biodataPasien[kodePasien - 1][i] = null;
        }

    }

    static void userPwSalah() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("|        Username Atau Passwordmu Salah       |");
        System.out.println("|              Masukan yang Benar             |");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
    }

    static void printBiodataPasien() {
        System.out.println("\n========================================");
        System.out.println("|       Pasien Nomor " + biodataPasien[iGlobal][7]);
        System.out.println("|=> Nama Pasien     : " + biodataPasien[iGlobal][0]);
        System.out.println("|=> Alamat Pasien   : " + biodataPasien[iGlobal][1]);
        System.out.println("|=> Nomer HP        : " + biodataPasien[iGlobal][2]);
        System.out.println("|=> Penyakit Pasien : " + biodataPasien[iGlobal][3]);
        System.out.println("|=> Kode Pasien     : " + biodataPasien[iGlobal][7]);
        System.out.println("========================================");
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

    static void printRiwayatTransaksi() {
        System.out.println("\n=========================================");
        System.out.println("| Transaksi pada Tanggal " + detailRiwayatTransaksi[iGlobal][6]);
        System.out.println("=========================================");
        System.out.println("|=> Nama Pasien : " + detailRiwayatTransaksi[iGlobal][0]);
        System.out.println("|=> Tanggal     : " + detailRiwayatTransaksi[iGlobal][6]);
        System.out.println("|=> Tagihan     : " + detailRiwayatTransaksi[iGlobal][9]);
        System.out.println("|=> Donasi      : " + detailRiwayatTransaksi[iGlobal][12]);
        System.out.println("========================================");
    }

    static void riwayatTransaksiAdmin(int i) {
        System.out.println("\n========================================");
        System.out.println("| Transaksi pada Tanggal ");
        System.out.println("========================================");
        System.out.println("|=> Nama Pasien : " + detailRiwayatTransaksi[i][0]);
        System.out.println("|=> Alamat      : " + detailRiwayatTransaksi[i][1]);
        System.out.println("|=> Nomor HP    : " + detailRiwayatTransaksi[i][2]);
        System.out.println("|=> Penyakit    : " + detailRiwayatTransaksi[i][3]);
        System.out.println("========================================");
        if (!detailRiwayatTransaksi[i][7].equalsIgnoreCase("0")) {
            System.out.println("|=> Lama Inap   : " + detailRiwayatTransaksi[i][7]);
        }

        System.out.println("|=> Obat        : " + detailRiwayatTransaksi[i][8]);
        System.out.println("|=> Tagihan     : " + detailRiwayatTransaksi[i][9]);
        System.out.println("|=> Donasi      : " + detailRiwayatTransaksi[i][12]);
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
