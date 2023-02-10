package com.juaracoding._vmspringbootjpa.utils;

public class ConstantMessage {

    /*
    Memperbolehkan nilai numerik dari 0 hingga 9.
    Memperbolehkan Huruf besar dan huruf kecil dari a sampai z.
    Yang diperbolehkan hanya garis bawah “_”, tanda hubung “-“ dan titik “.”
    Titik tidak diperbolehkan di awal dan akhir local part (sebelum tanda @).
    Titik berurutan tidak diperbolehkan.
    Local part, maksimal 64 karakter.
     */
//    public final static String REGEX_EMAIL_STRICT = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

    /*CONTENT TYPE*/
    public final static String CONTENT_TYPE_CSV = "text/csv";

    /*REGEX*/
    public final static String REGEX_PHONE = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    /*
    * Tidak memperbolehkan tanda | (pipa) dan ' (petik 1)
    */
    public final static String REGEX_EMAIL_STANDARD_RFC5322  = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public final static String REGEX_DATE_YYYYMMDD  = "^([0-9][0-9])?[0-9][0-9]-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$";
    public final static String REGEX_DATE_DDMMYYYY  = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";

    /*Global*/
    public final static String SUCCESS_SAVE = "DATA BERHASIL DIBUAT";
    public final static String ERROR_UPLOAD_CSV = "UPLOAD FILE GAGAL ";
    public final static String ERROR_NOT_CSV_FILE = "FORMAT FILE HARUS CSV ";
    public final static String SUCCESS_SEND_EMAIL = "SILAHKAN CEK EMAIL YANG TELAH ANDA DAFTARKAN";
    public final static String SUCCESS_FIND_BY = "OK";
    public final static String SUCCESS_TRANSFER = "TRANSFER BERHASIL";
    public final static String WARNING_NOT_FOUND = "DATA TIDAK DITEMUKAN";
    public final static String WARNING_DATA_EMPTY = "DATA TIDAK ADA";

    public final static String ERROR_DATA_INVALID = "DATA TIDAK VALID";
    public final static String ERROR_INTERNAL_SERVER = "INTERNAL SERVER ERROR";
    public final static String ERROR_MAIL_FORM_JSON = "Malformed JSON request";
    public final static String ERROR_EMAIL_FORMAT_INVALID = "FORMAT EMAIL TIDAK SESUAI (Nomor/Karakter@Nomor/Karakter)";
    public final static String ERROR_PHONE_NUMBER_FORMAT_INVALID = "FORMAT NOMOR HANDPHONE TIDAK SESUAI (+628XX-)";
    public final static String ERROR_DATE_FORMAT_YYYYMMDD = "FORMAT TANGGAL TIDAK SESUAI (YYYY-mm-dd)";

    public final static String ERROR_UNEXPECTED = "UNEXPECTED ERROR";
    public final static String ERROR_UNPROCCESSABLE = "Validation error. Check 'errors' field for details.";

    public final static String ERROR_NO_CONTENT = "PERMINTAAN TIDAK DAPAT DIPROSES";
    public final static String WELCOME_MESSAGE = "This is Springboot BootCamp BCAF BATCH 1";
    public final static String TAKE_TOUR = "Ready To Start";
    public final static String WARNING_EMAIL_EXIST = "EMAIL SUDAH TERDAFTAR";

    /*Customer*/

    public final static String WARNING_CUST_EMAIL_MANDATORY = "EMAIL WAJIB DIISI";
    public final static String WARNING_CUST_PH_NUMBER_MANDATORY = "NOMOR HANDPHONE WAJIB DIISI";
    public final static String WARNING_CUST_FIRSTNAME_MANDATORY = "NAMA AWAL WAJIB DIISI";
    public final static String WARNING_CUST_ADDRESS_MAX_LENGTH = "MAKSIMAL ALAMAT 255 KARAKTER";
    public final static String WARNING_CUST_MAX_LENGTH_FIRSTNAME = "MAKSIMAL NAMA AWAL 20 KARAKTER";
    public final static String WARNING_CUST_MAX_LENGTH_MIDDLENAME = "MAKSIMAL NAMA TENGAH 20 KARAKTER";
    public final static String WARNING_CUST_MAX_LENGTH_LASTNAME = "MAKSIMAL NAMA AKHIR 20 KARAKTER";
    public final static String WARNING_CUST_BIRTHDATE_MANDATORY = "TANGGAL-BULAN-TAHUN LAHIR WAJIB DIISI";
    public final static String WARNING_CUSTOMER_NOT_FOUND = "CUSTOMER BELUM TERDAFTAR";

