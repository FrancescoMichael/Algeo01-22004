# Algeo-01-22004

> Tubes 1 Algeo kelompok Tubes siji algeo

dalam rangka pemenuhan tugas 1 algeo

## Susunan Program

Folder test berisikan folder input dan output. Ketika hendak menggunakan file, sertakan .txt dan langsung namanya saja (untuk read dan write)

## Cara Menjalankan

jalankan perintah ini di folder /bin

```shell
java Main
```

ketike anda menjalankannya anda akan memasuki menu utama dan ketik 0 untuk menghentikan program dan anda bisa memilih menu yang tersedia. jika terjadi kesalahan input seperti menginput angka diluar dari opsi maka program tidak akan melakukan apa apa dan anda akan kembali ke menu utama. pastikan input yang anda beri adalah berupa angka bukan character lain pada bagian pemilihan menu (PASTIKAN INPUT ANDA VALID).

# Cara pengunaan

## cara input data dalam program

> 1. (CLI)input dengan memasukkan jumlah baris dan kolom, pastikan memasukkan dalam bentuk angka bukan character lain.... kemudian masukkan matriks anda sesuai dengan ukuran yang anda pilih dalam angka float maupun integer bukan charachter lain.
> 2. (FILE TXT) input dengan memanggil nama file, dan pastikan isi file hanya berisikan matriks dengan setiap elemen berupa angka bukan huruf maupun character lainnya.

pada program ini terdapat 6 menu

## 1. Sistem Persamaaan Linier

pada bagian ini terdapat 4 submenu

> 1.  metod eliminasi Gauss
>     > akan menghasilkan solusi dari spl baik dalam parametrik, tidak ditemukan, maupun dalam nilai eksak
> 2.  metode eliminasi Gauss Jordan
>     > akan menghasilkan solusi dari spl baik dalam parametrik, tidak ditemukan, maupun dalam nilai eksak
> 3.  Metode matriks balikan
>     > akan menghasilkan solusi dari spl jika nilai ada dan eksak(tidak dalam bentuk parametrik)
> 4.  Kaidah Crammer
>     > akan menghasilkan solusi dari spl jika nilai ada dan eksak(tidak dalam bentuk parametrik)

## 2. Determinan

> 1.  metode ekspansi kofaktor
>     > aakan menghasilkan nilai dari determinan tersebut pastikan input dalam bentuk matriks persegi (dan berisikan angka integer maupun float bukan char lainnya)
> 2.  metode OBE
>     > aakan menghasilkan nilai dari determinan tersebut pastikan input dalam bentuk matriks persegi (dan berisikan angka integer maupun float bukan char lainnya)

## 3. Matriks Balikan

> 1.  metod eliminasi OBE
>     > menghasilkan invers matriks, pastikan input berupa matriks persegi nXn dengan n > 0 dan jangan lupa untuk setiap input pastikan berupa float atau integer bukan character lainnya
> 2.  metode matriks adjoint
>     > menghasilkan invers matriks, pastikan input berupa matriks persegi nXn dengan n > 0 dan jangan lupa untuk setiap input pastikan berupa float atau integer bukan character lainnya

## 4. Interpolasi Polinom

> menghitung interpolasi pastikan input berbentuk matriks dan kemudian masukkan titik yang yang di taksir, jika input dalam file atasnya bentuk matriks kemudian dibawahnya ada 1 angka yang berisikan titik yang mau di taksir (sesuai spek) dan pastikan semua input dalam file ataupun cli berupa angka yang valid entak float maupun integer dan bukan merupakan charackter lainnya seperti huruf dll

## 5. Interpolasi Bicubic Spline
> Interpolasi bicubic spline adalah teknik interpolasi yang digunakan untuk mengaproksiasi fungsi di antara titik-titik data yang diketahui. Dalam pemrosesannya, digunakan 16 buah titik, yang terdiri dari 4 titik referensi utama di bagian pusat dan 12 titik di sekitarnya. Hal ini bertujuan sebagai aproksimasi turunan dari keempat titik referensi untuk membagun permukaan bikubik.


## 6. Regresi linier Berganda
> Menentukan hubungan antar beberapa variabel independen dengan suatu variabel dependen. Hubungan ini biasa dinyatakan dalam fungsi regresi. Fungsi ini mengambil data dari sampel yang ada untuk meningkatkan akurasi fungsi tersebut. Tujuan dari regresi linier berganda adalah memprediksi nilai variabel dependen berdasarkan nilai variabel independen yang diberikan.