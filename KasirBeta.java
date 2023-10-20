import java.util.Scanner;

public class KasirBeta {
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
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            int loginSbg;

            do {
                System.out.print("=> Login Sebagai : ");
                loginSbg = input.nextInt();
                if (!((loginSbg == 1) || (loginSbg == 2))) {
                    System.out.println("Input Invalid, Masukan Lagi");
                }
            } while (!((loginSbg == 1) || (loginSbg == 2)));

            switch (loginSbg) {
                case 1:
                    // Login sebagai Admin
                    loginAdmin(input);
                    break;

                case 2:
                    // Login sebagai Manager
                    loginManager(input);
                    break;

                default:
                    System.out.println("Masukan Angka 1 atau 2");
            }
        }
    }

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
            System.out.println("|          3. Cek Ketersediaan Kamar          |");
            System.out.println("|          4. Logout                          |");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            int menuAdmin;

            do {
                System.out.print("=> Pilih Menu : ");
                menuAdmin = input.nextInt();
                if (!((menuAdmin == 1) || (menuAdmin == 2) || (menuAdmin == 3) || (menuAdmin == 4))) {
                    System.out.println("Pilihan Tidak Tersedia, Masukan Angka 1, 2, 3, atau 4 Sesuai Menu");
                }
            } while (!((menuAdmin == 1) || (menuAdmin == 2) || (menuAdmin == 3) || (menuAdmin == 4)));

            switch (menuAdmin) {
                case 1:
                    // Menu Daftarkan Pasien
                    // Implementasi logika menu ini di sini
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
                    String perluInap;
                    do {
                        System.out.print("=> Perlu Rawat Inap y/n : ");
                        perluInap = inputAdm.nextLine();

                        if (!((perluInap.equalsIgnoreCase("y") || (perluInap.equalsIgnoreCase("n"))))) {
                            System.out.printf("Input %s Invalid masukan Y/N!\n", perluInap);
                        }
                    } while (!((perluInap.equalsIgnoreCase("y") || (perluInap.equalsIgnoreCase("n")))));

                    // Jika Perlu Rawat Inap
                    if (perluInap.equalsIgnoreCase("y")) {

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
                            if (!((kamar >= 1) && (kamar <= 4))) {
                                System.out.printf("Pilihan %d Tidak tersedia Masukan Lagi!\n", kamar);
                            }
                        } while (!((kamar >= 1) && (kamar <= 4)));

                        int lamaInapVIP, lamaInapReg, lamaInapBersama, obat, konsumsi, total = 0, bayar = 0,
                                kembalian = 0;
                        int donasi = 0, kembalianAkir = 0;
                        String apaDonasi, apaDonasiSemua;

                        if (kamar == 1) {
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

                            total = (1000000 * lamaInapVIP) + (obat * 100000 * lamaInapVIP)
                                    + (konsumsi * 50000 * lamaInapVIP);

                            System.out.println("== Total Tagihan            : " + total);
                            System.out.print("=> Bayar Sekarang           : ");
                            bayar = inputAdm.nextInt();
                            kembalian = bayar - total;
                            System.out.println("== Kembalian                : " + kembalian);
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
                            inputAdm.nextLine(); // TAMBAL BUGGGGGG

                            // Donasi Kemanusiaan
                            do {
                                System.out.print("=> Donasikan Kembalian  y/n : ");
                                apaDonasi = inputAdm.nextLine();
                                if (!((apaDonasi.equalsIgnoreCase("y") || (apaDonasi.equalsIgnoreCase("n"))))) {
                                    System.out.printf("Input %s Invalid masukan Y/N!\n", perluInap);
                                }

                            } while (!((apaDonasi.equalsIgnoreCase("y")) || (apaDonasi.equalsIgnoreCase("n"))));

                            if (apaDonasi.equalsIgnoreCase("y")) {
                                System.out.print("=> Donasikan Semuanya? y/n  : ");
                                apaDonasiSemua = inputAdm.nextLine();

                                if (apaDonasiSemua.equalsIgnoreCase("y")) {
                                    donasi = kembalian;
                                } else if (apaDonasiSemua.equalsIgnoreCase("n")) {
                                    System.out.print("=> Masukan Besar Donasi     : ");
                                    donasi = inputAdm.nextInt();
                                }
                                kembalianAkir = kembalian - donasi;
                            }

                            else if (apaDonasi.equalsIgnoreCase("n")) {
                                donasi = 0;
                            }

                        } else if (kamar == 2) {
                            System.out.print("=> Lama Menginap (Hari)     : ");
                            lamaInapReg = inputAdm.nextInt();
                            System.out.print("=> Layanan Obat per Perhari : ");
                            obat = inputAdm.nextInt();
                            System.out.print("=> Layanan Konsumsi Perhari : ");
                            konsumsi = inputAdm.nextInt();

                            total = (700000 * lamaInapReg) + (obat * 100000 * lamaInapReg)
                                    + (konsumsi * 50000 * lamaInapReg);

                            System.out.println("== Total Tagihan            : " + total);
                            System.out.print("=> Bayar Sekarang           : ");
                            bayar = inputAdm.nextInt();
                            kembalian = bayar - total;
                            System.out.println("== Kembalian                : " + kembalian);
                            inputAdm.nextLine(); // TAMBAL BUGGGGGG

                            // Donasi Kemanusiaan
                            System.out.print("=> Donasikan Kembalian  y/n : ");
                            apaDonasi = inputAdm.nextLine();

                            if (apaDonasi.equalsIgnoreCase("y")) {
                                System.out.print("=> Donasikan Semuanya? y/n  : ");
                                apaDonasiSemua = inputAdm.nextLine();

                                if (apaDonasiSemua.equalsIgnoreCase("y")) {
                                    donasi = kembalian;
                                } else if (apaDonasiSemua.equalsIgnoreCase("n")) {
                                    System.out.print("=> Masukan Besar Donasi     : ");
                                    donasi = inputAdm.nextInt();
                                }
                                kembalianAkir = kembalian - donasi;
                            }

                            else if (apaDonasi.equalsIgnoreCase("n")) {
                                donasi = 0;
                            }

                        } else if (kamar == 3) {
                            System.out.print("Lama Menginap (Hari)     : ");
                            lamaInapBersama = inputAdm.nextInt();
                            System.out.print("Layanan Obat per Perhari : ");
                            obat = inputAdm.nextInt();
                            System.out.print("Layanan Konsumsi Perhari : ");
                            konsumsi = inputAdm.nextInt();

                            total = (500000 * lamaInapBersama) + (obat * 100000 * lamaInapBersama)
                                    + (konsumsi * 50000 * lamaInapBersama);

                            System.out.println("Total Tagihan            : " + total);
                            System.out.print("Bayar Sekarang           : ");
                            bayar = inputAdm.nextInt();
                            kembalian = bayar - total;
                            System.out.println("Kembalian                : " + kembalian);
                            inputAdm.nextLine(); // TAMBAL BUGGGGGG

                            // Donasi Kemanusiaan
                            System.out.print("Donasikan Kembalian  y/n : ");
                            apaDonasi = inputAdm.nextLine();

                            if (apaDonasi.equalsIgnoreCase("y")) {
                                System.out.print("Donasikan Semuanya? y/n  : ");
                                apaDonasiSemua = inputAdm.nextLine();

                                if (apaDonasiSemua.equalsIgnoreCase("y")) {
                                    donasi = kembalian;
                                } else if (apaDonasiSemua.equalsIgnoreCase("n")) {
                                    System.out.print("Masukan Besar Donasi     : ");
                                    donasi = inputAdm.nextInt();
                                }
                                kembalianAkir = kembalian - donasi;
                            }

                            else if (apaDonasi.equalsIgnoreCase("n")) {
                                donasi = 0;
                            }
                        }
                        // Nota/Bukti Pembayaran
                        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++");
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

                        // Tidak Rawat Inap
                    } else if (perluInap.equalsIgnoreCase("n")) {
                        System.out.println("Tidak Rawat Inap");

                    } else { // Jika Input "Apakah Rawat Inap" Salah
                        System.out.println("Input salah");
                    }
                    break;

                case 2:
                    // Menu Bayar Tagihan Pasien
                    System.out.println("Menu 2 Bayar tagihan");
                    break;
                case 3:
                    // Menu Cek Ketersediaan Kamar
                    System.out.println("Menu 3");
                    break;
                case 4:
                    // Logout dan kembali ke menu login
                    System.out.print("Konfirmasi Logout y/n : ");
                    char logout = inputAdm.nextLine().charAt(0);

                    if ((logout == 'n')||(logout == 'N')) {
                        break;
                    } else if ((logout == 'y')||(logout == 'Y')) {
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

            do {
                System.out.print("=> Pilih Menu : ");
                menuManager = input.nextInt();
                if (!((menuManager == 1) || (menuManager == 2) || (menuManager == 3))) {
                    System.out.println("Pilihan Tidak Tersedia, Masukan Angka 1, 2, atau 3 Sesuai Menu");
                }
            } while (!((menuManager == 1) || (menuManager == 2) || (menuManager == 3)));

            switch (menuManager) {
                case 1:
                    // Menu Laporan Keuangan
                    System.out.println("Menu Laporan Keuangan");
                    break;
                case 2:
                    // Menu Riwayat Transaksi
                    System.out.println("Menu Riwayat Transaksi");
                    break;
                case 3:
                    System.out.print("Konfirmasi Logout y/n : ");
                    char logout = inputMnjr.nextLine().charAt(0);

                    if ((logout == 'n')||(logout == 'N')) {
                        break;
                    } else if ((logout == 'y')||(logout == 'Y')) {
                        return;
                    }
                default:
                    System.out.println("Pilihan Tidak Tersedia");
            }
        }
    }
}