    /*Products*/
    public final static String WARNING_PRODUCT_NOT_FOUND = "PRODUK TIDAK DITEMUKAN";
    public final static String WARNING_PROD_NAME_MANDATORY = "NAMA PRODUK WAJIB DIISI";
    public final static String WARNING_PROD_DESC_MANDATORY = "DESKRIPSI PRODUK WAJIB DIISI";
    public final static String WARNING_PROD_PRICE_MANDATORY = "HARGA PRODUK WAJIB DIISI";
    public final static String WARNING_PRODUCT_PRICE_SOP = "HARGA TIDAK BOLEH 1/2 ATAU 3 KALI DARI HARGA SEBELUMNYA";

    /*SupplierRepo*/
    public final static String WARNING_SUPPLIER_NAME_MANDATORY = "NAMA SUPPLIER WAJIB DIISI";
    public final static String WARNING_SUPPLIER_DESC_MANDATORY = "DESKRIPSI SUPPLIER WAJIB DIISI";
    public final static String WARNING_SUPPLIER_EMAIL_MANDATORY = "EMAIL SUPPLIER WAJIB DIISI";

    /*ProductCategory*/
    public final static String WARNING_PROD_CATEG_NAME_MANDATORY = "NAMA PRODUK KATEGORI WAJIB DIISI";
    public final static String WARNING_PROD_CATEG_DESC_MANDATORY = "DESKRIPSI PRODUK KATEGORI WAJIB DIISI";


    /*Customers*/
    public final static String WARNING_EMPL_EMAIL_MANDATORY = "EMAIL WAJIB DIISI";
    public final static String WARNING_EMPL_PH_NUMBER_MANDATORY = "NOMOR HANDPHONE WAJIB DIISI";
    public final static String WARNING_EMPL_FIRSTNAME_MANDATORY = "NAMA AWAL WAJIB DIISI";
    public final static String WARNING_EMPL_ADDRESS_MAX_LENGTH = "MAKSIMAL ALAMAT 255 KARAKTER";

    public final static String WARNING_EMPL_MAX_LENGTH_FIRSTNAME = "MAKSIMAL NAMA AWAL 50 KARAKTER";
    public final static String WARNING_EMPL_MAX_LENGTH_MIDDLENAME = "MAKSIMAL NAMA TENGAH 50 KARAKTER";
    public final static String WARNING_EMPL_MAX_LENGTH_LASTNAME = "MAKSIMAL NAMA AKHIR 50 KARAKTER";
    public final static String WARNING_EMPL_MAX_LENGTH_EMAIL = "MAKSIMAL EMAIL 50 KARAKTER";
    public final static String WARNING_EMPL_MAX_LENGTH_PHONE = "MAKSIMAL NOMOR HANDPHONE 50 KARAKTER";
    public final static String WARNING_EMPL_BIRTHDATE_MANDATORY = "TANGGAL-BULAN-TAHUN LAHIR WAJIB DIISI";
    public final static String WARNING_EMPL_NOT_FOUND = "KARYAWAN BELUM TERDAFTAR";


    /*ProductCategory*/
    public final static String WARNING_DIVISION_NAME_MANDATORY = "NAMA DIVISI WAJIB DIISI";
    public final static String WARNING_DIVISION_NAME_LENGTH = "NAMA DIVISI MAKSIMAL 50 KARAKTER";
    public final static String WARNING_DIVISION_DESC_MANDATORY = "DESKRIPSI DIVISI WAJIB DIISI";

    /*Transaction*/
    public final static String ERROR_NOT_ENOUGH_AMMOUNT = "MAAF SALDO ANDA TIDAK CUKUP";
    public final static String ERROR_ACCOUNT_SOURCE_NOT_AVAILABLE = "WALLET TIDAK TERDAFTAR";
    public final static String ERROR_ACCOUNT_DESTINATION_NOT_AVAILABLE = "WALLET TUJUAN TIDAK TERDAFTAR";
    public final static String WARNING_ACCOUNT_SOURCE_MANDATORY = "AKUN WALLET  ASAL TIDAK BOLEH KOSONG";
    public final static String WARNING_ACCOUNT_DESTINATION_MANDATORY = "AKUN WALLET TUJUAN TIDAKBOLEH KOSONG";
    public final static String WARNING_AMMOUNTZ_MANDATORY = "MASUKKAN JUMLAH NOMINAL YANG INGIN DITRANSFER";

}
